package org.example.algoritms.elementarysorts;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Permutation. Given two integer arrays of size n,
 * design a subquadratic algorithm to determine whether one is a permutation of the other.
 * That is, do they contain exactly the same entries but, possibly, in a different order.
 */

public class Permutation {
	private static int[] a = new int[]{1,2,3,4};
	private static int[] b = new int[]{1,2,4,4};

	public static void main(String[] args) {
		boolean permutation = true;
		Arrays.sort(a);
		Arrays.sort(b);
		for (int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) {
				permutation = false;
				break;
			}
		}
		System.out.println("Permutation: " + permutation);
	}
}
