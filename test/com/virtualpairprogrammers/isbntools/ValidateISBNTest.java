package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkAValidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue("first value", result);
		result = validator.checkISBN("0140177396");
		assertTrue("second value", result);
	}

	@Test
	public void ISBNNumbersEndingInXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("179844822X");
		assertTrue(result);
	}
	
	@Test
	public void checkAnInvalidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}

	@Test(expected = NumberFormatException.class)
	public void nineDigitISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("123456789");	
	}
	
	@Test(expected = NumberFormatException.class)
	public void nonDigitsStringIsNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("helloworld");	
	}
}
