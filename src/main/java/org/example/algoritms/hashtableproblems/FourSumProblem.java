package org.example.algoritms.hashtableproblems;

import io.vavr.Tuple2;

import java.util.*;
import java.util.stream.Collectors;

public class FourSumProblem {
	public static void main(String[] args) {
//		int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
//		fourSum(arr);

//		hashCodeEqualsErrors();
//		test();

		int[] arr = new int[]{1, 0, -1, 0, -2, 2};
//		int[] arr = new int[]{-4,-3,-2,-1,0,0,1,2,3,4};
		int target = 0;
		System.out.println(fourSum(arr, target));
	}

	private static void test() {
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			int finalI = i;
			set.stream().allMatch(el -> el != finalI);

		}
		set.stream().map(i -> i).collect(Collectors.toList());
		set.add(1);
		set.add(1);
		set.add(5);
		list.addAll(set);
		set.stream().allMatch(key -> {
			return false;
		});
		for (Integer key : set) {
			System.out.println(key);
		}
	}

	private static void fourSum(int[] arr) {
		Map<Integer, Tuple2<Integer, Integer>> hashMap = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				hashMap.put(arr[i] + arr[j], new Tuple2<>(i, j));

			}
		}
		for (int k = 0; k < arr.length; k++) {
			for (int l = k + 1; l < arr.length; l++) {
				if (hashMap.containsKey(arr[k] + arr[l])) {
					Tuple2<Integer, Integer> indexes = hashMap.get(arr[k] + arr[l]);
					if (indexes._1() != k && indexes._1() != l && indexes._2() != k && indexes._2() != l) {
						System.out.println("found: " + k + " " + l + " " + hashMap.get(arr[k] + arr[l]));
					}
				}
			}
		}
	}

	private static void hashCodeEqualsErrors() {
		Map<Bean1, String> map = new HashMap<>();
		Bean1 bean1 = new Bean1(1, 2);
		map.put(bean1, "1 2");
		System.out.println("equal: " + map.get(new Bean1(1, 2)));
		System.out.println("same: " + map.get(bean1));
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Map<Integer, List<Integer>> pairs = new HashMap<>();
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int key = nums[i] + nums[j];
				List<Integer> set = new ArrayList<>();
				if(pairs.containsKey(key)) {
					set = pairs.get(key);
				}
				set.add(i);
				set.add(j);
				pairs.put(nums[i] + nums[j], set);
			}
		}
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int key = target - (nums[i] + nums[j]);
				if (pairs.containsKey(key)) {
					List<Integer> pair = pairs.get(key);
					int k = 0;
					while(k < pair.size()) {
						int firstIndex = pair.get(k++);
						int secondIndex = pair.get(k++);
						if(firstIndex != i && firstIndex != j && secondIndex != i && secondIndex != j) {
							List<Integer> match = new ArrayList<>();
							match.add(nums[firstIndex]);
							match.add(nums[secondIndex]);
							match.add(nums[i]);
							match.add(nums[j]);
							Collections.sort(match);
							if(!result.contains(match)) {
								result.add(match);
							}
						}
					}
				}
			}
		}
		return result;
	}

	private static class Bean1 {
		private int prop1;
		private int prop2;

		public Bean1(int prop1, int prop2) {
			this.prop1 = prop1;
			this.prop2 = prop2;
		}

		public int getProp1() {
			return prop1;
		}

		public void setProp1(int prop1) {
			this.prop1 = prop1;
		}

		public int getProp2() {
			return prop2;
		}

		public void setProp2(int prop2) {
			this.prop2 = prop2;
		}

		public boolean equals(Bean1 o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Bean1 bean1 = (Bean1) o;
			return prop1 == bean1.prop1 &&
					prop2 == bean1.prop2;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Bean1 bean1 = (Bean1) o;
			return prop1 == bean1.prop1 &&
					prop2 == bean1.prop2;
		}

//		@Override
//		public int hashCode() {
//			return Objects.hash(prop1, prop2);
//		}
	}
}
