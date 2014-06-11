package com.airhacks.workshops.business.registrations.boundary;

import com.airhacks.workshops.business.registrations.control.VatCalculator;
import com.airhacks.workshops.business.registrations.entity.Registration;
import com.airhacks.workshops.business.tracing.boundary.Tracer;
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
    Tracer tracer;

    public Registration register(Registration request) {
        tracer.trace("registration arrived: " + request);
        Registration registration = em.merge(request);
        tracer.trace("registration stored: " + request);
        registration.setCalculator(priceCalculator::calculateTotal);
        tracer.trace("price computed: " + registration.getTotalPrice());
        return registration;
    }

    public Registration find(int registrationId) {
        return this.em.find(Registration.class, registrationId);
    }

}
