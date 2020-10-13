package edu.rseymour.advancedjava.util;

import edu.rseymour.advancedjava.model.DatabasesAccessObject;
import edu.rseymour.advancedjava.model.Person;
import edu.rseymour.advancedjava.model.PersonTest;
import edu.rseymour.advancedjava.model.Quotes;
import edu.rseymour.advancedjava.services.DatabasePersonService;
import edu.rseymour.advancedjava.services.PersonService;
import edu.rseymour.advancedjava.services.PersonServiceException;
import edu.rseymour.advancedjava.services.ServiceFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseUtilsTest {

    private DatabaseUtils databaseUtils;

    /**
     * Helper to get the db in original state
     */
    private void initDb() throws Exception {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }

    /**
     * Create the state of the database
     */
    @Before
    public void setUp() throws Exception {
        initDb();
    }

    /**
     * Get the db back to the original state after testing
     */
    @After
    public void tearDown() throws Exception {
        initDb();
    }

    /**
     * Test that the initialization file is working correctly
     */
    @Test
    public void testGoodInitFile() throws Exception {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }

    /**
     * Test that an error is thrown when the initialization file fails
     */
    @Test(expected = DatabaseInitializationException.class)
    public void testBadInitFile() throws Exception {
        DatabaseUtils.initializeDatabase("Init file failed.");
    }

    /**
     * Test that the application can maintain a connection to the database
     */
    @Test
    public void testGetConnection() throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        assertNotNull("Verify that we can get a connection OK", connection);
    }

    /**
     * Verify that an SQL statement can be executed on the database
     */
    @Test
    public void testGetConnectionWorks() throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        Statement statement = connection.createStatement();
        boolean execute = statement.execute("select * from quotes");
        assertTrue("Verify that we can execute a statement", execute);
    }

    /**
     *
     */
//    @Test
//    public void findUniqueResultByTest () throws Exception {
//        Person person = PersonTest.createPerson();
//        PersonService databasePersonService = new DatabasePersonService();
//        databasePersonService.addOrUpdatePerson(person);
//
//        String prop = "last_name";
//
//        Person test = DatabaseUtils.findUniqueResultBy(prop, person.getLastName(), Person.class,true);
//        assertEquals("Unique result found", test, person);
//
//        // property is the column being searched
//        // value is what I'm looking for in the column
//        // T is the class, e.g. Person
//        // handleTransaction is going to be true
//    }

//    @Test
//    public void findResultsByTest() throws PersonServiceException {
//        Person person = PersonTest.createPerson();
//        List<DatabasesAccessObject> test = new ArrayList<>();
//
//        test.add(person);
//        List<DatabasesAccessObject> personList = DatabaseUtils.findResultsBy(
//                "last_name",
//                "Malone",
//                Person.class,
//                true);
//        assertEquals("Unique result found", test, person);
//    }
}
