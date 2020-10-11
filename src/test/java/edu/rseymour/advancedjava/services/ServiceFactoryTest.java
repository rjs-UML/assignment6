package edu.rseymour.advancedjava.services;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ServiceFactoryTest {

    @Test
    public void getStockServiceInstanceTest() {
        StockService stockService = ServiceFactory.getStockServiceInstance();
        assertNotNull(stockService);
    }

    @Test
    public void getPersonServiceInstanceTest() {
        PersonService personService = ServiceFactory.getPersonServiceInstance();
        assertNotNull(personService);
    }
}
