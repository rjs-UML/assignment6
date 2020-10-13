package edu.rseymour.advancedjava.services;

import edu.rseymour.advancedjava.model.DatabasesAccessObject;
import edu.rseymour.advancedjava.model.Person;
import edu.rseymour.advancedjava.model.PersonQuotes;
import edu.rseymour.advancedjava.model.Quotes;
import edu.rseymour.advancedjava.util.DatabaseUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DatabasePersonService implements PersonService  {

    /**
     * Get a list of all Person records in the db
     *
     * @return a list of Person instances
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                or otherwise perform the requested operation.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getPerson() throws PersonServiceException {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        List<Person> returnValue = null;
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            returnValue = criteria.list();

        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersonServiceException("Could not get Person data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }

        return returnValue;

    }

    /**
     * Add a new person or update an existing Person's data
     *
     * @param person a person object to either update or create
     */
    @Override
    public void addOrUpdatePerson(Person person) {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(person);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }

    /**
     * Get a list of all the person's quotes.
     *
     * @param person the person that you want to return quotes for
     * @return a list of quotes
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                or otherwise perform the requested operation.
     */

    @Override
    @SuppressWarnings("unchecked")
    public List<Quotes> getQuotes(Person person) throws PersonServiceException {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Quotes> quotes = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(PersonQuotes.class);
            criteria.add(Restrictions.eq("person", person));

            List<PersonQuotes> list = criteria.list();
            for (PersonQuotes personQuotes : list) {
                quotes.add(personQuotes.getQuote());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
        return quotes;
    }

    public void addQuotesToPerson(Quotes quote, Person person) throws PersonServiceException {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            PersonQuotes personQuotes = new PersonQuotes();
            personQuotes.setQuote(quote);
            personQuotes.setPerson(person);
            session.saveOrUpdate(personQuotes);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }
}
