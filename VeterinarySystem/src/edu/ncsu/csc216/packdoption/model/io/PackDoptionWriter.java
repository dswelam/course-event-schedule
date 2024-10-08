package edu.ncsu.csc216.packdoption.model.io;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.File;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.rescue.Rescue;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.ArrayListQueue;

/**
 * A class for writing PackDoption files.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class PackDoptionWriter {

	/**
	 * Contructs a PackDoptionWriter.
	 */
	public PackDoptionWriter() {
		// Implement
	}

	/**
	 * Writes the pack adoption data to the specified file.
	 * 
	 * @param filename the file to write to
	 * @param list     the RescueList to write
	 * @throws IllegalArgumentException if there is an error while processing the
	 *                                  file with the message “Unable to save file.”
	 */
	public static void writeRescueFile(String filename, RescueList list) throws IllegalArgumentException {
		try (PrintStream fileWriter = new PrintStream(new File(filename))) {
			for (int i = 0; i < list.size(); i++) {
				if (i > 0) {
					fileWriter.println();
				}
				Rescue rescue = list.getRescue(i);
				fileWriter.println("# " + rescue.getName());

				// Write animals
				for (int j = 0; j < rescue.numAnimals(); j++) {
					Animal animal = rescue.getAnimal(j);
					String animalString = "* " + animalString(animal);

					if (j < rescue.numAnimals() - 1) {
						fileWriter.println(animalString);
					} else {
						fileWriter.print(animalString);
					}
				}

				// Write appointments
				ArrayListQueue<Animal> appointments = rescue.getAppointments();
				int size = appointments.size();
				fileWriter.println();
				for (int j = 0; j < size; j++) {
					Animal queueElement = appointments.element();
					String appointmentString = "- " + queueElement.getName() + ","
							+ queueElement.getBirthday().toString();

					if (j < size - 1) {
						fileWriter.println(appointmentString);
					} else {
						fileWriter.print(appointmentString);
					}
					appointments.add(appointments.remove());
				}

				fileWriter.println();
			}
			if (list.size() > 0) {
				fileWriter.println();
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}

	/**
	 * Converts an animal object to its string representation for the file.
	 * 
	 * @param animal the animal to convert
	 * @return the string representation of the animal
	 */
	private static String animalString(Animal animal) {
		if (animal instanceof Cat) {
			return catString((Cat) animal);
		} else if (animal instanceof Dog) {
			return dogString((Dog) animal);
		}
		return "";
	}

	/**
	 * Converts a cat object to its string representation for the file.
	 * 
	 * @param cat the cat to convert
	 * @return the string representation of the cat
	 */
	private static String catString(Cat cat) {
		StringBuilder animalString = new StringBuilder();
		if (!cat.adopted()) {
			animalString.append("Cat,").append(cat.getName()).append(",").append(cat.getBirthday().toString())
					.append(",").append(cat.getSize().toString()).append(",").append(cat.isHouseTrained()).append(",")
					.append(cat.isGoodWithKids()).append(",").append(cat.getDateEnterRescue().toString())
					.append(",NOTES");
		} else {
			animalString.append("Cat,").append(cat.getName()).append(",").append(cat.getBirthday().toString())
					.append(",").append(cat.getSize().toString()).append(",").append(cat.isHouseTrained()).append(",")
					.append(cat.isGoodWithKids()).append(",").append(cat.getDateEnterRescue().toString()).append(",")
					.append(cat.adopted()).append(",").append(cat.getDateAdopted().toString()).append(",")
					.append(cat.getOwner()).append(",NOTES");
		}
		for (int i = 0; i < cat.getNotes().size(); i++) {
			animalString.append(",").append(cat.getNotes().get(i).toString());
		}
		return animalString.toString();
	}

	/**
	 * Converts a dog object to its string representation for the file.
	 * 
	 * @param dog the dog to convert
	 * @return the string representation of the dog
	 */
	private static String dogString(Dog dog) {
		StringBuilder animalString = new StringBuilder();
		if (!dog.adopted()) {
			animalString.append("Dog,").append(dog.getName()).append(",").append(dog.getBirthday().toString())
					.append(",").append(dog.getSize().toString()).append(",").append(dog.isHouseTrained()).append(",")
					.append(dog.isGoodWithKids()).append(",").append(dog.getDateEnterRescue().toString()).append(",")
					.append(dog.getBreed().toString()).append(",NOTES");
		} else {
			animalString.append("Dog,").append(dog.getName()).append(",").append(dog.getBirthday().toString())
					.append(",").append(dog.getSize().toString()).append(",").append(dog.isHouseTrained()).append(",")
					.append(dog.isGoodWithKids()).append(",").append(dog.getDateEnterRescue().toString()).append(",")
					.append(dog.adopted()).append(",").append(dog.getDateAdopted().toString()).append(",")
					.append(dog.getOwner()).append(",").append(dog.getBreed().toString()).append(",NOTES");
		}
		for (int i = 0; i < dog.getNotes().size(); i++) {
			animalString.append(",").append(dog.getNotes().get(i).toString());
		}
		return animalString.toString();
	}
}