package com.airhacks.workshops.business.registrations.boundary;

import java.util.function.BiFunction;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Copied on purpose from workshops for true decoupling.
 *
 * @author airhacks.com
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Registration {

    private long id;

    @XmlTransient
    private BiFunction<Boolean, Integer, Integer> taxCalculator;

    private int numberOfDays;
    private int numberOfAttendees;
    private boolean vatIdAvailable;

    private final static int DAILY_PRICE = 300;

    public Registration(boolean vatIdAvailable, int numberOfDays, int numberOfAttendees) {
        this.numberOfDays = numberOfDays;
        this.numberOfAttendees = numberOfAttendees;
        this.vatIdAvailable = vatIdAvailable;
    }

    public Registration() {
    }

    public int getNetPrice() {
        return numberOfAttendees * numberOfDays * DAILY_PRICE;
    }

    public void setCalculator(BiFunction<Boolean, Integer, Integer> taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public int getTotalPrice() {
        return taxCalculator.apply(this.vatIdAvailable, getNetPrice());
    }

    public boolean isVatIdAvailable() {
        return vatIdAvailable;
    }

    public long getId() {
        return id;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

}
