package org.example.crackingcodeinterview.stringarrays;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

import static org.example.crackingcodeinterview.stringarrays.StringPermutations.ALPHABET_SIZE;
import static org.example.crackingcodeinterview.stringarrays.StringPermutations.getCharcode;

public class IsUnique {
	public static void main(String[] args) {
		System.out.printf("Is Unique: %s%n", withHashSet("asds"));
		System.out.printf("Is Unique: %s%n", withSort("asd"));
		System.out.printf("Is Unique: %s%n", withLong("asds"));
		System.out.printf("Is Unique: %s%n", withBitVector("asdga"));
		System.out.printf("Is Unique: %s%n", withBooleanArray("asdg"));

	}

	public static boolean withHashSet(String str) {
		Set<Character> chars = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			if(!chars.add(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	public static boolean withSort(String str) {
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		for (int i = 1; i < chars.length; i++) {
			if(chars[i] == chars[i - 1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Can be used if there is less than 65
	 * Example: just lowercase letters
	 * @param str
	 * @return
	 */
	public static boolean withLong(String str) {
		long bits = 0;
		for (int i = 0; i < str.length(); i++) {
			int charCode = 1 << getCharcode(str.charAt(i));
			if((bits & charCode) != 0) {
				return false;
			}
			bits |= charCode;
		}
		return true;
	}

	public static boolean withBitVector(String str) {
		BitSet bitSet = new BitSet();
		for (int i = 0; i < str.length(); i++) {
			int charCode = getCharcode(str.charAt(i));
			if(bitSet.get(charCode)) {
				return false;
			}
			bitSet.set(charCode);
		}
		return true;
	}
	private static boolean withBooleanArray(String str) {
		boolean[] bits = new boolean[ALPHABET_SIZE];
		for (int i = 0; i < str.length(); i++) {
			int charCode = getCharcode(str.charAt(i));
			if(bits[charCode]) {
				return false;
			}
			bits[charCode] = true;
		}
		return true;
	}
}
