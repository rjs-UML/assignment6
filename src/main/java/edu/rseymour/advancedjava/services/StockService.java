package edu.rseymour.advancedjava.services;

import java.util.Calendar;
import java.util.List;

import edu.rseymour.advancedjava.model.StockQuote;
import edu.rseymour.advancedjava.util.*;

/**
 * This API describes how to get stock data from an external source.
 */

public interface StockService {


    /**
     * Return the current price for a share of stock for the given symbol.
     * @param symbol the stock symbol for the company that you want a quote for.
     * @return a <CODE>BigDecimal</CODE> instance.
     * @throws StockServiceException if using the service generates an exception. If
     *                               this happens, trying the service may work, depending
     *                               on the actual cause of the error.
     */
    StockQuote getQuote(String symbol) throws StockServiceException;



    List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, Interval interval) throws StockServiceException;
}
