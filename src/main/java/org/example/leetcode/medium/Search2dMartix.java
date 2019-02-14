package org.example.leetcode.medium;

public class Search2dMartix {
	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{1 ,4 ,7 ,11,15},
				{2 ,5 ,8 ,12,19},
				{3 ,6 ,9 ,16,22},
				{10,13,14,17,24},
				{17,21,23,26,30},
		};
		Search2dMartix search = new Search2dMartix();
		System.out.println(search.searchMatrix(matrix, 18));
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix.length < 1) {
			return false;
		}
		return searchMatrixSub(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
	}

	public boolean searchMatrixSub(int[][] matrix, int target, int iLo, int iHi, int jLo, int jHi) {
		System.out.println(String.format("%s %s %s %s", iLo, iHi, jLo, jHi));

		// find point on diagonal that might contain target
		if(iHi - iLo == 0 && jHi - jLo == 0) {
			return target == matrix[iLo][jLo];
		}
//		int diagonalIndex = binarySearch(matrix, iLo, iHi, target);
//		System.out.println(diagonalIndex);
//		if(diagonalIndex < 0) {
//			int top = -diagonalIndex;
//			int bottom = top - 1;
//			return searchMatrixSub(matrix, target, top, iHi, jLo, bottom)
//					|| searchMatrixSub(matrix, target, iLo, bottom, top, jHi);
//		}
		int[] diagonalIndex = search(matrix, iLo, iHi, jLo, jHi, target);
		if(diagonalIndex[0] < 0) {
			int topI = -diagonalIndex[0];
			int topJ = -diagonalIndex[1];
			int bottomI = -diagonalIndex[2];
			int bottomJ = -diagonalIndex[3];
			return searchMatrixSub(matrix, target, topI, iHi, jLo, bottomJ)
					|| searchMatrixSub(matrix, target, iLo, bottomI, topJ, jHi);
		}

		return true;
	}
	public int[] search(int[][] arr, int iLo, int iHi, int jLo, int jHi, int target) {
		int i = iLo;
		int j = jLo;
		int prevI = i;
		int prevJ = j;
		while(target > arr[i][j] && (i < iHi || j < jHi)) {
			if(i < iHi) {
				prevI = i++;
			}
			if(j < jHi) {
				prevJ = j++;
			}
		}
		if(target == arr[i][j]) {
			return new int[]{i,j};
		}
		return new int[]{-i, -j, -prevI, -prevJ};
	}

	public int binarySearch(int[][] arr, int low, int hi, int target) {
		int middle = low  + (hi - low) / 2;
//		System.out.println(String.format("bianry: %s %s %s %s", low, hi, middle, arr[middle][middle]));

		if(middle == low || middle == hi) {
			return -hi;
		}
		if(target > arr[middle][middle]) {
			return binarySearch(arr, middle, hi, target);
		}
		if(target < arr[middle][middle]) {
			return binarySearch(arr, low, middle, target);
		}
		return middle;
	}
}
