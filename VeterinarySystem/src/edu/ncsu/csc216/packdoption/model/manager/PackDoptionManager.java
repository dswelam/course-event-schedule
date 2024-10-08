package edu.ncsu.csc216.packdoption.model.manager;

import edu.ncsu.csc216.packdoption.model.io.PackDoptionReader;
import edu.ncsu.csc216.packdoption.model.io.PackDoptionWriter;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * Maintains the data structures for the entire application. PackDoptionManager
 * implements the Singleton Design Pattern.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class PackDoptionManager {

	/** The single instance of PackDoptionManager */
	private static PackDoptionManager instance;
	/** The list of rescues managed by the application */
	private RescueList rescues;
	/** Whether the data has changed from the last save */
	private boolean changed;
	/** The string form of a filename */
	private String filename;

	/**
	 * Private constructor for Singleton Design Pattern.
	 */
	private PackDoptionManager() {
		rescues = new RescueList();
		changed = false;
	}

	/**
	 * Returns the single instance of PackDoptionManager.
	 * 
	 * @return the single instance of PackDoptionManager
	 */
	public static PackDoptionManager getInstance() {
		if (instance == null) {
			instance = new PackDoptionManager();
		}

		return instance;
	}

	/**
	 * Sets the rescue list to a new list.
	 */
	public void newList() {
		rescues = new RescueList();
	}

	/**
	 * Gets the list of rescues.
	 * 
	 * @return the list of rescues.
	 */
	public RescueList getRescueList() {
		return rescues;
	}

	/**
	 * Sets the data from last save.
	 * 
	 * @param changed data from last save.
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * Returns whether data has changed from last save.
	 * 
	 * @return true if data has changed, false otherwise
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Gets the filename with rescues.
	 * 
	 * @return string of form of filename.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename with leading and/or trailing whitespace removed.
	 * 
	 * @param filename the filename to set
	 * @throws IllegalArgumentException if filename is null or only whitespace
	 */
	public void setFilename(String filename) {
		if (filename == null || filename.isBlank()) {
			throw new IllegalArgumentException();
		}

		this.filename = filename.trim();
	}

	/**
	 * Loads the rescue list from the specified file.
	 * 
	 * @param filename the file to load
	 * @throws IllegalArgumentException if any errors occur reading the file
	 */
	public void loadFile(String filename) {
		setFilename(filename);
		try {
			rescues = PackDoptionReader.readRescueListFile(filename);
			changed = false;
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

	/**
	 * Saves the rescue list to the specified file.
	 * 
	 * @param filename the file to save to
	 * @throws IllegalArgumentException if any errors occur writing the file
	 */
	public void saveFile(String filename) {
		setFilename(filename);
		try {
			PackDoptionWriter.writeRescueFile(filename, rescues);
			changed = false;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

}