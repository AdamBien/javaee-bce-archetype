package com.airhacks.workshops.business.registrations.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author airhacks.com
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Registration {

    @Id
    @GeneratedValue
    private long id;

    private int numberOfDays;
    private int numberOfAttendees;
    private boolean vatIdAvailable;

    private final static int DAILY_PRICE = 300;

    private int totalPrice;

    public Registration(boolean vatIdAvailable, int numberOfDays, int numberOfAttendees) {
        this.numberOfDays = numberOfDays;
        this.numberOfAttendees = numberOfAttendees;
        this.vatIdAvailable = vatIdAvailable;
    }

    public Registration(long id, int numberOfDays, int numberOfAttendees, boolean vatIdAvailable, int totalPrice) {
        this.id = id;
        this.numberOfDays = numberOfDays;
        this.numberOfAttendees = numberOfAttendees;
        this.vatIdAvailable = vatIdAvailable;
        this.totalPrice = totalPrice;
    }

    public Registration() {
    }

    public int getNetPrice() {
        return numberOfAttendees * numberOfAttendees * DAILY_PRICE;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        if (totalPrice < 0) {
            throw new IllegalArgumentException("Price cannot be less than zero");
        }
        this.totalPrice = totalPrice;
    }

    public boolean isVatIdAvailable() {
        return vatIdAvailable;
    }

    public long getId() {
        return id;
    }

}
