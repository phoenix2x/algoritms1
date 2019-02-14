package org.example.interviewbit;

/**
 * Given an array of integers, find the length of longest subsequence which is first increasing then decreasing.
 *
 * **Example: **
 *
 * For the given array [1 11 2 10 4 5 2 1]
 *
 * Longest subsequence is [1 2 10 4 2 1]
 *
 * Return value 6
 */
public class LengthLongestSubsequence {
	public static void main(String[] args) {
		int[] arr = new int[]{1, 11, 2, 10, 4, 5, 2, 1};
		int result = findSubsequence(arr);
		System.out.println("Result: " + result);
	}

	private static int findSubsequence(int[] arr) {
		return 1;
	}
}
