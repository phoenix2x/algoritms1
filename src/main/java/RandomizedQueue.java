import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] data;
	private int size = 0;

	public static void main(String[] args) {
//		RandomizedQueue<Integer> queue = new RandomizedQueue<>();
//		queue.enqueue(1);
//		queue.enqueue(2);
//		queue.enqueue(3);
//		queue.enqueue(4);
////		System.out.println(queue.dequeue());
////		System.out.println(queue.sample());
//		for (Integer number: queue) {
//			System.out.println(number);
//		}
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(4);
		rq.size();
		rq.enqueue(1);
		System.out.println(rq.isEmpty());

    } // unit testing (optional)
	public RandomizedQueue() {
		data = (Item[]) new Object[10];
	}                 // construct an empty randomized queue
	public boolean isEmpty() {
		return size() == 0;
	}                 // is the randomized queue empty?
	public int size() {
		return size;
	}                        // return the number of items on the randomized queue
	public void enqueue(Item item) {
		if(item == null) {
			throw new IllegalArgumentException();
		}
		if(data.length == size) {
			resize(size * 2);
		}
		int index = StdRandom.uniform(size + 1);
		data[size++] = data[index];
		data[index] = item;
	}           // add the item
	public Item dequeue() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		int index = size - 1;
		Item item = data[index];
		data[index] = null;
		size--;
		if(data.length == size / 4) {
			resize(size / 2);
		}
		return item;
	}                    // remove and return a random item
	public Item sample() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		int index = StdRandom.uniform(size);
		return data[index];
	}                     // return a random item (but do not remove it)
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			Item[] shuffledData = shuffle(data);
			int head = 0;
			@Override
			public boolean hasNext() {
				return head != shuffledData.length && shuffledData[head] != null;
			}

			@Override
			public Item next() {
				if(head == shuffledData.length) {
					throw new NoSuchElementException();
				}
				return shuffledData[head++];
			}
		};
	}         // return an independent iterator over items in random order

	private void resize(int newSize) {
		Item[] newArr = (Item[]) new Object[newSize];
		for(int i = 0; i < data.length; i++) {
			newArr[i] = data[i];
		}
		data = newArr;
	}

	private Item[] shuffle(Item[] original) {
		int n = size;
		Item[] shuffled = (Item[]) new Object[n];
		for(int i = 0; i < n; ++i) {
			shuffled[i] = original[i];
		}
		StdRandom.shuffle(shuffled);
		return shuffled;
	}
}
