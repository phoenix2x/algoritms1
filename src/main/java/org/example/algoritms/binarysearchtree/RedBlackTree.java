package org.example.algoritms.binarysearchtree;

public class RedBlackTree<K extends Comparable<K>, V> {
	private Node<K, V> root;

	public static void main(String[] args) {
		test();
	}

	private static void test() {
		RedBlackTree<Integer, String> tree = new RedBlackTree<>();
		tree.put(1, "one");
		tree.put(2, "two");
		tree.put(3, "three");
		tree.put(4, "four");
		tree.put(5, "five");
		tree.put(6, "six");
		tree.put(7, "seven");
		tree.put(8, "eight");
		tree.put(9, "nine");
		tree.put(10, "ten");
		tree.put(12, "twelve");
	}

	public void put(K key, V value) {
		root = putRecursive(root, key, value);
		root.setRed(false);
		this.print();
	}

	private Node<K, V> putRecursive(Node<K, V> node, K key, V val) {
//		System.out.println("" + node + key + val);
		if (node == null) {
			return new Node<>(key, val);
		}
		int cmp = key.compareTo(node.getKey());
		if (cmp < 0) {
			node.setLeft(putRecursive(node.getLeft(), key, val));
		} else if (cmp > 0) {
			node.setRight(putRecursive(node.getRight(), key, val));
		} else {
			node.setValue(val);
		}
		Node<K, V> result = rotateIfNeeded(node);
		result.setTreeSize(1 + size(result.getLeft()) + size(result.getRight()));
		return result;
	}

	private Node<K, V> rotateIfNeeded(Node<K, V> node) {
		if(isRead(node.getRight()) && isRead(node.getLeft())) {
//			System.out.println("flipping");
			return flipColors(node);
		} else if(isRead(node.getRight())) {
//			System.out.println("left");

			return rotateLeft(node);
		} else if(isRead(node.getLeft()) && isRead(node.getLeft().getLeft())) {
//			System.out.println("right");
			Node<K, V> tmp = rotateRight(node);
			return flipColors(tmp);
		}
		return node;
	}

	private boolean isRead(Node<K, V> node) {
		return node != null && node.isRed();
	}

	private Node<K, V> rotateLeft(Node<K, V> node) {
		Node<K, V> rightChild = node.getRight();
		rightChild.setRed(false);
		node.setRed(true);
		node.setRight(rightChild.getLeft());
		rightChild.setLeft(node);
		return rightChild;
	}

	private Node<K, V> rotateRight(Node<K, V> node) {
		Node<K, V> leftChild = node.getLeft();
		leftChild.setRed(false);
		node.setRed(true);
		node.setLeft(leftChild.getRight());
		leftChild.setRight(node);
		return leftChild;
	}

	private Node<K, V> flipColors(Node<K, V> node) {
		node.getLeft().setRed(false);
		node.getRight().setRed(false);
		node.setRed(true);
		return node;
	}

	private int size(Node<K, V> node) {
		if (node == null) {
			return 0;
		}
		return node.getTreeSize();
	}

	public void print() {
		print("", true, root);
	}

	private void print(String prefix, boolean isLeft, Node<K, V> node) {
		if(node == null) {
			return;
		}
		print(prefix + (!isLeft ? "    " : "│   "), false, node.getRight());
		System.out.println(prefix + (isLeft ? isRead(node) ? "|_" : "└── " : isRead(node) ? "|-" : "┌── ") + node.getKey());
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
			this.isRed = true;
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
