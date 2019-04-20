package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

;

public class StockManagementTest {

	@Test
	public void testCanGetACorrectLocatorCode() {

		ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);
		when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));

		ExternalISBNDataService testDatabaseService = mock(ExternalISBNDataService.class);
		when(testDatabaseService.lookup(anyString())).thenReturn(null);

		StockManager stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(testDatabaseService);

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	@Test
	public void databaseIsUsedIfDataIsPresent() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		StockManager stockManager = new StockManager();
		stockManager.setDatabaseService(databaseService);
		stockManager.setWebService(webService);

		String isbn = "0140177396";

		String locatorCode = stockManager.getLocatorCode(isbn);
		verify(databaseService).lookup("0140177396");
		verify(webService, never()).lookup(anyString());

	}

	@Test
	public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

//		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		StockManager stockManager = new StockManager();
		stockManager.setDatabaseService(databaseService);
		stockManager.setWebService(webService);

		String isbn = "0140177396";

		String locatorCode = stockManager.getLocatorCode(isbn);
		verify(databaseService).lookup("0140177396");
		verify(webService).lookup("0140177396");

	}

}
