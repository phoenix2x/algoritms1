package org.example.leetcode.easy.sort;

import java.util.Arrays;

public class MergeTwoSortedArrays {
	public static void main(String[] args) {
		MergeTwoSortedArrays self = new MergeTwoSortedArrays();

//		int[] arr1 = new int[]{4, 5, 6, 0, 0, 0};
		int[] arr1 = new int[]{-1, 0, 0, 3, 3, 3, 0, 0, 0};
//		int[] arr2 = new int[]{1, 2, 3};
		int[] arr2 = new int[]{1, 2, 2};
		self.merge(arr1, 6, arr2, 3);
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int k = n + m - 1;
		int i = m - 1;
		int j = n - 1;
		while(i >= 0 && j >= 0) {
			if(nums1[i] > nums2[j]) {
				nums1[k--] = nums1[i--];
			} else {
				nums1[k--] = nums2[j--];
			}
		}
		while(i >= 0) {
			nums1[k--] = nums1[i--];
		}
		while(j >= 0) {
			nums1[k--] = nums2[j--];
		}
	}

//	public void merge(int[] nums1, int m, int[] nums2, int n) {
//		if (n == 0) {
//			return;
//		}
//		int j = 0;
//		for (int i = 0; i < m; i++) {
//			if (nums1[i] > nums2[j]) {
//				int swp = nums1[i];
//				nums1[i] = nums2[j];
//				nums2[j] = swp;
//				Arrays.sort(nums2);
//			}
//		}
//		for(int i = m; i < nums1.length; i++) {
//			nums1[i] = nums2[j++];
//		}
//	}
}
