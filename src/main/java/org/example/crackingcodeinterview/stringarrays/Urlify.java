package org.example.crackingcodeinterview.stringarrays;

import java.util.Arrays;

public class Urlify {
	private static final char[] ESCAPED_WHITESPACE = "%20".toCharArray();
	public static void main(String[] args) {
		System.out.println(iterate("asd dfg dfg    ", 11));
	}

	public static String iterate(String str, int trueLength) {
		char[] chars = str.toCharArray();
		int finalCharCount = 0;
		int endIndex = str.length() - 1;
		for (int i = trueLength - 1; i >= 0; i--) {
			if(isWhiteSpace(chars[i])) {
				putWhitespace(chars, endIndex);
				endIndex -= ESCAPED_WHITESPACE.length;
				finalCharCount += ESCAPED_WHITESPACE.length;
			} else {
				chars[endIndex--] = chars[i];
				finalCharCount++;
			}
		}
		return new String(Arrays.copyOfRange(chars, chars.length - finalCharCount, chars.length));
	}

	private static boolean isWhiteSpace(char character) {
		return Character.isWhitespace(character);
	}

	private static void putWhitespace(char[] chars, int endIndex) {
		for(char character: ESCAPED_WHITESPACE) {
			chars[endIndex--] = character;
		}
	}
}
