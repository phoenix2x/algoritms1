package org.example.algoritms2.shortespath.quizes;


import org.example.algoritms2.shortespath.DijkstraShortestPath;
import org.example.algoritms2.shortespath.IndexedMinPQ;
import org.example.algoritms2.shortespath.WeightenedDigraph;
import org.example.algoritms2.shortespath.WeightenedDirectedEdge;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Shortest path with one skippable edge. Given an edge-weighted digraph,
 * design an E*logV algorithm to find a shortest path from s to t
 * where you can change the weight of any one edge to zero. Assume the edge weights are nonnegative.
 */
public class ShortestWithOneSkip {
	private double[] distToOriginal;
	private double[] distToWithSkip;
	private WeightenedDirectedEdge[] pathToOriginal;
	private WeightenedDirectedEdge[] pathToWithSkip;
	private IndexedMinPQ<Double> pq;
	private int start;

	public static void main(String[] args) {
		WeightenedDigraph digraph = new WeightenedDigraph(6);
		digraph.addEdge(new WeightenedDirectedEdge(1, 2, 1.0));
		digraph.addEdge(new WeightenedDirectedEdge(2, 3, 2.0));
		digraph.addEdge(new WeightenedDirectedEdge(3, 4, 2.0));
		digraph.addEdge(new WeightenedDirectedEdge(1, 5, 6.0));
		digraph.addEdge(new WeightenedDirectedEdge(5, 4, 2.0));

		ShortestWithOneSkip sp = new ShortestWithOneSkip(digraph, 1);

		System.out.println(sp.pathTo(4));
	}

	public ShortestWithOneSkip(WeightenedDigraph digraph, int start) {
		this.start = start;
		distToOriginal = new double[digraph.size()];
		distToWithSkip = new double[digraph.size()];
		pathToOriginal = new WeightenedDirectedEdge[digraph.size()];
		pathToWithSkip = new WeightenedDirectedEdge[digraph.size()];
		pq = new IndexedMinPQ<>(digraph.size());

		for (int i = 0; i < digraph.size(); i++) {
			distToOriginal[i] = Double.POSITIVE_INFINITY;
			distToWithSkip[i] = Double.POSITIVE_INFINITY;
		}

		pq.add(0.0, start);
		distToOriginal[start] = 0.0;
		distToWithSkip[start] = 0.0;

		while (!pq.isEmpty()) {
			for (WeightenedDirectedEdge edge : digraph.adj(pq.removeMinIndex())) {
				relax(edge);
			}
		}
	}

	private void relax(WeightenedDirectedEdge edge) {
		int toNode = edge.getTo();
		int fromNode = edge.getFrom();
		System.out.println("relaxing: " + edge);

		//TODO: something MIGHT BE WRONG(need to test on bigger graph) with this condition, need to figure out how to correctly detect
		// shorter path in presence of two arrays.
		// maybe have separate check for them?
		if (
				distToWithSkip[toNode] > distToWithSkip[fromNode] + edge.getWeight()
						|| distToWithSkip[toNode] > distToOriginal[fromNode] + 0.0
		) {
			System.out.println("wights: " + Arrays.toString(distToOriginal));
			System.out.println("wights: " + Arrays.toString(distToWithSkip));
			double newWeight = Math.min(distToWithSkip[fromNode] + edge.getWeight(), distToOriginal[fromNode] + 0.0);
			updatePathAndDistWithSkip(edge, newWeight);
			updatePathToOriginal(edge);

			if (pq.containsIndex(toNode)) {
				System.out.println("shorter path found from " + fromNode + " to " + toNode);
				System.out.println("wights: " + Arrays.toString(distToOriginal));
				System.out.println("wights: " + Arrays.toString(distToWithSkip));
				pq.decreaseKey(newWeight, toNode);
			} else {
				System.out.println("new path from: " + fromNode + " to " + toNode);
				pq.add(newWeight, toNode);
			}
		} else {
			System.out.println("Already have shorter, ignoring");
		}
	}

	private void updatePathAndDistWithSkip(WeightenedDirectedEdge edge, double weight) {
		distToWithSkip[edge.getTo()] = weight;
		pathToWithSkip[edge.getTo()] = edge;
	}

	private void updatePathToOriginal(WeightenedDirectedEdge edge) {
		distToOriginal[edge.getTo()] = distToOriginal[edge.getFrom()] + edge.getWeight();
		pathToOriginal[edge.getTo()] = edge;
	}

	public Iterable<WeightenedDirectedEdge> pathTo(int finish) {
		Deque<WeightenedDirectedEdge> pathToList = new ArrayDeque<>();
		WeightenedDirectedEdge edge = pathToWithSkip[finish];
		while (edge.getFrom() != start) {
			pathToList.add(edge);
			edge = pathToWithSkip[edge.getFrom()];
		}
		pathToList.add(edge);
		return pathToList;
	}
}
