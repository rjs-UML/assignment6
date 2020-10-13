package edu.rseymour.advancedjava.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test for PersonQuotes class
 */
public class PersonQuotesTest {


    /**
     * Testing helper method for generating PersonQuotes test data
     *
     * @return a PersonQuotes object that uses Person and Quotes
     */
    public static PersonQuotes createPersonQuotes() {
        Person person = PersonTest.createPerson();
        Quotes quote = QuotesTest.createQuote();
        return new PersonQuotes(person, quote);
    }

    /**
     * Test the getters and setters for the PersonQuotes class
     */
    @Test
    public void PersonQuotesGettersAndSetters() {
        Person person = PersonTest.createPerson();
        Quotes quote = QuotesTest.createQuote();
        PersonQuotes personQuotes = new PersonQuotes();
        int id = 10;
        personQuotes.setId(id);
        personQuotes.setPerson(person);
        personQuotes.setQuote(quote);
        assertEquals("id matches", id, personQuotes.getId());
        assertEquals("person matches", person, personQuotes.getPerson());
        assertEquals("quotes matches", quote, personQuotes.getQuote());
    }

    /**
     * Test that PersonQuotes objects that are not equal fail the equality test
     */
    @Test
    public void EqualsNegativeDifferentPersonTest() {
        PersonQuotes personQuotes = createPersonQuotes();
        personQuotes.setId(10);
        Quotes quote = QuotesTest.createQuote();
        Person person = new Person();
        person.setFirstName(PersonTest.firstName);
        person.setLastName(PersonTest.lastName);
        PersonQuotes personQuotes2 = new PersonQuotes(person, quote);
        assertFalse("Different person", personQuotes.equals(personQuotes2));
    }

    /**
     * Test that PersonQuotes objects that are equal pass the equality test
     */
    @Test
    public void testEquals() {
        PersonQuotes personQuotes = createPersonQuotes();
        assertTrue("Same objects are equal", personQuotes.equals(createPersonQuotes()));
    }
}
