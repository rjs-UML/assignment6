package edu.rseymour.advancedjava.util;

import com.ibatis.common.jdbc.ScriptRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class that contains database related utility methods.
 */
public class DatabaseUtils {

    public static final String initializationFile = "./src/main/sql/stocks_db_initialization.sql";

    // hard-coded JDBC information
    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/stocks?useSSL=false&serverTimezone=EST";

    // hard-coded db creds
    private static final String USER = "monty";
    private static final String PASS = "some_pass";

    /**
     * Establishes a connection between the application and the database using
     * the JDBC driver.
     *
     * @return a connection to the DBMS which is used for the DBMS.
     * @throws DatabaseConnectionException if the connection cannot be made.
     */
    public static Connection getConnection() throws DatabaseConnectionException {
        Connection connection = null;
        try {
            // Load the JDBC driver
            Class.forName(JDBC_DRIVER);
            // initialize connection with DriverManager
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            String message = e.getMessage();
            throw new DatabaseConnectionException("Could not connect to the database." + message, e);
        }
        return connection;
    }

    /**
     * Utility method that runs a db initialize script.
     *
     * @param initializationScript full path to the script to create the schema.
     * @throws DatabaseInitializationException if the connection cannot be made.
     */
    public static void initializeDatabase(String initializationScript) throws DatabaseInitializationException {

        Connection connection = null;
        try {
            connection = getConnection();

            // groups SQL statments as one transaction
            connection.setAutoCommit(false);

            // tool to run db scripts
            ScriptRunner runner = new ScriptRunner(connection, false, false);

            // obtains input bytes from a file in a file system
            InputStream inputStream = new FileInputStream(initializationScript);

            // reads bytes from an input stream and decodes them into chars
            InputStreamReader reader = new InputStreamReader(inputStream);

            runner.runScript(reader);
            reader.close();

            // commits the transactions. Must add this because we set AutoCommit to false
            connection.commit();
            connection.close();


        } catch (DatabaseConnectionException | SQLException | IOException e) {
            throw new DatabaseInitializationException("Could not initialize db because of: "
            + e.getMessage(), e);
        }
    }

    /**
     * Execute SQL code.
     *
     * @param someSQL the code to execute.
     * @return true if the operation succeeded.
     * @throws DatabaseException if accessing and executing the SQL failed in any way.
     */
    public static boolean executeSQL(String someSQL) throws DatabaseException {
        Connection connection = null;
        boolean returnValue = false;

        try {
            // need connection obj to create a Statement
            connection = getConnection();

            // Statement creates an object that represents a SQL statement. It has methods, and
            // requires a connection.
            Statement statement = connection.createStatement();

            // execute returns true if the first object that the query returns is a ResultSet
            // Aside: executeQuery returns the ResultSet object
            returnValue = statement.execute(someSQL);

        } catch (DatabaseConnectionException | SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
        return returnValue;
    }
}
















