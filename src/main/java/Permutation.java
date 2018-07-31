import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 */

public class Permutation {
	public static void main(String[] args) {
		int count = Integer.parseInt(args[0]);
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		while(!StdIn.isEmpty()) {
			String str = StdIn.readString();
			queue.enqueue(str);
		}
		for(int i = 0; i < count; i++) {
			System.out.println(queue.dequeue());
			Arrays.sort(new int[1]);
		}
	}
}
