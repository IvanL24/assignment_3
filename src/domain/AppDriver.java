package domain;

import utilities.BSTree;
import utilities.BSTreeNode;
import utilities.Iterator;

import java.util.Random;

import exceptions.TreeException;

public class AppDriver {

	public static int rng() {
		int random = (int)(Math.random() * 50 + 1);
		return random;
	}
	
	public static void main(String[] args) throws TreeException {
		
		System.out.println("Creating a new tree");
		
		BSTree tree = new BSTree();
		
		for (int x = 0; x < 10; x++) {
			int rnVal = rng();
			System.out.println("Adding random node: " + rnVal);
			tree.add(rnVal);
		}
		
//		tree.add(70);
		
		System.out.println("Tree created successfully");
		
		// Test for contains
		boolean treeHas = tree.contains(70);
		boolean treeNotHave = tree.contains(71);
		
		// Test for find
		BSTreeNode foundNode = tree.search(70);
		
		// Test for size
		int treeSize = tree.size();
		
		// Test for height
		int treeHeight = tree.getHeight();
		
		Iterator t = tree.postorderIterator();

		// Test iterator
		
//		while(t.hasNext()) {
//			System.out.println("Next val: " + t.next());
//		}

			
		System.out.println("Local tests finished");
		
	}
	
}
