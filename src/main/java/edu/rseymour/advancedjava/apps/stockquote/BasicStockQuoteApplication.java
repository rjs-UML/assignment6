package edu.rseymour.advancedjava.apps.stockquote;

import edu.rseymour.advancedjava.model.StockQuery;
import edu.rseymour.advancedjava.model.StockQuote;
import edu.rseymour.advancedjava.services.StockService;
import edu.rseymour.advancedjava.services.StockServiceException;
import edu.rseymour.advancedjava.services.StockServiceFactory;
import edu.rseymour.advancedjava.util.Interval;

import java.text.ParseException;
import java.util.List;

/**
 * A simple application that shows the StockService in action.
 */
public class BasicStockQuoteApplication {

    private StockService stockService;

    private enum ProgramTerminationStatusEnum {
        NORMAL(0),
        ABNORMAL(-1);

        // When the program exits, this value is reported to the OS
        private int statusCode;

        /**
         * Create a new ProgramTerminationStatusEnum
         *
         * @param statusCodeValue the value to return to the OS. A value of 0
         *                        indicates success or normal termination.
         *                        Non 0 numbers indicate abnormal termination.
         */
        private ProgramTerminationStatusEnum(int statusCodeValue) {
            this.statusCode = statusCodeValue;
        }

        /**
         * @return the value sent to the OS when the program ends.
         */
        private int getStatusCode() {
            return statusCode;
        }
    }

    /**
     * Create a new Application. Pass this a stock service, like a DatabaseStockService.
     *
     * @param stockService the StockService that this application instance should
     *                     use for stock queries.
     */
    public BasicStockQuoteApplication(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Given a <CODE>stockQuery</CODE> get back the information about the stock to display
     * to the user.
     *
     * @param stockQuery the stock to get data for.
     * @return a String with the stock data in it.
     * @throws StockServiceException If data about the stock cannot be retrieved. This is a
     *                               fatal error.
     */
    public String displayStockQuotes(StockQuery stockQuery) throws StockServiceException {
        StringBuilder stringBuilder = new StringBuilder();

        // 1. Connects to the db
        // 2. Queries db to get resultSet of stock quotes
        // 3. Iterates through resultSet, adding each stock quote to ArrayList
        // 4. Returns ArrayList
        List<StockQuote> stockQuotes =
                stockService.getQuote(stockQuery.getSymbol(),
                        stockQuery.getFrom(),
                        stockQuery.getUntil(),
                        Interval.DAY);

        stringBuilder.append("Stock quotes for: " + stockQuery.getSymbol() + "\n");
        // iterate through stockQuotes, calling toString()
        for (StockQuote stockQuote : stockQuotes) {
            stringBuilder.append(stockQuote.toString());
        }
        return stringBuilder.toString();
    }

    /**
     * Terminate the application.
     *
     * @param statusCode        An enum value that indicates if the program terminated OK or not.
     * @param diagnosticMessage A essage to display to the ser when the program ends. This should
     *                          be an error message in the case of abnormal termination.
     *                          <p/>
     *                          NOTE: This is an example
     */
    private static void exit(ProgramTerminationStatusEnum statusCode, String diagnosticMessage) {
        if (statusCode == ProgramTerminationStatusEnum.NORMAL) {
            System.out.println(diagnosticMessage);
        } else if (statusCode == ProgramTerminationStatusEnum.ABNORMAL) {
            System.err.println(diagnosticMessage);
        } else {
            throw new IllegalStateException("Unknown ProgramTerminationStatusEnum");
        }
        System.exit(statusCode.getStatusCode());
    }

    public static void main(String[] args) {

        ProgramTerminationStatusEnum exitStatus = ProgramTerminationStatusEnum.NORMAL;
        String programTerminationMessage = "Normal program termination.";

        if (args.length != 3) {
            exit(ProgramTerminationStatusEnum.ABNORMAL,
                    "Please supply 3 arguments: a stock symbol, " +
                            "a start date (MM/DD/YYYY), and end date (MM/DD/YYYY).");
        }
        try {
            // New stockQuery consuming parameters from the command line
            StockQuery stockQuery = new StockQuery(args[0], args[1],args[2]);

            // Creating a StockServiceFactory. Returns a DatabaseStockService, so
            // it can talk to the db.
            StockService stockService = StockServiceFactory.getInstance();

            // Create a new app. Pass the stockService to the app using dependency injection
            // It takes an interface, so I can pass any kind of stock service
            BasicStockQuoteApplication basicStockQuoteApplication =
                    new BasicStockQuoteApplication(stockService);

            // Displays the stock quotes that are being fed in from the command line
            basicStockQuoteApplication.displayStockQuotes(stockQuery);

        } catch (ParseException e) {
            exitStatus = ProgramTerminationStatusEnum.ABNORMAL;
            programTerminationMessage = "Invalid date data: " + e.getMessage();
        } catch (StockServiceException e) {
            exitStatus = ProgramTerminationStatusEnum.ABNORMAL;
            programTerminationMessage = "StockService fialed: " + e.getMessage();
        } catch (Throwable t) {
            exitStatus = ProgramTerminationStatusEnum.ABNORMAL;
            programTerminationMessage = "General application error: " + t.getMessage();
        }

        exit(exitStatus, programTerminationMessage);
        System.out.println("Could not parse a date.");
    }
}























