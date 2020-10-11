package edu.rseymour.advancedjava.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;


/**
 * JUnit test for StockQuotes class
 */
public class QuotesTest {

    final static String symbol = "GOOG";
    final static Date date = Calendar.getInstance().getTime();
    final static BigDecimal price = new BigDecimal(100);


    public static Quotes createQuote() {
        Quotes quote = new Quotes();
        quote.setSymbol(symbol);
        quote.setTime(date);
        quote.setPrice(price);
        return quote;
    }


    /**
     * Test QuotesEntity getters and setters
     */
    @Test
    public void QuotesGettersAndSettersTest() {
        Quotes quote = createQuote();
        int id = 10;
        quote.setId(id);
        assertEquals("id", id, quote.getId());
        assertEquals("symbol", symbol, quote.getSymbol());
        assertEquals("date", date, quote.getTime());
        assertEquals("price", price, quote.getPrice());
    }
}
