package org.example.algoritms.elementarysorts;

import java.util.*;
import java.util.stream.Stream;

/**
 * Intersection of two sets. Given two arrays a[] and b[],
 * each containing n distinct 2D points in the plane,
 * design a subquadratic algorithm to count the number of points that are
 * contained both in array a[] and array b[].
 *
 * 1 5 6 7
 * 1 2 5 6
 */

public class IntersectionOfTwoSets {
	private static Point[] a = new Point[]{ new Point(1,2), new Point(3,4), new Point(3,6) };
	private static Point[] b = new Point[]{ new Point(3,4), new Point(3,6), new Point(7,6) };
	public static void main(String[] args) {
		int count = 0;
		Point[] joinedArr = Stream.of(a, b).flatMap(Stream::of).toArray(Point[]::new);
		Arrays.sort(joinedArr);
		int i = 0;
		while(i < joinedArr.length - 1) {
			if(joinedArr[i].equals(joinedArr[i + 1])) {
				count++;
				i += 2;
			} else {
				i++;
			}
		}
		System.out.println("Count: " + count);
	}

	private static class Point implements Comparable<Point> {
		private static final Comparator<Point> comparator = Comparator.comparing(Point::getX)
							.thenComparing(Point::getY);
		private int x;
		private int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Point point = (Point) o;
			return x == point.x &&
					y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public int compareTo(Point o) {
			return comparator.compare(this, o);
		}

		@Override
		public String toString() {
			return "x=" + x +
					", y=" + y;
		}
	}
}
