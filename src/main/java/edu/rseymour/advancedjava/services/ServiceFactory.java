package edu.rseymour.advancedjava.services;

/**
 * A factory that returns a <CODE>StockService</CODE> instance.
 */
public class ServiceFactory {

    /**
     * Private constructor to prevent instantiations.
     */
    private ServiceFactory() {}

    /**
     * @return a <CODE>StockService</CODE> instance.
     */
    public static StockService getStockServiceInstance() {
        return new DatabaseStockService();
    }

    /**
     * @return a <CODE>PersonService</CODE> instance.
     */
    public static PersonService getPersonServiceInstance() {
        return new DatabasePersonService();
    }
}
