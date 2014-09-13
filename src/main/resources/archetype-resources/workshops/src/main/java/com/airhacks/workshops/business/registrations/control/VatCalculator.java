package com.airhacks.workshops.business.registrations.control;

import java.math.BigDecimal;
import static java.math.BigDecimal.valueOf;

/**
 *
 * @author airhacks.com
 */
public class VatCalculator {

    public int calculateTotal(boolean vatIdAvailable, int price) {
        BigDecimal net = valueOf(price);
        if (vatIdAvailable) {
            return net.intValue();
        } else {
            return net.add(net.multiply(valueOf(0.19))).intValue();
        }
    }
}
