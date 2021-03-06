package org.example.algoritms2.graph;

import java.util.ArrayList;
import java.util.List;

public class DiGraph {
	private List<Integer>[] vetrices;

	@SuppressWarnings("unchecked")
	public DiGraph(int vertices) {
		this.vetrices  = (List<Integer>[]) new List[vertices];
		for(int i = 0; i < this.vetrices.length; i++) {
			this.vetrices[i] = new ArrayList<>();
		}
	}

	public void addEdge(int v, int w) {
		vetrices[v].add(w);
	}

	public Iterable<Integer> adj(int v) {
		return vetrices[v];
	}

	/**
	 *
	 * @return number of vertices
	 */
	public int V() {
		return vetrices.length;
	}

	/**
	 *
	 * @return number of edges
	 */
	public int E() {
		int result = 0;
		for(int i = 0; i < vetrices.length; i++) {
			result += vetrices[i].size();
		}
		return result;
	}

	public DiGraph reverse() {
			DiGraph reversed = new DiGraph(this.V());
		return reversed;
	}
}
