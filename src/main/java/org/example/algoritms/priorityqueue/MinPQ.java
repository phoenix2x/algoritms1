package org.example.algoritms.priorityqueue;

public class MinPQ<T extends Comparable<T>>{
	private T[] arr;
	// excluding zero element for easier index computations
	private int currentSize = 1;

	public static void main(String[] args) {
		MinPQ<Double> pq = new MinPQ<>(10);
		pq.add(6.00);
		pq.add(4.00);
		pq.add(1.00);
		pq.add(8.00);
		pq.add(3.00);
		System.out.println(pq.removeMin());
		pq.add(2.00);
		System.out.println(pq.removeMin());
		pq.add(10.00);
		System.out.println(pq.removeMin());
		System.out.println(pq.removeMin());
		System.out.println(pq.removeMin());
		System.out.println(pq.removeMin());
		System.out.println(pq.removeMin());
	}
	public MinPQ(int size) {
		this.arr = (T[]) new Comparable[size + 1];
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

	private T delete(int index) {
		T result = arr[currentSize];
		arr[currentSize] = null;
		return result;
	}

	public void add(T item) {
		int currentIndex = currentSize++;
		arr[currentIndex] = item;
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
	}
}
