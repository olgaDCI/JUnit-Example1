package com.example;
import org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StockManagementTest {
    @Test
    public void testCanGetACorrectLocatorCode(){
        //fail();
        String isbn = "0582827647";

        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book("0582827647", "Of Mice and man", "John Steinbeck");
            }
        };

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7647S4",locatorCode);
    }

    @Test
    public void databaseIsUsedWhenDataIsPresent(){
        String isbn = "0582827647";

        ExternalISBNDataService testDatabaseService = mock(ExternalISBNDataService.class);
        testDatabaseService.lookup(isbn);
        ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);

        when(testDatabaseService.lookup(isbn))
                .thenReturn(new Book("1234567890", "Of Mice and man", "John Steinbeck"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        verify(testDatabaseService, times(1)).lookup(isbn);
        verify(testWebService, never()).lookup(anyString());

        // fail("Not yet implemented");
    }

    @Test
    public void webServiceIsUsedWhenDataIsNotPresentInDatabase(){
        String isbn = "0582827647";

        ExternalISBNDataService testDatabaseService = mock(ExternalISBNDataService.class);
        testDatabaseService.lookup(isbn);
        ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);
        testWebService.lookup(isbn);

        when(testDatabaseService.lookup(isbn)).thenReturn(null);

        when(testWebService.lookup(isbn))
                .thenReturn(new Book("1234567890", "Of Mice and Men", "John Steinbeck"));

        StockManager stockManager = new StockManager();
        stockManager.setDatabaseService(testDatabaseService);
        stockManager.setWebService(testWebService);

        verify(testDatabaseService, times(1)).lookup(isbn);
        verify(testWebService, times(1)).lookup(isbn);


        //fail("Not yet implemented");
    }
}
