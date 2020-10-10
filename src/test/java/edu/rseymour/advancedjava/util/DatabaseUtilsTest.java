package edu.rseymour.advancedjava.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DatabaseUtilsTest {

    @Test
    public void testGoodInitFile() throws Exception {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }

    @Test(expected = DatabaseInitializationException.class)
    public void testBadInitFile() throws Exception {
        DatabaseUtils.initializeDatabase("Init file failed.");
    }

    @Test
    public void testGetConnection() throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        assertNotNull("Verify that we can get a connection OK");
    }

    @Test
    public void testGetConnectionWorks() throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        Statement statement = connection.createStatement();
        boolean execute = statement.execute("select * from quotes");
        assertTrue("Verify that we can execute a statement", execute);
    }
}
