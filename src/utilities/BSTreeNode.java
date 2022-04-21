package utilities;

import java.util.ArrayList;

public class BSTreeNode<E> {

	private BSTreeNode leftChild;
	private BSTreeNode rightChild;
	private int data;
	
	public BSTreeNode(int val) {
		this.data = val;
	}
	
	public boolean insert(int val) {
		
		boolean added = false;
			
		// Check to see this node is smaller than the new node
		if (val < this.data) {
			
			// If the left child has not been initialized
			if (this.leftChild == null) {
				this.leftChild = new BSTreeNode(val);
				return true;
			}else {
				// Otherwise, do a recursive call to the left-child
				added = this.leftChild.insert(val);
			}
			
		}else if (val > this.data) {
		
			// Otherwise, if the passed data is bigger than the current node.
				if (this.rightChild == null) {
					// If the right child doesn't exist, we create a new node.
					this.rightChild = new BSTreeNode(val);
					return true;
				} else {
					// Otherwise, do a recursive call to the right-child.
					added = this.rightChild.insert(val);
				}
			
		}
			
		return added;
	}
	
	public BSTreeNode find(int val) {
		
		BSTreeNode found = null;
		
		// Check to see if current node matches
		if (val == this.data) {
			return this;
		}else {
			
			if (val < this.data && this.leftChild != null) {
				// Make a recursive call on the left child
				found = this.leftChild.find(val);
			}else if (val > this.data && this.rightChild != null) {
				// Make a recursive child on the right child
				found = this.rightChild.find(val);
			}
			
		}
		
		return found;
	}
	
	public int findHeight(BSTreeNode node) {
		
		if (node == null) {
			return -1;
		}

		int left = this.findHeight(node.leftChild);
		int right = this.findHeight(node.rightChild);
		
		if (left > right) {
			return left + 1;
		}else {
			return right + 1;
		}
	
	}
	
	public void preorder(ArrayList<Integer> list) {
		
		// PREORDER: Log the current node into a list (passed-by-ref)
		list.add(this.data);

		// Prioritize the left subtree fist
		if (this.leftChild != null) {
			this.leftChild.preorder(list);
		}

		// Followed by the right subtree
		if (this.rightChild != null) {
			this.rightChild.preorder(list);
		}
		
	}
	
	public void inorder(ArrayList<Integer> list) {

		// Prioritize the left subtree fist
		if (this.leftChild != null) {
			this.leftChild.inorder(list);
		}

		// INORDER: Log the current node into a list (passed-by-ref)
		list.add(this.data);
		
		// Followed by the right subtree
		if (this.rightChild != null) {
			this.rightChild.inorder(list);
		}
		
	}
	
	public void postorder(ArrayList<Integer> list) {

		// Prioritize the left subtree fist
		if (this.leftChild != null) {
			this.leftChild.postorder(list);
		}

		// Followed by the right subtree
		if (this.rightChild != null) {
			this.rightChild.postorder(list);
		}

		// POST-ORDER: Log the current node into a list (passed-by-ref)
		list.add(this.data);
					
	}
	
}
