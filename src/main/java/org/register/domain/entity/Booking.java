package org.register.domain.entity;

import com.github.javafaker.DateAndTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
public class Booking {

    private Long id;
    private Guest guest;
    private Room room;
    private DateAndTime checkedIn;
    private DateAndTime checkedOut;


    public Booking(Long id, Guest guest, Room room, DateAndTime checkedIn, DateAndTime checkedOut) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.checkedIn = checkedIn;
        this.checkedOut = checkedOut;
    }

    public Booking() {}

    public Long getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public DateAndTime getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(DateAndTime checkedIn) {
        this.checkedIn = checkedIn;
    }

    public DateAndTime getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(DateAndTime checkedOut) {
        this.checkedOut = checkedOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        return new EqualsBuilder().append(id, booking.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
