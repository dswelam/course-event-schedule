package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * A concrete class extending Animal representing a dog.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class Dog extends Animal {
	/** The breed of the dog */
	private Breed breed;

	/**
	 * Enumeration representing one of the 12 possible breeds for a dog.
	 */
	public enum Breed {
		/** A Beagle breed */
		BEAGLE(),
		/** A Bulldog breed */
		BULLDOG,
		/** A French Bulldog breed */
		FRENCH_BULLDOG,
		/** A German Shepherd breed */
		GERMAN_SHEPHERD,
		/** A German Shorthaired Pointer breed */
		POINTER_GERMAN_SHORTHAIRED,
		/** A Poodle breed */
		POODLE,
		/** A Golden Retriever breed */
		RETRIEVER_GOLDEN,
		/** A Labrador Retriever breed */
		RETRIEVER_LABRADOR,
		/** A Rottweiler breed */
		ROTTWEILER,
		/** A Yorkshire Terrier breed */
		YORKSHIRE_TERRIER,
		/** A mixed breed */
		MIXED,
		/** Any other breed not listed */
		OTHER
	}

	/**
	 * Constructs a Dog with the specified parameters.
	 * 
	 * @param name            the name of the dog
	 * @param birthday        the birthday of the dog
	 * @param size            the size of the dog
	 * @param houseTrained    whether the dog is house trained
	 * @param goodWithKids    whether the dog is good with kids
	 * @param notes           the notes for the dog
	 * @param dateEnterRescue the date the dog entered the rescue
	 * @param adopted         whether the dog is adopted
	 * @param dateAdopted     the date the dog was adopted
	 * @param owner           the owner of the dog
	 * @param breed           the breed of the dog
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	public Dog(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner,
			Breed breed) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted, owner);

		if (breed == null) {
			throw new IllegalArgumentException("Invalid breed");
		}

		this.breed = breed;
	}

	/**
	 * Constructs a Dog with the specified parameters.
	 * 
	 * @param name            the name of the dog
	 * @param birthday        the birthday of the dog
	 * @param size            the size of the dog
	 * @param houseTrained    whether the dog is house trained
	 * @param goodWithKids    whether the dog is good with kids
	 * @param notes           the notes for the dog
	 * @param dateEnterRescue the date the dog entered the rescue
	 * @param breed           the breed of the dog
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	public Dog(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, Breed breed) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);

		if (breed == null) {
			throw new IllegalArgumentException("Invalid breed");
		}

		this.breed = breed;
	}

	/**
	 * Gets the breed of the dog.
	 * 
	 * @return the breed of dog.
	 */
	public Breed getBreed() {

		return breed;
	}

	/**
	 * Returns category of age based on age and size, the age category (young,
	 * adult, senior). Cats and small dogs are considered adult at 4 years old and
	 * senior at 9 years old. Medium dogs are considered adult at 3 years old and
	 * senior at 9 years old. Large dogs are considered adult at 3 years old and
	 * senior at 6 years old.
	 * 
	 * @param today the current date
	 * @return the age category of the dog
	 * @throws IllegalArgumentException Throws an IllegalArgumentException if (1)
	 *                                  today is null or (2) today is before
	 *                                  birthday.
	 */
	@Override
	public AgeCategory getAgeCategory(Date today) {

		if (today == null || today.compareTo(getBirthday()) < 0) {
			throw new IllegalArgumentException("Invalid age category");
		}

		int age = getAge(today);
		Size size = getSize();

		switch (size) {
		case SMALL:
			if (age < 4) {
				return AgeCategory.YOUNG;
			} else if (age < 9) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		case MEDIUM:
			if (age < 3) {
				return AgeCategory.YOUNG;
			} else if (age < 9) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		case LARGE:
			if (age < 3) {
				return AgeCategory.YOUNG;
			} else if (age < 6) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		default:
			throw new IllegalArgumentException("Invalid age category");
		}

	}

	/**
	 * returns a String array with seven elements based on today: Name, Type (Dog or
	 * Cat), Birthday, Age, Age Category, Adopted (Yes or No), Days in Rescue (if
	 * adopted then empty string).
	 *
	 * @param today the current date
	 * @return a string array representing the dog
	 * @throws IllegalArgumentException Throws an IllegalArgumentException if (1)
	 *                                  today is null or (2) today is before
	 *                                  birthday.
	 */
	@Override
	public String[] getAnimalAsArray(Date today) {

		if (today == null || today.compareTo(getBirthday()) < 0) {
			throw new IllegalArgumentException();
		}

		String name = getName();
		String type = "Dog";
		String birthday = getBirthday().toString();
		String age = Integer.toString(getAge(today));
		String ageCategory = getAgeCategory(today).toString();
		String adopted = adopted() ? "Yes" : "No";
		String daysInRescue = adopted() ? "" : Integer.toString(getDateEnterRescue().daysTo(today));

		return new String[] { name, type, birthday, age, ageCategory, adopted, daysInRescue };
	}

}