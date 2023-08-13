package org.register.domain.dto;

public class BookingDto {

    private String guestsFirstName;
    private String guestsLastName;
    private Long roomNumber;
    private boolean available;

    public BookingDto(String guestsFirstName, String guestsLastName, Long roomNumber, boolean avalible) {
        this.guestsFirstName = guestsFirstName;
        this.guestsLastName = guestsLastName;
        this.roomNumber = roomNumber;
        this.available = avalible;
    }

    public String getGuestsFirstName() {
        return guestsFirstName;
    }

    public void setGuestsFirstName(String guestsFirstName) {
        this.guestsFirstName = guestsFirstName;
    }

    public String getGuestsLastName() {
        return guestsLastName;
    }

    public void setGuestsLastName(String guestsLastName) {
        this.guestsLastName = guestsLastName;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
