package org.register.mapper;

import org.register.domain.dto.RoomDto;
import org.register.domain.entity.Guest;
import org.register.domain.entity.Room;

public class RoomMapper {

    public static Room toRoom(RoomDto roomDto, Guest guest) {
        return new Room(roomDto.getRoomNumber(), roomDto.isAvalible(), guest);
    }

    public static RoomDto toRoomDto(Room room) {
        return new RoomDto(room.getId(), room.getAvailable());
    }
}
