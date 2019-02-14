package org.example.algoritms2.shortespath;

import java.util.ArrayList;
import java.util.List;

public class WeightenedDigraph {
	private List<WeightenedDirectedEdge>[] vertices;

	public WeightenedDigraph(int n) {
		vertices = new List[n];
		for(int i = 0; i < n; i++) {
			vertices[i] = new ArrayList<>();
		}
	}

	public void addEdge(WeightenedDirectedEdge edge) {
		vertices[edge.getFrom()].add(edge);
	}

	public Iterable<WeightenedDirectedEdge> adj(int v) {
		return vertices[v];
	}

	public int size() {
		return vertices.length;
	}
}
