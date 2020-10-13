package edu.rseymour.advancedjava.services;

//      Create concrete implementation of this interface using ORM and new DatabaseUtils methods

import edu.rseymour.advancedjava.model.Person;
import edu.rseymour.advancedjava.model.Quotes;

import java.util.List;

public interface PersonService {

    /**
     * Get a list of all Person records in the db
     *
     * @return a list of Person instances
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                or otherwise perform the requested operation.
     */
    List<Person> getPerson() throws PersonServiceException;

    /**
     * Add a new person or update an existing Person's data
     *
     * @param person a person object to either update or create
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                or otherwise perform the requested operation.
     */
    void addOrUpdatePerson(Person person) throws PersonServiceException;

    /**
     * Get a list of all the person's quotes.
     *
     * @param person the person that you want to return quotes for
     * @return a list of quotes
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                or otherwise perform the requested operation.
     */
    List<Quotes> getQuotes(Person person) throws PersonServiceException;

    /**
     * Assign a quote to a person.
     *
     * @param quote  The quote to assign
     * @param person The person to assign the quote too.
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                or otherwise perform the requested operation.
     */
    public void addQuotesToPerson(Quotes quote, Person person) throws PersonServiceException;
}
