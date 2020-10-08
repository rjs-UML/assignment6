package edu.rseymour.advancedjava.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StockQueryTest {

    @Test
    public void testBasicConstuction()  throws Exception {
        String symbol = "APPL";
        StockQuery stockQuery = new StockQuery(symbol, "2011-10-29 12:12:12", "2011-11-29 12:12:12");
        assertEquals("Verify construction", symbol, stockQuery.getSymbol());
    }
}
