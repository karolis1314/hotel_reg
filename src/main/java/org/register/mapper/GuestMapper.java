package org.register.mapper;

import org.register.domain.dto.GuestDto;

public class GuestMapper {

    public static GuestDto fromResultSetToDto(Long id, String firstName, String lastName) {
        return new GuestDto(id, firstName, lastName);
    }
}
