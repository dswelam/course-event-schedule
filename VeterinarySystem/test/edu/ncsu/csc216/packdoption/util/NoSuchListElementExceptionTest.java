/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for NoSuchListElementException class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class NoSuchListElementExceptionTest {

	/**
	 * Test method for the default constructor of NoSuchListElementException.
	 */
	@Test
	void testNoSuchListElementException() {
		NoSuchListElementException e = assertThrows(NoSuchListElementException.class, () -> {
			throw new NoSuchListElementException();
		});
		assertEquals("No such element in list.", e.getMessage());
	}

	/**
	 * Test method for the constructor with a specified message of
	 * NoSuchListElementException.
	 */
	@Test
	void testNoSuchListElementExceptionString() {
		String message = "Custom exception message";
		NoSuchListElementException e = assertThrows(NoSuchListElementException.class, () -> {
			throw new NoSuchListElementException(message);
		});
		assertEquals(message, e.getMessage());
	}
}