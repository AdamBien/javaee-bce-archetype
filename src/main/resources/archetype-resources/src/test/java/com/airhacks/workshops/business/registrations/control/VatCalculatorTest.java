package com.airhacks.workshops.business.registrations.control;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class VatCalculatorTest {

    VatCalculator cut;

    @Before
    public void init() {
        this.cut = new VatCalculator();
    }

    @Test
    public void attendeeEU() {
        final boolean vatIDAvailable = true;
        int price = this.cut.calculateTotal(vatIDAvailable, 300);
        assertThat(price, is(300));

        price = this.cut.calculateTotal(vatIDAvailable, 600);
        assertThat(price, is(600));
    }

    @Test
    public void attendeeNonEU() {
        final boolean vatIDAvailable = false;
        int price = this.cut.calculateTotal(vatIDAvailable, 300);
        assertThat(price, is(357));

        price = this.cut.calculateTotal(vatIDAvailable, 600);
        assertThat(price, is(714));
    }

}
