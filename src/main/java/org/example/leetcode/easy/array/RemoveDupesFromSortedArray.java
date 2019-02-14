package org.example.leetcode.easy.array;

import java.util.Arrays;

public class RemoveDupesFromSortedArray {
	public static void main(String[] args) {
		RemoveDupesFromSortedArray removeDupesFromSortedArray = new RemoveDupesFromSortedArray();
		int[] arr = new int[]{1,2};
//		System.out.println(removeDupesFromSortedArray.removeDuplicatesNaive(arr));
		System.out.println(removeDupesFromSortedArray.removeDuplicatesImproved(arr));
		System.out.println(Arrays.toString(arr));
	}
	public int removeDuplicatesImproved(int[] nums) {
		if(nums.length == 0) {
			return nums.length;
		}
		int i = 0;
		for(int j = i + 1; j < nums.length; j++) {
			if(nums[i] != nums[j]) {
				nums[++i] = nums[j];
			}
		}
		return ++i;
	}

	public int removeDuplicatesNaive(int[] nums) {
		if (nums.length < 2) {
			return nums.length;
		}
		int i = 1;
		while (i < nums.length) {
			if (nums[i] <= nums[i - 1]) {

				int nonDupIndex = findFirstBiggerNonDup(nums, i);

				if(nonDupIndex == nums.length) {
					System.out.println("Didn't find any correct elements");
					break;
				}
				swap(nums, i, nonDupIndex);
			}
			i++;
		}
		return i;
	}
	private static int findFirstBiggerNonDup(int[] arr, int currentIndex) {
		int j = currentIndex;
		while (j < arr.length && arr[currentIndex - 1] >= arr[j]) {
			j++;
		}
		return j;
	}

	private static void swap(int[] arr, int i, int j) {
		System.out.println("swap");
		int swp = arr[i];
		arr[i] = arr[j];
		arr[j] = swp;
	}
}
