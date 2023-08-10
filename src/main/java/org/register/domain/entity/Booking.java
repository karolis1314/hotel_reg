package org.register.domain.entity;

import com.github.javafaker.DateAndTime;

public class Booking {

    private Long id;
    private String guestFullName;
    private Long guestRoomNumber;
    private DateAndTime checkedIn;
    private DateAndTime checkedOut;
}
