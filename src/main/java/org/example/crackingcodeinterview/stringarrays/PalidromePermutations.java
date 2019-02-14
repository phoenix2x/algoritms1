package org.example.crackingcodeinterview.stringarrays;


import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import static org.example.crackingcodeinterview.stringarrays.StringPermutations.ALPHABET_SIZE;

public class PalidromePermutations {
	public static void main(String[] args) {
		System.out.println(hasPalindromePermutations("tcoc otabb"));
	}

	public static boolean hasPalindromePermutations(String str) {
		int[] charCounts = new int[ALPHABET_SIZE];
		int oddCounts = 0;
		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			if (!Character.isWhitespace(currentChar)) {
				charCounts[currentChar]++;
				if (charCounts[currentChar] % 2 == 0) {
					oddCounts--;
				} else {
					oddCounts++;
				}
			}
		}

		return oddCounts <= 1;
	}
}
