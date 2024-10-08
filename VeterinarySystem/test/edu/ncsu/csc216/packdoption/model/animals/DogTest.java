/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Test class for Dog class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class DogTest {

	/**
	 * Test method for constructing a Dog with all parameters.
	 */
	@Test
	void testDogConstructorWithAllParams() {
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		Date dateAdopted = new Date(1, 1, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();

		Dog dog = assertDoesNotThrow(() -> new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes,
				dateEnterRescue, true, dateAdopted, "Michael", Breed.BEAGLE));
		assertAll("Dog", () -> assertEquals("Luna", dog.getName()), () -> assertEquals(birthday, dog.getBirthday()),
				() -> assertEquals(Animal.Size.MEDIUM, dog.getSize()), () -> assertTrue(dog.isHouseTrained()),
				() -> assertTrue(dog.isGoodWithKids()), () -> assertEquals(notes, dog.getNotes()),
				() -> assertEquals(dateEnterRescue, dog.getDateEnterRescue()), () -> assertTrue(dog.adopted()),
				() -> assertEquals(dateAdopted, dog.getDateAdopted()), () -> assertEquals("Michael", dog.getOwner()),
				() -> assertEquals(Breed.BEAGLE, dog.getBreed()));
	}

	/**
	 * Test method for constructing a Dog without adoption details.
	 */
	@Test
	void testDogConstructorWithoutAdoptionDetails() {
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();

		Dog dog = assertDoesNotThrow(
				() -> new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.BULLDOG));
		assertAll("Dog", () -> assertEquals("Luna", dog.getName()), () -> assertEquals(birthday, dog.getBirthday()),
				() -> assertEquals(Animal.Size.MEDIUM, dog.getSize()), () -> assertTrue(dog.isHouseTrained()),
				() -> assertTrue(dog.isGoodWithKids()), () -> assertEquals(notes, dog.getNotes()),
				() -> assertEquals(dateEnterRescue, dog.getDateEnterRescue()), () -> assertFalse(dog.adopted()),
				() -> assertNull(dog.getDateAdopted()), () -> assertNull(dog.getOwner()),
				() -> assertEquals(Breed.BULLDOG, dog.getBreed()));
	}

	/**
	 * Test method for getAgeCategory(Date).
	 */
	@Test
	void testGetAgeCategory() {
		Date birthday = new Date(1, 1, 2015);
		Date dateEnterRescue = new Date(1, 1, 2020);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();

		Dog smallDog = new Dog("Lady", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue, Breed.BULLDOG);
		Dog mediumDog = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue,
				Breed.BULLDOG);
		Dog largeDog = new Dog("LunasSister", birthday, Animal.Size.LARGE, true, true, notes, dateEnterRescue,
				Breed.BULLDOG);

		Date youngDate = new Date(1, 1, 2017);
		Date adultDateSmall = new Date(1, 1, 2019);
		Date adultDateMedium = new Date(1, 1, 2018);
		Date adultDateLarge = new Date(1, 1, 2018);
		Date seniorDateSmall = new Date(1, 1, 2024);
		Date seniorDateMedium = new Date(1, 1, 2024);
		Date seniorDateLarge = new Date(1, 1, 2021);

		// Small Dog Age Categories
		assertEquals(Animal.AgeCategory.YOUNG, smallDog.getAgeCategory(youngDate));
		assertEquals(Animal.AgeCategory.ADULT, smallDog.getAgeCategory(adultDateSmall));
		assertEquals(Animal.AgeCategory.SENIOR, smallDog.getAgeCategory(seniorDateSmall));

		// Medium Dog Age Categories
		assertEquals(Animal.AgeCategory.YOUNG, mediumDog.getAgeCategory(youngDate));
		assertEquals(Animal.AgeCategory.ADULT, mediumDog.getAgeCategory(adultDateMedium));
		assertEquals(Animal.AgeCategory.SENIOR, mediumDog.getAgeCategory(seniorDateMedium));

		// Large Dog Age Categories
		assertEquals(Animal.AgeCategory.YOUNG, largeDog.getAgeCategory(youngDate));
		assertEquals(Animal.AgeCategory.ADULT, largeDog.getAgeCategory(adultDateLarge));
		assertEquals(Animal.AgeCategory.SENIOR, largeDog.getAgeCategory(seniorDateLarge));

		// Test for null today date
		assertThrows(IllegalArgumentException.class, () -> {
			smallDog.getAgeCategory(null);
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
		Date dateAdopted = new Date(2, 1, 2020);
		Animal d = new Dog("Luna", birthday, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, true, dateAdopted,
				"Michael", Breed.BEAGLE);
		Date today = new Date(1, 1, 2021);

		String[] expected = { "Luna", "Dog", "1/1/2015", "6", "ADULT", "Yes", "" };
		assertArrayEquals(expected, d.getAnimalAsArray(today));
	}

}
