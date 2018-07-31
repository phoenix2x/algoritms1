package org.example.algoritms.seach;

import java.util.Arrays;

/**
 * 3-SUM in quadratic time.
 * Design an algorithm for the 3-SUM problem that takes time proportional to n2 in the worst case.
 * You may assume that you can sort the n integers in time proportional to n2 or better.
 *
 * Hint:
 * Hint: given an integer x and a sorted array a[] of n distinct integers,
 * design a linear-time algorithm to determine
 * if there exists two distinct indices i and j such that a[i]+a[j]==x.
 */

public class ThreeSum {
    public static void main(String[] args) {
        int[] data = new int[]{-3,-2,0,1,2,3,10};
//        Arrays.sort(data);
        int result = findNumberOfTriplesThatSumsToZero(data);
        System.out.println(result);
    }

    private static int findNumberOfTriplesThatSumsToZero(int[] sorterData) {
        int result = 0;
        for (int i = 0; i < sorterData.length - 2; i++) {
            int start  = i + 1;
            int end = sorterData.length - 1;
            int currentValue = sorterData[i];
            while(start < end) {
                int startValue = sorterData[start];
                int endValue = sorterData[end];
                System.out.println(currentValue + " " + startValue + " " + endValue);
//                System.out.println("sum: " + (startValue + endValue + currentValue));

                if((startValue + endValue + currentValue) > 0) {
                    end = end - 1;
                } else if((startValue + endValue + currentValue) < 0){
                    start = start + 1;
                } else if((startValue + endValue + currentValue) == 0) {
                    result += 1;
                    System.out.println("found");
                    start += 1;
                }
            }

        }
        return result;
    }

}
