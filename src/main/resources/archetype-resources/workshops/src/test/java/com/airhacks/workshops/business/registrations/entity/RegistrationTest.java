package com.airhacks.workshops.business.registrations.entity;

import java.util.function.BiFunction;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author airhacks.com
 */
public class RegistrationTest {

    Registration cut;

    @Test
    public void getNetPrice() {
        int expected = 600;
        this.cut = new Registration(false, 2, 1);
        int actualNetPrice = this.cut.getNetPrice();
        assertThat(expected, is(actualNetPrice));
    }

    @Test
    public void getTotalPrice() {
        boolean vatAvailable = true;
        this.cut = new Registration(vatAvailable, 2, 1);

        BiFunction<Boolean, Integer, Integer> taxCalculator = mock(BiFunction.class);
        this.cut.setCalculator(taxCalculator);

        int netPrice = this.cut.getNetPrice();
        final int expectedPrice = 42;
        when(taxCalculator.apply(vatAvailable, netPrice)).thenReturn(expectedPrice);

        int totalPrice = this.cut.getTotalPrice();
        assertThat(totalPrice, is(expectedPrice));
        verify(taxCalculator).apply(vatAvailable, netPrice);

    }

}
