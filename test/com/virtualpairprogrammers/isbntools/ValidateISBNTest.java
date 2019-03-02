package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkAValidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0373060291");
		assertTrue("first value", result);
		
		result = validator.checkISBN("0525536515");
		assertTrue("second value", result);
	}

	@Test
	public void checkAnInvalidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0373060292");
		assertFalse(result);
	}

}
