package com.airhacks.workshops.business.registrations.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class RegistrationIT {

    EntityManager em;
    EntityTransaction tx;

    @Before
    public void initEM() {
        this.em = Persistence.createEntityManagerFactory("integration-test").createEntityManager();
        this.tx = this.em.getTransaction();
    }

    @Test
    public void validateORMapping() {
        this.tx.begin();
        this.em.merge(new Registration(true, 2, 42));
        this.tx.commit();
    }

}
