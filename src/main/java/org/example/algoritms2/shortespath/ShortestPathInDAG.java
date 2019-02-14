package org.example.algoritms2.shortespath;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * calculates shortest path based on Topological order,
 * can work with negative waits
 * CAN'T work with graph with direct cycles
 */
public class ShortestPathInDAG {
	private final double[] distTo;
	private final WeightenedDirectedEdge[] pathTo;
	private final int start;


	public static void main(String[] args) {
		WeightenedDigraph digraph = new WeightenedDigraph(6);
		digraph.addEdge(new WeightenedDirectedEdge(0, 1, 4.0));
		digraph.addEdge(new WeightenedDirectedEdge(1, 2, 5.0));
		digraph.addEdge(new WeightenedDirectedEdge(2, 3, 5.0));
		digraph.addEdge(new WeightenedDirectedEdge(3, 5, 1.0));
		digraph.addEdge(new WeightenedDirectedEdge(0, 4, 10.0));
		digraph.addEdge(new WeightenedDirectedEdge(4, 3, 2.0));

		ShortestPathInDAG sp = new ShortestPathInDAG(digraph, 0);

		System.out.println(sp.pathTo(5));

	}

	public ShortestPathInDAG(WeightenedDigraph digraph, int start) {
		distTo = new double[digraph.size()];
		pathTo = new WeightenedDirectedEdge[digraph.size()];
		this.start = start;

		for(int i = 0; i < distTo.length; i++) {
			distTo[i] = Double.MAX_VALUE;
		}

		distTo[start] = 0.0;

		TopologicalSortForDAG order = new TopologicalSortForDAG(digraph);
		for(int v: order) {
//			System.out.println("order: " + v);
			for(WeightenedDirectedEdge edge: digraph.adj(v)) {
				relaxEdge(edge);
			}
		}
	}

	private void relaxEdge(WeightenedDirectedEdge edge) {
//		System.out.println("edge: " + edge);
		if(distTo[edge.getTo()] > distTo[edge.getFrom()] + edge.getWeight()) {
//			System.out.println("found shorter path");
			distTo[edge.getTo()] = distTo[edge.getFrom()] + edge.getWeight();
			pathTo[edge.getTo()] = edge;
//		} else {
//			System.out.println("Already know shorter path, ignoring");
//			System.out.println("distTo[edge.getTo()]: " + distTo[edge.getTo()]);
//			System.out.println("distTo[edge.getFrom()]: " + distTo[edge.getFrom()] + "edge.getWeight()" + edge.getWeight());
		}
	}

	public Iterable<WeightenedDirectedEdge> pathTo(int finish) {
//		System.out.println("pathTo: " + Arrays.toString(pathTo));
//		System.out.println("distTo: " + Arrays.toString(distTo));
//		System.out.println("distTo: " + finish + " equals to " + distTo[finish]);
		Deque<WeightenedDirectedEdge> stack = new ArrayDeque<>();
		WeightenedDirectedEdge edge = pathTo[finish];
		while(edge.getFrom() != start) {
			stack.push(edge);
			edge = pathTo[edge.getFrom()];
		}
		stack.push(edge);
		return stack;
	}
}
