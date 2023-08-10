package org.register.domain.dto;

public class BookingDto {

    private String guestsFirstName;
    private String guestsLastName;
    private boolean avalible;

    public BookingDto(String guestsFirstName, String guestsLastName, boolean avalible) {
        this.guestsFirstName = guestsFirstName;
        this.guestsLastName = guestsLastName;
        this.avalible = avalible;
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

    public boolean isAvalible() {
        return avalible;
    }

    public void setAvalible(boolean avalible) {
        this.avalible = avalible;
    }
}
