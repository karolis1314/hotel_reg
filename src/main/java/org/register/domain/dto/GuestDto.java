package org.register.domain.dto;

import org.register.domain.entity.Booking;

public class GuestDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Long roomNumber;

    public GuestDto(Long id, String firstName, String lastName, Long roomNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomNumber = roomNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }
}
