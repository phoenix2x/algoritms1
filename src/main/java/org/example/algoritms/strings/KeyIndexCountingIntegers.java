package org.example.algoritms.strings;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class KeyIndexCountingIntegers {
	private static final int RADIX = 10;

	public static void main(String[] args) {
		int[][] arr = new int[3][20];
		for (int[] subarray: arr) {
			for (int i = 0; i < subarray.length; i++) {
				subarray[i] = ThreadLocalRandom.current().nextInt(RADIX);
			}
		}

		sort(arr);
	}

	public static void sort(int[][] arr) {
		printArray(arr);

		for (int i = 0; i < arr.length; i++) {
			keyIndexCountingSort(arr[i]);
		}

		printArray(arr);
	}

	public static void keyIndexCountingSort(int[] arr) {
		int[] count = new int[RADIX + 1];
		// count of every number
		for (int i = 0; i < arr.length; i++) {
			count[arr[i] + 1]++;
		}
//		System.out.println(Arrays.toString(count));

		// count starting index of every number
		for (int i = 0; i < RADIX; i++) {
			count[i + 1] += count[i];
		}

//		System.out.println(Arrays.toString(count));

		// filling aux array using indices
		int[] aux = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			aux[count[arr[i]]++] = arr[i];
		}

		// coping back to input array
		for (int i = 0; i < arr.length; i++) {
			arr[i] = aux[i];
		}
	}

	public static void printArray(int[][] arr) {
		for (int[] subarrya: arr) {
			System.out.println(Arrays.toString(subarrya));
		}
	}
}
