package org.example.algoritms2.shortespath;

import java.util.*;

public class DijkstraShortestPath {
	private double[] distTo;
	private WeightenedDirectedEdge[] pathTo;
	private IndexedMinPQ<Double> pq;
	private int start;

	public static void main(String[] args) {
		WeightenedDigraph digraph = new WeightenedDigraph(4);
		digraph.addEdge(new WeightenedDirectedEdge(1, 2, 1.0));
		digraph.addEdge(new WeightenedDirectedEdge(2, 3, 2.0));
		digraph.addEdge(new WeightenedDirectedEdge(1, 3, 4.0));

		DijkstraShortestPath sp = new DijkstraShortestPath(digraph, 1);

		System.out.println(sp.pathTo(3));
	}

	public DijkstraShortestPath(WeightenedDigraph digraph, int start) {
		this.start = start;
		distTo = new double[digraph.size()];
		pathTo = new WeightenedDirectedEdge[digraph.size()];
		pq = new IndexedMinPQ<>(digraph.size());

		for(int i = 0; i < digraph.size(); i++) {
			distTo[i] = Double.MAX_VALUE;
		}

		pq.add(0.0, start);
		distTo[start] = 0.0;

		while(!pq.isEmpty()) {
			for(WeightenedDirectedEdge edge: digraph.adj(pq.removeMinIndex())) {
				relaxEdge(edge);
			}
		}

	}

	private void relaxEdge(WeightenedDirectedEdge edge) {
		int toNode = edge.getTo();
		int fromNode = edge.getFrom();
//		System.out.println("old distTo: " + distTo[toNode]);
//		System.out.println("new distTo: distTo from node " + distTo[fromNode] + " plus " + edge.getWeight());
		if(distTo[toNode] > distTo[fromNode] + edge.getWeight()) {
			distTo[toNode] = distTo[fromNode] + edge.getWeight();
			pathTo[toNode] = edge;
			if(pq.containsIndex(toNode)) {
				System.out.println("shorter path found from " + fromNode + " to " + toNode);
				pq.decreaseKey(distTo[fromNode] + edge.getWeight(), toNode);
			} else {
				System.out.println("new path from: " + fromNode + " to " + toNode);
				pq.add(distTo[fromNode] + edge.getWeight(), toNode);
			}
		} else {
			System.out.println("Already know shortest path, ignoring.");
		}
	}

	public double distTo(int finish) {
		return distTo[finish];
	}

	public Iterable<WeightenedDirectedEdge> pathTo(int finish) {
		Deque<WeightenedDirectedEdge> pathToList = new ArrayDeque<>();
		WeightenedDirectedEdge edge = pathTo[finish];
		while(edge.getFrom() != start) {
			pathToList.add(edge);
			edge = pathTo[edge.getFrom()];
		}
		pathToList.add(edge);
		return pathToList;
	}
}
