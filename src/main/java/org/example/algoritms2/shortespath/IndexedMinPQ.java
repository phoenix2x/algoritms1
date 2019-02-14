package org.example.algoritms2.shortespath;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Indexed priority queue allows clients to change priority of any item, by calling decreaseKey.
 * basically associates int index with every item and allows to change this item maintaining priority
 */
public class IndexedMinPQ<T extends Comparable<T>> {
	// actual heap with items
	private T[] arr;
	// array by given index has number that is index of the corresponding heap item
	private int[] indexes;
	// same as heap but contains reference to 'indexes' array(allows to find index of the given item)
	private int[] backIndexes;
	// excluding zero element for easier index computations
	private int currentSize = 1;

	public static void main(String[] args) {
		IndexedMinPQ<Double> pq = new IndexedMinPQ<>(10);
		pq.add(6.00, 1);
		pq.print();

		pq.add(4.00, 2);
		pq.print();

		pq.add(1.00, 3);
		pq.print();

		pq.add(8.00, 4);
		pq.print();
//
		pq.add(3.00, 5);
		pq.print();
//		pq.decreaseKey(2.00, 4);
		System.out.println(pq.removeMinIndex());
		System.out.println(pq.removeMinIndex());
////		pq.add(2.00, 6);
//		System.out.println(pq.removeMin());
////		pq.add(10.00, 7);
//		System.out.println(pq.removeMin());
//		System.out.println(pq.removeMin());
//		System.out.println(pq.removeMin());
//		System.out.println(pq.removeMin());
//		System.out.println(pq.removeMin());
	}
	public IndexedMinPQ(int size) {
		this.arr = (T[]) new Comparable[size + 1];
		this.indexes = new int[size + 1];
		this.backIndexes = new int[size + 1];
	}

	public void decreaseKey(T item, int index) {
		int indexInArr = indexes[index];
		arr[indexInArr] = item;
		swim(indexInArr);
	}

	/**
	 * removeMin min element
	 * @return
	 */
	public T removeMin() {
		swap(1, --currentSize);
		sink(1);
		return delete(currentSize);
	}

	public int removeMinIndex() {
		swap(1, --currentSize);
		sink(1);
		return deleteIndex(currentSize);
	}

	private int deleteIndex(int index) {
		int result = backIndexes[index];
		arr[index] = null;

		indexes[backIndexes[index]] = 0;

		backIndexes[index] = 0;

		return result;
	}

	private T delete(int index) {
		T result = arr[index];
		arr[index] = null;

		indexes[backIndexes[index]] = 0;

		backIndexes[index] = 0;

		return result;
	}

	public void add(T item, int index) {
		int currentIndex = currentSize++;
		arr[currentIndex] = item;
		indexes[index] = currentIndex;
		backIndexes[currentIndex] = index;
		swim(currentIndex);
	}

	private void sink(int index) {
		int childIndex = index * 2;
		while(childIndex < currentSize && arr[index].compareTo(arr[childIndex]) > 0) {
			swap(index, childIndex);
			index = childIndex;
			childIndex = index * 2;
		}
	}

	private void swim(int index) {
		int parentIndex = index / 2;
		while(index > 1 && arr[index].compareTo(arr[parentIndex]) < 0) {
			swap(index, parentIndex);
			index = parentIndex;
			parentIndex = index / 2;
		}
	}

	private void swap(int a, int b) {
		T swp = arr[a];
		arr[a] = arr[b];
		arr[b] = swp;


		indexes[backIndexes[a]] = b;
		indexes[backIndexes[b]] = a;

		int backIndex = backIndexes[a];
		backIndexes[a] = backIndexes[b];
		backIndexes[b] = backIndex;
	}

	public boolean containsIndex(int index) {
		return indexes[index] != 0;
	}

	public T[] getArr() {
		return arr;
	}

	public void print() {
		System.out.println("Heap: " + Arrays.toString(arr));
		System.out.println("Indexes: " + Arrays.toString(indexes));
	}

	public int size() {
		return arr.length;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}
}
