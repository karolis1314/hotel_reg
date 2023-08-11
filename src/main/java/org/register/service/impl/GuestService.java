package org.register.service.impl;

import org.register.domain.dto.GuestDto;
import org.register.mapper.GuestMapper;
import org.register.service.database.JdbcPsqlConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestService {

    public GuestService(){}


    public List<GuestDto> getAllGuest() {
        List<GuestDto> guests = new ArrayList<>();
        try {
            var connection = JdbcPsqlConnection.connect();

            if (connection != null) {
                String query = "SELECT * FROM Guest";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    guests.add(GuestMapper.fromResultSetToDto(
                            resultSet.getLong("id"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName")));
                }

                resultSet.close();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return guests;
    }

}
