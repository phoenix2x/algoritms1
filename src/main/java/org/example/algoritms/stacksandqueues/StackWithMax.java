package org.example.algoritms.stacksandqueues;

/**
 * Stack with max. Create a data structure that efficiently supports the stack operations (push and pop)
 * and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.
 */

public class StackWithMax {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(5);
		stack.push(3);
		stack.push(10);
		stack.pop();
		System.out.println(stack.max());
	}

	private static class Stack<T extends Comparable<? super T>> {
		private edu.princeton.cs.algs4.Stack<T> data = new edu.princeton.cs.algs4.Stack<>();
		private edu.princeton.cs.algs4.Stack<T> maxes = new edu.princeton.cs.algs4.Stack<>();

		public T pop() {
			maxes.pop();
			return data.pop();
		}

		public void push(T item) {
			data.push(item);
			if(maxes.isEmpty() || item.compareTo(maxes.peek()) > 0) {
				maxes.push(item);
			} else {
				maxes.push(maxes.peek());
			}
		}

		public T max() {
			return maxes.peek();
		}
	}

//	private static class Stack<T extends Comparable<? super T>> {
//		private int head = 0;
//		private T[] arr;
//
//		@SuppressWarnings("unchecked")
//		public Stack() {
//			this.arr = (T[]) new Comparable[10];
//		}
//
//		public T pop() {
//			if(head == arr.length / 4) {
//				resize(arr.length / 2);
//			}
//			return arr[--head];
//		}
//
//		public void push(T item) {
//			if(arr.length == head - 1) {
//				resize(arr.length * 2);
//			}
//			arr[head++] = item;
//		}
//
//		public T max() {
//			T max = arr[0];
//			for (int i = 0; i <= head - 1; i++) {
//				if(arr[i].compareTo(max) > 0) {
//					max = arr[i];
//				}
//			}
//			return max;
//		}
//
//		@SuppressWarnings("unchecked")
//		private void resize(int newSize) {
//			T[] newArr = (T[]) new Object[newSize];
//			for(int i = 0; i < arr.length; i++) {
//				newArr[i] = arr[i];
//			}
//			arr = newArr;
//		}
//	}
}
