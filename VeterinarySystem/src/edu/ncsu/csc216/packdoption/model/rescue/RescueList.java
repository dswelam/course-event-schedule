package edu.ncsu.csc216.packdoption.model.rescue;

import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * A RescueList has a SortedLinkedList of Rescues.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class RescueList {

	/** The list of rescues */
	private SortedLinkedList<Rescue> s;

	/**
	 * Constructs a new RescueList with an empty SortedLinkedList of Rescues.
	 */
	public RescueList() {
		s = new SortedLinkedList<>();
	}

	/**
	 * Adds a rescue to the list.
	 * 
	 * @param r the rescue to add
	 * @throws IllegalArgumentException if the rescue is null or already in the list
	 */
	public void addRescue(Rescue r) {
		if (r == null || s.contains(r)) {
			throw new IllegalArgumentException();
		}

		s.add(r);
	}

	/**
	 * Adds a rescue with the specified name to the list.
	 * 
	 * @param name the name of the rescue
	 * @throws IllegalArgumentException if name is invalid or already in the list
	 */
	public void addRescue(String name) {
		if (name == null || name.isBlank() || name.contains("\n")) {
			throw new IllegalArgumentException();
		}

		Rescue newRescue = new Rescue(name);

		if (s.contains(newRescue)) {
			throw new IllegalArgumentException();
		}

		s.add(newRescue);
	}

	/**
	 * Returns the rescue at the specified index.
	 * 
	 * @param idx the index
	 * @return the rescue at the index
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	public Rescue getRescue(int idx) {
		if (idx < 0 || idx > size() - 1) {
			throw new IndexOutOfBoundsException();
		}

		return s.get(idx);
	}

	/**
	 * Returns the number of rescues in the list.
	 * 
	 * @return the number of rescues
	 */
	public int size() {
		return s.size();
	}

}