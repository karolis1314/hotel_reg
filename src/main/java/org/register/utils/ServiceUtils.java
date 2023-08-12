package org.register.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ServiceUtils {

    private static final Logger LOGGER = Logger.getLogger(ServiceUtils.class.getName());

    public static ResultSet getResultsFromQuery(String query, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    public static void handleError(String operation, SQLException e) {
        LOGGER.severe("Error " + operation + ": " + e.getMessage());
    }

    public static void logErrorToConsole(String error) {
        LOGGER.warning(error);
    }
}
