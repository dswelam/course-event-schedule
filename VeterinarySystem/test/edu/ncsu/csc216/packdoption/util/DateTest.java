package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test class for Date class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class DateTest {

	/**
	 * Tests constructing a Date with valid month, day, and year.
	 */
	@Test
	public void testDateValid() {
		// Test a valid construction
		Date d = assertDoesNotThrow(() -> new Date(11, 4, 2017), "Should not throw exception");

		assertAll("Date", () -> assertEquals(11, d.getMonth(), "incorrect month"),
				() -> assertEquals(4, d.getDay(), "incorrect day"),
				() -> assertEquals(2017, d.getYear(), "incorrect year"));
	}

	/**
	 * Tests constructing a Date with invalid month, day, and year.
	 */
	@Test
	public void testDateInvalid() {
		assertThrows(IllegalArgumentException.class, () -> new Date(13, 15, 2020),
				"Should throw exception for invalid month");
		assertThrows(IllegalArgumentException.class, () -> new Date(1, 32, 2020),
				"Should throw exception for invalid day");
		assertThrows(IllegalArgumentException.class, () -> new Date(1, 15, 1999),
				"Should throw exception for invalid year");
	}

	/**
	 * Tests constructing a Date from a valid date.
	 * 
	 * @param dateStr a valid date string
	 */
	@ParameterizedTest
	@CsvSource({ "1/15/2020", "12/31/2050", "2/29/2020" })
	public void testDateStringValid(String dateStr) {
		Date d = assertDoesNotThrow(() -> new Date(dateStr), "Should not throw exception");

		String[] parts = dateStr.split("/");
		assertAll("Date", () -> assertEquals(Integer.parseInt(parts[0]), d.getMonth(), "incorrect month"),
				() -> assertEquals(Integer.parseInt(parts[1]), d.getDay(), "incorrect day"),
				() -> assertEquals(Integer.parseInt(parts[2]), d.getYear(), "incorrect year"));
	}

	/**
	 * Tests constructing a Date from an invalid date.
	 * 
	 * @param dateStr an invalid date string
	 */
	@ParameterizedTest
	@ValueSource(strings = { "13/15/2020", "1/32/2020", "1/15/1999", "2020/1/15", "1-15-2020" })
	public void testDateStringInvalid(String dateStr) {
		assertThrows(IllegalArgumentException.class, () -> new Date(dateStr),
				"Should throw exception for invalid date string");
	}

	/**
	 * Tests isValidDate method with valid dates.
	 * 
	 * @param month the month in a date
	 * @param day   the day in a date
	 * @param year  the year in a date
	 */
	@ParameterizedTest
	@CsvSource({ "1, 15, 2020", "12, 31, 2050", "2, 29, 2020" })
	public void testIsValidDateValid(int month, int day, int year) {
		assertTrue(Date.isValidDate(month, day, year), "Date should be valid");
	}

	/**
	 * Tests isValidDate method with invalid dates.
	 * 
	 * @param month the month in a date
	 * @param day   the day in a date
	 * @param year  the year in a date
	 */
	@ParameterizedTest
	@CsvSource({ "13, 15, 2020", "1, 32, 2020", "1, 15, 1999", "0, 15, 2020", "1, 0, 2020" })
	public void testIsValidDateInvalid(int month, int day, int year) {
		assertFalse(Date.isValidDate(month, day, year), "Date should be invalid");
	}

	/**
	 * Tests isValidDate with valid date strings.
	 * 
	 * @param dateStr a valid date string
	 */
	@ParameterizedTest
	@CsvSource({ "1/15/2020", "12/31/2050", "2/29/2020" })
	public void testIsValidDateStringValid(String dateStr) {
		assertTrue(Date.isValidDate(dateStr), "Date string should be valid");
	}

	/**
	 * Tests isValidDate with invalid date strings.
	 * 
	 * @param dateStr an invalid date string
	 */
	@ParameterizedTest
	@ValueSource(strings = { "13/15/2020", "1/32/2020", "1/15/1999", "2020/1/15", "1-15-2020" })
	public void testIsValidDateStringInvalid(String dateStr) {
		assertFalse(Date.isValidDate(dateStr), "Date string should be invalid");
	}

	/**
	 * Tests compareTo method.
	 */
	@Test
	public void testCompareTo() {
		Date d1 = new Date(1, 15, 2020);
		Date d2 = new Date(1, 15, 2020);
		Date d3 = new Date(2, 15, 2020);
		Date d4 = new Date(1, 16, 2020);
		Date d5 = new Date(1, 15, 2021);

		assertEquals(0, d1.compareTo(d2), "Dates should be equal");
		assertTrue(d1.compareTo(d3) < 0, "d1 should be less than d3");
		assertTrue(d1.compareTo(d4) < 0, "d1 should be less than d4");
		assertTrue(d1.compareTo(d5) < 0, "d1 should be less than d5");
		assertTrue(d3.compareTo(d1) > 0, "d3 should be greater than d1");
		assertTrue(d4.compareTo(d1) > 0, "d4 should be greater than d1");
		assertTrue(d5.compareTo(d1) > 0, "d5 should be greater than d1");
	}

	/**
	 * Tests toString method.
	 */
	@Test
	public void testToString() {
		Date d = new Date(1, 15, 2020);
		assertEquals("1/15/2020", d.toString(), "Incorrect toString output");
	}

	/**
	 * Tests equals method.
	 */
	@Test
	public void testEqualsObject() {
		Date d1 = new Date(1, 15, 2020);
		Date d2 = new Date(1, 15, 2020);
		Date d3 = new Date(2, 15, 2020);
		Date d4 = new Date(1, 16, 2020);
		Date d5 = new Date(1, 15, 2021);

		assertEquals(d1, d2, "Dates should be equal");
		assertNotEquals(d1, d3, "Dates should not be equal");
		assertNotEquals(d1, d4, "Dates should not be equal");
		assertNotEquals(d1, d5, "Dates should not be equal");
	}

	/**
	 * Tests hashCode method.
	 */
	@Test
	public void testHashCode() {
		Date d1 = new Date(1, 15, 2020);
		Date d2 = new Date(1, 15, 2020);
		Date d3 = new Date(2, 15, 2020);

		assertEquals(d1.hashCode(), d2.hashCode(), "Hash codes should be equal");
		assertNotEquals(d1.hashCode(), d3.hashCode(), "Hash codes should not be equal");
	}

	/**
	 * Tests daysTo method.
	 */
	@Test
	public void testDaysTo() {
		Date d1 = new Date(1, 1, 2020);
		Date d2 = new Date(1, 2, 2020);
		Date d3 = new Date(12, 31, 2020);

		assertEquals(1, d1.daysTo(d2), "Should be 1 day difference");
		assertEquals(364, d1.daysTo(d3), "Should be 364 days difference");
		assertEquals(-1, d2.daysTo(d1), "Should be -1 day difference");
	}

	/**
	 * Tests yearsTo method.
	 */
	@Test
	public void testYearsTo() {
		Date d1 = new Date(1, 1, 2020);
		Date d2 = new Date(1, 1, 2021);
		Date d3 = new Date(1, 1, 2019);

		assertEquals(1, d1.yearsTo(d2), "Should be 1 year difference");
		assertEquals(-1, d1.yearsTo(d3), "Should be -1 year difference");
		assertEquals(0, d1.yearsTo(new Date(12, 31, 2020)), "Should be 0 year difference");
	}

}