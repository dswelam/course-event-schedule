/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Test class for Cat class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class CatTest {

	/** Field for birthday */
	private Date birthday;
	/** Field for date that cat entered rescue */
	private Date dateEnterRescue;
	/** Field for list of notes */
	private SortedLinkedList<Note> notes;
	/** Field for first note */
	private Note note1;
	/** Fielf for second note */
	private Note note2;

	@BeforeEach
	void setUp() {
		birthday = new Date(1, 1, 2015);
		dateEnterRescue = new Date(1, 1, 2020);
		notes = new SortedLinkedList<>();
		note1 = new Note(new Date(1, 2, 2020), "Initial checkup");
		note2 = new Note(new Date(1, 3, 2020), "Vaccination");
		notes.add(note1);
		notes.add(note2);
	}

	/**
	 * Test method for Cat constructor with all parameters.
	 */
	@Test
	void testCatConstructorAllParameters() {
		Cat cat = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertAll("Cat", () -> assertEquals("Rosie", cat.getName()), () -> assertEquals(birthday, cat.getBirthday()),
				() -> assertEquals(Animal.Size.SMALL, cat.getSize()), () -> assertTrue(cat.isHouseTrained()),
				() -> assertTrue(cat.isGoodWithKids()), () -> assertEquals(notes, cat.getNotes()),
				() -> assertEquals(dateEnterRescue, cat.getDateEnterRescue()), () -> assertFalse(cat.adopted()),
				() -> assertNull(cat.getDateAdopted()), () -> assertNull(cat.getOwner()));
	}

	/**
	 * Test method for Cat constructor without adoption information.
	 */
	@Test
	void testCatConstructorWithoutAdoptionInfo() {
		Cat cat = new Cat("Charlie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		assertAll("Cat", () -> assertEquals("Charlie", cat.getName()), () -> assertEquals(birthday, cat.getBirthday()),
				() -> assertEquals(Animal.Size.SMALL, cat.getSize()), () -> assertTrue(cat.isHouseTrained()),
				() -> assertTrue(cat.isGoodWithKids()), () -> assertEquals(notes, cat.getNotes()),
				() -> assertEquals(dateEnterRescue, cat.getDateEnterRescue()), () -> assertFalse(cat.adopted()),
				() -> assertNull(cat.getDateAdopted()), () -> assertNull(cat.getOwner()));
	}

	/**
	 * Test method for getAgeCategory(Date today).
	 */
	@Test
	void testGetAgeCategory() {
		Cat cat = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		assertThrows(IllegalArgumentException.class, () -> cat.getAgeCategory(new Date(12, 31, 2014)));
		assertEquals(Animal.AgeCategory.YOUNG, cat.getAgeCategory(new Date(12, 31, 2018)));
		assertEquals(Animal.AgeCategory.ADULT, cat.getAgeCategory(new Date(12, 31, 2022)));
		assertEquals(Animal.AgeCategory.SENIOR, cat.getAgeCategory(new Date(12, 31, 2024)));
	}

	/**
	 * Test method for getAnimalAsArray(Date today).
	 */
	@Test
	void testGetAnimalAsArray() {
		Cat cat = new Cat("Charlie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		assertThrows(IllegalArgumentException.class, () -> cat.getAnimalAsArray(new Date(12, 31, 2014)));

		String[] expectedArray = { "Charlie", "Cat", "1/1/2015", "9", "SENIOR", "No", "1460" };
		assertArrayEquals(expectedArray, cat.getAnimalAsArray(new Date(1, 1, 2024)));

		cat.setAdoptionInfo(true, new Date(1, 1, 2023), "Alice");
		expectedArray = new String[] { "Charlie", "Cat", "1/1/2015", "9", "SENIOR", "Yes", "" };
		assertArrayEquals(expectedArray, cat.getAnimalAsArray(new Date(1, 1, 2024)));
	}

	/**
	 * Test method for addNote method.
	 */
	@Test
	void testAddNote() {
		Cat cat = new Cat("Roie", birthday, Animal.Size.SMALL, true, true, new SortedLinkedList<>(), dateEnterRescue);

		Note note3 = new Note(new Date(1, 4, 2020), "Spaying");
		assertTrue(cat.addNote(note3));
		assertEquals(1, cat.getNotes().size());
		assertEquals(note3, cat.getNotes().get(0));

		Note note4 = new Note(new Date(1, 5, 2020), "Checkup");
		assertTrue(cat.addNote(note4));
		assertEquals(2, cat.getNotes().size());
		assertEquals(note3, cat.getNotes().get(0));
		assertEquals(note4, cat.getNotes().get(1));

		assertThrows(IllegalArgumentException.class, () -> cat.addNote(note3)); // Adding duplicate note
		assertThrows(IllegalArgumentException.class, () -> cat.addNote(null)); // Adding null note
	}

}
