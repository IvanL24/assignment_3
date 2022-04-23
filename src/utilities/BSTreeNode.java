package utilities;

import java.io.Serializable;
import java.util.ArrayList;

public class BSTreeNode<E> implements Serializable {

	private BSTreeNode leftChild;
	private BSTreeNode rightChild;
	private int data;
	public Word word;
	
	public BSTreeNode(Word wordObj) {
		// Assign this.data to the hash code of the specific word
		this.data = wordObj.word.hashCode();
		// Store the raw object for serialization purposes
		this.word = wordObj;
	}
	
	public boolean insert(Word wordObj) {
		
		boolean added = false;
		
		int val = wordObj.word.hashCode();
		
		// Check to see this node is smaller than the new node
		if (val < this.data) {
			
			// If the left child has not been initialized
			if (this.leftChild == null) {
				this.leftChild = new BSTreeNode(wordObj);
				return true;
			}else {
				// Otherwise, do a recursive call to the left-child
				added = this.leftChild.insert(wordObj);
			}
			
		}else if (val > this.data) {
		
			// Otherwise, if the passed data is bigger than the current node.
				if (this.rightChild == null) {
					// If the right child doesn't exist, we create a new node.
					this.rightChild = new BSTreeNode(wordObj);
					return true;
				} else {
					// Otherwise, do a recursive call to the right-child.
					added = this.rightChild.insert(wordObj);
				}
			
		}
			
		return added;
	}
	
	public BSTreeNode find(Word wordObj) {
		
		BSTreeNode found = null;
		int val = wordObj.word.hashCode();
	
		// Check to see if current node matches
		if (wordObj.word.hashCode() == this.data) {
			return this;
		}else {
			
			if (val < this.data && this.leftChild != null) {
				// Make a recursive call on the left child
				found = this.leftChild.find(wordObj);
			}else if (val > this.data && this.rightChild != null) {
				// Make a recursive child on the right child
				found = this.rightChild.find(wordObj);
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
	
	public void preorder(ArrayList<Word> list) {
		
		// PREORDER: Log the current node into a list (passed-by-ref)
		list.add(this.word);

		// Prioritize the left subtree fist
		if (this.leftChild != null) {
			this.leftChild.preorder(list);
		}

		// Followed by the right subtree
		if (this.rightChild != null) {
			this.rightChild.preorder(list);
		}
		
	}
	
	public void inorder(ArrayList<Word> list) {

		// Prioritize the left subtree fist
		if (this.leftChild != null) {
			this.leftChild.inorder(list);
		}

		// INORDER: Log the current node into a list (passed-by-ref)
		list.add(this.word);
		
		// Followed by the right subtree
		if (this.rightChild != null) {
			this.rightChild.inorder(list);
		}
		
	}
	
	public void postorder(ArrayList<Word> list) {

		// Prioritize the left subtree fist
		if (this.leftChild != null) {
			this.leftChild.postorder(list);
		}

		// Followed by the right subtree
		if (this.rightChild != null) {
			this.rightChild.postorder(list);
		}

		// POST-ORDER: Log the current node into a list (passed-by-ref)
		list.add(this.word);
					
	}
	
}
