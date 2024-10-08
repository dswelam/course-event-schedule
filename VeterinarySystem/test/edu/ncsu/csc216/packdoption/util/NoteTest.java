/**
 * 
 */
package edu.ncsu.csc216.packdoption.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Note class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class NoteTest {

	/** Field for date */
	private Date date;
	/** Field for valid message string */
	private String validMessage;
	/** Field for invalid message number 1 */
	private String invalidMessage1;
	/** Filed for invalid message number 2 */
	private String invalidMessage2;

	@BeforeEach
	void setUp() {
		date = new Date(10, 3, 2019);
		validMessage = "This is a valid note";
		invalidMessage1 = "Invalid\nMessage";
		invalidMessage2 = "Invalid,Message";
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.packdoption.util.Note#hashCode()}.
	 */
	@Test
	void testHashCode() {
		Note note1 = new Note(date, validMessage);
		Note note2 = new Note(date, validMessage);
		assertEquals(note1.hashCode(), note2.hashCode());

		Note note3 = new Note(new Date(10, 4, 2019), validMessage);
		assertNotEquals(note1.hashCode(), note3.hashCode());

		Note note4 = new Note(date, "I love Rosie");
		assertNotEquals(note1.hashCode(), note4.hashCode());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.util.Note#Note(edu.ncsu.csc216.packdoption.util.Date, java.lang.String)}.
	 */
	@Test
	void testNote() {
		assertDoesNotThrow(() -> new Note(date, validMessage));

		assertThrows(IllegalArgumentException.class, () -> new Note(null, validMessage));
		assertThrows(IllegalArgumentException.class, () -> new Note(date, null));
		assertThrows(IllegalArgumentException.class, () -> new Note(date, "   "));
		assertThrows(IllegalArgumentException.class, () -> new Note(date, invalidMessage1));
		assertThrows(IllegalArgumentException.class, () -> new Note(date, invalidMessage2));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.util.Note#compareTo(edu.ncsu.csc216.packdoption.util.Note)}.
	 */
	@Test
	void testCompareTo() {
		Note note1 = new Note(date, validMessage);
		Note note2 = new Note(date, validMessage);
		assertEquals(0, note1.compareTo(note2));

		Note note3 = new Note(new Date(10, 4, 2019), validMessage);
		assertTrue(note1.compareTo(note3) < 0);
		assertTrue(note3.compareTo(note1) > 0);

		Note note4 = new Note(date, "This is a valid note");
		assertEquals(note1.compareTo(note4), 0);
		assertEquals(note4.compareTo(note1), 0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.packdoption.util.Note#toString()}.
	 */
	@Test
	void testToString() {
		Note note = new Note(date, validMessage);
		assertEquals("10/3/2019 This is a valid note", note.toString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.util.Note#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		Note note1 = new Note(date, validMessage);
		Note note2 = new Note(date, validMessage);
		assertTrue(note1.equals(note2));
		assertTrue(note2.equals(note1));

		Date differentDate = new Date(1, 5, 2021);
		Note note3 = new Note(differentDate, validMessage);
		assertFalse(note1.equals(note3));

		Note note4 = new Note(date, "I love Rosie and Charlie");
		assertFalse(note1.equals(note4));

	}

}
