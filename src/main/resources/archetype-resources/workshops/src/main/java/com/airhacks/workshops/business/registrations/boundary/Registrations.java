package com.airhacks.workshops.business.registrations.boundary;

import com.airhacks.workshops.business.registrations.control.VatCalculator;
import com.airhacks.workshops.business.registrations.entity.Registration;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collector;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class Registrations {

    @PersistenceContext
    EntityManager em;

    @Inject
    VatCalculator priceCalculator;

    @Inject
    Logger tracer;

    public static final String CONFIRMATION_ID = "confirmation-id";

    public JsonObject register(Registration request) {
        tracer.info("registration arrived: " + request);
        Registration registration = em.merge(request);
        tracer.info("registration stored: " + request);
        registration.setCalculator(priceCalculator::calculateTotal);
        tracer.info("price computed: " + registration.getTotalPrice());
        return convert(registration);
    }

    public Registration find(long registrationId) {
        return this.em.find(Registration.class, registrationId);
    }

    public JsonObject findAsJson(int registrationId) {
        return convert(find(registrationId));
    }

    public List<Registration> all() {
        return this.em.createNamedQuery(Registration.findAll).
                getResultList();
    }

    public JsonArray allAsJson() {
        Collector<JsonObject, ?, JsonArrayBuilder> jsonCollector
                = Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add,
                        (left, right) -> {
                            left.add(right);
                            return left;
                        });
        return all().stream().map(this::convert).
                collect(jsonCollector).build();

    }

    /**
     * Because the application only offers JAX-RS endpoint and may offer
     * WebSockets in the future, the conversion from domain objects (entities)
     * to JsonObjects happens in the protocol-neutral boundary.
     *
     * You could also convert entities into JsonObject in the JAX-RS / WebSocket
     * endpoints in case you need the domain objects for a serverside Java web
     * framework.
     */
    JsonObject convert(Registration registration) {
        registration.setCalculator(this.priceCalculator::calculateTotal);
        return Json.createObjectBuilder().
                add("price", registration.getTotalPrice()).
                add(CONFIRMATION_ID, registration.getId()).build();
    }

}
