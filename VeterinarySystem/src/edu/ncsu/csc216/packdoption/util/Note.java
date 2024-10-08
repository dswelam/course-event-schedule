package edu.ncsu.csc216.packdoption.util;

/**
 * A class that represents a note as a date and a message. Implements the
 * Comparable interface.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class Note implements Comparable<Note> {

	/** Field for date of note */
	private Date date;
	/** Field for message in note */
	private String message;

	/**
	 * Constructs a Note with the specified date and message.
	 * 
	 * @param date    the date of the note
	 * @param message the message of the note
	 * @throws IllegalArgumentException if with message “Invalid note” if (a) date
	 *                                  is null, (b) message is null, (c) message is
	 *                                  whitespace only, or (d) message contains \n
	 *                                  or ,. message should be trimmed of leading
	 *                                  and/or trailing whitespace.
	 */
	public Note(Date date, String message) {

		if (date == null || message == null || message.contains("\n") || message.contains(",")
				|| message.trim().isEmpty()) {
			throw new IllegalArgumentException("Invalid note");
		}

		this.message = message.trim();
		this.date = date;
	}

	/**
	 * Compares this object with the specified object for order. Returns a negative
	 * integer, zero, or a positive integer as this object is less than, equal to,
	 * or greater than the specified object.
	 * 
	 * @param other the Note to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Note other) {

		if (other == null) {
			throw new NullPointerException();
		}

		int dateComparison = this.getDate().compareTo(other.getDate());
		if (dateComparison != 0) {
			return dateComparison;
		}

		return this.getMessage().compareTo(other.getMessage());
	}

	/**
	 * Returns Note as a string with the format date message. For example,
	 * “10/3/2019 a note”.
	 * 
	 * @return the string representation of the Note.
	 */
	@Override
	public String toString() {

		return date + " " + message;

	}

	/**
	 * Returns message in note.
	 * 
	 * @return message in given note.
	 */
	public String getMessage() {
		return message;

	}

	/**
	 * Returns date of note.
	 * 
	 * @return the date of the note.
	 */
	public Date getDate() {
		return date;

	}

	/**
	 * Generates a hash code for the Note object.
	 * 
	 * @return the hash code for the Note object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	/**
	 * Compares this Note object to another object for equality.
	 * 
	 * @param obj the object to compare to.
	 * @return true if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}
