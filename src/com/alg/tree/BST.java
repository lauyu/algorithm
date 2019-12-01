package com.alg.tree;

public class BST<K extends Comparable<K>, V> {

	private BSTNode<K, V> root;
	
	public V search(K k) {
		return doSearch(root, k);
	}

	/**
	 * 递归的事先
	 * @param root
	 * @param k
	 * @return
	 */
	private V doSearch(BSTNode<K, V> root, K k) {
		if(root == null) return null;
		if(root.getKey().compareTo(k) == 0) {
			return root.getValue();
		} else if (root.getKey().compareTo(k) < 0) {
			return doSearch(root.getRight(), k);
		} else {
			return doSearch(root.getLeft(), k);
		}
	}
	
	/**
	 * 非递归的实现
	 * @param k
	 * @return
	 */
	public V find(K k) {
		if(root == null) return null;
		BSTNode<K, V> cur = root;
		while (cur != null) {
			int c = cur.getKey().compareTo(k);
			if(c < 0) {
				cur = cur.getRight();
			} else if (c > 0) {
				cur = cur.getLeft();
			} else {
				return cur.getValue();
			}
		}
		return null;
	}

	/**
	 * 插入的递归实现，不支持插入已存在的k（返回false）
	 * @param k
	 * @param v
	 */
	public boolean insert(K k, V v) {
//		if(root == null) {
//			root = new BSTNode<K, V>(k, v);
//			return true;
//		};
		return doInsert(root, k, v) != null;
	}

	/**
	 * 把<k,v>插入到根结点root的树下面，并且返回该根结点。递归的时候，注意
	 * @param root
	 * @param k
	 * @param v
	 * @return
	 */
	public BSTNode<K, V> doInsert(BSTNode<K, V> root, K k, V v) {
		if(root == null) {
			root = new BSTNode<K, V>(k, v);
			return root;
		};
		int c = root.getKey().compareTo(k);
		if(c < 0) {
			root.right = doInsert(root.getRight(), k, v);
			return root;
			
		} else if (c > 0) {
			root.left = doInsert(root.getLeft(), k, v);
			return root;
			
		} else {
			return null;
		}
	}
	
	/**
	 * 插入的非递归实现，不支持插入已存在的k
	 * @param k
	 * @param v
	 * @return
	 */
	public boolean put(K k, V v) {
		if(root == null) {
			root = new BSTNode<K, V>(k, v);
			return true;
		}
		BSTNode<K, V> cur = root;
		while (cur != null) {
			int c = cur.getKey().compareTo(k);
			if(c < 0) {
				if(cur.getRight() == null) {
					cur.setRight(new BSTNode<K, V>(k, v));
					return true;
				}
				cur = cur.getRight();
			} else if (c > 0) {
				if(cur.getLeft() == null) {
					cur.setLeft(new BSTNode<K, V>(k, v));
					return true;
				}
				cur = cur.getLeft();
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 递归实现中序
	 * @return
	 */
	public K[] inorder() {
		order(root);
		return null;
	}
	
	private K[] order(BSTNode<K, V> root) {
		if(root == null) return null;
		order(root.getLeft());
		System.out.print(root.getKey()+", ");
		order(root.right);
		return null;	
	}
	
//	public V delete(K k) {
//		if(root == null) return null;
//	}
	
	public V doDelete(BSTNode<K, V> parent, BSTNode<K, V> node, K k) {
		if(node == null) return null;
		int c = node.getKey().compareTo(k);
		if(c < 0) {
			return doDelete(node, node.getRight(), k);
		} else if (c > 0) {
			return doDelete(node, node.getLeft(), k);
		} else {
//			deleteNode(node);
			return node.getValue();
		}
	}
	
	private void deleteNode(BSTNode<K, V> parent, BSTNode<K, V> node) {
		if(node.getLeft() == null) { //删除的节点的右分支替代被删除的
			parent.right = node.getRight();
		} else if (node.getRight()==null) {
			parent.left = node.getLeft();
		} else { //用右分支的最左结点替代删除的结点
			BSTNode<K, V> p = node;
			BSTNode<K, V> cur = p.getRight();
			while (cur.left != null) {
				p = cur;
				cur = cur.left;
			}
//			parent.
		}
		node.left = null;
		node.right = null;
	}

	public V min() {
		if(root == null) return null;
		return min(root).getValue();
	}
	
	private BSTNode<K, V> min(BSTNode<K, V> root) {
		if(root.left != null) return min(root.left);
		return root;
	}
	
	public void deleteMin() {
		if(root == null) return;
		root = deleteMin(root);
	}
	
	private BSTNode<K, V> deleteMin(BSTNode<K, V> root) {
		if(root.left == null) return root.right;
		root.left = deleteMin(root.left);
		return root;
	}
	
	public void delete2(K k) {
		
	}
	
	public BSTNode<K, V> delete2(BSTNode<K, V> node, K k) {
		if(node == null) return null;
		int c = node.getKey().compareTo(k);
		if(c < 0) {
			node.right = delete2(node.right, k);
		} else if (c > 0) {
			node.left = delete2(node.left, k);
		} else {
			if(node.left == null) {
				node = node.right;
			}
			else if (node.right == null) {
				node = node.left;
			} else {
				BSTNode<K, V> min = min(node);
				min.left = node.left;
				min.right = deleteMin(node);
				node = min;
			}
		}
		return node;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {15, 5, 3, 12, 16, 20, 23, 13, 18, 10, 6, 7};
//		BSTNode<Integer, String> root = new BSTNode<Integer, String>(1, "1");
//		System.out.println(root.left);
		BST<Integer, String> bst = new BST();
		for(int i: arr) {
//			bst.insert(i, String.valueOf(i));
			System.out.println("i="+i+", res="+bst.put(i, String.valueOf(i)));;
		}
		bst.inorder();
	}
}
