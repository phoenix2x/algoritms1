package org.example.algoritms.sortsimplementations;

import java.util.Arrays;

/**
 */

public class SimpleSorts {
	public static void main(String[] args) {
		int[] arr = new int[]{3,2,5,6,7,1,3,4,10,9};
//		int[] arr = new int[]{3,2,1};
		System.out.println(Arrays.toString(arr));
//		bubble(arr);
//		selection(arr);
		insertion(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void bubble(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 1; j < arr.length - i; j++) {
				if(arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
				}
			}
		}
	}

	private static void selection(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			int smallest = i;
			for(int j = i; j < arr.length; j++) {
				if(arr[j] < arr[smallest]) {
					smallest = j;
				}
			}
			swap(arr, i, smallest);
		}
	}

	private static void insertion(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			int j = i;
			while(j > 0 && arr[j] < arr[j - 1]) {
				swap(arr, j, j - 1);
				j--;
			}
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int swap = arr[i];
		arr[i] = arr[j];
		arr[j] = swap;
	}
}
