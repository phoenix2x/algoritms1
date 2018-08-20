package org.example.algoritms.binarysearchtree;

public class BinaryTreeProblems {

	private static int MIN = Integer.MAX_VALUE;
	private static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) {

		printLeaves();
	}

	private static void printLeaves() {
		Node<String, String> tree = populateTree();
		traverse(tree, 1);
		System.out.println("min height: " + MIN + ", max hight:  " + MAX);
		System.out.println((MAX - MIN > 1) ? "unbalanced" : "balanced");
	}

	private static void traverse(Node node, int level) {
		if(node == null) {
			MIN = Math.min(MIN, level);
			MAX = Math.max(MAX, level);
			return;
		}
		level++;
		traverse(node.getLeft(), level);
		traverse(node.getRight(), level);
	}

	private static Node<String, String> populateTree() {
		Node<String, String> root = new Node<>("0", "zero");
		Node<String, String> one = new Node<>("1", "one");
		Node<String, String> two = new Node<>("2", "one");
		Node<String, String> three = new Node<>("3", "one");
		Node<String, String> four = new Node<>("4", "one");
		Node<String, String> five = new Node<>("5", "one");
		Node<String, String> six = new Node<>("6", "one");
		Node<String, String> seven = new Node<>("7", "one");

		root.setLeft(one);
		one.setLeft(two);
		one.setRight(seven);
		two.setLeft(three);
		three.setRight(five);
		three.setLeft(six);
		root.setRight(four);


		return root;
	}

	private static class Node<K extends Comparable<K>, V> {
		private K key;
		private V val;
		private Node<K, V> left;
		private Node<K, V> right;

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getVal() {
			return val;
		}

		public void setVal(V val) {
			this.val = val;
		}

		public Node<K, V> getLeft() {
			return left;
		}

		public void setLeft(Node<K, V> left) {
			this.left = left;
		}

		public Node<K, V> getRight() {
			return right;
		}

		public void setRight(Node<K, V> right) {
			this.right = right;
		}
	}
}
