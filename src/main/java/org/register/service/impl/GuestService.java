package org.register.service.impl;

import jakarta.ws.rs.core.Response;
import org.register.Constants;
import org.register.domain.dto.GuestDto;
import org.register.exception.ApplicationException;
import org.register.mapper.GuestMapper;
import org.register.service.database.JdbcPsqlConnection;
import org.register.utils.ServiceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.register.utils.ServiceUtils.*;

public class GuestService {

    public List<GuestDto> getAllGuest() {
        List<GuestDto> guests = new ArrayList<>();
        try (Connection connection = JdbcPsqlConnection.connect();
             ResultSet resultSet = getResultsFromQuery("SELECT * FROM Guest", connection)) {

            while (resultSet.next()) {
                guests.add(GuestMapper.fromResultSetToDto(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName")));
            }
        } catch (SQLException e) {
            handleError(String.format(Locale.getDefault(), Constants.ERROR_SQL_GUEST, "retrieving"), e);
        }

        return guests;
    }

    public void createGuest(GuestDto dto) {
        try (Connection connection = JdbcPsqlConnection.connect()) {
            if (connection != null) {
                String insertQuery = "INSERT INTO Guest (firstName, lastName) VALUES (?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setString(1, dto.getFirstName());
                    insertStatement.setString(2, dto.getLastName());
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            handleError(String.format(Locale.getDefault(), Constants.ERROR_SQL_GUEST, "creating"), e);
            if (e.getMessage().contains("duplicate key value violates unique constraint")) {
                ApplicationException.errorMessage(Response.Status.BAD_REQUEST,
                        "Error: Guest with the same first name and last name already exists.");
            }
        }
    }

    public void updateGuest(Long id, GuestDto dto) {
        try (Connection connection = JdbcPsqlConnection.connect()) {
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
                    logErrorToConsole(String.format(Locale.getDefault(), Constants.NO_GUEST_FOUND, id));
                    ApplicationException.errorMessage(Response.Status.BAD_REQUEST,
                            String.format(Locale.getDefault(), Constants.NO_GUEST_FOUND, id));
                }
            }
        } catch (SQLException e) {
            handleError(String.format(Locale.getDefault(), Constants.ERROR_SQL_GUEST, "updating"), e);
        }
    }

    public void deleteGuest(Long id) {
        if (hasGuestById(id)) {
            try (Connection connection = JdbcPsqlConnection.connect()) {
                if (connection != null) {
                    String deleteQuery = "DELETE FROM Guest WHERE id = ?";
                    try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                        deleteStatement.setLong(1, id);
                        deleteStatement.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                handleError(String.format(Locale.getDefault(), Constants.ERROR_SQL_GUEST, "deleting"), e);
            }
        } else {
            logErrorToConsole(String.format(Locale.getDefault(), Constants.NO_GUEST_FOUND, id));
            ApplicationException.errorMessage(Response.Status.NOT_FOUND,
                    String.format(Locale.getDefault(), Constants.NO_GUEST_FOUND, id));
        }
    }

    public GuestDto getGuestByName(String firstName, String lastName) {
        GuestDto guestDto = null;
        try (Connection connection = JdbcPsqlConnection.connect()) {
            String query = "SELECT * FROM Guest WHERE firstName = ? AND lastName = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);

                try (ResultSet resultSet = ServiceUtils.getResultsFromQuery(statement.toString(), connection)) {
                    if (resultSet.next()) {
                        guestDto = GuestMapper.fromResultSetToDto(
                                resultSet.getLong("id"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            handleError(String.format(Constants.ERROR_SQL_GUEST, "retrieving"), e);
        }

        return guestDto;
    }

        private boolean hasGuestById (Long id){
            try (Connection connection = JdbcPsqlConnection.connect()) {
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
                handleError(String.format(Locale.getDefault(), Constants.ERROR_SQL_GUEST, "finding"), e);
            }
            return false;
        }
    }