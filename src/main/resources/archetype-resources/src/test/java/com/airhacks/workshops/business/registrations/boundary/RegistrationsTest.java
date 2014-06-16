package com.airhacks.workshops.business.registrations.boundary;

import com.airhacks.workshops.business.registrations.control.VatCalculator;
import com.airhacks.workshops.business.registrations.entity.Registration;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author airhacks.com
 */
public class RegistrationsTest {

    Registrations cut;

    @Before
    public void init() {
        this.cut = new Registrations();
        this.cut.priceCalculator = mock(VatCalculator.class);
        this.cut.em = mock(EntityManager.class);
        this.cut.tracer = mock(Logger.class);
    }

    @Test
    public void successfulRegistration() {
        when(this.cut.priceCalculator.calculateTotal(Matchers.anyBoolean(), Matchers.anyInt())).thenReturn(1);
        Registration registration = new Registration(true, 1, 1);
        merge(registration);
        this.cut.register(registration);
    }

    void merge(Registration registration) {
        when(this.cut.em.merge(registration)).thenReturn(registration);
    }

}
