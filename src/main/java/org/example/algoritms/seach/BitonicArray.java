package org.example.algoritms.seach;

/**
 * Search in a bitonic array. An array is bitonic
 * if it is comprised of an increasing sequence of integers followed
 * immediately by a decreasing sequence of integers. Write a program that,
 * given a bitonic array of n distinct integer values,
 * determines whether a given integer is in the array.
 *
 * Standard version: Use ∼3lgn compares in the worst case.
 *
 * Solution:
 * 1. find biggest element - log(n)
 * 2. use binarySearch for left side
 * 3. if needed use binarySearch for right side
 *
 * Hints: Standard version.
 * First, find the maximum integer using ∼1lgn compares—
 * this divides the array into the increasing and decreasing pieces.
 *
 * Signing bonus. Do it without finding the maximum integer
 */

public class BitonicArray {

    public static void main(String[] args) {
        int[] bitonic = new int[]{-5, -4, -3, 20, 25, 18, 3, 0, -1, -6, -8};
        int search = 80;
        int biggestIndex = findBiggest(bitonic);
        System.out.println("biggest index: " + biggestIndex);
        System.out.println("biggest result: " + bitonic[biggestIndex]);
        int result = -1;
        if(search < bitonic[biggestIndex]) {
            result = binarySearch(bitonic, search, biggestIndex);
        }

        System.out.println("result: " + result);
    }

    private static int findBiggest(int[] data) {
        int leftBound = 0;
        int rightBound = data.length - 1;
        return find(data, leftBound, rightBound);
    }
    private static int find(int[] data, int leftBound, int rightBound) {
        int middleIndex = (leftBound + rightBound) / 2;
        System.out.println("left: " + leftBound + " right: " + rightBound + " middle: " + middleIndex);
        if(middleIndex == leftBound || middleIndex == rightBound) {
            System.out.println("index equals bound");
            return middleIndex;
        }
        int middleData = data[middleIndex];
        if(data[middleIndex - 1] > middleData) {
            return find(data, leftBound, middleIndex);
        }
        if(data[middleIndex + 1] > middleData) {
            return find(data, middleIndex, rightBound);
        }
        return middleIndex;
    }
    private static int binarySearch(int[] data, int search, int biggestIndex) {
        int leftBound = 0;
        int rightBound = data.length - 1;
        int result = binarySearchLeft(data, search, leftBound, biggestIndex);
        if(result != -1) {
            return result;
        }
        return binarySearchRight(data, search, biggestIndex + 1, rightBound);
    }
    private static int binarySearchLeft(int[] data, int search, int startIndex, int finishIndex) {
        int middleIndex = (startIndex + finishIndex) / 2;
        if(middleIndex == startIndex || middleIndex == finishIndex) {
            return -1;
        }
        int middle = data[middleIndex];
        if(middle == search) {
            return middleIndex;
        }
        if(middle < search) {
            return binarySearchLeft(data, search, middleIndex, finishIndex);
        }
        return binarySearchLeft(data, search, startIndex, middleIndex);
    }

    private static int binarySearchRight(int[] data, int search, int startIndex, int finishIndex) {
        int middleIndex = (startIndex + finishIndex) / 2;
        if(middleIndex == startIndex || middleIndex == finishIndex) {
            return -1;
        }
        int middle = data[middleIndex];
        if(middle == search) {
            return middleIndex;
        }
        if(middle > search) {
            return binarySearchLeft(data, search, middleIndex, finishIndex);
        }
        return binarySearchLeft(data, search, startIndex, middleIndex);
    }
}
