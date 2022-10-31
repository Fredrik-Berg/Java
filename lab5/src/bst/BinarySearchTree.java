package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		size = 0;

	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			return add(x, root);

		}
	}
	
	private boolean add(E x, BinaryNode<E> fnode){
		if(fnode.element.compareTo(x) == 0){
			return false;
		} else {
			if(fnode.element.compareTo(x) > 0){
				if(fnode.left == null){
					size++;
					fnode.left = new BinaryNode<E>(x);
					return true;
				} else {
					return add(x, fnode.left);
				}
				
			} else {
				if(fnode.right == null){
					size++;
					fnode.right = new BinaryNode<E>(x);
					return true;
				} else {
					return add(x, fnode.right);
				}
			}
		}
		
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		if (root == null) {
			return 0;
		} else {
			return findHeight(root);
		}
	}

	private int findHeight(BinaryNode<E> node) {
		if (node == null) {
			return -1;
		}

		int lefth = findHeight(node.left);
		int righth = findHeight(node.right);
		return Math.max(lefth, righth) + 1;

	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);

	}
	
	private void printTree(BinaryNode<E> pnode){
		if(pnode != null){
			printTree(pnode.left);
			System.out.println(pnode.element);
			printTree(pnode.right);
			
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		toArray(root, a, 0);
		root = buildTree(a, 0, a.length - 1);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1
	 * (the first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n != null){
			index = toArray(n.left, a, index);
			a[index] = n.element;
			index++;
			index = toArray(n.right, a, index);
		}
		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if(first > last){
			return null;
		} else {
			BinaryNode<E> root;
			int middle = first + ((last - first)/2);
			root = new BinaryNode<E>(a[middle]);
			root.left = buildTree(a, first, middle - 1);
			root.right = buildTree(a, middle + 1, last);
			return root;
		}
		
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}
	
	public static void main(String[] args){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(0);
		bst.add(3);
		bst.add(2);
		bst.add(6);
		bst.add(4);
		bst.add(5);
		BSTVisualizer a = new BSTVisualizer("before", 300, 300);
		a.drawTree(bst);
		bst.rebuild();
		BSTVisualizer b = new BSTVisualizer("after", 300, 300);
		b.drawTree(bst);
	}

}
