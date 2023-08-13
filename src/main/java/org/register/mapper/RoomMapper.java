package org.register.mapper;

import org.register.domain.dto.RoomDto;

public class RoomMapper {

    public static RoomDto fromResultSetToDto(Long roomNumber, boolean available) {
        return new RoomDto(roomNumber, available);
    }
}
