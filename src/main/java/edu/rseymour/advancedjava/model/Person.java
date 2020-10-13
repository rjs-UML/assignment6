package edu.rseymour.advancedjava.model;

import javax.persistence.*;

/**
 * Models the stocks.person table
 */

@Entity
@Table(name = "person", schema = "stocks")
public class Person extends DatabasesAccessObject {

    private int id;
    private String firstName;
    private String lastName;

    /**
     * Primary Key - Unique ID for a particular row in the person table.
     * @return an integer value
     */
    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * Set the unique ID for a particular row in the hobby table.
     * This method should not be called by client code. The value is managed internally.
     *
     * @param id a unique value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the person's first name
     */
    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 256)
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the person's first name
     * @param firstName a String value
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the person's last name
     */
    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 256)
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the person's last name
     * @param lastName a String value
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
