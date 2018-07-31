package org.example.algoritms.quicksort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Selection in two sorted arrays. Given two sorted arrays a[] and b[], of sizes n_1 and n_2
 * ​	, respectively, design an algorithm to find the k-th largest key.
 * The order of growth of the worst case running time of your algorithm should be log(n),
 * where n = n_1 + n_2
 * Version 1: n_1 = n_2n and k = n/2
 * Version 2: k = n/2
 * Version 3: no restrictions
 */

public class TwoArraysSelection {
	public static void main(String[] args) {
//		int[] arr1 = new int[]{-1,1,2,3,4,5,6,7};
//		int[] arr2 = new int[]{8,9,10,11,12,13,14,15};
//		int[] arr1 = new int[]{1,2,4,6,8};
//		int[] arr2 = new int[]{3,5,7,9,10};
//		int[] arr1 = new int[]{1,5,6,7,9};
//		int[] arr2 = new int[]{2,3,4,8,10};
//		int[] arr1 = new int[]{1,7,8,9,10};
//		int[] arr2 = new int[]{2,3,4,5,6};
//
		int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
		StdRandom.shuffle(arr);
		int[] arr1 = new int[arr.length / 2];
		int[] arr2 = new int[arr.length / 2];
		for(int i = 0; i < arr.length; i++) {
			if(i < arr1.length) {
				arr1[i] = arr[i];
			} else {
				arr2[i - arr1.length] = arr[i];
			}
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		System.out.println("arr1: " + Arrays.toString(arr1));
		System.out.println("arr2: " + Arrays.toString(arr2));
		version1(arr1, arr2);

	}

	private static void version1(int[] arr1, int[] arr2) {
		int n = arr1.length + arr2.length;
		int k = n / 2;
		int effectiveK = (int) Math.ceil((double)k / 2);
		int effectivePosition = -1;
		System.out.println("K: " + k);
		System.out.println("effK: " + effectiveK);
		System.out.println("arr1 eff: " + arr1[effectiveK - 1]);
		System.out.println("arr2 eff: " + arr2[effectiveK - 1]);
		int kTh;
		if(arr1[effectiveK - 1] < arr2[effectiveK - 1]) {
			kTh = v1Sub(arr1, effectiveK - 1, arr1.length, arr2, 0, effectiveK, k);
			System.out.println("smaller: " + Arrays.toString(arr1));
			System.out.println("bigger: " + Arrays.toString(arr2));
		} else {
			kTh = v1Sub(arr2, effectiveK - 1, arr2.length, arr1, 0, effectiveK, k);
			System.out.println("smaller: " + Arrays.toString(arr2));
			System.out.println("bigger: " + Arrays.toString(arr1));
		}



		System.out.println("kTh: " + kTh);
	}

	private static int v1Sub(int[] smaller, int offsetSmaller, int smallerCap, int[] bigger, int offsetBigger, int biggerCap, int k) {
		System.out.println("offsets: " + offsetSmaller + " " + offsetBigger);
		System.out.println("caps: " + smallerCap + " " + biggerCap);
		if(offsetSmaller + offsetBigger + 2 == k) {
			if(offsetBigger == 0) {
				return smaller[offsetSmaller + 1] < bigger[offsetBigger]
						? smaller[offsetSmaller + 1]
						: smaller[offsetSmaller];
			}
			return Math.max(smaller[offsetSmaller], bigger[offsetBigger]);
		}
		int newSmallerOffset = offsetSmaller + (int) Math.ceil((double)(smallerCap - offsetSmaller) / 2);
		System.out.println("newSmallerOff: " + newSmallerOffset);
		int smallerTry = smaller[newSmallerOffset - 1];

		int newBiggerOffset = offsetBigger + (int) Math.ceil((double)(biggerCap - offsetBigger) / 2);
		System.out.println("newBiggerOff: " + newBiggerOffset);
		if(newBiggerOffset == 0) {
			return smaller[smaller.length - 1];
		}
		int biggerTry = bigger[newBiggerOffset - 1];
		System.out.println("smaller try: " + smallerTry);
		System.out.println("bigger try: " + biggerTry);
		int newBiggerCap = newBiggerOffset;
		int newSmallerCap = newSmallerOffset;
		if(smallerTry < biggerTry) {
			return v1Sub(smaller, newSmallerOffset - 1, smallerCap, bigger, offsetBigger, newBiggerCap, k);
		}
		if(smallerTry > biggerTry) {
			return v1Sub(smaller, offsetSmaller, newSmallerCap, bigger, newBiggerOffset - 1, biggerCap, k);
		}
		throw new RuntimeException("asd");
	}
}
