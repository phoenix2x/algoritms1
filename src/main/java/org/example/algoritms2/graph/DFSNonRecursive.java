package org.example.algoritms2.graph;

import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class DFSNonRecursive {
	private boolean[] marked;
	private int[] paths;
	private int s;
	private Stack<Iterator<Integer>> stack;
	private Stack<Tuple2<Iterator<Integer>,Integer>> stackWithParent;

	public static void main(String[] args) {

		Graph graph = new Graph(10);
		graph.addEdge(1,2);
		graph.addEdge(1,3);
		graph.addEdge(3,4);
		graph.addEdge(3,6);
		graph.addEdge(4,5);
		graph.addEdge(5,1);
		DFSNonRecursive dfs = new DFSNonRecursive(graph, 1);
		System.out.println("marked: " + Arrays.toString(dfs.getMarked()));
		System.out.println("paths: " + Arrays.toString(dfs.getPaths()));
		System.out.println("has path 8: " + dfs.hasPathTo(8));
		System.out.println("has path 5: " + dfs.hasPathTo(5));
		System.out.println("path to 5: " + dfs.pathTo(5));
	}

	public DFSNonRecursive(Graph graph, int s) {
		this.s = s;
		marked = new boolean[graph.V()];
		stack = new Stack<>();
		stackWithParent = new Stack<>();
		paths = new int[graph.V()];
//		dfsNonRecursive(graph, s);
//		dfsNonRecursiveWithTuple(graph, s);
		dfsRecursive(graph, s);
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v)) throw new IllegalArgumentException("check if connected first");
		Stack<Integer> path = new Stack<>();
		path.push(v);
		int parent = paths[v];
		while(parent != this.s) {
			path.push(parent);
			parent = paths[parent];
		}
		path.push(this.s);

		return path;
	}

	private void dfsNonRecursiveWithTuple(Graph graph, int v) {
		marked[v] = true;
		stackWithParent.push(Tuple.of(graph.adj(v).iterator(), v));
		while(!stackWithParent.empty()) {
			Tuple2<Iterator<Integer>, Integer> tuple = stackWithParent.pop();
			Iterator<Integer> it = tuple._1();
			int parent = tuple._2();
			while (it.hasNext()) {
				int adj = it.next();
				if (!marked[adj]) {
					marked[adj] = true;
					paths[adj] = parent;
					stackWithParent.push(tuple);
					stackWithParent.push(Tuple.of(graph.adj(adj).iterator(), adj));
					break;
				}
			}
		}
	}

	private void dfsNonRecursive(Graph graph, int v) {
		marked[v] = true;
		int parent = v;
		boolean breaked = false;
		stack.push(graph.adj(v).iterator());
		while(!stack.empty()) {
			Iterator<Integer> it = stack.pop();
			while (it.hasNext()) {
				int adj = it.next();
				if (!marked[adj]) {
					marked[adj] = true;
					stack.push(it);
					stack.push(graph.adj(adj).iterator());
					paths[adj] = parent;
					parent = adj;
					breaked = true;
					break;
				}
			}
			if(breaked) {
				breaked = false;
			} else {
				parent = paths[parent];
			}
		}
	}

	private void dfsRecursive(Graph graph, int v) {
		System.out.println("v: " + v);
		marked[v] = true;
		for(int adj: graph.adj(v)) {
			if(!marked[adj]) {
				paths[adj] = v;
				dfsRecursive(graph, adj);
			}
		}
	}

	public boolean[] getMarked() {
		return marked;
	}

	public void setMarked(boolean[] marked) {
		this.marked = marked;
	}

	public int[] getPaths() {
		return paths;
	}

	public void setPaths(int[] paths) {
		this.paths = paths;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public Stack<Iterator<Integer>> getStack() {
		return stack;
	}

	public void setStack(Stack<Iterator<Integer>> stack) {
		this.stack = stack;
	}
}
