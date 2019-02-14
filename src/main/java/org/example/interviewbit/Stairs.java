package org.example.interviewbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Example :
 * <p>
 * Input : 3
 * Return : 3
 * <p>
 * Steps :
 *  1: 1 - [1]
 *  2: 2 - [1 1], [2]
 *  3: 3 - [1 1 1], [1 2], [2 1]
 *  4: 5 - [1 1 1 1], [1 2 1], [2 1 1] , [1 1 2], [2 2]
 *  5: 8 - [1 1 1 1 1], [1 2 1 1], [2 1 1 1] , [1 1 2 1], [1 1 1 2], [2 2 1], [1 2 2], [2 1 2]
 *  6: 13 - [1 1 1 1 1 1], [1 2 1 1 1], [2 1 1 1 1] , [1 1 2 1 1], [1 1 1 2 1], [1 1 1 1 2], [2 2 1 1], [1 2 2 1], [1 1 2 2], [1 2 1 2], [2 1 2 1], [2 1 1 2], [2 2 2]
 */
public class Stairs {
	public static void main(String[] args) {
		Stairs stairs = new Stairs();
		System.out.println(stairs.climbStairs(6));
	}
	public int climbStairs(int A) {
		if(A <= 2) {
			return A;
		}
		int[] result = new int[A + 1];
		result[1] = 1;
		result[2] = 2;
		for(int i = 3; i <= A; i++) {
			result[i] = result[i - 1] + result[i - 2];
		}
		return result[A];
	}
}
