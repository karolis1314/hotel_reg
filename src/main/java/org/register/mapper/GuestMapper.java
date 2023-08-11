package org.register.mapper;

import org.register.domain.dto.GuestDto;
import org.register.domain.entity.Booking;
import org.register.domain.entity.Guest;

public class GuestMapper {

    public static Guest toGuest(GuestDto dto, Booking booking) {
        return new Guest(dto.getId(), dto.getFirstName(), dto.getLastName(), booking);
    }

    public static GuestDto toGuestDto(Guest guest) {
        return new GuestDto(guest.getId(), guest.getFirstName(), guest.getLastName(), guest.getBooking().getRoom().getId());
    }

    public static GuestDto fromResultSetToDto(Long id, String firstName, String lastName) {
        return new GuestDto(id, firstName, lastName);
    }
}
