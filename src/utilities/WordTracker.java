package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class WordTracker {

	private String fileName;
	private String outputName;
	private String formatOption;
	private BSTree bsTree;
	boolean shouldLog;
	
	
	public WordTracker(String fileName, String outputName, String formatOption, boolean shouldLog) {
		this.fileName = fileName;
		this.outputName = outputName;
		this.formatOption = formatOption;
		this.shouldLog = shouldLog;
	}

	/**
	 * Main interface to the WordTracker application
	 */
	public void BeginProcess() {
		
		// First check to see if an existing serialization is available
		boolean existingTree = this.checkExistingTree();
	
		if (existingTree) {
			System.out.println("Existing tree found 'repository.ser' deserializing first.");
			this.DeserializeExistingFile();
			System.out.println("Imported: " + this.bsTree.size() + " records from repository.ser");
		}else {
			System.out.println("No previous tree found 'repository.ser'.");
			
			// Create new tree
			this.bsTree = new BSTree();
		}
		
		
		System.out.println("Importing file: " + this.fileName);
		int importedRecords = this.PopulateTree(this.bsTree);
		
		System.out.println("Imported " + importedRecords + " *unique* words!");
		
		System.out.println("Generating report now: " + this.formatOption);
		
		this.OutputTree(this.formatOption);
		
		System.out.println("Serializing Tree: 'repository.ser'");
		this.SerializeTree();
		
		System.out.println("Done!");
		
	}
	
	/**
	 * Given a container of references (type Word), it will remove all duplicates. O(n2)
	 * @param wordContainer
	 * @param word
	 * @return
	 */
	private static int IsDuplicate(ArrayList<Word> wordContainer, String word) {
			
		for (int x = 0; x < wordContainer.size(); x++) {
			if (wordContainer.get(x).word.equals(word)) {
				return x;
			}
		}
		
		return -1;
	}

	private int PopulateTree(BSTree tree) {
		
		ArrayList<Word> wordCollection = this.ProcessFile(this.fileName);
		for (Word w: wordCollection) {
			tree.add(w);
		}
		
		return wordCollection.size();
	}
	
	/**
	 * Will fix punctuation and normalize each word
	 * @param word
	 * @return
	 */
	private static String ProcessWord(String word) {	
		
		String processedWord = new String(word);
		
		// Set the word to all Lowercase for consistency.
		processedWord = processedWord.toLowerCase();

		// Remove non-alpha numeric characters from the word.
		processedWord = processedWord.replaceAll("[^A-Za-z0-9]", "");	
		
		return processedWord;
	}
	
	/**
	 * Will import and parse text files
	 * @param fileName
	 * @return ArrayList<Word>
	 */
	private ArrayList<Word> ProcessFile(String fileName) {
				
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

	/**
	 * Will check for an existing tree serialization and import it before doing anything else
	 * @return true (if it exists), false (if not)
	 */
	private boolean checkExistingTree() {
		
		File file = new File("./repository.ser");
		boolean exists = file.exists();
				
		if (exists) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Will de-serialize the existing tree and populate existing BSTree
	 */
	private void DeserializeExistingFile() {
		
		try {
		
			BSTree importedTree = null;
			FileInputStream fileIn = new FileInputStream("./repository.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			importedTree = (BSTree)in.readObject();
			in.close();
			fileIn.close();

			// Update self to point to imported tree
			this.bsTree = importedTree;
		
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Will serialize current tree and write to disk
	 */
	private void SerializeTree() {
	
		try {
		
			FileOutputStream fileOut = new FileOutputStream("./repository.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(this.bsTree);
			out.close();
			fileOut.close();
		
		} catch (IOException e) {
			System.out.println("Error serializing tree");
			e.printStackTrace();
		}

	}
	
	/**
	 * Will generate a log, either to disk or to console (or both).
	 * The report is generated based upon provided variables from the CLI
	 */
	private void OutputTree(String formatOption) {
		
		// Use some iterator to grab the words from the tree
		ArrayList<Word> treeWords = new ArrayList<Word>();
		Iterator it = this.bsTree.inorderIterator();
		while(it.hasNext()) {
			Word word = (Word)it.next();
			treeWords.add(word);
		}
		
		// Sort alphabetically
		ArrayList<Word> sortedContainer = new ArrayList<Word>();
		final String chars = "abcdefghijklmnopqrstuvwxyz";
		
		// Starting from the every char O(N^2)
		for (int x = 0; x < chars.length(); x++) {
			// Loop through all words
			for (int i = 0; i < treeWords.size(); i++) {
				// If it starts with the character
				if (treeWords.get(i).word.startsWith(chars.charAt(x) + "")) {
					// Add it to the container
					sortedContainer.add(treeWords.get(i));		
				}
			}
		}
		
		// Based upon the format option passed, trigger the correct formatting function
		switch(this.formatOption) {
		case "pl":
			this.FormatPL(sortedContainer);
			break;
		case "pf":
			this.FormatPF(sortedContainer);
			break;
		case "po":
			this.FormatPO(sortedContainer);
			break;
		default:
			System.out.println("Skipping: Unknown logging option: " + this.formatOption);
		}
		
	}
	
	/**
	 * to print in alphabetic order all words along with the corresponding list of files in which 
	 * the words occur
	 * @param alphaSorted ArrayList contained alpha-numerically sorted word objects
	 */
	private void FormatPF(ArrayList<Word> alphaSorted) {
		ArrayList<String> log = new ArrayList<String>();
		
		// For all words
		for (Word w: alphaSorted) {
			ArrayList<String> occurances = new ArrayList<String>();
			log.add("++ Word: '" + w.word + "':");
			
			log.add("-Located in File(s): ");
			// For all locations in the word
			for (Location l: w.locations) {
				
				// Store only unique file occurrences.
				if (!occurances.contains(l.fileName)) {
					occurances.add(l.fileName);
					log.add(l.fileName);
				}
				
			}
			
		}
		
		this.ProcessLog(log);
	}
	
	/**
	 * to print in alphabetic order all words along with the corresponding list of files and 
	 * numbers of the lines in which the word occur
	 * @param alphaSorted ArrayList contained alpha-numerically sorted word objects
	 */
	private void FormatPL(ArrayList<Word> alphaSorted) {
		
		ArrayList<String> log = new ArrayList<String>();
		
		// For all words
		for (Word w: alphaSorted) {
			
			ArrayList<String> occurances = new ArrayList<String>();
			ArrayList<String> lines = new ArrayList<String>();
			
			log.add("++ Word: '" + w.word + "':");
			log.add("-Located in File(s): ");
			
			// For all locations in the word
			for (Location l: w.locations) {
				
				// Store only unique file occurrences.
				if (!occurances.contains(l.fileName)) {
					occurances.add(l.fileName);
					lines.add(String.valueOf(l.lineNumber) + ", ");
				}else {
					int occuranceIndex = occurances.indexOf(l.fileName);
					lines.set(occuranceIndex,
							lines.get(occuranceIndex) + (String.valueOf(l.lineNumber) + ", "));
				}
				
			}
			
			// For every unique file occurance
			for (int x = 0; x < occurances.size(); x++) {
				log.add("File: " + occurances.get(x));
				log.add("Lines: " + lines.get(x));
			}
						
		}
	
		this.ProcessLog(log);
	}
	
	/**
	 * to print in alphabetic order all words along with the corresponding list of files, 
	 * numbers of the lines in which the word occur and the frequency of occurrence of the 
     * words
	 * @param alphaSorted ArrayList contained alpha-numerically sorted word objects
	 */
	private void FormatPO(ArrayList<Word> alphaSorted) {
	
		ArrayList<String> log = new ArrayList<String>();
		
		// For all words
		for (Word w: alphaSorted) {
			
			ArrayList<String> occurances = new ArrayList<String>();
			ArrayList<String> lines = new ArrayList<String>();
			
			log.add("++ Word: '" + w.word + "':");
			log.add("-Located in File(s): ");
			
			// For all locations in the word
			for (Location l: w.locations) {
				
				// Store only unique file occurrences.
				if (!occurances.contains(l.fileName)) {
					occurances.add(l.fileName);
					lines.add(String.valueOf(l.lineNumber) + ", ");
				}else {
					int occuranceIndex = occurances.indexOf(l.fileName);
					lines.set(occuranceIndex,
							lines.get(occuranceIndex) + (String.valueOf(l.lineNumber) + ", "));
				}
				
			}
			
			
			log.add("Total Occurrences: " + String.valueOf(w.locations.size()));
			
			// For every unique file occurance
			for (int x = 0; x < occurances.size(); x++) {
				log.add("File: " + occurances.get(x));
				log.add("Lines: " + lines.get(x));
			}
						
		}
				
		this.ProcessLog(log);
	}

	/**
	 * Private function to process the log (either print to screen or output to file) 
	 * @param log passed from previous steps
	 */
	private void ProcessLog(ArrayList<String> log) {
		
		if (!this.shouldLog) {
			
			for (String lEntry: log) {
				System.out.println(lEntry);
			}
			
		}else {
			
			File file = new File(this.outputName);
			try {
				FileWriter fw = new FileWriter(file);
				for (String entry: log) {
					fw.write(entry + "\n");
				}
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error writing output log");
				e.printStackTrace();
			}
			
		}
		
	}
	
}
