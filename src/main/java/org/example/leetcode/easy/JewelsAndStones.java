package org.example.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
	public static void main(String[] args) {
		JewelsAndStones test = new JewelsAndStones();
		System.out.println(test.numJewelsInStones("aA", "vsdAsdaA"));
	}
	public int numJewelsInStones(String J, String S) {
//		Set<Character> jewels = new HashSet<>();
//		for(char item: J.toCharArray()) {
//			jewels.add(item);
//		}
//		int result = 0;
//		for (char str : S.toCharArray()) {
//			if (jewels.contains(str)) {
//				result++;
//			}
//		}
//		return result;
		boolean[] chars = new boolean[255];
		for(int i = 0; i < J.length(); i++) {
			chars[J.charAt(i)] = true;
		}
		int result = 0;
		for(int i = 0; i < S.length(); i++) {
			if(chars[S.charAt(i)]) {
				result++;
			}
		}
		return result;
	}
}
