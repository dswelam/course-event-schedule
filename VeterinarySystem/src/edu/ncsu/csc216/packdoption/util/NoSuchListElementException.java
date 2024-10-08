package edu.ncsu.csc216.packdoption.util;

/**
 * Custom unchecked exception to be thrown when there’s no such list element.
 * 
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class NoSuchListElementException extends RuntimeException {

	/** Field for SerialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception without specified message.
	 */
	public NoSuchListElementException() {
		this("No such element in list.");
	}

	/**
	 * Constructs a new exception with the specified detail message. The default
	 * message should be “No such element in list.”
	 * 
	 * @param message the detail message.
	 */
	public NoSuchListElementException(String message) {
		super(message);
	}
}
