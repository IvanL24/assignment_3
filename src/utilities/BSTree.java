package utilities;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import exceptions.TreeException;


@SuppressWarnings("rawtypes")
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E>{


	private static final long serialVersionUID = 1L;
	
	
	private BSTreeNode root;
	private int sizeCounter = 0;
	
	@Override
	public BSTreeNode getRoot() throws TreeException {
		return this.root;
	}

	@Override
	public int getHeight() {
		int height = this.root.findHeight(root);
		
		
		if (height > 0) {
			// Include root with the height
			height++;
		}
		
		return height;
	}

	@Override
	public int size() {
		// Return local size counter
		return this.sizeCounter;
	}

	@Override
	public boolean isEmpty() {
		if (this.root == null) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		// Set root to null and remove size counter
		this.root = null;
		this.sizeCounter = 0;
	}

	@Override
	public boolean contains(E entry) throws TreeException {
		int tempCast = (int)entry;
		BSTreeNode found = this.root.find(tempCast);
		
		return found != null;
	}

	@Override
	public BSTreeNode search(E entry) throws TreeException {

		int tempCast = (int)entry;
		
		if (this.isEmpty()) {
			throw new TreeException("Tree is empty");
		}else {
			BSTreeNode found = this.root.find(tempCast);
			return found;
		}

	}

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		
		int tempCast = (int)newEntry;
		boolean inserted = false;
		
		// First check to see if the tree is empty
		if (this.isEmpty()) {
			this.root = new BSTreeNode(tempCast);
			inserted = true;
		}else {
			// Otherwise, we call insert on the root
			inserted = this.root.insert(tempCast);
		}
		
		// After successful insertion, increment local node counter;
		if (inserted) {
			this.sizeCounter++;
		}
		
		return inserted;
	}

	@Override
	public Iterator inorderIterator() {
		ArrayList<E> t = new ArrayList<E>();
		this.root.inorder(t);
		return new ArrayBasedIterator(t);
	}

	@Override
	public Iterator preorderIterator() {
		ArrayList<E> t = new ArrayList<E>();
		this.root.preorder(t);		
		return new ArrayBasedIterator(t);
	}

	@Override
	public Iterator postorderIterator() {	
		ArrayList<E> t = new ArrayList<E>();
		this.root.postorder(t);		
		return new ArrayBasedIterator(t);
	}

	private class ArrayBasedIterator implements Iterator<E> {
		
		private ArrayList<E> container;
		private int currentIndex = 0;
		
		public ArrayBasedIterator(ArrayList<E> a) {
			this.container = a;
		}
		
		@Override
		public boolean hasNext() {
			
			if (this.currentIndex < this.container.size()) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public E next() throws NoSuchElementException {
			if (this.currentIndex >= this.container.size()) {
				throw new NoSuchElementException();
			}
			E toReturn = this.container.get(currentIndex++);
			return toReturn;
		}
		
	}
	
}
