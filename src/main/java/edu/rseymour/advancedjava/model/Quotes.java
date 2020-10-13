package edu.rseymour.advancedjava.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "quotes", schema = "stocks")
public class Quotes extends DatabasesAccessObject {
    private int id;
    private String symbol;
    private Date date;
    private BigDecimal price;

    /**
     * Primary Key - Unique ID for a particular row in the quotes table.
     *
     * @return an integer value
     */
    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * Set the unique ID for a particular row in the quotes table.
     * This method should not be called by the client code. The value is managed
     * internally.
     *
     * @param id a unique value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the symbol column as a String
     */
    @Basic
    @Column(name = "symbol", nullable = false, insertable = true, updatable = true, length = 256)
    public String getSymbol() {
        return symbol;
    }

    /**
     * Specify the symbol's name.
     *
     * @param symbol a String value
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the value of the timestamp as a Timestamp
     */
    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true)
    public Date getTime() {
        return date;
    }

    /**
     * Specify the quote's timestamp
     *
     * @param date a Timestamp value
     */
    public void setTime(Date date) {
        this.date = date;
    }

    /**
     * @return the value of the stock quote
     */
    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, length = 8)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quotes quote = (Quotes) o;
        return id == quote.id &&
                price == quote.price &&
                Objects.equals(symbol, quote.symbol) &&
                Objects.equals(date, quote.date);
    }

    @Override
    public int hashCode() {
        int result = id;
        return Objects.hash(id, symbol, date, price);
    }

    @Override
    public String toString() {
        return "PersonQuotes{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price +
                '}';
    }

}
