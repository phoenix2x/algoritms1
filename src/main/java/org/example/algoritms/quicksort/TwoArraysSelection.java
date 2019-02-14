package org.example.algoritms.quicksort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
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
//		int[] arr1 = new int[]{1,2,4};
//		int[] arr2 = new int[]{3,5,6};
//		int[] arr1 = new int[]{1,5,6,7,9};
//		int[] arr2 = new int[]{2,3,4,8,10};
//		int[] arr1 = new int[]{1,7,8,9,10};
//		int[] arr2 = new int[]{2,3,4,5,6};
//		int[] arr1 = new int[]{4, 8, 9, 11, 12, 14, 15, 18, 19, 20};
//		int[] arr2 = new int[]{1, 2, 3, 5, 6, 7, 10, 13, 16, 17, 22,23,24,25,26,28};
//		int[] arr1 = new int[]{1, 4, 6, 8, 10, 11, 13, 14, 18, 19};
//		int[] arr2 = new int[]{2, 3, 5, 7, 9, 12, 15, 16, 17, 20};
//
		int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
		StdRandom.shuffle(arr);
		int arr1Size = new Random().nextInt(arr.length + 1);
		int arr2Size = arr.length - arr1Size;
		int[] arr1 = new int[arr1Size];
		int[] arr2 = new int[arr2Size];
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
//		version1(arr1, arr2);
//		int k = (arr1.length + arr2.length) / 2;
		int k = 5;
		System.out.println("K: " + k);

		finalVer(arr1, arr2, k);
	}

	private static void finalVer(int[] arr1, int[] arr2, int k) {
//		int	result = subRoutine(arr1, 0, arr1.length, arr2, 0, arr2.length, k);
		int	result = subRoutine(arr1, arr2, k);

		System.out.println("kTh: " + result);
	}

	private static int subRoutine(int[] arr1, int[] arr2, int k) {
		System.out.println("arr1: " + Arrays.toString(arr1));
		System.out.println("arr2: " + Arrays.toString(arr2));
		System.out.println(k);
		// once one array empty we can find an answer in the second array
		if(arr1.length == 0) {
			return arr2[k - 1];
		}
		if(arr2.length == 0) {
			return arr1[k - 1];
		}
		// if k > arr.length we can safely drop array tail since it sorted
		if(k < arr1.length) {
			arr1 = Arrays.copyOfRange(arr1, 0, k);
		}
		if(k < arr2.length) {
			arr2 = Arrays.copyOfRange(arr2, 0, k);
		}
		System.out.println("arr1 after drop: " + Arrays.toString(arr1));
		System.out.println("arr2 after drop: " + Arrays.toString(arr2));
		int middle1 = middle(0, arr1.length - 1);
		int middle2 = middle(0, arr2.length - 1);
		System.out.println("middle1: " + arr1[middle1] + ", middle2: " + arr2[middle2]);
		//comparing middles of both arrays and passing smaller as a first argument
		if(arr1[middle1] < arr2[middle2]) {
			return sub1(arr1, middle1, arr2, middle2, k);
		} else {
			return sub1(arr2, middle2, arr1, middle1, k);
		}
	}

	private static int sub1(int[] smaller, int smallerMiddle, int[] bigger, int biggerMiddle, int k) {
		System.out.println("smaller in sub1: " + Arrays.toString(smaller));
		System.out.println("bigger in sub1: " + Arrays.toString(bigger));
		System.out.println("smallerMiddle: " + smallerMiddle + ", biggerMiddle: " + biggerMiddle + ", k:" + k);
		/**
		 * Main trick:
		 *  calculating minimal and maximum positions at which middles can occur in MERGED array:
		 *      smallerLow  - can't occur earlier than checked(smallerMiddle) - because all elements
		 *          before it is going to be before it in merged array as well.
		 *      smallerHi - can't be later than smaller+bigger middles - because all elements in both
		 *          sub arrays after middles are going to be after it in merged array
		 *      biggerLow - can't be earlier than smaller+bigger middles - because all elements before
		 *          middles in both sub arrays are going to be before it in merged array
		 *      biggerHi  - can't be later than whole small array plus bigger - because we only
		 *          know that element from bigger sub array are going to be after it in merged array
		 *
		 *  to throw array portions we need carefully compare k with ranges from previous step:
		 *      If k smaller than smallest possible position of the middle we can safely
		 *      drop middle and all element after
		 *      if k bigger than biggest possible position we can safely drop middle and
		 *      all element before it
		 *
		 *      Basically we comparing
		 *      1: k with sub array low bound and if it's smaller
		 *          we can safely throw all after middle including middle
		 *      2: k with sub array hi bound and if it's bigger
		 *          we can safely throw all before middle including middle
		 *      repeat for second sub array
		 *
		 */
		/**
		 * smallerLow = earliestPossiblePositionOfTheMiddleElementInMergedArray
		 * smallerHi = latestPossiblePositionOfTheMiddleElementInMergedArray
		 */
		int smallerLow = smallerMiddle + 1;
		int smallerHi = smallerMiddle + biggerMiddle + 1;
		int biggerLow = smallerMiddle + biggerMiddle + 2;
		int biggerHi = smaller.length + biggerMiddle + 1;

		System.out.println(smallerLow + " " + smallerHi + " " + biggerLow + " " + biggerHi);
		if(k > smallerHi || k < smallerLow) {
			if (k > smallerHi) {
				smaller = Arrays.copyOfRange(smaller, smallerMiddle + 1, smaller.length);
				System.out.println("smaller --k " + k + " " + smallerMiddle);
				k = k - smallerMiddle - 1;
			}
			if (k < smallerLow) {
				System.out.println("smaller ");
				smaller = Arrays.copyOfRange(smaller, 0, smallerMiddle);
			}
		} else {
			if (k > biggerHi) {
				bigger = Arrays.copyOfRange(bigger, biggerMiddle + 1, bigger.length);
				System.out.println("bigger --k " + k + " " + biggerMiddle);
				k = k - biggerMiddle - 1;
			}
			if (k < biggerLow) {
				System.out.println("bigger k: " + k + ", biggerLow " + biggerLow);
				bigger = Arrays.copyOfRange(bigger, 0, biggerMiddle);
			}
		}

		return subRoutine(smaller, bigger, k);
//		if(k > smallerMiddle && k < biggerMiddle) {
//			System.out.println("middle");
//			return subRoutine(
//					Arrays.copyOfRange(smaller, smallerMiddle + 1, smaller.length),
//					Arrays.copyOfRange(bigger, 0, biggerMiddle),
//					k - smallerMiddle
//			);
//		} else if(k > smallerMiddle) {
//			System.out.println("> smaller");
//			return subRoutine(
//					Arrays.copyOfRange(smaller, smallerMiddle + 1, smaller.length),
//					bigger,
//					k - smallerMiddle
//			);
//		} else {
//			System.out.println("< bigger");
//			return subRoutine(
//					smaller,
//					Arrays.copyOfRange(bigger, 0, biggerMiddle),
//					k
//			);
//		}
	}


//	private static int subRoutine(int[] arr1, int offset1, int cap1, int[] arr2, int offset2, int cap2, int k) {
//		System.out.println("offset1: " + offset1 + ", cap1 " + cap1);
//		System.out.println("offset2: " + offset2 + ", cap2 " + cap2);
////		if(cap1 - offset1 <= 2 && cap2 - offset2 <= 2) {
////			int[] result1 = new int[2];
////			result1[0] = arr1[offset1];
////			result1[1] = arr1[offset1 + 1];
////			result1[2] = arr2[offset2];
////			result1[3] = arr2[offset2 + 1];
////			Arrays.sort(result1);
////			return result1[k - (offset1 + offset2)];
////
////		}
//
//		if(cap1 - offset1 < 1) {
//			int index = k - offset1;
//			return arr2[index];
//		}
//		if(cap2 - offset2 < 1) {
//			int index = k - offset2;
//			return arr1[index];
//		}
////		if(offset1 + offset2  + 2 == k - 1) {
////			if(offset1 == arr1.length - 1) {
////				return arr2[offset2 + 1];
////			}
////			if(offset2 == arr2.length - 1) {
////				return arr1[offset1 + 1];
////			}
////			return Math.min(arr1[offset1 + 1], arr2[offset2 + 1]);
////		}
//		int middle1 = middle(offset1, cap1);
//		int middle2 = middle(offset2, cap2);
//		System.out.println("middle1: " + middle1 + ", middle2: " + middle2);
//		System.out.println("middle1_val: " + arr1[middle1] + ", middle2_val: " + arr2[middle2]);
//		if(arr1[middle1] < arr2[middle2]) {
//			return subRoutine(arr1, middle1, cap1, arr2, offset2, middle2 + 1, k);
//		} else {
//			return subRoutine(arr1, offset1, middle1 + 1, arr2, middle2, cap2, k);
//		}
//	}

	private static int middle(int low, int hi) {
		return (low + ((hi - low) / 2));
//		return low + (int) Math.ceil((double)(hi - low - 1) / 2);
	}

//	private static void version1(int[] arr1, int[] arr2) {
//		int n = arr1.length + arr2.length;
//		int k = n / 2;
//		int effectiveK = (int) Math.ceil((double)k / 2);
//		int effectivePosition = -1;
//		System.out.println("K: " + k);
//		System.out.println("effK: " + effectiveK);
//		System.out.println("arr1 eff: " + arr1[effectiveK - 1]);
//		System.out.println("arr2 eff: " + arr2[effectiveK - 1]);
//		int kTh;
//		if(arr1[effectiveK - 1] < arr2[effectiveK - 1]) {
//			kTh = v1Sub(arr1, effectiveK - 1, arr1.length, arr2, 0, effectiveK, k);
//			System.out.println("smaller: " + Arrays.toString(arr1));
//			System.out.println("bigger: " + Arrays.toString(arr2));
//		} else {
//			kTh = v1Sub(arr2, effectiveK - 1, arr2.length, arr1, 0, effectiveK, k);
//			System.out.println("smaller: " + Arrays.toString(arr2));
//			System.out.println("bigger: " + Arrays.toString(arr1));
//		}
//
//
//
//		System.out.println("kTh: " + kTh);
//	}


//	private static int v1Sub(int[] smaller, int offsetSmaller, int smallerCap, int[] bigger, int offsetBigger, int biggerCap, int k) {
//		System.out.println("offsets: " + offsetSmaller + " " + offsetBigger);
//		System.out.println("caps: " + smallerCap + " " + biggerCap);
//		if(offsetSmaller + offsetBigger + 2 == k) {
//			if(offsetBigger == 0) {
//				return smaller[offsetSmaller + 1] < bigger[offsetBigger]
//						? smaller[offsetSmaller + 1]
//						: smaller[offsetSmaller];
//			}
//			return Math.max(smaller[offsetSmaller], bigger[offsetBigger]);
//		}
////		int newSmallerOffset = offsetSmaller + (int) Math.ceil((double)(smallerCap - offsetSmaller) / 2);
//		int newSmallerOffset = getNewOffset(offsetSmaller, smallerCap);
//		System.out.println("newSmallerOff: " + newSmallerOffset);
//		int smallerTry = smaller[newSmallerOffset - 1];
//
////		int newBiggerOffset = offsetBigger + (int) Math.ceil((double)(biggerCap - offsetBigger) / 2);
//		int newBiggerOffset = getNewOffset(offsetBigger, biggerCap);
//		System.out.println("newBiggerOff: " + newBiggerOffset);
//		if(newBiggerOffset == 0) {
//			return smaller[smaller.length - 1];
//		}
//		int biggerTry = bigger[newBiggerOffset - 1];
//		System.out.println("smaller try: " + smallerTry);
//		System.out.println("bigger try: " + biggerTry);
//		int newBiggerCap = newBiggerOffset;
//		int newSmallerCap = newSmallerOffset;
//		if(smallerTry < biggerTry) {
//			return v1Sub(smaller, newSmallerOffset - 1, smallerCap, bigger, offsetBigger, newBiggerCap, k);
//		}
//		if(smallerTry > biggerTry) {
//			return v1Sub(smaller, offsetSmaller, newSmallerCap, bigger, newBiggerOffset - 1, biggerCap, k);
//		}
//		throw new RuntimeException("asd");
//	}
//
//	private static int getNewOffset(int offset, int cap) {
//		System.out.println("in get new offset " + offset + " " + cap);
//		if(cap - offset == 2) {
//			return offset + 1;
//		}
//		return offset + (int) Math.ceil((double)(cap - offset) / 2);
//	}
}
