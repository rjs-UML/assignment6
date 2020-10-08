package edu.rseymour.advancedjava.services;

import edu.rseymour.advancedjava.model.StockQuote;
import edu.rseymour.advancedjava.util.Interval;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * An implementation of the StockService that returns hard coded data.
 */
public class SimpleStockService implements StockService {

    /**
     * Return the current price for a share of stock for the given symbol.
     *
     * @param symbol the stock symbol for the company that you want a quote for.
     * @return a <CODE>BigDecimal</CODE> instance
     * @throws StockServiceException if using the service generates an exception.
     *                               If this happens, trying the service may work,
     *                               depending on the actual cause of the error.
     */
    @Override
    public StockQuote getQuote(String symbol) {
        // returns hard-coded stock quote instead of querying the db.
        return new StockQuote(new BigDecimal(100), Calendar.getInstance().getTime(), symbol);
    }

    /**
     * Get a historical list of stock quotes for the provided symbol.
     *
     * @param symbol   the stock symbol to search for
     * @param from     the date of the first stock quote
     * @param until    the date of the last stock quote
     * @param interval the unit of time
     * @return         a list of StockQuote instances
     * @throws         StockServiceException if using the service generates an exception.
     *                 If this happens, trying the service might work, depending on the
     *                 actual cause of the error.
     */
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, Interval interval) throws StockServiceException {
        // create the list to fill with stock quotes
        List<StockQuote> stockQuotes = new ArrayList<>();
        // get a Date object that represents the time value of from
        Date aDay = from.getTime();
        // calendar.after() is a boolean. While the until date is after the from date...
        while(until.after(aDay)) {
            // ... add this stock quote to the list
            stockQuotes.add(new StockQuote(new BigDecimal(100), aDay, symbol));
            // add 1 day to the from date (this is kinda like count++)
            from.add(Calendar.DAY_OF_YEAR, 1);
            // update the aDay as 1 day later than before for the next while loop iteration
            aDay = from.getTime();
        }
        // the while loop quits when aDay has advanced equal to until and returns the list
        return stockQuotes;
    }
}
