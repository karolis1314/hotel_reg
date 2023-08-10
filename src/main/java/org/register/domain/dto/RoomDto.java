package org.register.domain.dto;

public class RoomDto {
    private Long roomNumber;
    private boolean avalible;

    public RoomDto(Long roomNumber, boolean avalible) {
        this.roomNumber = roomNumber;
        this.avalible = avalible;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvalible() {
        return avalible;
    }

    public void setAvalible(boolean avalible) {
        this.avalible = avalible;
    }
}
