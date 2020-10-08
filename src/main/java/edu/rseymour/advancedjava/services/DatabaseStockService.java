package edu.rseymour.advancedjava.services;

import edu.rseymour.advancedjava.model.StockData;
import edu.rseymour.advancedjava.model.StockQuote;
import edu.rseymour.advancedjava.util.DatabaseConnectionException;
import edu.rseymour.advancedjava.util.DatabaseUtils;
import edu.rseymour.advancedjava.util.Interval;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseStockService implements StockService {

    /**
     * Return the current price for a share of stock for the given symbol.
     *
     * @param symbol the stock symbol for the company that you want a quote for.
     * @return a <CODE>BigDecimal</CODE> instance.
     * @throws StockServiceException if using the service generates an exception.
     *                               If an exception is generated, trying the
     *                               service might work, depending on the actual
     *                               cause of the error.
     */
    @Override
    public StockQuote getQuote(String symbol) throws StockServiceException {
        List<StockQuote> stockQuotes = null;

        try {
            Connection connection = DatabaseUtils.getConnection();

            // Statement creates an obj that represents a SQL statement. It requires a
            // connection. It doesn't do anything on it's own, you must use one of its
            // methods
            Statement statement = connection.createStatement();

            // SQL query to pass to the statement
            String queryString = "select * from quotes where symbol = '" + symbol + "'";

            // A table of data representing a database result set. resultSet represents
            // the table returned by executing queryString on the db.
            ResultSet resultSet = statement.executeQuery(queryString);

            // initialize stockQuote ArrayList, passing the size of the resultSet for optimization.
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());

            // iterate through resultSet. Initial row position is null, so first iteration reads first tuple
            // each iteration creates a StockQuote object and adds it to the ArrayList
            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                // retrieve value in 'time' column as a SQL Date object
                Date time = resultSet.getDate("time");
                // retrieve value in 'price' column as BigDecimal object
                BigDecimal price = resultSet.getBigDecimal("price");
                // use retrieved values to create a StockQuote obj and add it to the ArrayList
                stockQuotes.add(new StockQuote(price, time, symbolValue));
            }
        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for: " + symbol);
        }
        return stockQuotes.get(0);
    }

    /**
     * Get a historical list of stock quotes for the provided symbol.
     *
     * @param symbol   the stock symbol to search for.
     * @param from     the date of the first stock quote.
     * @param until    the date of the last stock quote.
     * @param interval the number of stock quotes to get per 24 hour period.
     * @return a list of StockQuote instances.
     * @throws StockServiceException if using the service generates an exception.
     *                               If an exception is generated, trying the
     *                               service might work, depending on the actual
     *                               cause of the error.
     */
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, Interval interval) throws StockServiceException {
        List<StockQuote> stockQuotes = null;

        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            // create a date format as defined in the abstract class StockData
            // SimpleDateFormat objects let you define how a date is represented
            // .format() accepts a Calendar/Date obj and returns text
            // .parse() accepts text and returns a Calendar/Date object
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StockData.dateFormat);
            String fromString = simpleDateFormat.format(from.getTime());
            String untilString = simpleDateFormat.format(until.getTime());

            String queryString = "select * from quotes where symbol = '" + symbol + "'"
                    + "and time BETWEEN '" + fromString + "' and '" + untilString + "'";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
            StockQuote previousStockQuote = null;

            // The resultSet is currently a table containing all stock quotes filtered using
            // the queryString.
            // Now, iterate through resultSet. Initial row position is null, so first iteration reads first tuple.
            // Each iteration creates a StockQuote object and adds it to the ArrayList

            Calendar calendar = Calendar.getInstance();
            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");

                // returns a java.sql.timestamp instance from the value stored in the
                // time column that represents the number of milliseconds from Jan 1, 1970 to
                // the 'time' column value for the current tuple
                Timestamp timestamp = resultSet.getTimestamp("time");

                // sets time for the calendar object using the timestamp
                calendar.setTimeInMillis(timestamp.getTime());

                // retrieve value in 'price' column as BigDecimal object
                BigDecimal price = resultSet.getBigDecimal("price");

                // convert the SQL timestamp into a java Date obj
                java.util.Date time = calendar.getTime();

                // create a StockQuote instance of the tuple in the current iteration
                StockQuote currentStockQuote = new StockQuote(price, time, symbolValue);

                if (previousStockQuote == null) {                   // add first iteration to ArrayList
                    stockQuotes.add(currentStockQuote);
                } else if (isInterval(currentStockQuote.getDate(),  // if the currentStockQuote date is at least
                        interval,                                   // 'interval' of time later than
                        previousStockQuote.getDate())) {            // previousStockQuote, add it to the ArrayList
                    stockQuotes.add(currentStockQuote);
                }
                previousStockQuote = currentStockQuote;
            }


        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for: " + symbol);
        }

        return stockQuotes;
    }

    /**
     * Returns true if the currentStockQuote has a date that is later than the time
     * specified in the interval value from the previousStockQuote time.
     *
     * @param endDate   the end time
     * @param interval  the period of time that mus exist between previousStockQuote and
     *                  currentStockQuote in order for this method to return true.
     * @param startDate the starting date
     * @return
     */
    private boolean isInterval(java.util.Date endDate, Interval interval, java.util.Date startDate) {

        // create a calendar instance
        Calendar startDatePlusInterval = Calendar.getInstance();

        // set the time to the startDate
        startDatePlusInterval.setTime(startDate);

        // add the number of minutes in the provided interval to the minutes value of the calendar obj
        startDatePlusInterval.add(Calendar.MINUTE, interval.getMinutes());

        // return whether the end date is after the startDate + interval
        return endDate.after(startDatePlusInterval.getTime());
    }
}
