package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.TreeException;
import utilities.BSTree;
import utilities.BSTreeNode;
import utilities.Iterator;
import utilities.Location;
import utilities.Node;
import utilities.Word;


public class BSTreeTest {

	BSTree bstree;
	ArrayList<String> wordsUsed;
	
	final int num_words = 1000;
	
	@BeforeEach
	void setUp() throws Exception{
		bstree = new BSTree();
		wordsUsed = new ArrayList<String>();
		this.populateBSTree();
	}

	public static int rng() {
		int random = (int)(Math.random() * 25 + 1);
		return random;
	}
	
	private void populateBSTree() {
		
		// Will use random words for all tests 
		for (int x = 0; x < num_words; x++) {
			String word = "";
			for (int i = 0; i < 8; i++) {
				word += ((char)(rng() + 97));
			}
			Word wordObj = new Word(word);
			this.bstree.add(wordObj);
		}
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		bstree = null;
		wordsUsed = null;
	}
	
	/**
	 * Will remove all items from the list, then assert that the size is equal to 0
	 */
	@Test
	void testClear() {
		this.bstree.clear();
		assertEquals(this.bstree.size(), 0);
	}
	
	
	/**
	 * Will add a new item and asset true against the modified size and constant number of words generated 
	 */
	@Test
	void testAdd() {

		int sizeBefore = this.bstree.size();
		Word word = new Word("Apple");
		this.bstree.add(word);
		assertEquals(this.bstree.size(), sizeBefore + 1);
	}

	/**
	 * Will make sure that the size is correctly reported (against the constant number of words generated)
	 */
	@Test
	void testSize() {
		assertEquals(this.bstree.size(), num_words);
	}
	
	/**
	 * Will check to see if the iterator has a next item
	 */
	@Test
	void testIterator() {
		Iterator it = this.bstree.inorderIterator();
		assertTrue(it.hasNext());
	}
	
	/**
	 * Will insert a new item and try to find it, then it will make sure both values are the same
	 */
	@Test
	void testFind() {
		
		Word wordExists = new Word("IExist");
		this.bstree.add(wordExists);
		BSTreeNode findResult = null;
		
		try {
			findResult = this.bstree.search(wordExists);
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			assertTrue(0 == 1);
		}
		
		assertTrue(findResult != null);
		
		Word wordCast = (Word)findResult.word;
		assertTrue(wordCast.word.equals(wordExists.word));
	}
	
	/**
	 * Will add a word to the tree, and assert that it exists within the tree, then it will try with a word that does not exist
	 */
	@Test
	void testContains() {
		Word wordExists = new Word("IExist");
		Word wordNonExistant = new Word("IDontExist");
		
		this.bstree.add(wordExists);
		boolean findResult = false;
		boolean findResultNon = false;
		
		try {
			findResult = this.bstree.contains(wordExists);
			findResultNon = this.bstree.contains(wordNonExistant);
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			assertTrue(0 == 1);
		}
		
		assertTrue(findResult);
		assertFalse(findResultNon);
		
	}
	
	/*
	 * Will add a new item to the BSTree and check to see if it's been added using contains
	 */
	@Test
	void testInsert() {
	
		Word wordExists = new Word("IExist");
		this.bstree.add(wordExists);
		try {
			assertTrue(this.bstree.contains(wordExists));
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Will check to see if the list is empty or not by getting the size, then clearing, then testing again.
	 */
	@Test
	void testIsEmpty() {
		assertFalse(this.bstree.isEmpty());
		this.bstree.clear();
		assertTrue(this.bstree.isEmpty());
	}
	
}
