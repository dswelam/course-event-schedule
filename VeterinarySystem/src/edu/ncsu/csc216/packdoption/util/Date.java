package edu.ncsu.csc216.packdoption.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that represents a date as a month, a day, and a year (M/D/YYYY).
 * Implements the Comparable interface.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class Date implements Comparable<Date> {

	/** Field for month in date. */
	private int month;
	/** Field for day in date. */
	private int day;
	/** Field for year in date. */
	private int year;

	/**
	 * Constructs a Date with specified month, day, and year.
	 * 
	 * @param month the month (1-12)
	 * @param day   the day (1-31)
	 * @param year  the year (2000-2050)
	 * @throws IllegalArgumentException with “Invalid date” message if month, day,
	 *                                  and year do not form a valid date.
	 */
	public Date(int month, int day, int year) {

		if (!isValidDate(month, day, year)) {
			throw new IllegalArgumentException("Invalid date");
		}

		this.month = month;
		this.day = day;
		this.year = year;
	}

	/**
	 * Constructs a Date from a string in the format M/D/YYYY. Note that for this
	 * method, any month and day can be listed with two digits. For example, all of
	 * the following are valid: 10/8/2019, 10/18/2019, 9/7/2020, 9/21/2020, and
	 * 09/07/2020.
	 * 
	 * @param date the date string
	 * @throws IllegalArgumentException with “Invalid date” message if month, day,
	 *                                  and year do not form a valid date.
	 */
	public Date(String date) {

		if (!isValidDate(date)) {
			throw new IllegalArgumentException("Invalid date");
		}

		String[] parts = date.split("/");
		this.month = Integer.parseInt(parts[0]);
		this.day = Integer.parseInt(parts[1]);
		this.year = Integer.parseInt(parts[2]);
	}

	/**
	 * Returns the month of the date.
	 * 
	 * @return the month (1-12)
	 */
	public int getMonth() {

		return month;
	}

	/**
	 * Returns the day of the date.
	 * 
	 * @return the day (1-31)
	 */
	public int getDay() {

		return day;
	}

	/**
	 * Returns the year of the date.
	 * 
	 * @return the year (2000-2050)
	 */
	public int getYear() {

		return year;
	}

	/**
	 * Checks if the given parameters form a valid date.
	 * 
	 * @param month the month
	 * @param day   the day
	 * @param year  the year
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidDate(int month, int day, int year) {

		if (month < 1 || month > 12 || day < 1 || day > 31 || year < 2000 || year > 2050) {
			return false;
		}

		boolean isLeapYear = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
		int[] daysInMonth = { 31, isLeapYear ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		return day <= daysInMonth[month - 1];
	}

	/**
	 * Checks if the given parameters form a valid date.
	 * 
	 * @param date valid date
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidDate(String date) {

		String regex = "^(\\d{1,2})/(\\d{1,2})/(\\d{4})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(date);

		if (!matcher.matches()) {
			return false;
		}

		int month = Integer.parseInt(matcher.group(1));
		int day = Integer.parseInt(matcher.group(2));
		int year = Integer.parseInt(matcher.group(3));

		return isValidDate(month, day, year);
	}

	/**
	 * Compares this object with the specified object for order. Returns a negative
	 * integer, zero, or a positive integer as this object is less than, equal to,
	 * or greater than the specified object.
	 * 
	 * @param o the Date to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Date o) {

		if (this.year != o.year) {
			if (this.year > o.year) {
				return 1;
			} else if (this.year < o.year) {
				return -1;
			}
		} else if (this.month != o.month) {
			if (this.month > o.month) {
				return 1;
			} else if (this.month < o.month) {
				return -1;
			}
		} else if (this.day != o.day) {
			if (this.day > o.day) {
				return 1;
			} else if (this.day < o.day) {
				return -1;
			}
		}
		return 0;
	}

	/**
	 * Returns a string representation of the date in the format M/D/YYYY.
	 * 
	 * @return the string representation of the date.
	 */
	@Override
	public String toString() {

		return month + "/" + day + "/" + year;

	}

	/**
	 * Generates a hash code for the Date object.
	 * 
	 * @return the hash code for the Date object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	/**
	 * Compares this Date object to another object for equality.
	 * 
	 * @param obj the object to compare to.
	 * @return true if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Date other = (Date) obj;
		return day == other.day && month == other.month && year == other.year;
	}

	/**
	 * Returns the number of days between this date and another date. Number will be
	 * negative if other is before this.
	 * 
	 * @param other the number of days between dates.
	 * @return the number of days
	 */
	public int daysTo(Date other) {

		int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int thisDays = 0;
		int otherDays = 0;

		// Calculate days for 'this' date
		thisDays += (this.year - 2000) * 365;
		for (int i = 0; i < this.month - 1; i++) {
			thisDays += daysInMonth[i];
		}
		thisDays += this.day;

		// Calculate days for 'other' date
		otherDays += (other.year - 2000) * 365;
		for (int i = 0; i < other.month - 1; i++) {
			otherDays += daysInMonth[i];
		}
		otherDays += other.day;

		return otherDays - thisDays;
	}

	/**
	 * Returns the number of years between this date and another date. Only reports
	 * full years. For example, years between 11/8/2018 and 11/7/2019 would return
	 * 0, and years between 11/8/2018 and 11/8/2019 would return 1. Number will be
	 * negative if other is at least a year before this.
	 * 
	 * @param other the other date
	 * @return the number of years
	 */
	public int yearsTo(Date other) {

		return daysTo(other) / 365;

	}

}