package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * An abstract class representing an animal.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public abstract class Animal implements Comparable<Animal> {

	/** The name of the animal */
	private String name;
	/** The birthday of the animal */
	private Date birthday;
	/** The size of the animal */
	private Size size;
	/** Whether the animal is house trained */
	private boolean houseTrained;
	/** Whether the animal is good with kids */
	private boolean goodWithKids;
	/** The notes for the animal */
	private SortedLinkedList<Note> notes;
	/** The date the animal entered the rescue */
	private Date dateEnterRescue;
	/** Whether the animal is adopted */
	private boolean adopted;
	/** The date the animal was adopted */
	private Date dateAdopted;
	/** The owner of the animal */
	private String owner;

	/**
	 * Enumeration representing one of the three possible age categories for an
	 * animal.
	 */
	public enum AgeCategory {
		/** Represents a young animal */
		YOUNG,
		/** Represents an adult animal */
		ADULT,
		/** Represents a senior animal */
		SENIOR
	}

	/**
	 * Enumeration representing one of the three possible sizes for an animal.
	 */
	public enum Size {
		/** Represents a small-sized animal */
		SMALL,
		/** Represents a medium-sized animal */
		MEDIUM,
		/** Represents a large-sized animal */
		LARGE
	}

	/**
	 * Constructs an Animal with the specified parameters. (constructor 1)
	 * 
	 * @param name            the name of the animal
	 * @param birthday        the birthday of the animal
	 * @param size            the size of the animal
	 * @param houseTrained    whether the animal is house trained
	 * @param goodWithKids    whether the animal is good with kids
	 * @param notes           the notes for the animal
	 * @param dateEnterRescue the date the animal entered the rescue
	 * @param adopted         whether the animal is adopted
	 * @param dateAdopted     the date the animal was adopted
	 * @param owner           the owner of the animal
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	public Animal(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner) {

		if (name == null || name.trim().isEmpty() || name.contains("\n") || name.contains(",")) {
			throw new IllegalArgumentException("Cannot add: Invalid name");
		}
		if (birthday == null) {
			throw new IllegalArgumentException("Cannot add: Invalid birthday");
		}
		if (size == null) {
			throw new IllegalArgumentException("Cannot add: Invalid size");
		}
		if (notes == null) {
			throw new IllegalArgumentException("Cannot add: Invalid note");
		}
		if (dateEnterRescue == null || dateEnterRescue.compareTo(birthday) < 0) {
			throw new IllegalArgumentException("Date of adoption cannot be before date of entry into rescue");
		}
		if (adopted && (dateAdopted == null || owner == null)) {
			throw new IllegalArgumentException("Cannot add: Animal is adopted but owner or adoption date is missing");
		}
		if (!adopted && (dateAdopted != null || owner != null)) {
			throw new IllegalArgumentException("Cannot add: Animal is not adopted but has an owner and adoption date");
		}
		if (dateAdopted != null && dateAdopted.compareTo(dateEnterRescue) < 0) {
			throw new IllegalArgumentException("Cannot add: Invalid date entered rescue");
		}
		if (owner != null && (owner.trim().isEmpty() || owner.contains("\n") || owner.contains(","))) {
			throw new IllegalArgumentException("Cannot add: Invalid owner");
		}

		this.name = name != null ? name.trim() : null;
		this.owner = owner != null ? owner.trim() : null;
		this.birthday = birthday;
		this.houseTrained = houseTrained;
		this.goodWithKids = goodWithKids;
		this.notes = notes != null ? notes : new SortedLinkedList<>();
		this.dateEnterRescue = dateEnterRescue;
		this.adopted = adopted;
		this.dateAdopted = dateAdopted;
		setSize(size);
	}

	/**
	 * Constructs an Animal with the specified parameters. (constructor 2)
	 * 
	 * @param name            the name of the animal
	 * @param birthday        the birthday of the animal
	 * @param size            the size of the animal
	 * @param houseTrained    whether the animal is house trained
	 * @param goodWithKids    whether the animal is good with kids
	 * @param notes           the notes for the animal
	 * @param dateEnterRescue the date the animal entered the rescue
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	public Animal(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue) {
		this(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, false, null, null);

	}

	/**
	 * Returns the name of the animal.
	 * 
	 * @return the name of the animal
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the birthday of the animal.
	 * 
	 * @return the birthday of the animal
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Returns the date the animal entered the rescue.
	 * 
	 * @return the date the animal entered the rescue
	 */
	public Date getDateEnterRescue() {
		return dateEnterRescue;
	}

	/**
	 * Returns the date the animal was adopted.
	 * 
	 * @return the date the animal was adopted
	 */
	public Date getDateAdopted() {
		return dateAdopted;
	}

	/**
	 * Returns the owner of the animal.
	 * 
	 * @return the owner of the animal
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Returns the notes of the animal.
	 * 
	 * @return the notes of the animal
	 */
	public SortedLinkedList<Note> getNotes() {
		return notes;
	}

	/**
	 * Returns the size of the animal.
	 * 
	 * @return the size of the animal
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * Returns whether the animal is good with kids.
	 * 
	 * @return true if the animal is good with kids, false otherwise
	 */
	public boolean isGoodWithKids() {
		return goodWithKids;
	}

	/**
	 * Returns whether the animal is house trained.
	 * 
	 * @return true if the animal is house trained, false otherwise
	 */
	public boolean isHouseTrained() {
		return houseTrained;
	}

	/**
	 * Returns whether the animal is adopted.
	 * 
	 * @return true if the animal is adopted, false otherwise
	 */
	public boolean adopted() {
		return adopted;
	}

	/**
	 * Sets the adoption information for the animal.
	 * 
	 * @param adopted     whether the animal is adopted
	 * @param dateAdopted the date the animal was adopted
	 * @param owner       the owner of the animal
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	public void setAdoptionInfo(boolean adopted, Date dateAdopted, String owner) {

		if (adopted && (dateAdopted == null || owner == null)) {
			throw new IllegalArgumentException("Cannot add: Animal is adopted but owner or adoption date is missing");
		}
		if (!adopted && (dateAdopted != null || owner != null)) {
			throw new IllegalArgumentException("Cannot add: Animal is not adopted but has an owner and adoption date");
		}
		if (dateAdopted != null && dateAdopted.compareTo(dateEnterRescue) < 0) {
			throw new IllegalArgumentException("Cannot add: Invalid date entered rescue");
		}
		if (owner != null && (owner.trim().isEmpty() || owner.contains("\n") || owner.contains(","))) {
			throw new IllegalArgumentException("Cannot add: Invalid owner");
		}

		this.adopted = adopted;
		this.dateAdopted = dateAdopted;
		this.owner = owner != null ? owner.trim() : null;
	}

	/**
	 * Sets the size of the animal. Cats and small dogs are considered adult at 4
	 * years old and senior at 9 years old. Medium dogs are considered adult at 3
	 * years old and senior at 9 years old. Large dogs are considered adult at 3
	 * years old and senior at 6 years old.
	 * 
	 * @param size the size of the animal
	 * @throws IllegalArgumentException if size is null
	 */
	public void setSize(Size size) {

		if (size == null) {
			throw new IllegalArgumentException("Cannot add: Invalid size");
		}

		this.size = size;
	}

	/**
	 * Adds a note to the animal's notes.
	 * 
	 * @param note the note to add
	 * @return true if added, false otherwise
	 * @throws IllegalArgumentException if note is null or already exists
	 */
	public boolean addNote(Note note) {

		if (note == null || this.notes.contains(note)) {
			throw new IllegalArgumentException("Cannot add note");
		}

		return this.notes.add(note);
	}

	/**
	 * Returns the age of the animal in years.
	 * 
	 * @param today the current date
	 * @return the age in years
	 * @throws IllegalArgumentException if today is invalid
	 */
	public int getAge(Date today) {

		if (today == null || today.compareTo(birthday) < 0) {
			throw new IllegalArgumentException("Invalid age");
		}

		return birthday.yearsTo(today);
	}

	/**
	 * Returns the number of days the animal has been available for adoption.
	 * Returns days in rescue (from dateEnterRescue to today). Returns -1 if animal
	 * has been adopted. Throws an IllegalArgumentException if (1) today is null or
	 * (2) today is before dateEnterRescue.
	 * 
	 * @param today the current date
	 * @return the number of days
	 * @throws IllegalArgumentException if today is invalid
	 */
	public int getDaysAvailableForAdoption(Date today) {

		if (today == null || today.compareTo(dateEnterRescue) < 0) {
			throw new IllegalArgumentException("Invalid days available for adoption");
		}

		if (adopted) {
			return -1;
		} else {
			return dateEnterRescue.daysTo(today);
		}

	}

	/**
	 * Abstract method that returns the age category based on today.
	 * 
	 * @param today the current date
	 * @return the age category
	 * @throws IllegalArgumentException if today is invalid
	 */
	public abstract AgeCategory getAgeCategory(Date today);

	/**
	 * Abstract method that returns a string array representation of the animal.
	 * 
	 * @param today the current date
	 * @return the string array representation
	 * @throws IllegalArgumentException if today is invalid
	 */
	public abstract String[] getAnimalAsArray(Date today);

	/**
	 * Compares this object with the specified object for order. Returns a negative
	 * integer, zero, or a positive integer as this object is less than, equal to,
	 * or greater than the specified object.
	 * 
	 * @param other the animal object to compare to
	 * @return the integer based on the comparison
	 */
	@Override
	public int compareTo(Animal other) {
		if (other == null) {
			throw new NullPointerException();
		}

		int birthdayComparison = this.getBirthday().compareTo(other.getBirthday());
		if (birthdayComparison != 0) {
			return birthdayComparison;
		}

		String thisName = this.getName();
		String otherName = other.getName();

		return thisName.compareTo(otherName);
	}

	/**
	 * Returns Animal as a string with the format name (birthday)\nnotes. For
	 * example, “Peyton (9/8/2012)\n-9/8/2012 Birthday!!!”.
	 * 
	 * @return string representation of animal
	 */
	@Override
	public String toString() {

		StringBuilder string = new StringBuilder();
		string.append(name).append(" (").append(birthday).append(")\n");

		for (int i = 0; i < notes.size(); i++) {
			string.append("-").append(notes.get(i)).append("\n");
		}

		return string.toString().trim();
	}

	/**
	 * Generates a hash code for the Animal object.
	 * 
	 * @return the hash code for the Animal object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		return result;
	}

	/**
	 * Compares this Animal object to another object for equality.
	 * 
	 * @param obj the object to compare to
	 * @return true if the objects are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		return true;
	}

}