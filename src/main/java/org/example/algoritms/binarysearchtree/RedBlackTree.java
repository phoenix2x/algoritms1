package org.example.algoritms.binarysearchtree;

public class RedBlackTree<K, V> {
	private Node<K, V> root;

	public static void main(String[] args) {

	}

	private static void test() {
		RedBlackTree<String, String> tree = new RedBlackTree<>();
		tree.put("1", "one");
		tree.put("3", "three");
		tree.put("5", "five");
		tree.put("6", "six");
		tree.put("4", "four");
		tree.put("2", "two");

		System.out.println(tree);
	}

	public void put(K key, V val) {

	}

	public void print() {
		print("", true, root);
	}

	private void print(String prefix, boolean isLeft, Node<K, V> node) {
		if(node == null) {
			return;
		}
		print(prefix + (!isLeft ? "    " : "│   "), false, node.getRight());
		System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.getKey());
		print(prefix + (isLeft ? "    " : "│   "), true, node.getLeft());
	}


	public static class Node<K, V> {
		private K key;
		private V value;
		private Node<K, V> left;
		private Node<K, V> right;
		private int treeSize;
		private boolean isRed;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.treeSize = 1;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
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

		public int getTreeSize() {
			return treeSize;
		}

		public void setTreeSize(int treeSize) {
			this.treeSize = treeSize;
		}

		public boolean isRed() {
			return isRed;
		}

		public void setRed(boolean red) {
			isRed = red;
		}

		@Override
		public String toString() {
			return "Node{" +
					"key=" + key +
					", value=" + value +
					'}';
		}
	}
}
