package edu.rseymour.advancedjava.services;

import edu.rseymour.advancedjava.model.Person;
import edu.rseymour.advancedjava.model.PersonTest;
import edu.rseymour.advancedjava.model.Quotes;
import edu.rseymour.advancedjava.util.DatabaseUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabasePersonServiceTest {

    private PersonService personService;

    private void initDb() throws Exception {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }

    // do not assume db is correct
    @Before
    public void setUp() throws Exception {
        initDb();
        personService = ServiceFactory.getPersonServiceInstance();
    }

    @After
    public void tearDown() throws Exception {
        initDb();
    }

    @Test
    public void getPersonServiceInstanceTest() {
        assertNotNull("Make sure PersonService is available", personService);
    }

    @Test
    public void getPersonTest() throws PersonServiceException {
        List<Person> personList = personService.getPerson();
        assertFalse("Make sure we return Person objects from service", personList.isEmpty());
    }

    @Test
    public void addOrUpdatePersonTest() throws PersonServiceException {
        Person newPerson = PersonTest.createPerson();
        personService.addOrUpdatePerson(newPerson);
        List<Person> personList = personService.getPerson();
        boolean found = false;
        for (Person person : personList) {
            String returnedFirstName = person.getFirstName();
            String returnedLastName = person.getLastName();
            if (returnedFirstName.equals(newPerson.getFirstName()) &&
                    returnedLastName.equals(newPerson.getLastName())) {
                found = true;
                break;
            }
        }
        assertTrue("Found the person we added", found);
    }

    @Test
    public void getQuotesByPersonTest() throws PersonServiceException {
        Person person = PersonTest.createPerson();
        List<Quotes> quotes = personService.getQuotes(person);

        for (Quotes quote : quotes) {
            personService.addQuotesToPerson(quote, person);
        }
        List<Quotes> quoteList = personService.getQuotes(person);
        for (Quotes quote : quotes) {
            boolean removed = quoteList.remove(quote);
            assertTrue("Verify that the quote was found on the list", removed);
        }
        // if quoteList is empty, we know the lists matched
        assertTrue("Verify the list of returned quotes match what was expected", quoteList.isEmpty());
    }
}
