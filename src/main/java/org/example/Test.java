package org.example;

import edu.princeton.cs.algs4.In;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Test {
	public static void main(String[] args) {
//		varargsTest(new ArrayList<>());

//		waitTest();

//		latch();

//		getField();
//		getField();
//		getField();

//		linkHashMap();


//		Stream<List<Integer>> lists = ContiguousSublists.testContiguousSublists(Arrays.asList(1,2,3,4,5));
//		lists.forEach(System.out::println);

//		toInt("FF", 16);
//		fromInt("31", 16);

//		int arr[] = {6,6,4};
//		int n = arr.length;
//		System.out.println(findGCD(arr, n));
//		System.out.println(50%100); //50%100=50 100%50=0
//
//		int a = 28;
//		int b = 70;
//		while(b != 0) {
//			int temp = b;
//			b = a % b;
//			a = temp;
//		}
//		System.out.println(a);

		String hex = "F";



//		System.out.println(Integer.valueOf(hex, 16));
		System.out.println(Integer.toBinaryString(12));
		System.out.println(Integer.toBinaryString(-12));
		System.out.println(Integer.toBinaryString(~12));
		System.out.println(Integer.toBinaryString(-12 >>>2));
//		System.out.println(Integer.toBinaryString(2));
	}

	//gcd(50,100) = 50 // gcd(100,50) =
	static int gcd(int a, int b) {
		if (a == 0)
			return b;
		return gcd(b % a, a);
	}

	// Function to find gcd of array of
	// numbers
	static int findGCD(int arr[], int n) {
		int result = arr[0];
		for (int i = 1; i < n; i++)
			result = gcd(arr[i], result);

		return result;
	}

	//JAVA 9
//	private static void curl(String url) {
//		try(InputStream in = new URL(url).openStream()) {
//			in.transferTo(System.out);
//		}
//	}

	private static void toInt(String str, int base) {
		int result = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			int digit = Character.getNumericValue(str.charAt(i));
			result += digit * Math.pow(base, i);
		}
		System.out.printf("str: %s , base: %s%n", str, base);
		System.out.println(result);
	}

	private static void fromInt(String intStr, int base) {
		StringBuilder result = new StringBuilder();
		int digit = 0;
		for (int i = 0; i < intStr.length(); i++) {
			digit = Integer.valueOf(String.valueOf(digit) + intStr.charAt(i));

			while (digit >= base) {
				int remider = digit % base;
				digit /= base;
				result.append(toHexIfNeeded(remider));

			}

		}
		result.append(toHexIfNeeded(digit));
		System.out.printf("int: %s ,base: %s%n", intStr, base);
		System.out.println(result.reverse().toString());
	}


	private static String toHexIfNeeded(int digit) {
		switch (digit) {
			case 10:
				return "A";
			case 11:
				return "B";
			case 12:
				return "C";
			case 13:
				return "D";
			case 14:
				return "E";
			case 15:
				return "F";
		}
		return Integer.toString(digit);
	}


	private static class Bean {
		private int prop;

		public Bean(int prop) {
			this.prop = prop;
		}

		public int getProp() {
			return prop;
		}
	}

	private static void varargsTest(List<String>... stringLists) {
		List<Integer> integers = new ArrayList<>();
		integers.add(42);
		Object[] objects = stringLists;
		objects[0] = integers;
		String s = stringLists[0].get(0);
		System.out.println(s);
	}

	private static void dynamicCastTest() {
		Class<?> clazz = String.class;
		clazz.cast("");
	}

	private static void checkedCollections() {
		Collections.checkedSet(new HashSet<>(), String.class);
	}

	private static synchronized void waitTest() {
		for (int i = 0; i < 10; i++) {
			try {
				Test.class.wait(1000);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
			System.out.println("loop");
		}
	}

	private static void latch() {
		CountDownLatch latch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			try {
				latch.await(1, SECONDS);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
			System.out.println("loop");
		}
	}

	private static void executor() {
		CompletionService<String> completionService = new ExecutorCompletionService<>(Executors.newCachedThreadPool());
//		completionService.
	}

	private static void concurentColledtions() {
//		Collections.synchronizedMap(new HashMap<>());
//		new ConcurrentHashMap<>();
	}


	//Static field lazy init, holder class idiom
	private static class FieldHolder {
		static final String field = computeValue();

		private static String computeValue() {
			System.out.println("init");
			return "asdf" + ThreadLocalRandom.current();
		}

		static String getField() {
			return field;
		}
	}

	private static String getField() {
		System.out.println("getField");
		return FieldHolder.getField();
	}

	private static void linkHashMap() {
		int i = 0b101;
//		i >>= 2;
		System.out.println(i);
		System.out.println(0b110 & 1);
	}


	//Observation that contiguous sublists is just a prefixes of the suffixes(or suffixes of the prefixes)
	// where prefixes is all contiguous sublists containing first element
	// and suffixes is all contiguous sublists containing last element
	//Eg: list - (a,b,c)
	// prefixes - (a),(a,b),(a,b,c)
	// suffixes - (a,b,c),(b,c),(c)
	private static class ContiguousSublists {
		public static <E> Stream<List<E>> testContiguousSublists(List<E> list) {
			return Stream.concat(Stream.of(emptyList()),
					prefixes(list).flatMap(ContiguousSublists::suffixes)
			);
		}

		private static <E> Stream<List<E>> prefixes(List<E> list) {
			return IntStream.rangeClosed(1, list.size())
					.mapToObj(end -> list.subList(0, end));
		}

		private static <E> Stream<List<E>> suffixes(List<E> list) {
			return IntStream.range(0, list.size())
					.mapToObj(start -> list.subList(start, list.size()));
		}
	}

}
