package edu.ncsu.csc216.packdoption.util;

import java.util.Arrays;

/**
 * Implements the Queue interface with an array data structure.
 * 
 * @param <E> The object element the client works with when constructing the
 *            Generic ArrayList
 * @author Michael Pacheco
 * @author Dania Swelam
 */
public class ArrayListQueue<E> implements Queue<E> {

	/** Field for element objects in ArrayList */
	private E[] list;
	/** Initial size of ArrayList */
	private int size;
	/** Initial capacity of the array */
	private static final int INITIAL_CAPACITY = 10;
	/** Front of the queue */
	private int front;
	/** Rear of the queue */
	private int rear;

	/**
	 * Constructor for ArrayListQueue Class.
	 */
	@SuppressWarnings("unchecked")
	public ArrayListQueue() {
		list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
		front = 0;
		rear = 0;
	}

	/**
	 * Checks if the queue is empty.
	 * 
	 * @return true if the queue is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return the number of elements in the queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the rear of the queue.
	 * 
	 * @param element the element to be added
	 * @return true if the element is added successfully
	 * @throws NullPointerException if the element is null
	 */
	@Override
	public boolean add(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (size == list.length) {
			growArray();
		}
		list[rear] = element;
		rear = (rear + 1) % list.length;
		size++;
		return true;
	}

	/**
	 * Removes and returns the front element of the queue.
	 * 
	 * @return the front element of the queue
	 * @throws NoSuchListElementException if the queue is empty
	 */
	@Override
	public E remove() {
		if (isEmpty()) {
			throw new NoSuchListElementException("No such element in list.");
		}
		E element = list[front];
		list[front] = null;
		front = (front + 1) % list.length;
		size--;
		return element;
	}

	/**
	 * Returns the front element of the queue without removing it.
	 * 
	 * @return the front element of the queue
	 * @throws NoSuchListElementException if the queue is empty
	 */
	@Override
	public E element() {
		if (isEmpty()) {
			throw new NoSuchListElementException("No such element in list.");
		}
		return list[front];
	}

	/**
	 * Resizes the array when it reaches capacity.
	 */
	private void growArray() {

		if (size == list.length) {
			E[] newList = Arrays.copyOf(list, list.length * 2);
			for (int i = 0; i < size; i++) {
				newList[i] = list[(front + i) % list.length];
			}
			list = newList;
			front = 0;
			rear = size;
		}
	}
}