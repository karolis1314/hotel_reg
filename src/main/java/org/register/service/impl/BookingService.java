package org.register.service.impl;

import jakarta.ws.rs.core.Response;
import org.register.Constants;
import org.register.domain.dto.BookingDto;
import org.register.domain.dto.GuestDto;
import org.register.exception.ApplicationException;
import org.register.mapper.BookingMapper;
import org.register.service.database.JdbcPsqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.register.utils.ServiceUtils.getResultsFromQuery;
import static org.register.utils.ServiceUtils.handleError;

public class BookingService {

    private final GuestService guestService;
    private final RoomService roomService;

    public BookingService() {
        this.guestService = new GuestService();
        this.roomService = new RoomService();
    }


    public List<BookingDto> getAllBookingsSortedBySurname() {
        List<BookingDto> bookings = new ArrayList<>();
        try (Connection connection = JdbcPsqlConnection.connect();
             ResultSet resultSet = getResultsFromQuery(
                     "SELECT b.*, g.firstName, g.lastName, r.room_id, r.available " +
                             "FROM Booking b " +
                             "JOIN Guest g ON b.guest_id = g.id " +
                             "JOIN Room r ON b.room_id = r.id",
                     connection)) {

            while (resultSet.next()) {
                bookings.add(BookingMapper.toBookingDto(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getBoolean("available"),
                        resultSet.getLong("room_id")
                ));
            }
        } catch (SQLException e) {
            handleError(String.format(Locale.getDefault(), Constants.ERROR_SQL_BOOKING, "retrieving"), e);
        }
        return bookings.stream()
                .sorted(Comparator.comparing(BookingDto::getGuestsLastName))
                .collect(Collectors.toList());
    }

    public void createBooking(String firstName, String lastName) {
        GuestDto guestDto = guestService.getGuestByName(firstName, lastName);
        var availableRoom = roomService.getAllAvailableRooms().stream().findAny();

        if (availableRoom.isPresent()) {
            try (Connection connection = JdbcPsqlConnection.connect()) {
                if (connection != null) {
                    String insertQuery = "INSERT INTO Booking (guest_id, room_id, checkedIn) VALUES (?, ?, ?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                        insertStatement.setLong(1, guestDto.getId());
                        insertStatement.setLong(2, availableRoom.get().getRoomNumber());
                        insertStatement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                        insertStatement.executeUpdate();
                    }
                }
            } catch (SQLException e) {

            }
        } else {
            ApplicationException.errorMessage(Response.Status.BAD_REQUEST, "We are fully booked");
        }
    }
}
