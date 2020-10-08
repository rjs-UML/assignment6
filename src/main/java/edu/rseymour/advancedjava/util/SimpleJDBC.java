package edu.rseymour.advancedjava.util;

import java.sql.*;

public class SimpleJDBC {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/stocks";

    private static final String USER = "username";
    private static final String PASS = "password";

    public static Connection getConnection() throws SQLException, DatabaseConnectionException {
        Connection connection = null;

        try {
            // Load the JDBC driver
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            String message = e.getMessage();
            throw new DatabaseConnectionException("Could not connect to the database: " + message, e);
        }
        return connection;
    }

    public static void executeStatement() throws SQLException, DatabaseConnectionException {
        // create a statement with the connection (if the connection object is in scope,
        // use ... = connection.createStatement();
        Statement statement = getConnection().createStatement();

        // create a simple query
        String sqlQuery = "select firstName, mi, lastName from table where lastName = 'Seymour'";

        // execute statement. Accepts String
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        // iterate through resultSet. Initial row position is null, so first iteration reads first tuple
        // getString accepts String or column #
        while(resultSet.next()) {
            System.out.println(resultSet.getString("firstName") + "\t" +
                               resultSet.getString("mi") + "\t" +
                               resultSet.getString("lastName")
            );
        }
    }
}
