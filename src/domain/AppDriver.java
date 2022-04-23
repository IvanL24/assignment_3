package domain;

import utilities.BSTree;
import utilities.BSTreeNode;
import utilities.Iterator;
import utilities.Location;
import utilities.Node;
import utilities.Word;

import java.util.ArrayList;
import java.util.Random;

import exceptions.TreeException;

// Imports for file operations
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class AppDriver {

	public static int rng() {
		int random = (int)(Math.random() * 50 + 1);
		return random;
	}
	
	private static ArrayList<Word> ProcessFile(String fileName) {
		
		File file = new File(fileName);
		Scanner fScan = null;
		try {
			fScan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Word> wordContainer = new ArrayList<Word>();
		
		String tempLine = "";
		String tempWord = "";
		int lineCounter = 0;
		String[] lineCapture;
		
		while(fScan.hasNextLine()) {
			
			tempLine = fScan.nextLine();
			lineCapture = tempLine.split(" ");
			
			for (int x = 0; x < lineCapture.length; x++) {
				
				// Get individual word from line
				tempWord = lineCapture[x];
				
				// Normalize word
				String processedWord = ProcessWord(tempWord);
				
				// Check if duplicate or not
				int duplicateIndex = IsDuplicate(wordContainer, processedWord);
				
				// Create location object
				Location location = new Location(fileName, lineCounter);
				
				if (duplicateIndex > -1) {	
					// Update existing word to include location of duplicate
					wordContainer.get(duplicateIndex).addLocation(location);
				}else {
					// Instantiate new word object and add to container
					Word word = new Word(processedWord);
					word.locations.add(location);
					// Add word to container
					wordContainer.add(word);
				}
				
			}
			
			// Keep track of which line we're currently processing
			lineCounter++;
		}
		
		return wordContainer;
	}
	
	private static int IsDuplicate(ArrayList<Word> wordContainer, String word) {
		
		for (int x = 0; x < wordContainer.size(); x++) {
			if (wordContainer.get(x).word.equals(word)) {
				return x;
			}
		}
		
		return -1;
	}
	
	private static String ProcessWord(String word) {	
		
		String processedWord = new String(word);
		
		// Set the word to all Lowercase for consistency.
		processedWord = processedWord.toLowerCase();

		// Remove non-alpha numeric characters from the word.
		processedWord = processedWord.replaceAll("[^A-Za-z0-9]", "");	
		
		return processedWord;
	}
	
	public static void main(String[] args) throws TreeException {
		
		System.out.println("Trying to read file");
		
		ArrayList<Word> wordContainer = ProcessFile("./res/textfile.txt");
		System.out.println("Got: " + wordContainer.size() + " words");
		
		System.out.println("Creating a new tree");
		BSTree tree = new BSTree();
		
		
		String word1 = "Hello";
		String word2 = "World";
		
		System.out.println("Comparing: " + word1.compareTo(word2));
		
		
//		for (int x = 0; x < 10; x++) {
//			int rnVal = rng();
//			System.out.println("Adding random node: " + rnVal);
//			tree.add(rnVal);
//		}
		
//		tree.add(70);
		
		
		for (int x = 0; x < wordContainer.size(); x++) {
			tree.add(wordContainer.get(x));
		}

		
		System.out.println("Added words!");
		
		// Test for serialization
//		try {
//			
//			System.out.println("Testing for serialization");
//			FileOutputStream fileOut = new FileOutputStream("./test.ser");
//			ObjectOutputStream out = new ObjectOutputStream(fileOut);
//			
//			out.writeObject(tree);
//			out.close();
//			fileOut.close();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// Test for deserialization
		
		
//		try {
//			
//			System.out.println("Testing for deserialization");
//			BSTree importedTree = null;
//			FileInputStream fileIn = new FileInputStream("./test.ser");
//			ObjectInputStream in = new ObjectInputStream(fileIn);
//			
//			importedTree = (BSTree)in.readObject();
//			in.close();
//			fileIn.close();
//			
//			System.out.println("Deserialization completed");
//
//			
//		} catch (IOException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println("Tree created successfully");
//		
//		// Test for contains
		
		Word word = new Word("DoesNotExist");
		boolean treeHas = tree.contains(wordContainer.get(1));
		boolean treeNotHave = tree.contains(word);
		
		
		
		System.out.println("Word has: " + treeHas);
		System.out.println("Word doesn't have: " + treeNotHave);
//		
		
		
//		// Test for find
		
		BSTreeNode foundNode = tree.search(wordContainer.get(1));
		System.out.println(foundNode.word.word);
		
		
		
//		
//		// Test for size
//		int treeSize = tree.size();
//		
//		// Test for height
//		int treeHeight = tree.getHeight();
//		
//		Iterator t = tree.postorderIterator();
//
//		// Test iterator
//		
////		while(t.hasNext()) {
////			System.out.println("Next val: " + t.next());
////		}
//
//			
//		System.out.println("Local tests finished");
		
		
		// --- Strings Parser --- 
		
//		String arg1 = args[0];
//		String arg2 = args[1];
//		
//		try {
//			if(args.length == 2) {
//				System.out.println("working!");
//				System.out.println(arg1);
//			}
//			if(arg2.substring(0,1).equals("-")) {
//				System.out.println("true");
//				if(arg2.substring(1,2).equalsIgnoreCase("p")) {
//					System.out.println("true2");
//					if(arg2.substring(2,3).equalsIgnoreCase("f")) {
//						System.out.println("true3");
//						Scanner scan = new Scanner(new File("res/textfile.txt"));
//						
//						while(scan.hasNext()) {
//							String word = scan.next();
//							Node node = new Node(word);
//							String newWord = scan.next();
//							node.insertNode(newWord);
//							node.AscendingSort();
//								
//						}						
//
//						
//					}else if (arg2.substring(2,3).equalsIgnoreCase("l")) {
//						System.out.println("true L");
//
//						
//					}else if (arg2.substring(2,3).equalsIgnoreCase("o")) {
//						System.out.println("true O");
//
//						
//					}	
//				}
//				
//			}
//		}catch (Exception e) {
//			
//		}
		
	}
	
}
