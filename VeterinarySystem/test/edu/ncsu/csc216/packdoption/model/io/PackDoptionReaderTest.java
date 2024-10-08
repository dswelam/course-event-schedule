/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Test class for PackDoptionReader class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class PackDoptionReaderTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.io.PackDoptionReader#readRescueListFile(java.lang.String)}.
	 */
	@Test
	void testReadRescueListFile() {
		RescueList list = new RescueList();
		Date birthday = new Date("3/14/2004");
		Date enteredRescueDate = new Date("4/8/2004");
		Date adopted = new Date("5/13/2004");
		Date birthday2 = new Date("5/14/2011");
		Date enteredRescueDate2 = new Date("6/23/2011");
		Date birthday3 = new Date("2/20/2019");
		Date enteredRescueDate3 = new Date("7/8/2019");
		Date adopted2 = new Date("7/26/2019");
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();

		Cat loki = new Cat("Loki", birthday, Size.SMALL, false, true, notes, enteredRescueDate, true, adopted,
				"Ethan and Family");
		Cat lily = new Cat("Lily", birthday2, Size.SMALL, false, true, notes, enteredRescueDate2);
		Cat leo = new Cat("Leo", birthday3, Size.SMALL, true, false, notes, enteredRescueDate3, true, adopted2,
				"Mason and Family");

		Dog jack1 = new Dog("Jack", new Date("3/9/2001"), Size.LARGE, true, true, notes, new Date("6/1/2001"),
				Breed.POINTER_GERMAN_SHORTHAIRED);
		Dog duke = new Dog("Duke", new Date("2/29/2004"), Size.LARGE, true, true, notes, new Date("7/21/2004"), true,
				new Date("7/28/2004"), "Olivia and Family", Breed.ROTTWEILER);
		Cat jack2 = new Cat("Jack", new Date("4/21/2011"), Size.MEDIUM, false, true, notes, new Date("8/27/2011"));
		Cat george = new Cat("George", new Date("6/25/2011"), Size.MEDIUM, true, true, notes, new Date("8/12/2011"),
				true, new Date("8/12/2011"), "James and Family");

		list = PackDoptionReader.readRescueListFile("test-files/rescue_records.md");

		assertEquals("Ms. Wuf's Rescue", list.getRescue(0).getName());
		assertEquals("NCSU Rescue", list.getRescue(1).getName());
		assertEquals(2, list.size());
		assertTrue(list.getRescue(0).getAnimal(0).equals(loki));
		assertTrue(list.getRescue(0).getAnimal(1).equals(lily));
		assertTrue(list.getRescue(0).getAnimal(2).equals(leo));

		assertTrue(list.getRescue(1).getAnimal(0).equals(jack1));
		assertTrue(list.getRescue(1).getAnimal(1).equals(duke));
		assertTrue(list.getRescue(1).getAnimal(2).equals(jack2));
		assertTrue(list.getRescue(1).getAnimal(3).equals(george));

	}

	/**
	 * Tests that an IllegalArgumentException is thrown when the file does not
	 * exist.
	 */
	@Test
	public void testFileNotFound() {
		assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/nonexistentfile.md");
		});
	}

	/**
	 * Tests that an IllegalArgumentException is thrown when the rescue line is
	 * invalid.
	 */
	@Test
	public void testInvalidRescueLine() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/invalid_rescue_line.md");
		});

		assertEquals("Unable to load file.", e.getMessage());
	}

	/**
	 * Tests that an IllegalArgumentException is thrown when the animal data is
	 * invalid.
	 */
	@Test
	public void testInvalidAnimalData() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/invalid_animal_data.md");
		});

		assertEquals("Unable to load file.", e.getMessage());
	}

	/**
	 * Tests that an IllegalArgumentException is thrown when an animal entry appears
	 * before any rescue is defined.
	 */
	@Test
	public void testAnimalBeforeRescue() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/animal_before_rescue.md");
		});

		assertEquals("Unable to load file.", e.getMessage());
	}

	/**
	 * Tests that an IllegalArgumentException is thrown when an appointment entry
	 * appears before any rescue is defined.
	 */
	@Test
	public void testAppointmentBeforeRescue() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/appointment_before_rescue.md");
		});

		assertEquals("Unable to load file.", e.getMessage());
	}

	/**
	 * Tests that an IllegalArgumentException is thrown when the data is incorrectly
	 * formatted.
	 */
	@Test
	public void testIncorrectlyFormattedData() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/incorrectly_formatted_data.md");
		});

		assertEquals("Unable to load file.", e.getMessage());
	}

	/**
	 * Tests that an IllegalArgumentException is thrown when animal size is invalid.
	 */
	@Test
	public void testInvalidAnimalSize() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/invalid_animal_size.md");
		});

		assertEquals("Unable to load file.", e.getMessage());
	}

	/**
	 * Tests that an IllegalArgumentException is thrown when dog breed is invalid.
	 */
	@Test
	public void testInvalidDogBreed() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/invalid_dog_breed.md");
		});

		assertEquals("Unable to load file.", e.getMessage());
	}

	/**
	 * Tests that an IllegalArgumentException is thrown when House Trained boolean
	 * is invalid.
	 */
	@Test
	public void testInvalidHouseTrainedBoolean() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/invalid_HouseTrained_boolean.md");
		});

		assertEquals("Unable to load file.", e.getMessage());
	}

	/**
	 * Tests that an IllegalArgumentException is thrown when Good With Kids boolean
	 * is invalid.
	 */
	@Test
	public void testInvalidGoodWithKidsBoolean() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			PackDoptionReader.readRescueListFile("test-files/invalid_GoodWithKids_boolean.md");
		});

		assertEquals("Unable to load file.", e.getMessage());
	}

}
