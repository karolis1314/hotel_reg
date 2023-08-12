package org.register.domain.dto;

public class RoomDto {
    private Long roomNumber;
    private boolean available;

    public RoomDto(Long roomNumber, boolean available) {
        this.roomNumber = roomNumber;
        this.available = available;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
