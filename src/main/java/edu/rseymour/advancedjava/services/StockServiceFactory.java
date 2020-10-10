package edu.rseymour.advancedjava.services;

import edu.rseymour.advancedjava.model.StockQuote;
import edu.rseymour.advancedjava.util.Interval;

import java.util.Calendar;
import java.util.List;

/**
 * A factory that returns a <CODE>StockService</CODE> instance.
 */
public class StockServiceFactory {

    /**
     * Private constructor to prevent instantiations.
     */
    private StockServiceFactory() {}

    /**
     * @return a <CODE>StockService</CODE> instance.
     */
    public static StockService getInstance() {
        // Return a new DatabaseStockService
        return new DatabaseStockService();
    }
}
