package org.example.algoritms.strings;

import java.util.Arrays;

public class KeyIndexCounting {
	private static final int A_CHAR_CODE = "a".charAt(0);

	public static void main(String[] args) {
		sort("cfgnyuasdfhrjkdvdaabababa");
	}

	public static void sort(String a) {
		int englishLowercaseCharsCount = 26;
		int[] count = new int[englishLowercaseCharsCount + 1];

		// counting count of each char
		// [0,'a count','b count','c count',...,'z count']
		for (int i = 0; i < a.length(); i++) {
			count[getCharCode(a.charAt(i)) + 1]++;
		}

		//compute starting index of every char
		// [0,'number of a(b's start here)','number of a+b(c's start here)'...]
		for (int i = 0; i < englishLowercaseCharsCount; i++) {
			count[i + 1] += count[i];
		}
//		System.out.println(Arrays.toString(count));

		// filling aux array using indices from count array;
		// incrementing index in count array for a given char every time we use it
		// [a,a,a,b,b,b...]
		char[] aux = new char[a.length()];
		for (int i = 0; i < a.length(); i++) {
			aux[count[getCharCode(a.charAt(i))]++] = a.charAt(i);
		}

		//optionally coping aux array into input array;
		System.out.println(Arrays.toString(aux));
	}

	public static int getCharCode(int charCode) {
		return charCode - A_CHAR_CODE;
	}
}
