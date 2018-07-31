package org.example.algoritms.elementarysorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Dutch national flag. Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:
 *
 * swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 * color(i): determine the color of the pebble in bucket i.
 * The performance requirements are as follows:
 *
 * At most n calls to color().
 * At most n calls to swap().
 * Constant extra space.
 */

public class DutchNatioalFlag {

	private static int[] arr = new int[]{1,2,3,3,3,2,1,2,2,3};
	private static int swaps = 0;
	private static int colorColls = 0;
	public static void main(String[] args) {
		int lastPointer = arr.length - 1;
		int firstPointer = 0;
		for (int i = 0; i < arr.length && i < lastPointer; i++) {
			int first = color(i);
			if(first == 3) {
				while(arr[lastPointer] == 3) {
					lastPointer--;
				}
				swap(i, lastPointer);
				first = color(i);
			}
			if(first == 1) {
				if(firstPointer < i) {
					swap(i, firstPointer);
				}
				firstPointer++;
			}
		}
		System.out.println(Arrays.toString(arr));
		System.out.println("Swaps: " + swaps);
		System.out.println("ColorCalls: " + colorColls);
	}

	private static void swap(int i, int j) {
		swaps++;
		int swap = arr[i];
		arr[i] = arr[j];
		arr[j] = swap;
	}

	private static int color(int i) {
		colorColls++;
		return arr[i];
	}
}
