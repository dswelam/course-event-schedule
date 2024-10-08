package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * A concrete class extending Animal representing a cat.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class Cat extends Animal {

	/**
	 * Constructs a Cat with the specified parameters.
	 * 
	 * @param name            the name of the cat
	 * @param birthday        the birthday of the cat
	 * @param size            the size of the cat
	 * @param houseTrained    whether the cat is house trained
	 * @param goodWithKids    whether the cat is good with kids
	 * @param notes           the notes for the cat
	 * @param dateEnterRescue the date the cat entered the rescue
	 * @param adopted         whether the cat is adopted
	 * @param dateAdopted     the date the cat was adopted
	 * @param owner           the owner of the cat
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	public Cat(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted, owner);
	}

	/**
	 * Constructs a Cat with the specified parameters.
	 * 
	 * @param name            the name of the cat
	 * @param birthday        the birthday of the cat
	 * @param size            the size of the cat
	 * @param houseTrained    whether the cat is house trained
	 * @param goodWithKids    whether the cat is good with kids
	 * @param notes           the notes for the cat
	 * @param dateEnterRescue the date the cat entered the rescue
	 * @throws IllegalArgumentException if any parameters are invalid
	 */
	public Cat(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids,
			SortedLinkedList<Note> notes, Date dateEnterRescue) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);
	}

	/**
	 * Based on age and size, the age category (young, adult, senior) is calculated.
	 * Cats and small dogs are considered adult at 4 years old and senior at 9 years
	 * old.
	 * 
	 * @param today the current date
	 * @return the age category of the cat
	 * @throws IllegalArgumentException if the provided date is invalid
	 */
	@Override
	public AgeCategory getAgeCategory(Date today) {

		if (today == null || today.compareTo(getBirthday()) < 0) {
			throw new IllegalArgumentException("Invalid age category");
		}

		int age = getAge(today);

		if (age < 4) {
			return AgeCategory.YOUNG;
		} else if (age < 9) {
			return AgeCategory.ADULT;
		} else {
			return AgeCategory.SENIOR;
		}
	}

	/**
	 * Returns a string array representation of the cat.
	 * 
	 * @param today the current date
	 * @return a string array representing the cat
	 * @throws IllegalArgumentException if the provided date is invalid
	 */
	@Override
	public String[] getAnimalAsArray(Date today) {

		if (today == null || today.compareTo(getBirthday()) < 0) {
			throw new IllegalArgumentException();
		}

		String name = getName();
		String type = "Cat";
		String birthday = getBirthday().toString();
		String age = Integer.toString(getAge(today));
		String ageCategory = getAgeCategory(today).toString();
		String adopted = adopted() ? "Yes" : "No";
		String daysInRescue = adopted() ? "" : Integer.toString(getDateEnterRescue().daysTo(today));

		return new String[] { name, type, birthday, age, ageCategory, adopted, daysInRescue };
	}

}