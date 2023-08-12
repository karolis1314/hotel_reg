package org.register.service.impl;

import jakarta.ws.rs.WebApplicationException;
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

    public GuestService() {
    }

    private static final Logger LOGGER = Logger.getLogger(GuestService.class.getName());


    public List<GuestDto> getAllGuest() {
        List<GuestDto> guests = new ArrayList<>();
        try (Connection connection = connectToDb()) {
            if (connection != null) {
                var resultSet = getResultsFromQuery("SELECT * FROM Guest", connection);
                while (resultSet.next()) {
                    guests.add(GuestMapper.fromResultSetToDto(
                            resultSet.getLong("id"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName")));
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error retrieving guests: " + e.getMessage());
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
            LOGGER.severe("Error creating guest: " + e.getMessage());
            if (e.getMessage().contains("duplicate key value violates unique constraint")) {
                ApplicationException.errorMessage(Response.Status.BAD_REQUEST,
                        "Error: Guest with the same name and surname already exists.");
            }
        }
    }


    private Connection connectToDb() {
        return JdbcPsqlConnection.connect();
    }

    private ResultSet getResultsFromQuery(String query, Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

}
