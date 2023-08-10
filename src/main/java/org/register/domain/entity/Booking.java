package org.register.domain.entity;

import com.github.javafaker.DateAndTime;

public class Booking {

    private Long id;
    private Guest guest;
    private Room room;
    private DateAndTime checkedIn;
    private DateAndTime checkedOut;
}
