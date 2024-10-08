package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Test class for Animal class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class AnimalTest {

	@BeforeEach
	void setUp() {
		final SortedLinkedList<Note> notes = new SortedLinkedList<>();
		notes.add(new Note(new Date(1, 2, 2020), "Initial checkup"));
	}

	/**
	 * Test method for the Animal constructor with parameters.
	 */
	@Test
	void testAnimalConstructor() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);

		// Test valid construction
		assertDoesNotThrow(() -> new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue,
				false, null, null, Dog.Breed.BEAGLE));
		assertDoesNotThrow(() -> new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue,
				false, null, null));

		// Test invalid name
		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> new Dog("", birthday,
				Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null, Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Invalid name", e1.getMessage());

		IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
				() -> new Cat("", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null));
		assertEquals("Cannot add: Invalid name", e2.getMessage());

		// Test invalid birthday
		IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class, () -> new Dog("Luna", null,
				Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null, Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Invalid birthday", e3.getMessage());

		// Test invalid size
		IllegalArgumentException e4 = assertThrows(IllegalArgumentException.class, () -> new Dog("Luna", birthday, null,
				true, true, notes, dateEnterRescue, false, null, null, Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Invalid size", e4.getMessage());

		// Test invalid notes
		IllegalArgumentException e5 = assertThrows(IllegalArgumentException.class, () -> new Dog("Luna", birthday,
				Animal.Size.MEDIUM, true, true, null, dateEnterRescue, false, null, null, Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Invalid note", e5.getMessage());

		// Test invalid dateEnterRescue
		IllegalArgumentException e6 = assertThrows(IllegalArgumentException.class, () -> new Dog("Luna", birthday,
				Animal.Size.MEDIUM, true, true, notes, new Date(1, 1, 2014), false, null, null, Dog.Breed.BEAGLE));
		assertEquals("Date of adoption cannot be before date of entry into rescue", e6.getMessage());

		// Test adopted with null dateAdopted
		IllegalArgumentException e7 = assertThrows(IllegalArgumentException.class, () -> new Dog("Luna", birthday,
				Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, true, null, "John Doe", Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Animal is adopted but owner or adoption date is missing", e7.getMessage());

		// Test adopted with null owner
		IllegalArgumentException e8 = assertThrows(IllegalArgumentException.class, () -> new Dog("Luna", birthday,
				Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, true, dateEnterRescue, null, Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Animal is adopted but owner or adoption date is missing", e8.getMessage());

		// Test not adopted with non-null dateAdopted and owner
		IllegalArgumentException e9 = assertThrows(IllegalArgumentException.class,
				() -> new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false,
						dateEnterRescue, "John Doe", Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Animal is not adopted but has an owner and adoption date", e9.getMessage());

		// Test dateAdopted before dateEnterRescue
		IllegalArgumentException e10 = assertThrows(IllegalArgumentException.class,
				() -> new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, true,
						new Date(1, 1, 2019), "John Doe", Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Invalid date entered rescue", e10.getMessage());

		// Test invalid owner
		IllegalArgumentException e11 = assertThrows(IllegalArgumentException.class, () -> new Dog("Luna", birthday,
				Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, true, dateEnterRescue, "", Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Invalid owner", e11.getMessage());
	}

	/**
	 * Test method for the Animal constructor without owner parameters.
	 */
	@Test
	void testAnimalConstructorWithoutOwner() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);

		// Test valid construction
		assertDoesNotThrow(() -> new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue,
				Dog.Breed.BEAGLE));
		assertDoesNotThrow(() -> new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue));

		// Test invalid name
		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
				() -> new Dog("", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, Dog.Breed.BEAGLE));
		assertEquals("Cannot add: Invalid name", e1.getMessage());

		IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
				() -> new Cat("", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue));
		assertEquals("Cannot add: Invalid name", e2.getMessage());
	}

	/**
	 * Test method for getName().
	 */
	@Test
	void testGetName() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertEquals("Luna", d.getName());
		assertEquals("Rosie", c.getName());
	}

	/**
	 * Test method for getBirthday().
	 */
	@Test
	void testGetBirthday() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertEquals(birthday, d.getBirthday());
		assertEquals(birthday, c.getBirthday());
	}

	/**
	 * Test method for getDateEnterRescue().
	 */
	@Test
	void testGetDateEnterRescue() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertEquals(dateEnterRescue, d.getDateEnterRescue());
		assertEquals(dateEnterRescue, c.getDateEnterRescue());
	}

	/**
	 * Test method for getDateAdopted().
	 */
	@Test
	void testGetDateAdopted() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Date dateAdopted = new Date(1, 2, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, true, dateAdopted,
				"John Doe", Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, true, dateAdopted,
				"Jane Doe");

		assertEquals(dateAdopted, d.getDateAdopted());
		assertEquals(dateAdopted, c.getDateAdopted());
	}

	/**
	 * Test method for getOwner().
	 */
	@Test
	void testGetOwner() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Date dateAdopted = new Date(1, 2, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, true, dateAdopted,
				"Michael", Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, true, dateAdopted,
				"Dania");

		assertEquals("Michael", d.getOwner());
		assertEquals("Dania", c.getOwner());
	}

	/**
	 * Test method for getNotes().
	 */
	@Test
	void testGetNotes() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Note note1 = new Note(new Date(1, 1, 2020), "First note");
		Note note2 = new Note(new Date(1, 2, 2020), "Second note");
		notes.add(note1);
		notes.add(note2);
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertEquals(notes, d.getNotes());
		assertEquals(notes, c.getNotes());
	}

	/**
	 * Test method for getSize().
	 */
	@Test
	void testGetSize() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertEquals(Animal.Size.MEDIUM, d.getSize());
		assertEquals(Animal.Size.SMALL, c.getSize());
	}

	/**
	 * Test method for isGoodWithKids().
	 */
	@Test
	void testIsGoodWithKids() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertTrue(d.isGoodWithKids());
		assertTrue(c.isGoodWithKids());
	}

	/**
	 * Test method for isHouseTrained().
	 */
	@Test
	void testIsHouseTrained() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertTrue(d.isHouseTrained());
		assertTrue(c.isHouseTrained());
	}

	/**
	 * Test method for adopted().
	 */
	@Test
	void testAdopted() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertFalse(d.adopted());
		assertFalse(c.adopted());
	}

	/**
	 * Test method for setAdoptionInfo(boolean, Date, String).
	 */
	@Test
	void testSetAdoptionInfo() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Date dateAdopted = new Date(1, 2, 2020);
		Animal d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, true, dateAdopted,
				"Michael", Dog.Breed.BEAGLE);
		Animal c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, true, dateAdopted,
				"Dania");

		d.setAdoptionInfo(true, dateAdopted, "Michael");
		assertTrue(d.adopted());
		assertEquals(dateAdopted, d.getDateAdopted());
		assertEquals("Michael", d.getOwner());

		c.setAdoptionInfo(true, dateAdopted, "Dania");
		assertTrue(c.adopted());
		assertEquals(dateAdopted, c.getDateAdopted());
		assertEquals("Dania", c.getOwner());

		// Test invalid adoption info
		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> {
			d.setAdoptionInfo(true, null, "Michael");
		});
		assertEquals("Cannot add: Animal is adopted but owner or adoption date is missing", e1.getMessage());

		IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> {
			d.setAdoptionInfo(true, dateAdopted, null);
		});
		assertEquals("Cannot add: Animal is adopted but owner or adoption date is missing", e2.getMessage());

		IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class, () -> {
			d.setAdoptionInfo(false, dateAdopted, null);
		});
		assertEquals("Cannot add: Animal is not adopted but has an owner and adoption date", e3.getMessage());

		IllegalArgumentException e6 = assertThrows(IllegalArgumentException.class, () -> {
			d.setAdoptionInfo(false, null, "Michael");
		});
		assertEquals("Cannot add: Animal is not adopted but has an owner and adoption date", e6.getMessage());

		IllegalArgumentException e4 = assertThrows(IllegalArgumentException.class, () -> {
			d.setAdoptionInfo(true, new Date(1, 1, 2019), "Michael");
		});
		assertEquals("Cannot add: Invalid date entered rescue", e4.getMessage());

		IllegalArgumentException e5 = assertThrows(IllegalArgumentException.class, () -> {
			d.setAdoptionInfo(true, dateAdopted, "");
		});
		assertEquals("Cannot add: Invalid owner", e5.getMessage());
	}

	/**
	 * Test method for setSize(Size).
	 */
	@Test
	void testSetSize() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		d.setSize(Animal.Size.LARGE);
		assertEquals(Animal.Size.LARGE, d.getSize());

		c.setSize(Animal.Size.MEDIUM);
		assertEquals(Animal.Size.MEDIUM, c.getSize());

		// Test invalid size
		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> {
			d.setSize(null);
		});
		assertEquals("Cannot add: Invalid size", e1.getMessage());
	}

	/**
	 * Test method for addNote(Note).
	 */
	@Test
	void testAddNote() {
		final SortedLinkedList<Note> notes = new SortedLinkedList<>();
		notes.add(new Note(new Date(1, 2, 2020), "Initial checkup"));

		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);

		Animal dog = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null,
				null, Dog.Breed.BEAGLE);
		Animal cat = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null,
				null);

		Note newNote = new Note(new Date(1, 3, 2020), "New note");
		Note newNote1 = new Note(new Date(1, 3, 2020), "Another new note");

		assertTrue(dog.addNote(newNote));
		assertTrue(dog.getNotes().contains(newNote));

		assertTrue(cat.addNote(newNote1));
		assertTrue(cat.getNotes().contains(newNote1));

		assertThrows(IllegalArgumentException.class, () -> dog.addNote(newNote)); // Adding duplicate note
		assertThrows(IllegalArgumentException.class, () -> dog.addNote(null)); // Adding null note

		assertThrows(IllegalArgumentException.class, () -> cat.addNote(newNote)); // Adding duplicate note
		assertThrows(IllegalArgumentException.class, () -> cat.addNote(null)); // Adding null note
	}

	/**
	 * Test method for equals(Object).
	 */
	@Test
	void testEqualsObject() {
		SortedLinkedList<Note> notes1 = new SortedLinkedList<>();
		SortedLinkedList<Note> notes2 = new SortedLinkedList<>();
		Date birthday1 = new Date(1, 1, 2015);
		Date birthday2 = new Date(1, 1, 2015);
		Date dateEnterRescue1 = new Date(1, 1, 2020);
		Date dateEnterRescue2 = new Date(1, 1, 2020);
		Date dateAdopted1 = new Date(1, 5, 2020);
		Date dateAdopted2 = new Date(1, 5, 2020);

		Dog d1 = new Dog("Luna", birthday1, Animal.Size.MEDIUM, true, true, notes1, dateEnterRescue1, true,
				dateAdopted1, "Michael", Dog.Breed.BEAGLE);
		Dog d2 = new Dog("Luna", birthday2, Animal.Size.MEDIUM, true, true, notes2, dateEnterRescue2, true,
				dateAdopted2, "Michael", Dog.Breed.BEAGLE);
		Cat c1 = new Cat("Rosie", birthday1, Animal.Size.SMALL, true, true, notes1, dateEnterRescue1, true,
				dateAdopted1, "Dania");
		Cat c2 = new Cat("Rosie", birthday2, Animal.Size.SMALL, true, true, notes2, dateEnterRescue2, true,
				dateAdopted2, "Dania");

		assertEquals(d1, d2);
		assertEquals(c1, c2);

		// Test unequal cases
		Dog d3 = new Dog("Lady", birthday1, Animal.Size.MEDIUM, true, true, notes1, dateEnterRescue1, false, null, null,
				Dog.Breed.BEAGLE);
		assertNotEquals(d1, d3);

		Cat c3 = new Cat("Charlie", birthday1, Animal.Size.SMALL, true, true, notes1, dateEnterRescue1, false, null,
				null);
		assertNotEquals(c1, c3);
		assertNotEquals(d1, new Object());

		assertNotNull(d1);
	}

	/**
	 * Test method for compareTo(Animal).
	 */
	@Test
	void testCompareTo() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday1 = new Date(1, 1, 2015);
		Date birthday2 = new Date(1, 2, 2015);
		Date birthday3 = new Date(1, 1, 2016);
		Date dateEnterRescue = new Date(1, 1, 2020);

		Dog d1 = new Dog("Luna", birthday1, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Dog d2 = new Dog("Lady", birthday1, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Dog d3 = new Dog("Luna", birthday2, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Dog d4 = new Dog("Luna", birthday3, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c1 = new Cat("Rosie", birthday1, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);
		Cat c2 = new Cat("Charlie", birthday1, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null,
				null);
		Cat c3 = new Cat("Rosie", birthday2, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);
		Cat c4 = new Cat("Rosie", birthday3, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		// Same birthday, different names
		assertTrue(d1.compareTo(d2) > 0);
		assertTrue(d2.compareTo(d1) < 0);
		assertTrue(c1.compareTo(c2) > 0);
		assertTrue(c2.compareTo(c1) < 0);

		// Different birthdays, with older birthday treated as greater
		assertTrue(d1.compareTo(d3) < 0);
		assertTrue(d3.compareTo(d1) > 0);
		assertTrue(d1.compareTo(d4) < 0);
		assertTrue(d4.compareTo(d1) > 0);

		assertTrue(c1.compareTo(c3) < 0);
		assertTrue(c3.compareTo(c1) > 0);
		assertTrue(c1.compareTo(c4) < 0);
		assertTrue(c4.compareTo(c1) > 0);

		assertThrows(NullPointerException.class, () -> d1.compareTo(null));
	}

	/**
	 * Test method for toString().
	 */
	@Test
	void testToString() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Note note1 = new Note(new Date(1, 1, 2020), "First note");
		notes.add(note1);
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertEquals("Luna (1/1/2015)\n-1/1/2020 First note", d.toString());
		assertEquals("Rosie (1/1/2015)\n-1/1/2020 First note", c.toString());
	}

	/**
	 * Test method for getAge(Date).
	 */
	@Test
	void testGetAge() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Date today = new Date(1, 1, 2021);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertEquals(6, d.getAge(today));
		assertEquals(6, c.getAge(today));

		Date invalidDate = new Date(1, 1, 2014);
		assertThrows(IllegalArgumentException.class, () -> {
			d.getAge(invalidDate);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			d.getAge(null);
		});
	}

	/**
	 * Test method for getDaysAvailableForAdoption(Date).
	 */
	@Test
	void testGetDaysAvailableForAdoption() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Date today = new Date(1, 1, 2021);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertEquals(365, d.getDaysAvailableForAdoption(today));
		assertEquals(365, c.getDaysAvailableForAdoption(today));

		d.setAdoptionInfo(true, new Date(1, 1, 2021), "Michael");
		assertEquals(-1, d.getDaysAvailableForAdoption(today));

		Date invalidDate = new Date(1, 1, 2014);
		assertThrows(IllegalArgumentException.class, () -> {
			d.getDaysAvailableForAdoption(invalidDate);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			d.getDaysAvailableForAdoption(null);
		});
	}

	/**
	 * Test method for getAgeCategory(Date).
	 */
	@Test
	void testGetAgeCategory() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Date today = new Date(1, 1, 2021);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);

		assertEquals(Animal.AgeCategory.ADULT, d.getAgeCategory(today));
		assertEquals(Animal.AgeCategory.ADULT, c.getAgeCategory(today));

		assertThrows(IllegalArgumentException.class, () -> {
			d.getAgeCategory(null);
		});

		Date invalidDate = new Date(1, 1, 2014);
		assertThrows(IllegalArgumentException.class, () -> {
			d.getAgeCategory(invalidDate);
		});
	}

	/**
	 * Test method for getAnimalAsArray(Date).
	 */
	@Test
	void testGetAnimalAsArray() {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Dog d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c = new Cat("Rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, false, null, null);
		Date today = new Date(1, 1, 2021);

		String[] expectedDog = { "Luna", "Dog", "1/1/2015", "6", "ADULT", "No", "365" };
		assertArrayEquals(expectedDog, d.getAnimalAsArray(today));

		String[] expectedCat = { "Rosie", "Cat", "1/1/2015", "6", "ADULT", "No", "365" };
		assertArrayEquals(expectedCat, c.getAnimalAsArray(today));
	}

	/**
	 * Test method for hashCode().
	 */
	@Test
	void testHashCode() {
		SortedLinkedList<Note> notes1 = new SortedLinkedList<>();
		SortedLinkedList<Note> notes2 = new SortedLinkedList<>();
		Date birthday1 = new Date(1, 1, 2015);
		Date birthday2 = new Date(1, 1, 2015);
		Date dateEnterRescue1 = new Date(1, 1, 2020);
		Date dateEnterRescue2 = new Date(1, 1, 2020);

		Dog d1 = new Dog("Luna", birthday1, Animal.Size.MEDIUM, true, true, notes1, dateEnterRescue1, false, null, null,
				Dog.Breed.BEAGLE);
		Dog d2 = new Dog("Luna", birthday2, Animal.Size.MEDIUM, true, true, notes2, dateEnterRescue2, false, null, null,
				Dog.Breed.BEAGLE);
		Cat c1 = new Cat("Rosie", birthday1, Animal.Size.SMALL, true, true, notes1, dateEnterRescue1, false, null,
				null);
		Cat c2 = new Cat("Rosie", birthday2, Animal.Size.SMALL, true, true, notes2, dateEnterRescue2, false, null,
				null);

		assertEquals(d1.hashCode(), d2.hashCode(), "Dog hash codes should be equal");
		assertEquals(c1.hashCode(), c2.hashCode(), "Cat hash codes should be equal");
	}

}
