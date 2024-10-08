/**
 * 
 */
package edu.ncsu.csc216.packdoption.model.rescue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * Test class for Rescue class.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
class RescueTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#hashCode()}.
	 */
	@Test
	void testHashCode() {
		Rescue rescue1 = new Rescue("MikesRescue");
		Rescue rescue2 = new Rescue("MikesRescue");
		assertEquals(rescue1.hashCode(), rescue2.hashCode());
		rescue2 = new Rescue("DaniaRescue");
		assertNotEquals(rescue1.hashCode(), rescue2.hashCode());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#Rescue(java.lang.String)}.
	 */
	@Test
	void testRescue() {
		assertThrows(IllegalArgumentException.class, () -> new Rescue(null));
		assertThrows(IllegalArgumentException.class, () -> new Rescue("   "));
		assertThrows(IllegalArgumentException.class, () -> new Rescue("Invalid\nName"));

		Rescue rescue = new Rescue("DaniaRescue");
		assertEquals("DaniaRescue", rescue.getName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#getName()}.
	 */
	@Test
	void testGetName() {
		Rescue rescue = new Rescue("MikesRescue");
		assertEquals("MikesRescue", rescue.getName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#addAnimal(edu.ncsu.csc216.packdoption.model.animals.Animal)}.
	 */
	@Test
	void testAddAnimal() {
		Rescue rescue = new Rescue("MikesRescue");
		Date birthday = new Date(6, 3, 2018);
		Date dateEnterRescue = new Date(5, 21, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);

		assertTrue(rescue.addAnimal(luna));
		assertEquals(luna, rescue.getAnimal(0));
		assertFalse(rescue.addAnimal(luna));
		assertThrows(IllegalArgumentException.class, () -> rescue.addAnimal(null));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#getAnimal(int)}.
	 */
	@Test
	void testGetAnimalInt() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(7, 20, 2020);
		Date dateEnterRescue = new Date(3, 19, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Cat rosie = new Cat("rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		rescue.addAnimal(rosie);
		assertEquals(rosie, rescue.getAnimal("rosie", birthday));
		assertNull(rescue.getAnimal("luna", birthday));

		assertThrows(IndexOutOfBoundsException.class, () -> rescue.getAnimal(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> rescue.getAnimal(1));
		assertThrows(IllegalArgumentException.class, () -> rescue.getAnimal(null, birthday));
		assertThrows(IllegalArgumentException.class, () -> rescue.getAnimal("rosie", null));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#getAnimal(java.lang.String, edu.ncsu.csc216.packdoption.util.Date)}.
	 */
	@Test
	void testGetAnimalStringDate() {
		Rescue rescue = new Rescue("MikesRescue");
		Date birthday = new Date(6, 3, 2018);
		Date dateEnterRescue = new Date(5, 21, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);

		rescue.addAnimal(luna);

		assertEquals(luna, rescue.getAnimal("luna", birthday));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#getAppointments()}.
	 */
	@Test
	void testGetAppointments() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(7, 20, 2020);
		Date dateEnterRescue = new Date(3, 19, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Cat rosie = new Cat("rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		rescue.addAnimal(rosie);
		rescue.addAppointment(rosie);

		assertEquals(1, rescue.getAppointments().size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#contains(edu.ncsu.csc216.packdoption.model.animals.Animal)}.
	 */
	@Test
	void testContains() {
		Rescue rescue = new Rescue("MikesRescue");
		Date birthday = new Date(6, 3, 2018);
		Date dateEnterRescue = new Date(5, 21, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);

		rescue.addAnimal(luna);
		assertTrue(rescue.contains(luna));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#addNote(edu.ncsu.csc216.packdoption.model.animals.Animal, edu.ncsu.csc216.packdoption.util.Note)}.
	 */
	@Test
	void testAddNote() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(7, 20, 2020);
		Date dateEnterRescue = new Date(3, 19, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);
		Cat rosie = new Cat("rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		rescue.addAnimal(rosie);
		Note addNote = new Note(dateEnterRescue, "rosie has entered rescue.");
		assertTrue(rescue.addNote(rosie, addNote));
		assertFalse(rescue.addNote(luna, addNote));
		assertTrue(rosie.getNotes().contains(addNote));
		assertThrows(IllegalArgumentException.class, () -> rescue.addNote(null, addNote));
		assertThrows(IllegalArgumentException.class, () -> rescue.addNote(rosie, null));
		assertThrows(IllegalArgumentException.class, () -> rescue.addNote(rosie, addNote));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#setAdoptionInfo(edu.ncsu.csc216.packdoption.model.animals.Animal, boolean, edu.ncsu.csc216.packdoption.util.Date, java.lang.String)}.
	 */
	@Test
	void testSetAdoptionInfo() {
		Rescue rescue = new Rescue("MikesRescue");
		Date birthday = new Date(6, 3, 2018);
		Date dateEnterRescue = new Date(5, 21, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);
		Cat rosie = new Cat("rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		rescue.addAnimal(luna);
		Date dateAdopted = new Date(12, 25, 2023);

		rescue.setAdoptionInfo(luna, true, dateAdopted, "Thania");
		assertEquals("Thania", luna.getOwner());

		rescue.setAdoptionInfo(rosie, true, dateAdopted, "Dania");

		assertThrows(IllegalArgumentException.class, () -> rescue.setAdoptionInfo(null, false, dateAdopted, null));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#numAnimals()}.
	 */
	@Test
	void testNumAnimals() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(7, 20, 2020);
		Date dateEnterRescue = new Date(3, 19, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Cat rosie = new Cat("rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		rescue.addAnimal(rosie);
		assertEquals(rosie, rescue.getAnimal(0));
		assertEquals(1, rescue.numAnimals());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#numAnimalsAvailable()}.
	 */
	@Test
	void testNumAnimalsAvailable() {
		Rescue rescue = new Rescue("MikesRescue");
		Date birthday = new Date(6, 3, 2018);
		Date dateEnterRescue = new Date(5, 21, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);

		rescue.addAnimal(luna);
		assertEquals(1, rescue.numAnimalsAvailable());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#numAnimalsAdopted()}.
	 */
	@Test
	void testNumAnimalsAdopted() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(7, 20, 2020);
		Date dateEnterRescue = new Date(3, 19, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Cat rosie = new Cat("rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		rescue.addAnimal(rosie);
		Date dateAdopted = new Date(2, 18, 2023);

		rescue.setAdoptionInfo(rosie, true, dateAdopted, "Amira");
		assertEquals(1, rescue.numAnimalsAdopted());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#animalsAvailable()}.
	 */
	@Test
	void testAnimalsAvailable() {
		Rescue rescue = new Rescue("MikeRescue");
		Date birthday = new Date(6, 3, 2018);
		Date dateEnterRescue = new Date(5, 21, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);

		rescue.addAnimal(luna);

		assertTrue(rescue.animalsAvailable().contains(luna));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#availableCats()}.
	 */
	@Test
	void testAvailableCats() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(7, 20, 2020);
		Date dateEnterRescue = new Date(3, 19, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Cat rosie = new Cat("rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		rescue.addAnimal(rosie);

		assertTrue(rescue.availableCats().contains(rosie));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#availableDogs()}.
	 */
	@Test
	void testAvailableDogs() {
		Rescue rescue = new Rescue("MikesRescue");
		Date birthday = new Date(6, 3, 2018);
		Date dateEnterRescue = new Date(5, 21, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);

		rescue.addAnimal(luna);

		assertTrue(rescue.availableDogs().contains(luna));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#animalsAdopted()}.
	 */
	@Test
	void testAnimalsAdopted() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(7, 20, 2020);
		Date dateEnterRescue = new Date(3, 19, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Cat rosie = new Cat("rosie", birthday, Animal.Size.SMALL, true, true, notes, dateEnterRescue);

		rescue.addAnimal(rosie);
		Date dateAdopted = new Date(2, 18, 2023);
		rescue.setAdoptionInfo(rosie, true, dateAdopted, "Amira");

		assertEquals(rosie, rescue.animalsAdopted().get(0));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#availableAnimalsDayRange(edu.ncsu.csc216.packdoption.util.Date, int, int)}.
	 */
	@Test
	void testAvailableAnimalsDayRange() {
		Rescue rescue = new Rescue("MikesRescue");
		Date birthday = new Date(6, 3, 2018);
		Date birthday2 = new Date(6, 4, 2019);
		Date dateEnterRescue = new Date(10, 4, 2020);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);
		Cat rosie = new Cat("rosie", birthday2, Size.MEDIUM, true, true, notes, dateEnterRescue);
		rescue.addAnimal(luna);
		rescue.addAnimal(rosie);
		Date today = new Date(2, 28, 2021);
		assertEquals(dateEnterRescue, luna.getDateEnterRescue());
		assertEquals(147, luna.getDaysAvailableForAdoption(today));
		assertEquals(147, rosie.getDaysAvailableForAdoption(today));
		SortedLinkedList<Animal> animals = new SortedLinkedList<>();
		animals.add(luna);
		animals.add(rosie);
		assertEquals(animals, rescue.availableAnimalsDayRange(today, 0, 150));

		assertThrows(IllegalArgumentException.class, () -> rescue.availableAnimalsDayRange(null, 0, 0));
		assertThrows(IllegalArgumentException.class, () -> rescue.availableAnimalsDayRange(today, 3, 0));
		assertThrows(IllegalArgumentException.class, () -> rescue.availableAnimalsDayRange(today, -1, 0));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#availableAnimalsAge(edu.ncsu.csc216.packdoption.util.Date, int, int)}.
	 */
	@Test
	void testAvailableAnimalsAge() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(6, 3, 2018);
		Date birthday2 = new Date(6, 4, 2019);
		Date dateEnterRescue = new Date(10, 4, 2020);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);
		Cat rosie = new Cat("rosie", birthday2, Size.MEDIUM, true, true, notes, dateEnterRescue);

		rescue.addAnimal(luna);
		rescue.addAnimal(rosie);
		Date today = new Date(2, 28, 2021);
		assertEquals(dateEnterRescue, luna.getDateEnterRescue());
		assertEquals(147, luna.getDaysAvailableForAdoption(today));
		assertEquals(147, rosie.getDaysAvailableForAdoption(today));
		SortedLinkedList<Animal> animals = new SortedLinkedList<>();
		animals.add(luna);
		animals.add(rosie);
		assertEquals(animals, rescue.availableAnimalsAge(today, 0, 2));

		assertThrows(IllegalArgumentException.class, () -> rescue.availableAnimalsAge(null, 0, 3));
		assertThrows(IllegalArgumentException.class, () -> rescue.availableAnimalsAge(today, 3, 0));
		assertThrows(IllegalArgumentException.class, () -> rescue.availableAnimalsAge(today, -1, 0));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#compareTo(edu.ncsu.csc216.packdoption.model.rescue.Rescue)}.
	 */
	@Test
	void testCompareTo() {
		Rescue rescue1 = new Rescue("Rescue1");
		Rescue rescue2 = new Rescue("Rescue2");
		assertTrue(rescue1.compareTo(rescue2) < 0);
		assertTrue(rescue2.compareTo(rescue1) > 0);
		rescue2 = new Rescue("Rescue1");
		assertEquals(0, rescue1.compareTo(rescue2));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		Rescue rescue1 = new Rescue("MikesRescue");
		Rescue rescue2 = new Rescue("MikesRescue");
		Rescue rescue3 = new Rescue("DaniaRescue");
		assertTrue(rescue1.equals(rescue2));
		assertFalse(rescue1.equals(rescue3));
		assertNotNull(rescue1);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#toString()}.
	 */
	@Test
	void testToString() {
		Rescue rescue1 = new Rescue("MikesRescue");
		assertEquals("MikesRescue", rescue1.toString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#getAnimalsAsArray(edu.ncsu.csc216.packdoption.util.Date)}.
	 */
	@Test
	void testGetAnimalsAsArray() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(6, 3, 2018);
		Date birthday2 = new Date(6, 4, 2019);
		Date dateEnterRescue = new Date(10, 4, 2020);
		Date adoptionDate = new Date(10, 4, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);
		Cat rosie = new Cat("rosie", birthday2, Size.MEDIUM, true, true, notes, dateEnterRescue);
		Dog d = new Dog("max", birthday2, Animal.Size.MEDIUM, true, true, notes, dateEnterRescue, true, adoptionDate,
				"Thania", Dog.Breed.BEAGLE);

		rescue.addAnimal(luna);
		rescue.addAnimal(rosie);
		rescue.addAnimal(d);

		Date today = new Date(3, 11, 2022);

		String[][] array = rescue.getAnimalsAsArray(today);
		assertEquals("luna", array[0][0]);
		assertEquals("max", array[1][0]);
		assertEquals("rosie", array[2][0]);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#addAppointment(edu.ncsu.csc216.packdoption.model.animals.Animal)}.
	 */
	@Test
	void testAddAppointment() {
		Rescue rescue = new Rescue("MikesRescue");
		Date birthday = new Date(6, 3, 2018);
		Date birthday2 = new Date(6, 4, 2019);
		Date dateEnterRescue = new Date(5, 21, 2021);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);
		Cat rosie = new Cat("rosie", birthday2, Size.MEDIUM, true, true, notes, dateEnterRescue);
		Cat charlie = new Cat("charlie", birthday2, Size.MEDIUM, true, true, notes, dateEnterRescue);

		rescue.addAnimal(luna);
		rescue.addAppointment(luna);

		assertEquals(1, rescue.getAppointments().size());

		rescue.addAnimal(rosie);
		rescue.addAppointment(rosie);
		rescue.addAppointment(rosie);
		assertEquals(2, rescue.getAppointments().size());
		assertFalse(rescue.addAppointment(charlie));

		assertThrows(NullPointerException.class, () -> rescue.addAppointment(null));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.packdoption.model.rescue.Rescue#getAppointmentsAsArray(edu.ncsu.csc216.packdoption.util.Date)}.
	 */
	@Test
	void testGetAppointmentsAsArray() {
		Rescue rescue = new Rescue("DaniaRescue");
		Date birthday = new Date(6, 3, 2018);
		Date birthday2 = new Date(6, 4, 2019);
		Date dateEnterRescue = new Date(10, 4, 2020);
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		Dog luna = new Dog("luna", birthday, Size.MEDIUM, true, true, notes, dateEnterRescue, Breed.OTHER);
		Cat rosie = new Cat("rosie", birthday2, Size.MEDIUM, true, true, notes, dateEnterRescue);

		rescue.addAnimal(luna);
		rescue.addAnimal(rosie);

		rescue.addAppointment(luna);
		rescue.addAppointment(rosie);

		Date today = new Date(3, 18, 2021);

		String[][] array = rescue.getAppointmentsAsArray(today);
		assertEquals("luna", array[0][0]);
		assertEquals("rosie", array[1][0]);
	}

}
