package org.example.algoritms.hashtableproblems;

import io.vavr.Tuple2;
import io.vavr.collection.Stream;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FourSumProblem {
	public static void main(String[] args) {
//		int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
//		fourSum(arr);

		int[] arr = new int[]{1, 0, -1, 0, -2, 2};
//		int[] arr = new int[]{-4,-3,-2,-1,0,0,1,2,3,4};
		int target = 0;
		System.out.println(fourSumImproved(arr, target));
	}


	public static List<List<Integer>> fourSumImproved(int[] nums, int target) {
		Map<Integer, List<Integer>> pairs = new HashMap<>();
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int key = nums[i] + nums[j];
				int targetKey = target - key;
				// check if there is already a matching pair
				if (pairs.containsKey(targetKey)) {
					List<Integer> pair = pairs.get(targetKey);
					int k = 0;
					while (k < pair.size()) {
						int firstIndex = pair.get(k++);
						int secondIndex = pair.get(k++);
						if (firstIndex != i && firstIndex != j && secondIndex != i && secondIndex != j) {
							List<Integer> match = new ArrayList<>();
							match.add(nums[firstIndex]);
							match.add(nums[secondIndex]);
							match.add(nums[i]);
							match.add(nums[j]);
							Collections.sort(match);
							if (!result.contains(match)) {
								result.add(match);
							}
						}
					}
				}
				// put this pair in a map for future lookups
				List<Integer> set = new ArrayList<>();
				if (pairs.containsKey(key)) {
					set = pairs.get(key);
				}
				set.add(i);
				set.add(j);
				pairs.put(nums[i] + nums[j], set);
			}
		}
		return result;
	}

//	public static List<List<Integer>> fourSum(int[] nums, int target) {
//		Map<Integer, List<Integer>> pairs = new HashMap<>();
//		List<List<Integer>> result = new ArrayList<>();
//		for (int i = 0; i < nums.length; i++) {
//			for (int j = i + 1; j < nums.length; j++) {
//				int key = nums[i] + nums[j];
//				List<Integer> set = new ArrayList<>();
//				if (pairs.containsKey(key)) {
//					set = pairs.get(key);
//				}
//				set.add(i);
//				set.add(j);
//				pairs.put(nums[i] + nums[j], set);
//			}
//		}
//		for (int i = 0; i < nums.length; i++) {
//			for (int j = i + 1; j < nums.length; j++) {
//				int key = target - (nums[i] + nums[j]);
//				if (pairs.containsKey(key)) {
//					List<Integer> pair = pairs.get(key);
//					int k = 0;
//					while (k < pair.size()) {
//						int firstIndex = pair.get(k++);
//						int secondIndex = pair.get(k++);
//						if (firstIndex != i && firstIndex != j && secondIndex != i && secondIndex != j) {
//							List<Integer> match = new ArrayList<>();
//							match.add(nums[firstIndex]);
//							match.add(nums[secondIndex]);
//							match.add(nums[i]);
//							match.add(nums[j]);
//							Collections.sort(match);
//							if (!result.contains(match)) {
//								result.add(match);
//							}
//						}
//					}
//				}
//			}
//		}
//		return result;
//	}
}
