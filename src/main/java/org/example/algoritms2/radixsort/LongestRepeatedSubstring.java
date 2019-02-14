package org.example.algoritms2.radixsort;

import java.util.Arrays;

public class LongestRepeatedSubstring {
	public static void main(String[] args) {
		findLongestSubstring("asdzxjdgeadjdfkasdzxcdgdk");
	}

	public static void findLongestSubstring(String str) {
		String[] prefixes = new String[str.length()];
		for (int i = 0; i < str.length(); i++) {
			prefixes[i] = str.substring(i);
		}
		System.out.println(Arrays.toString(prefixes));

		Arrays.sort(prefixes);

		System.out.println(Arrays.toString(prefixes));

		String lcs = "";
		for (int i = 0; i < prefixes.length - 1; i++) {
			int length = getCommonSubstringLength(prefixes[i], prefixes[i + 1]);
			if (length > lcs.length()) {
				lcs = prefixes[i].substring(0, length);
			}
		}
		System.out.println(lcs);
	}

	private static int getCommonSubstringLength(String str1, String str2) {
		int length = 0;
		int index = 0;
		while (index < str1.length() && index < str2.length()
				&& str1.charAt(index) == str2.charAt(index)
		) {
			length++;
			index++;
		}
		return length;
	}
}
