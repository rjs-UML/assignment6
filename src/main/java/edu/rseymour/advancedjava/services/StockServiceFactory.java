package edu.rseymour.advancedjava.services;

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
