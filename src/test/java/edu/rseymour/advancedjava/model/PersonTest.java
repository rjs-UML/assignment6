package edu.rseymour.advancedjava.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for the PersonEntity class
 */
public class PersonTest {

    public static final String firstName = "Ryan";
    public static final String lastName = "Seymour";

    /**
     * Testing helper method for generating PersonEntity test data
     *
     * @return a PersonEntity object that uses static constants for data.
     */
    public static Person createPerson() {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }

    /**
     * Test the getters and setters for the Person class
     */
    @Test
    public void PersonEntityGettersAndSettersTest() {
        Person person = createPerson();
        int id = 10;
        person.setId(id);
        assertEquals("id matches", id, person.getId());
        assertEquals("first name matches", firstName, person.getFirstName());
        assertEquals("last name matches", lastName, person.getLastName());
    }
}
