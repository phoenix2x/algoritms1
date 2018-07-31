package org.example.algoritms.quicksort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Nuts and bolts. A disorganized carpenter has a mixed pile of n nuts and n bolts.
 * The goal is to find the corresponding pairs of nuts and bolts.
 * Each nut fits exactly one bolt and each bolt fits exactly one nut.
 * By fitting a nut and a bolt together, the carpenter can see which one is bigger
 * (but the carpenter cannot compare two nuts or two bolts directly).
 * Design an algorithm for the problem that uses n*log(n) compares (probabilistically).
 */

public class NutsAndBolts {
	public static void main(String[] args) {
		Item[] pile = new Item[]{
				new Item(1, "nut"),
				new Item(1, "bolt"),
				new Item(2, "nut"),
				new Item(3, "bolt"),
				new Item(3, "nut"),
				new Item(2, "bolt"),
				new Item(4, "nut"),
				new Item(4, "bolt"),
		};

		sort(pile);

//		System.out.println("result: " + Arrays.toString(pile));

	}

	private static void sort(Item[] arr) {
		StdRandom.shuffle(arr);
		int i = 0;
		int j = arr.length - 1;
		sortSubRoutine(arr, i, j);
	}

	private static void sortSubRoutine(Item[] arr, int i, int j) {
		if(i >= j) return;
		int pivot = i;
		int lo = i;
		int hi = j;
		i++;
		int timeout = 0;
		while(j - i > 1 && timeout < 20) {
			while(arr[i].isSameType(arr[pivot])
					|| arr[i].compareTo(arr[pivot]) < 0) {
				i++;
			}
//			System.out.println("j same type: " + arr[j].isSameType(arr[pivot]));
//			System.out.println("j compare: " + arr[j].compareTo(arr[pivot]));
			while(arr[j].isSameType(arr[pivot])
					|| arr[j].compareTo(arr[pivot]) > 0) {
				j--;
			}
			if(i >= j) {
				break;
			}
			i++;
			j--;
			swap(arr, i, j);
//			System.out.println("q" + i + " " + j);
			timeout++;
		}
		swap(arr, pivot, j);
		System.out.println("pivot: " + arr[pivot]);
		System.out.println("partitioned: " + Arrays.toString(arr));
//		sortSubRoutine(arr, lo, pivot - 1);
//		sortSubRoutine(arr, pivot + 1, hi);
	}

	private static void swap(Item[] arr, int i, int j) {
		System.out.println("swap: " + arr[i] + arr[j]);
		Item swap = arr[i];
		arr[i] = arr[j];
		arr[j] = swap;
	}


	private static class Item implements Comparable<Item> {
		private int size;
		private String type;

		public Item(int size, String type) {
			this.size = size;
			this.type = type;
		}

		public boolean isSameType(Item item) {
			return this.type == item.type;
		}

		@Override
		public int compareTo(Item item) {
			if(item.type == this.type) {
				throw new RuntimeException("can't compare same type items");
			}
			return this.size - item.size;
		}

		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

		@Override
		public String toString() {
			return "size=" + size +
				", type=" + type + "\n";
		}
	}
}
