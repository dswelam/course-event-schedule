package edu.ncsu.csc216.packdoption.util;

/**
 * An implementation of the SortedList interface with a data structure of linked
 * Nodes.
 * 
 * @param <E> The object element the client works with when constructing the
 *            Generic LinkedList
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class SortedLinkedList<E extends Comparable<E>> implements SortedList<E> {

	/** Field for front node of list */
	private Node<E> head;
	/** Field for the size of the list */
	private int size;

	/** Constructor to initialize the list */
	public SortedLinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * Private, static inner class that contains an element and a reference to the
	 * next Node in the SortedLinkedList.
	 * 
	 * @param <E> The object element the client works with when constructing the
	 *            Generic LinkedList
	 */
	private static class Node<E> {

		/** Field for element data in list */
		E data;
		/** Field for next node in list */
		Node<E> next;

		/**
		 * Constructor for Node
		 * 
		 * @param data the data put in list
		 */
		public Node(E data) {
			this.data = data;
			this.next = null;
		}
	}

	/**
	 * A private inner class that provides a cursor for iterating forward through
	 * the list without changing the list.
	 */
	private class Cursor implements SimpleListIterator<E> {

		/** Field for current node in list */
		Node<E> current;

		/** Constructor for Cursor */
		public Cursor() {
			this.current = head;
		}

		/**
		 * Returns true if the iteration has more elements.
		 * 
		 * @return true if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * Returns the next element in the iteration.
		 * 
		 * @return the next element in the iteration
		 * @throws NoSuchListElementException if the iteration has no more elements
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchListElementException("No element available with call to next.");
			}
			E data = current.data;
			current = current.next;
			return data;
		}
	}

	/**
	 * Removes the element at the specified position in this list.
	 * 
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<E> current = head;
		Node<E> previous = null;

		for (int i = 0; i < index; i++) {
			previous = current;
			current = current.next;
		}

		if (previous == null) {
			head = head.next;
		} else {
			previous.next = current.next;
		}

		size--;
		return current.data;
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index the index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		return current.data;
	}

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this list contains no elements.
	 * 
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns true if this list contains the specified element.
	 * 
	 * @param e element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	@Override
	public boolean contains(E e) {
		Node<E> current = head;
		while (current != null) {
			if (current.data.equals(e)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param e element to be appended to this list
	 * @return true (as specified by Collection.add)
	 * @throws NullPointerException     if the specified element is null
	 * @throws IllegalArgumentException if the specified element already exists in
	 *                                  the list
	 */
	@Override
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (contains(e)) {
			throw new IllegalArgumentException();
		}

		Node<E> newNode = new Node<>(e);
		if (head == null || head.data.compareTo(e) > 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node<E> current = head;
			Node<E> previous = null;
			while (current != null && current.data.compareTo(e) <= 0) {
				previous = current;
				current = current.next;
			}
			newNode.next = current;
			previous.next = newNode;
		}

		size++;
		return true;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 * 
	 * @param e element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(E e) {
		Node<E> current = head;
		int index = 0;
		while (current != null) {
			if (current.data.equals(e)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	/**
	 * Returns a SimpleListIterator.
	 * 
	 * @return a new Cursor instance
	 */
	public SimpleListIterator<E> iterator() {
		return new Cursor();
	}

	/**
	 * Returns a string representation of the list in the format “-A\n-B\n…-X” where
	 * “A” is the first list item, “B” is the second, …, and “X” is the last. For
	 * example, if the list contains “Apple”, “Betty”, “Claude”, and “Matthew,” then
	 * the string representation would be “-Apple\n-Betty\n-Claude\n-Matthew”. An
	 * empty list would return “”.
	 * 
	 * @return a string representation of the list
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<E> current = head;
		while (current != null) {
			sb.append("-").append(current.data).append("\n");
			current = current.next;
		}
		return sb.toString().trim();
	}

	/**
	 * Returns the hash code value for this list.
	 * 
	 * @return the hash code value for this list
	 */
	@Override
	public int hashCode() {
		int result = 1;
		Node<E> current = head;
		while (current != null) {
			result = 31 * result + (current.data == null ? 0 : current.data.hashCode());
			current = current.next;
		}
		return result;
	}

	/**
	 * Compares the specified object with this list for equality.
	 * 
	 * @param obj the object to be compared for equality with this list
	 * @return true if the specified object is equal to this list
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		SortedLinkedList<?> other = (SortedLinkedList<?>) obj;
		if (size != other.size) {
			return false;
		}
		Node<?> current1 = this.head;
		Node<?> current2 = other.head;
		while (current1 != null) {
			if (!current1.data.equals(current2.data)) {
				return false;
			}
			current1 = current1.next;
			current2 = current2.next;
		}
		return true;
	}
}