package edu.rseymour.advancedjava.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person_quotes", schema = "stocks")
public class PersonQuotes extends DatabasesAccessObject {
    private int id;
    private Person person;
    private Quotes quote;

    /**
     * Create a PersonQuotesEntity that needs to be initialized
     */
    public PersonQuotes() {
        // empty constructor
    }

    public PersonQuotes(Person person, Quotes quote) {
        setPerson(person);
        setQuote(quote);
    }

    /**
     * Primary Key - unique ID for a particular row in the person_quotes table
     *
     * @return an integer value
     */
    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    /**
     * Set the unique ID for a particular row in the person_quotes table.
     * This method should not be called by client code. This calue is managed
     * internally.
     *
     * @param id a unique value.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return get the Person associated with this quote
     */
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "ID", nullable = false)
    public Person getPerson() {
        return person;
    }

    /**
     * Specify the Person associated with the quote
     *
     * @param person a PersonEntity instance
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return get the QuoteEntity associated with this PersonEntity
     */
    @ManyToOne
    @JoinColumn(name = "quote_id", referencedColumnName = "ID", nullable = false)
    public Quotes getQuote() {
        return quote;
    }

    /**
     * Specify the QuoteEntity associated with the Person
     *
     * @param quote a person instance
     */
    public void setQuote(Quotes quote) {
        this.quote = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonQuotes that = (PersonQuotes) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PersonQuotes{" +
                "id=" + id +
                ", person=" + person +
                ", quote=" + quote +
                '}';
    }
}
