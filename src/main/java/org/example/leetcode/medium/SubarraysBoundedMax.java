package org.example.leetcode.medium;

import java.util.Arrays;

public class SubarraysBoundedMax {
	public static void main(String[] args) {
		SubarraysBoundedMax test = new SubarraysBoundedMax();
		int[] arr = new int[]{3,2,1,1,4,1};
		int l = 2;
		int r = 4;
		int result = test.numSubarrayBoundedMax(arr, l, r);
		System.out.println("result: " + result);
	}

	/**
	 * 3 -      [3]                                                     - 1
	 * 32 -     [3][2][32]                                              - 3 - +(length + 1)
	 * 321 -    [3][2][32][21][321]                                     - 5 - +(lengthOfValidSubArray)
	 * 3211 -   [3][2][32][21][321][211][3211]                          - 7 - +(lengthOfValidSubArray)
	 * 32114 -  [3][2][4][32][21][14][321][211][114][3211][2114][32114] - 12 - +(length + 1)
	 * 321141 - [3][2][4][32][21][14][41][321][211][114][141][3211][2114][1141][32114][21141][321141] - 17
	 */
	public int numSubarrayBoundedMax(int[] A, int L, int R) {
		int result = 0;
		int currentLengthOfMinMaxBounded = 0;
		int currentLengthOfMaxBounded = 0;
		for(int i = 0; i < A.length; i++) {
			if(A[i] <= R && A[i] >= L) { // minMax bounded item - factorial
				currentLengthOfMaxBounded++;
				currentLengthOfMinMaxBounded = currentLengthOfMaxBounded;
				result += currentLengthOfMaxBounded;
			} else if(A[i] <= L) { // item less then min - adding length of valid sub array
				currentLengthOfMaxBounded++;
				result += currentLengthOfMinMaxBounded;
			} else { // item bigger than max - we're done with sub array
				currentLengthOfMinMaxBounded = 0;
				currentLengthOfMaxBounded = 0;
			}
		}
		return result;
	}
}
