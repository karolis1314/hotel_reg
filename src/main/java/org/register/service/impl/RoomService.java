package org.register.service.impl;

import org.register.Constants;
import org.register.domain.dto.RoomDto;
import org.register.mapper.RoomMapper;
import org.register.service.database.JdbcPsqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.register.utils.ServiceUtils.getResultsFromQuery;
import static org.register.utils.ServiceUtils.handleError;

public class RoomService {

    public RoomService() {}


    public List<RoomDto> getAllRooms() {
        List<RoomDto> rooms = new ArrayList<>();
        try (Connection connection = JdbcPsqlConnection.connect();
             ResultSet resultSet = getResultsFromQuery("SELECT * FROM Room", connection)) {

            while (resultSet.next()) {
                rooms.add(RoomMapper.fromResultSetToDto(
                        resultSet.getLong("id"),
                        resultSet.getBoolean("available")));
            }
        } catch (SQLException e) {
            handleError(String.format(Locale.getDefault(), Constants.ERROR_SQL_ROOMS, "retrieving"), e);
        }

        return rooms;
    }

    public List<RoomDto> getAllAvailableRooms() {
        List<RoomDto> allRooms = getAllRooms();

        return allRooms.stream().filter(RoomDto::isAvailable).collect(Collectors.toList());
    }

    public List<RoomDto> getAllBookedRooms() {
        List<RoomDto> allRooms = getAllRooms();

        return allRooms.stream().filter(Predicate.not(RoomDto::isAvailable)).collect(Collectors.toList());
    }
}
