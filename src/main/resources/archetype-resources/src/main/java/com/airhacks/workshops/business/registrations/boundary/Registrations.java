package com.airhacks.workshops.business.registrations.boundary;

import com.airhacks.workshops.business.registrations.control.VatCalculator;
import com.airhacks.workshops.business.registrations.entity.Registration;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    public Registration register(Registration request) {
        tracer.info("registration arrived: " + request);
        Registration registration = em.merge(request);
        tracer.info("registration stored: " + request);
        registration.setCalculator(priceCalculator::calculateTotal);
        tracer.info("price computed: " + registration.getTotalPrice());
        return registration;
    }

    public Registration find(int registrationId) {
        return this.em.find(Registration.class, registrationId);
    }

}
