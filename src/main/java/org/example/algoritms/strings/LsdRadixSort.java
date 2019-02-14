package org.example.algoritms.strings;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * stably sort equal length strings by last char first, then by preLast etc,
 */
public class LsdRadixSort {
	private static final int A_CHAR_CODE = "a".charAt(0);
	private static final int RADIX = 4;

	public static void main(String[] args) {
		String[] arr = new String[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = randomString();
		}

		sort(arr);

		for (String str : arr) {
			System.out.println(str);
		}
	}

	public static void sort(String[] arr) {
		for (int i = arr[0].length() - 1; i >= 0; i--) {
			sortByChar(arr, i);
		}
	}
	// index counting sort by given char position in each string
	public static void sortByChar(String[] arr, int position) {
		int[] count = new int[RADIX + 1];

		for (int i = 0; i < arr.length; i++) {
			count[getCharCodeIndex(arr[i].charAt(position)) + 1]++;
		}
		for (int i = 0; i < RADIX; i++) {
			count[i + 1] += count[i];
		}
		String[] aux = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			aux[count[getCharCodeIndex(arr[i].charAt(position))]++] = arr[i];
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = aux[i];
		}
	}

	public static int getCharCodeIndex(int charcode) {
		return charcode - A_CHAR_CODE;
	}

	public static String randomString() {
		int aCode = 97;
//		int zCode = 122;
		int zCode = aCode + RADIX; // not a 'z' anymore for simplicity
		int stringLength = 5;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < stringLength; i++) {
			builder.append((char) ThreadLocalRandom.current().nextInt(aCode, zCode));
		}
		return builder.toString();
	}
}
