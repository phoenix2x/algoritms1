package org.example.algoritms.stacksandqueues;

import edu.princeton.cs.algs4.Stack;

/**
 * Queue with two stacks. Implement a queue with two stacks so that each queue operations takes
 * a constant amortized number of stack operations.
 *
 */

public class QueueWithTwoStacks {
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(1);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		System.out.println(queue.dequeue());
		queue.enqueue(6);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
	}

	private static class Queue<T> {
		private Stack<T> forward = new Stack<>();
		private Stack<T> reverse = new Stack<>();

		public void enqueue(T item) {
			forward.push(item);
		}

		public T dequeue() {
			if(reverse.isEmpty() && !forward.isEmpty()) {
				int size = forward.size();
				for(int i = 0; i < size; i++) {
					reverse.push(forward.pop());
				}
			}
			return reverse.pop();
		}
	}
}
