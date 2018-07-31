import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 */

public class Deque<Item> implements Iterable<Item> {
	private Node<Item> head;
	private Node<Item> tail;
	private int size = 0;

	public static void main(String[] args) {
//		Deque<Integer> deque = new Deque<>();
//		deque.addFirst(1);
//		deque.addFirst(2);
//		deque.addFirst(3);
//		deque.addLast(-1);
//		System.out.println(deque.removeFirst());
//		System.out.println(deque.removeLast());
//		System.out.println(deque.removeLast());
//		System.out.println(deque.removeLast());
//		for(Integer number: deque) {
//			System.out.println(number);
//		}
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(0);
		deque.isEmpty();
		deque.addLast(2);
		deque.removeLast();
		deque.removeFirst();
		System.out.println(deque.size());
		deque.addLast(6);
		deque.removeLast();
		deque.addFirst(8);
		System.out.println(deque.removeLast());

	}   // unit testing (optional)

	public Deque() {

	}                           // construct an empty deque
	public boolean isEmpty() {
		return size() == 0;
	}                 // is the deque empty?
	public int size() {
		return size;
	}                        // return the number of items on the deque
	public void addFirst(Item item) {
		if(item == null) {
			throw new IllegalArgumentException();
		}
		Node<Item> newNode = new Node<>(item, head, null);
		if(head != null) {
			head.setPrevious(newNode);
		}
		head = newNode;
		if(tail == null) {
			tail = newNode;
		}
		size++;
	}          // add the item to the front
	public void addLast(Item item) {
		if(item == null) {
			throw new IllegalArgumentException();
		}
		Node<Item> newNode = new Node<>(item, null, tail);
		if(tail != null) {
			tail.setNext(newNode);
		}
		tail = newNode;
		if(head == null) {
			head = newNode;
		}
		size++;
	}           // add the item to the end
	public Item removeFirst() {
		if(head == null) {
			throw new NoSuchElementException();
		}
		Node<Item> result = head;
		head = head.getNext();
		if(head != null) {
			head.setPrevious(null);
		} else {
			tail = null;
		}
		size--;
		return result.getData();
	}                // remove and return the item from the front
	public Item removeLast() {
		if(tail == null) {
			throw new NoSuchElementException();
		}
		Node<Item> result = tail;
		tail = tail.getPrevious();
		if(tail != null) {
			tail.setNext(null);
		} else {
			head = null;
		}
		size--;
		return result.getData();
	}                 // remove and return the item from the end
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			private Node<Item> current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Item next() {
				if(current == null) {
					throw new NoSuchElementException();
				}

				Item result = current.getData();
				current = current.getNext();
				return result;
			}
		};
	}         // return an iterator over items in order from front to end

	private static class Node<T> {
		private T data;
		private Node<T> next;
		private Node<T> previous;

		public Node(T data, Node<T> next, Node<T> previous) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}

		public T getData() {
			return data;
		}

		public Node<T> getNext() {
			return next;
		}

		public Node<T> getPrevious() {
			return previous;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public void setPrevious(Node<T> previous) {
			this.previous = previous;
		}
	}
}
