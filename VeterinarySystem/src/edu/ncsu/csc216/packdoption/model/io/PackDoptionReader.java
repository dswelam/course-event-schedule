package edu.ncsu.csc216.packdoption.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.model.rescue.Rescue;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * A class for reading PackDoption files.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class PackDoptionReader {

	/**
	 * Constructs a PackDoptionReader.
	 */
	public PackDoptionReader() {
		// Implement
	}

	/**
	 * Reads the pack adoption data from the specified file.
	 * 
	 * @param filename the file to read
	 * @return the RescueList read from the file
	 * @throws IllegalArgumentException if there is an error while processing the
	 *                                  file an IllegalArgumentException is thrown
	 *                                  with the message “Unable to load file.”
	 */
	public static RescueList readRescueListFile(String filename) throws IllegalArgumentException {

		try (Scanner fileReader = new Scanner(new FileInputStream(filename))) {
			RescueList rescueList = new RescueList();
			boolean firstLine = true;
			boolean rescueFlag = false;

			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine().trim();

				if (!line.isEmpty()) {
					char indicator = line.charAt(0);
					String data = line.substring(2).trim();

					switch (indicator) {
					case '#':
						if (rescueFlag) {
							throw new IllegalArgumentException("Unable to load file.");
						}
						rescueFlag = true;
						rescueList.addRescue(new Rescue(data));
						break;
					case '*':
						if (rescueList.size() > 0) {
							rescueFlag = true;
							addAnimal(rescueList, data);
						} else {
							throw new IllegalArgumentException("Unable to load file.");
						}
						break;
					case '-':
						if (rescueList.size() > 0) {
							addAppointment(rescueList, data);
						} else {
							throw new IllegalArgumentException("Unable to load file.");
						}
						break;
					default:
						throw new IllegalArgumentException("Unable to load file.");
					}
					firstLine = false;
				} else if (firstLine) {
					throw new IllegalArgumentException("Unable to load file.");
				} else {
					rescueFlag = false;
				}
			}
			fileReader.close();
			return rescueList;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}
	}

	/**
	 * Adds an animal to the specified rescue list based on the provided data.
	 * 
	 * @param rescueList the list of rescues
	 * @param data       the data for the animal
	 * @throws IllegalArgumentException if there is an error while processing the
	 *                                  animal data
	 */
	private static void addAnimal(RescueList rescueList, String data) {
		String[] animalData = data.split(",");
		Animal animal;

		if ("Cat".equals(animalData[0])) {
			animal = createCat(animalData);
		} else if ("Dog".equals(animalData[0])) {
			animal = createDog(animalData);
		} else {
			throw new IllegalArgumentException("Unable to load file.");
		}

		rescueList.getRescue(rescueList.size() - 1).addAnimal(animal);
	}

	/**
	 * Creates a Cat object from the provided data.
	 * 
	 * @param data the data for the cat
	 * @return the created Cat object
	 * @throws IllegalArgumentException if there is an error while processing the
	 *                                  cat data
	 */
	private static Animal createCat(String[] data) {
		boolean adopted = Boolean.parseBoolean(data[7]);
		Date dateAdopted = adopted ? parseDate(data[8]) : null;
		String owner = adopted ? data[9] : null;

		return new Cat(data[1], parseDate(data[2]), parseSize(data[3]), parseBoolean(data[4]), parseBoolean(data[5]),
				parseNotes(data, adopted ? 11 : 8), parseDate(data[6]), adopted, dateAdopted, owner);
	}

	/**
	 * Creates a Dog object from the provided data.
	 * 
	 * @param data the data for the dog
	 * @return the created Dog object
	 * @throws IllegalArgumentException if there is an error while processing the
	 *                                  dog data
	 */
	private static Animal createDog(String[] data) {
		boolean adopted = Boolean.parseBoolean(data[7]);
		Date dateAdopted = adopted ? parseDate(data[8]) : null;
		String owner = adopted ? data[9] : null;

		return new Dog(data[1], parseDate(data[2]), parseSize(data[3]), parseBoolean(data[4]), parseBoolean(data[5]),
				parseNotes(data, adopted ? 12 : 9), parseDate(data[6]), adopted, dateAdopted, owner,
				parseBreed(data[adopted ? 10 : 7]));
	}

	/**
	 * Adds an appointment to the specified rescue list based on the provided data.
	 * 
	 * @param rescueList the list of rescues
	 * @param data       the data for the appointment
	 * @throws IllegalArgumentException if there is an error while processing the
	 *                                  appointment data
	 */
	private static void addAppointment(RescueList rescueList, String data) {
		String[] appointmentData = data.split(",");
		Date date = parseDate(appointmentData[1].trim());
		Animal animal = rescueList.getRescue(rescueList.size() - 1).getAnimal(appointmentData[0], date);
		rescueList.getRescue(rescueList.size() - 1).addAppointment(animal);
	}

	/**
	 * Parses a Date object from the provided string.
	 * 
	 * @param date the string representing the date
	 * @return the parsed Date object
	 * @throws IllegalArgumentException if there is an error while parsing the date
	 */
	private static Date parseDate(String date) {
		try {
			return new Date(date);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

	/**
	 * Parses a Size object from the provided string.
	 * 
	 * @param size the string representing the size
	 * @return the parsed Size object
	 * @throws IllegalArgumentException if there is an error while parsing the size
	 */
	private static Size parseSize(String size) {
		try {
			return Size.valueOf(size);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

	/**
	 * Parses a boolean value from the provided string.
	 * 
	 * @param value the string representing the boolean value
	 * @return the parsed boolean value
	 * @throws IllegalArgumentException if the string is not a valid boolean value
	 */
	private static boolean parseBoolean(String value) {
		if ("true".equals(value)) {
			return true;
		} else if ("false".equals(value)) {
			return false;
		} else {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

	/**
	 * Parses a Breed object from the provided string.
	 * 
	 * @param breed the string representing the breed
	 * @return the parsed Breed object
	 * @throws IllegalArgumentException if there is an error while parsing the breed
	 */
	private static Breed parseBreed(String breed) {
		try {
			return Breed.valueOf(breed.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

	/**
	 * Parses a list of notes from the provided data starting at the specified
	 * index.
	 * 
	 * @param data       the array of strings representing the notes data
	 * @param startIndex the starting index in the array to parse notes from
	 * @return a SortedLinkedList of parsed Note objects
	 * @throws IllegalArgumentException if there is an error while parsing the notes
	 */
	private static SortedLinkedList<Note> parseNotes(String[] data, int startIndex) {
		SortedLinkedList<Note> notes = new SortedLinkedList<>();
		for (int i = startIndex; i < data.length; i++) {
			notes.add(parseNote(data[i]));
		}
		return notes;
	}

	/**
	 * Parses a Note object from the provided string.
	 * 
	 * @param noteData the string representing the note data
	 * @return the parsed Note object
	 * @throws IllegalArgumentException if there is an error while parsing the note
	 */
	private static Note parseNote(String noteData) {
		String[] parts = noteData.split(" ", 2);
		try {
			return new Note(new Date(parts[0]), parts[1]);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

}