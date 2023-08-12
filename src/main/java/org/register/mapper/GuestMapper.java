package org.register.mapper;

import org.register.domain.dto.GuestDto;
import org.register.domain.entity.Guest;

public class GuestMapper {

    public static Guest toGuest(GuestDto dto) {
        return new Guest(dto.getId(), dto.getFirstName(), dto.getLastName());
    }

    public static GuestDto toGuestDto(Guest guest) {
        return new GuestDto(guest.getId(), guest.getFirstName(), guest.getLastName());
    }

    public static GuestDto fromResultSetToDto(Long id, String firstName, String lastName) {
        return new GuestDto(id, firstName, lastName);
    }
}
