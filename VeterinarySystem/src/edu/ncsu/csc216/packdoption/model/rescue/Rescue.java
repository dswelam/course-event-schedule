package edu.ncsu.csc216.packdoption.model.rescue;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.util.ArrayListQueue;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * A representation of a rescue.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class Rescue implements Comparable<Rescue> {

	/** The name of the rescue */
	private String name;
	/** The list of animals in the rescue */
	private SortedLinkedList<Animal> animals;
	/** The queue of animals waiting to see the veterinarian */
	private ArrayListQueue<Animal> vetAppointments;

	/**
	 * Constructs a Rescue with the specified name.
	 * 
	 * @param name the name of the rescue
	 * @throws IllegalArgumentException if name is invalid
	 */
	public Rescue(String name) {
		if (name == null || name.isBlank() || name.contains("\n")) {
			throw new IllegalArgumentException();
		}

		this.name = name.trim();
		this.animals = new SortedLinkedList<>();
		this.vetAppointments = new ArrayListQueue<>();
	}

	/**
	 * Gets the name of the rescue.
	 * 
	 * @return the name of the rescue.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Adds an animal to the rescue.
	 * 
	 * @param animal the animal to add
	 * @return true if the animal was added, false otherwise
	 * @throws IllegalArgumentException if animal is null
	 */
	public boolean addAnimal(Animal animal) {
		if (animal == null) {
			throw new IllegalArgumentException();
		}
		if (animals.contains(animal)) {
			return false;
		}

		animals.add(animal);
		return true;
	}

	/**
	 * Returns the animal at the specified index.
	 * 
	 * @param i the index
	 * @return the animal at the index
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	public Animal getAnimal(int i) {
		if (i < 0 || i > animals.size() - 1) {
			throw new IndexOutOfBoundsException();
		}

		return animals.get(i);
	}

	/**
	 * Returns the animal with the specified name and birthday.
	 * 
	 * @param name     the name of the animal
	 * @param birthday the birthday of the animal
	 * @return the animal, or null if not found
	 * @throws IllegalArgumentException if name or birthday is null
	 */
	public Animal getAnimal(String name, Date birthday) {
		if (name == null || birthday == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).getName().equals(name) && animals.get(i).getBirthday().equals(birthday)) {
				return animals.get(i);
			}
		}

		return null;
	}

	/**
	 * Gets the vet appointments for animal in rescue.
	 * 
	 * @return an array list of appointments for animal.
	 */
	public ArrayListQueue<Animal> getAppointments() {
		return this.vetAppointments;
	}

	/**
	 * Checks if the rescue contains the specified animal.
	 * 
	 * @param a the animal to check
	 * @return true if the rescue contains the animal, false otherwise
	 */
	public boolean contains(Animal a) {
		return animals.contains(a);
	}

	/**
	 * Adds a note to the specified animal.
	 * 
	 * @param animal the animal
	 * @param note   the note
	 * @return true if the note was added, false otherwise
	 * @throws IllegalArgumentException if animal or note is null, or note already
	 *                                  exists
	 */
	public boolean addNote(Animal animal, Note note) {
		if (animal == null || note == null) {
			throw new IllegalArgumentException();
		}
		if (!contains(animal)) {
			return false;
		}

		return animal.addNote(note);
	}

	/**
	 * Sets the adoption information for the specified animal.
	 * 
	 * @param animal      the animal
	 * @param adopted     whether the animal is adopted
	 * @param dateAdopted the adoption date
	 * @param owner       the owner
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	public void setAdoptionInfo(Animal animal, boolean adopted, Date dateAdopted, String owner) {
		if (animal == null) {
			throw new IllegalArgumentException();
		}
		if (!contains(animal)) {
			return;
		}

		animal.setAdoptionInfo(adopted, dateAdopted, owner);
	}

	/**
	 * Returns the number of animals in the rescue.
	 * 
	 * @return the number of animals
	 */
	public int numAnimals() {
		return animals.size();
	}

	/**
	 * Returns the number of animals available for adoption.
	 * 
	 * @return the number of animals available for adoption
	 */
	public int numAnimalsAvailable() {
		int count = 0;
		for (int i = 0; i < numAnimals(); i++) {
			if (!animals.get(i).adopted()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the number of animals that have been adopted.
	 * 
	 * @return the number of animals adopted
	 */
	public int numAnimalsAdopted() {
		int count = 0;
		for (int i = 0; i < numAnimals(); i++) {
			if (animals.get(i).adopted()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns a list of all animals available for adoption.
	 * 
	 * @return the list of animals available for adoption
	 */
	public SortedLinkedList<Animal> animalsAvailable() {
		SortedLinkedList<Animal> availableAnimals = new SortedLinkedList<>();
		for (int i = 0; i < numAnimals(); i++) {
			if (!animals.get(i).adopted()) {
				availableAnimals.add(getAnimal(i));
			}
		}

		return availableAnimals;
	}

	/**
	 * Returns a list of all cats available for adoption.
	 * 
	 * @return the list of cats available for adoption
	 */
	public SortedLinkedList<Animal> availableCats() {
		SortedLinkedList<Animal> availableCats = new SortedLinkedList<>();
		for (int i = 0; i < numAnimals(); i++) {
			if (!animals.get(i).adopted() && animals.get(i) instanceof Cat) {
				availableCats.add(getAnimal(i));
			}
		}

		return availableCats;
	}

	/**
	 * Returns a list of all dogs available for adoption.
	 * 
	 * @return the list of dogs available for adoption
	 */
	public SortedLinkedList<Animal> availableDogs() {
		SortedLinkedList<Animal> availableDogs = new SortedLinkedList<>();
		for (int i = 0; i < numAnimals(); i++) {
			if (!animals.get(i).adopted() && animals.get(i) instanceof Dog) {
				availableDogs.add(getAnimal(i));
			}
		}

		return availableDogs;
	}

	/**
	 * Returns a list of animals that have been adopted.
	 * 
	 * @return the list of animals adopted
	 */
	public SortedLinkedList<Animal> animalsAdopted() {
		SortedLinkedList<Animal> adoptedAnimals = new SortedLinkedList<>();
		for (int i = 0; i < numAnimals(); i++) {
			if (animals.get(i).adopted()) {
				adoptedAnimals.add(getAnimal(i));
			}
		}

		return adoptedAnimals;
	}

	/**
	 * Returns a list of animals available for adoption within a specific day range.
	 * 
	 * @param today the current date
	 * @param min   the minimum days
	 * @param max   the maximum days
	 * @return the list of animals
	 * @throws IllegalArgumentException if today is invalid or min > max
	 */
	public SortedLinkedList<Animal> availableAnimalsDayRange(Date today, int min, int max) {
		if (today == null || max < min || min < 0) {
			throw new IllegalArgumentException();
		}

		SortedLinkedList<Animal> availableAnimals = new SortedLinkedList<>();
		for (int i = 0; i < numAnimals(); i++) {
			if (today.compareTo(animals.get(i).getDateEnterRescue()) < 0) {
				throw new IllegalArgumentException();
			}
			int daysAvailable = animals.get(i).getDaysAvailableForAdoption(today);
			if (daysAvailable >= min && daysAvailable <= max && !animals.get(i).adopted()) {
				availableAnimals.add(getAnimal(i));
			}
		}

		return availableAnimals;
	}

	/**
	 * Returns a list of animals available for adoption within a specific age range.
	 * 
	 * @param today the current date
	 * @param min   the minimum age in years
	 * @param max   the maximum age in years
	 * @return the list of animals
	 * @throws IllegalArgumentException if today is invalid or min > max
	 */
	public SortedLinkedList<Animal> availableAnimalsAge(Date today, int min, int max) {
		if (today == null || max < min || min < 0) {
			throw new IllegalArgumentException();
		}

		SortedLinkedList<Animal> availableAnimals = new SortedLinkedList<>();
		for (int i = 0; i < numAnimals(); i++) {
			if (today.compareTo(animals.get(i).getBirthday()) < 0) {
				throw new IllegalArgumentException();
			}
			int age = animals.get(i).getAge(today);
			if (age >= min && age <= max && !animals.get(i).adopted()) {
				availableAnimals.add(getAnimal(i));
			}
		}

		return availableAnimals;
	}

	@Override
	public int compareTo(Rescue o) {
		return this.name.compareTo(o.name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Rescue other = (Rescue) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Returns a 2D array of strings representing all animals in the rescue.
	 * 
	 * @param today the current date
	 * @return the 2D array of strings
	 * @throws IllegalArgumentException if today is invalid
	 */
	public String[][] getAnimalsAsArray(Date today) {
		int size = numAnimals();
		String[][] animalsArray = new String[size][7];

		for (int i = 0; i < size; i++) {
			Animal animal = getAnimal(i);

			animalsArray[i][0] = animal.getName();
			if (animal instanceof Cat) {
				animalsArray[i][1] = "Cat";
			} else if (animal instanceof Dog) {
				animalsArray[i][1] = "Dog";
			}
			animalsArray[i][2] = animal.getBirthday().toString();
			animalsArray[i][3] = Integer.toString(animal.getAge(today));
			animalsArray[i][4] = animal.getAgeCategory(today).toString();
			if (animal.adopted()) {
				animalsArray[i][5] = "Yes";
				animalsArray[i][6] = "";
			} else {
				animalsArray[i][5] = "No";
				animalsArray[i][6] = Integer.toString(animal.getDaysAvailableForAdoption(today));
			}
		}

		return animalsArray;
	}

	/**
	 * Adds an animal to the veterinary appointments queue.
	 * 
	 * @param animal the animal to add
	 * @return true if added, false otherwise
	 * @throws NullPointerException if animal is null
	 */
	public boolean addAppointment(Animal animal) {
		if (animal == null) {
			throw new NullPointerException();
		}

		if (!animals.contains(animal)) {
			return false;
		}

		boolean duplicate = false;
		int vetSize = vetAppointments.size();

		for (int i = 0; i < vetSize; i++) {
			Animal currentAnimal = vetAppointments.remove();
			if (currentAnimal.equals(animal)) {
				duplicate = true;
			}
			vetAppointments.add(currentAnimal);
		}

		if (duplicate) {
			return false;
		}

		int checkSize = vetAppointments.size();
		vetAppointments.add(animal);
		return vetAppointments.size() == checkSize + 1;

	}

	/**
	 * Returns a 2D array of strings representing animals in the veterinary queue.
	 * 
	 * @param today the current date
	 * @return the 2D array of strings
	 * @throws IllegalArgumentException if today is invalid
	 */
	public String[][] getAppointmentsAsArray(Date today) {
		int size = vetAppointments.size();
		String[][] animalsArray = new String[size][7];

		for (int i = 0; i < size; i++) {
			Animal currentAnimal = vetAppointments.remove();
			animalsArray[i][0] = currentAnimal.getName();
			if (currentAnimal instanceof Cat) {
				animalsArray[i][1] = "Cat";
			} else if (currentAnimal instanceof Dog) {
				animalsArray[i][1] = "Dog";
			}
			animalsArray[i][2] = currentAnimal.getBirthday().toString();
			animalsArray[i][3] = Integer.toString(currentAnimal.getAge(today));
			animalsArray[i][4] = currentAnimal.getAgeCategory(today).toString();
			if (currentAnimal.adopted()) {
				animalsArray[i][5] = "Yes";
				animalsArray[i][6] = "";
			} else {
				animalsArray[i][5] = "No";
				animalsArray[i][6] = Integer.toString(currentAnimal.getDaysAvailableForAdoption(today));
			}
			vetAppointments.add(currentAnimal);
		}

		return animalsArray;

	}

}