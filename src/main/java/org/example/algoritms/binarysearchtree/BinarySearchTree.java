package org.example.algoritms.binarysearchtree;

import io.vavr.Tuple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<BinarySearchTree.Node<K, V>> {
	private Node<K, V> root;

	public static void main(String[] args) {
		BinarySearchTree<String, String> tree = new BinarySearchTree<>();
		tree.put("1", "one");
		System.out.println(tree);
		tree.put("2", "two");
		System.out.println(tree);

		tree.put("3", "three");
		System.out.println(tree);

		System.out.println(
				tree.get("2")
		);
	}

	public void put(K key, V value) {
		putNonRecursive(key, value);
//		if(root == null) {
//			root = new Node<>(key, value);
//		}
//		putInternal(root, key, value);
//		Tuple2<Node<K, V>, Node<K, V>> result = searchWithParent(root, key);
//		if(result._1 != null) {
//			result._1.setValue(value);
//		} else {
////			System.out.println(result);
//			if(result._2.getKey().compareTo(key) > 0) {
//				result._2.setLeft(new Node<>(key, value));
//			} else {
//				result._2.setRight(new Node<>(key, value));
//			}
//		}
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
		List<Node<K, V>> list = new ArrayList<>();
		toList(list, root);
		return list.iterator();
	}
	private void toList(List<Node<K, V>> list, Node<K, V> node) {
		if(node == null) {
			return;
		}
		toList(list, node.getLeft());
		list.add(node);
		toList(list, node.getRight());
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
	private void putNonRecursive(K key, V val) {
		if(root == null) {
			root = new Node<>(key, val);
		}
		Node<K, V> parent = root;
		Node<K, V> child = root;
		while(child != null) {
			int cmp = child.getKey().compareTo(key);
			if(cmp < 0) {
				parent = child;
				child = child.getRight();
			} else if(cmp > 0) {
				parent = child;
				child = parent.getLeft();
			} else {
				child.setValue(val);
				return;
			}
		}
		if(parent.getKey().compareTo(key) < 0) {
			parent.setRight(new Node<>(key, val));
		} else {
			parent.setLeft(new Node<>(key, val));
		}
	}
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
		if(node == null) {
			return node;
		}
		int cmp = node.getKey().compareTo(key);
		if(cmp < 0) {
			return search(node.getRight(), key);
		}
		if(cmp > 0) {
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
