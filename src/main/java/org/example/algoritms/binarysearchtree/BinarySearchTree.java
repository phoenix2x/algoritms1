package org.example.algoritms.binarysearchtree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<BinarySearchTree.Node<K, V>> {
	private Node<K, V> root;

	public static void main(String[] args) {
//		BinarySearchTree<String, String> tree = new BinarySearchTree<>();
//		tree.put("1", "one");
//		System.out.println(tree);
//		tree.put("2", "two");
//		System.out.println(tree);
//
//		tree.put("3", "three");
//		System.out.println(tree);
//
//		System.out.println(
//				tree.get("2")
//		);
//		testFloor();
		testRange();
	}

	private static void testRange() {
		BinarySearchTree<String, String> tree = new BinarySearchTree<>();
		tree.put("1", "one");
		tree.put("3", "three");
		tree.put("5", "five");
		tree.put("6", "six");
		tree.put("4", "four");
		tree.put("2", "two");
		tree.put("-1", "-one");
		tree.put("-3", "-one");
		tree.put("-8", "-one");
		tree.put("-2", "-one");
		tree.put("-5", "-one");
		tree.put("-4", "-one");
		tree.put("-10", "-one");

		System.out.println(tree);
		System.out.println("size" + tree.size());
		System.out.println(tree.range("2.1", "8"));
		tree.print();
	}
//	private static void testFloor() {
//		BinarySearchTree<String, String> tree = new BinarySearchTree<>();
//		tree.put("1", "one");
//		tree.put("3", "three");
//		tree.put("5", "five");
//		tree.put("6", "six");
//		tree.put("4", "four");
//		tree.put("2", "two");
//
//		System.out.println(tree);
//		System.out.println("size" + tree.size());
//		System.out.println(tree.floor("5.57"));
//		System.out.println("rank " + tree.rank("5.5"));
//	}

	public void print() {
		print("", true, root);
	}

//	private void print(String prefix, boolean isTail, Node<K, V> node) {
//		if(node == null) {
//			return;
//		}
//		System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getKey());
//		print(prefix + (isTail ? "    " : "│   "), false, node.getRight());
//		print(prefix + (isTail ? "    " : "│   "), true, node.getLeft());
//	}

	private void print(String prefix, boolean isLeft, Node<K, V> node) {
		if(node == null) {
			return;
		}
		print(prefix + (!isLeft ? "    " : "│   "), false, node.getRight());
		System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.getKey());
		print(prefix + (isLeft ? "    " : "│   "), true, node.getLeft());
	}

	public int rank(K key) {
		return rank(root, key);
	}

	private int rank(Node<K, V> node, K key) {
		if (node == null) {
			return 0;
		}
		int cmp = key.compareTo(node.getKey());
		if (cmp < 0) {
			return rank(node.getLeft(), key);
		} else if (cmp > 0) {
			return 1 + size(node.getLeft()) + rank(node.getRight(), key);
		} else {
			return size(node.getLeft());
		}
	}

	public List<Node<K, V>> range(K low, K hi) {
		List<Node<K, V>> results = new ArrayList<>();
		range(results, root, low, hi);
		return results;
	}

	private void range(List<Node<K, V>> list, Node<K, V> node, K low, K hi) {
		if (node == null) {
			return;
		}
		int cmpLow = low.compareTo(node.getKey());
		int cmpHi = hi.compareTo(node.getKey());
		if (cmpLow < 0) {
			range(list, node.getLeft(), low, hi);
		}
		if (cmpLow <= 0 && cmpHi >= 0) {
			list.add(node);
		}
		if (cmpHi > 0) {
			range(list, node.getRight(), low, hi);
		}
	}

	public void put(K key, V value) {
//		putNonRecursive(key, value);
		root = putRecursive(root, key, value);
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
		node.setTreeSize(1 + size(node.getLeft()) + size(node.getRight()));
		return node;
	}

	public int size() {
		return size(root);
	}

	public V get(K key) {
		Node<K, V> node = search(root, key);
		return node != null ? node.getValue() : null;
	}

	public boolean contains(K key) {
		return search(root, key) != null;
	}

	public void remove(K key) {
		put(key, null);
	}

	public String toString() {
		List<Node<K, V>> list = new ArrayList<>();
		toList(list, root);
		return list.toString();
	}

	public Iterator<Node<K, V>> iterator() {
		return this.nodes().iterator();
	}

	public Iterable<Node<K, V>> nodes() {
		List<Node<K, V>> list = new ArrayList<>();
		toList(list, root);
		return list;
	}

	private void toList(List<Node<K, V>> list, Node<K, V> node) {
		if (node == null) {
			return;
		}
		toList(list, node.getLeft());
		list.add(node);
		toList(list, node.getRight());
	}

	public Node<K, V> floor(K key) {
		Node<K, V> current = root;
		Node<K, V> candidate = null;
		while (current != null) {
			if (current.getKey().compareTo(key) > 0) {
				current = current.getLeft();
			} else if (current.getKey().compareTo(key) < 0) {
				candidate = current;
				current = current.getRight();
			} else if (current.getKey().compareTo(key) == 0) {
				return current;
			}
		}
		return candidate;
	}

	private int size(Node<K, V> node) {
		if (node == null) {
			return 0;
		}
		return node.getTreeSize();
	}

	//	private Tuple2<Node<K, V>, Node<K, V>> searchWithParent(Node<K, V> node, K key) {
////		System.out.println(node);
//		if(node == null) {
//			return new Tuple2<>(node, null);
//		}
//		int cmp = node.getKey().compareTo(key);
//		if(cmp < 0) {
//			Tuple2<Node<K, V>, Node<K, V>> result = searchWithParent(node.getRight(), key);
//			return result._1 != null ? result : new Tuple2<>(null, node);
//		}
//		if(cmp > 0) {
//			Tuple2<Node<K, V>, Node<K, V>> result = searchWithParent(node.getLeft(), key);
//			return result._1 != null ? result : new Tuple2<>(null, node);
//		}
//		return new Tuple2<>(node, null);
//	}
//	private void putNonRecursive(K key, V val) {
//		if(root == null) {
//			root = new Node<>(key, val);
//		}
//		Node<K, V> parent = root;
//		Node<K, V> child = root;
//		while(child != null) {
//			int cmp = child.getKey().compareTo(key);
//			if(cmp < 0) {
//				parent = child;
//				child = child.getRight();
//			} else if(cmp > 0) {
//				parent = child;
//				child = parent.getLeft();
//			} else {
//				child.setValue(val);
//				return;
//			}
//		}
//		if(parent.getKey().compareTo(key) < 0) {
//			parent.setRight(new Node<>(key, val));
//		} else {
//			parent.setLeft(new Node<>(key, val));
//		}
//	}
//	private void putInternal(Node<K, V> node, K key, V value) {
//		int cmp = node.getKey().compareTo(key);
//		if(cmp > 0) {
//			Node<K, V> child = node.getLeft();
//			if(child != null) {
//				putInternal(child, key, value);
//			} else {
//				node.setLeft(new Node<>(key, value));
//			}
//		} else if(cmp < 0){
//			Node<K, V> child = node.getRight();
//			if(child != null) {
//				putInternal(child, key, value);
//			} else {
//				node.setRight(new Node<>(key, value));
//			}
//		} else {
//			node.setValue(value);
//		}
//
//	}
	private Node<K, V> search(Node<K, V> node, K key) {
		if (node == null) {
			return node;
		}
		int cmp = node.getKey().compareTo(key);
		if (cmp < 0) {
			return search(node.getRight(), key);
		}
		if (cmp > 0) {
			return search(node.getLeft(), key);
		}
		return node;
	}

	public static class Node<K, V> {
		private K key;
		private V value;
		private Node<K, V> left;
		private Node<K, V> right;
		private int treeSize;

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

		@Override
		public String toString() {
			return "Node{" +
					"key=" + key +
					", value=" + value +
					'}';
		}
	}
}
