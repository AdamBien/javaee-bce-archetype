package com.airhacks.workshops.business.registrations.boundary;

import com.airhacks.workshops.business.registrations.control.VatCalculator;
import com.airhacks.workshops.business.registrations.entity.Registration;
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

    public Registration register(Registration request) {
        Registration registration = em.merge(request);
        registration.setCalculator(priceCalculator::calculateTotal);
        return registration;
    }

    public Registration find(int registrationId) {
        return this.em.find(Registration.class, registrationId);
    }

}
