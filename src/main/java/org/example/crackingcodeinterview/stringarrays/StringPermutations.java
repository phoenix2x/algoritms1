package org.example.crackingcodeinterview.stringarrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringPermutations {
	/**
	 * 256 is enough to handle all standard chars plus
	 * some additions like EURO and POUND signs etc...
	 */
	public static final int ALPHABET_SIZE = 256;

	public static void main(String[] args) {
		System.out.printf("Is permutation: %s%n", withSort("asd", "sda"));
		System.out.printf("Is permutation: %s%n", withIntArray("asd", "sda"));
		System.out.printf("Is permutation: %s%n", withHashMap("asdd", "sdda"));
	}
	public static boolean withSort(String str1, String str2) {
		char[] chars1 = str1.toCharArray();
		char[] chars2 = str2.toCharArray();
		Arrays.sort(chars1);
		Arrays.sort(chars2);
		return Arrays.equals(chars1, chars2);
	}

	public static boolean withHashMap(String str1, String str2) {
		if(str1.length() != str2.length()) {
			return false;
		}
		Map<Character, Integer> counts = new HashMap<>();
		for (int i = 0; i < str1.length(); i++) {
			char character = str1.charAt(i);
			counts.putIfAbsent(character, 0);
			counts.compute(character, (c, count) -> ++count);
		}
		for (int i = 0; i < str1.length(); i++) {
			Integer remainCount = counts.computeIfPresent(str2.charAt(i), (c, count) -> --count);
			if(remainCount == null || remainCount < 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean withIntArray(String str1, String str2) {
		if(str1.length() != str2.length()) {
			return false;
		}

		int[] characters = new int[ALPHABET_SIZE];
		for (int i = 0; i < str1.length(); i++) {
			characters[getCharcode(str1.charAt(i))]++;
		}
		for (int i = 0; i < str1.length(); i++) {
			characters[getCharcode(str2.charAt(i))]--;
			if(characters[getCharcode(str2.charAt(i))] < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * get charcode and reduce it if needed
	 * @param character
	 * @return
	 */
	public static int getCharcode(char character) {
//		return character - 'A';
		return character;
	}
}
