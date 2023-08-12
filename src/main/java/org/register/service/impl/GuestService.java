package org.register.service.impl;

import jakarta.ws.rs.core.Response;
import org.register.domain.dto.GuestDto;
import org.register.exception.ApplicationException;
import org.register.mapper.GuestMapper;
import org.register.service.database.JdbcPsqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GuestService {

    private static final Logger LOGGER = Logger.getLogger(GuestService.class.getName());

    public List<GuestDto> getAllGuest() {
        List<GuestDto> guests = new ArrayList<>();
        try (Connection connection = connectToDb();
             ResultSet resultSet = getResultsFromQuery("SELECT * FROM Guest", connection)) {

            while (resultSet.next()) {
                guests.add(GuestMapper.fromResultSetToDto(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName")));
            }
        } catch (SQLException e) {
            handleError("retrieving guests", e);
        }

        return guests;
    }

    public void createGuest(GuestDto dto) {
        try (Connection connection = connectToDb()) {
            if (connection != null) {
                String insertQuery = "INSERT INTO Guest (firstName, lastName) VALUES (?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setString(1, dto.getFirstName());
                    insertStatement.setString(2, dto.getLastName());
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            handleError("creating guest", e);
            if (e.getMessage().contains("duplicate key value violates unique constraint")) {
                ApplicationException.errorMessage(Response.Status.BAD_REQUEST,
                        "Error: Guest with the same name and surname already exists.");
            }
        }
    }

    public void updateGuest(Long id, GuestDto dto) {
        try (Connection connection = connectToDb()) {
            if (connection != null) {
                if (hasGuestById(id)) {
                    String updateQuery = "UPDATE Guest SET firstName = ?, lastName = ? WHERE id = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setString(1, dto.getFirstName());
                        updateStatement.setString(2, dto.getLastName());
                        updateStatement.setLong(3, id);
                        updateStatement.executeUpdate();
                    }
                } else {
                    LOGGER.warning("Guest with ID " + id + " not found.");
                    ApplicationException.errorMessage(Response.Status.BAD_REQUEST, "No such guest was found.");
                }
            }
        } catch (SQLException e) {
            handleError("updating guest", e);
        }
    }

    public void deleteGuest(Long id) {
        if (hasGuestById(id)) {
            try (Connection connection = connectToDb()) {
                if (connection != null) {
                    String deleteQuery = "DELETE FROM Guest WHERE id = ?";
                    try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                        deleteStatement.setLong(1, id);
                        deleteStatement.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                handleError("deleting guest", e);
            }
        } else {
            LOGGER.warning("Guest with ID " + id + " not found.");
            ApplicationException.errorMessage(Response.Status.NOT_FOUND, "Guest not found.");
        }
    }

    private boolean hasGuestById(Long id) {
        try (Connection connection = connectToDb()) {
            if (connection != null) {
                String query = "SELECT id FROM Guest WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setLong(1, id);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        return resultSet.next();
                    }
                }
            }
        } catch (SQLException e) {
            handleError("checking guest existence", e);
        }
        return false;
    }

    private Connection connectToDb() {
        return JdbcPsqlConnection.connect();
    }

    private ResultSet getResultsFromQuery(String query, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    private void handleError(String operation, SQLException e) {
        LOGGER.severe("Error " + operation + ": " + e.getMessage());
    }
}