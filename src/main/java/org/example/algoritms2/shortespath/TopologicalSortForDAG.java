package org.example.algoritms2.shortespath;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

public class TopologicalSortForDAG implements Iterable<Integer> {
	private Deque<Integer> stack;
	private boolean[] marked;

	public TopologicalSortForDAG(WeightenedDigraph digraph) {
		stack = new ArrayDeque<>();
		marked = new boolean[digraph.size()];
		for(int i = 0; i < digraph.size(); i++) {
			if(!marked[i]) {
				marked[i] = true;
				dfs(digraph, i);
			}
		}
	}

	private void dfs(WeightenedDigraph digraph, int vertex) {
//		System.out.println("visiting node: " + vertex);
		for(WeightenedDirectedEdge edge: digraph.adj(vertex)) {
//			System.out.println("adj edge: " + edge);
			if(!marked[edge.getTo()]) {
				marked[edge.getTo()] = true;
				dfs(digraph, edge.getTo());
			}
		}
//		System.out.println("marked: " + Arrays.toString(marked));
//		System.out.println("pushing: " + vertex);
		stack.push(vertex);
	}

	@Override
	public Iterator<Integer> iterator() {
		return stack.iterator();
	}
}
