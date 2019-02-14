package org.example.algoritms.priorityqueue;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * maintain equal sizes of two PQ's
 * insert
 * - compare with median to check target PQ
 * - insert into target PQ
 * - if sizes out of balanceIfNeeded - move item from bigger PQ to median and median to smaller PQ
 * removeMin
 * - removeMin median
 * - move top from smaller PQ to median
 */

public class MedianPQ<T extends Comparable<T>> implements Queue<T> {

	private Queue<T> smaller = new PriorityQueue<>(Comparator.reverseOrder());
	private Queue<T> bigger = new PriorityQueue<>();
	private T median;

	public static void main(String[] args) {
		MedianPQ<Integer> mPQ = new MedianPQ<>();
		for(int i = 0; i < 20; i++) {
			mPQ.add(i);
		}
		System.out.println(mPQ);
		System.out.println(mPQ.poll());
		System.out.println(mPQ);
		System.out.println(mPQ.poll());
		System.out.println(mPQ);
		System.out.println(mPQ.poll());
		System.out.println(mPQ);
		System.out.println(mPQ.poll());
		System.out.println(mPQ);
		System.out.println(mPQ.poll());
		System.out.println(mPQ);
		System.out.println(mPQ.poll());
		System.out.println(mPQ);
		mPQ.add(22);
		System.out.println(mPQ);
	}

	@Override
	public String toString() {
		return smaller.toString() +
				" Median: " + median +
				bigger.toString();
	}

	@Override
	public int size() {
		return smaller.size() + bigger.size() + 1;
	}

	@Override
	public boolean isEmpty() {
		return median == null;
	}

	@Override
	public T poll() {
		T result = median;
		if(smaller.size() > bigger.size()) {
			median = smaller.poll();
		} else if(bigger.size() > smaller.size()){
			median = bigger.poll();
		} else {
			//TODO: special case?
			median = smaller.poll();
		}
		return result;
	}

	@Override
	public boolean add(T t) {
		if(median == null) {
			median = t;
			return true;
		}
		if(t.compareTo(median) > 0) {
			bigger.add(t);
		} else if(t.compareTo(median) < 0) {
			smaller.add(t);
		} else {
			insertToSmallerSize(t);
		}
		balanceIfNeeded();

		return true;
	}

	private void balanceIfNeeded() {
		if(smaller.size() - bigger.size() > 1) {
			bigger.add(median);
			median = smaller.poll();
		} else if(bigger.size() - smaller.size() > 1) {
			smaller.add(median);
			median = bigger.poll();
		}
	}

	private void insertToSmallerSize(T t) {
		if(smaller.size() < bigger.size()) {
			smaller.add(t);
		} else {
			bigger.add(t);
		}
	}

	@Override
	public boolean offer(T t) {
		return add(t);
	}

	@Override
	public T remove() {
		return poll();
	}

	@Override
	public T element() {
		return peek();
	}

	@Override
	public T peek() {
		return median;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return new Object[0];
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return null;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {

	}
}
