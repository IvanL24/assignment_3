package domain;

import utilities.BSTree;
import utilities.BSTreeNode;
import utilities.Iterator;
import utilities.Location;
import utilities.Node;
import utilities.Word;
import utilities.WordTracker;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import exceptions.TreeException;

// Imports for file operations

public class AppDriver {

	public static int rng() {
		int random = (int)(Math.random() * 50 + 1);
		return random;
	}
	
	
	
	public static void main(String[] args) throws TreeException {
	
		
		
		ArrayList<String> arguments = new ArrayList<String>();
		
		String inputFileName = "";
		String logFormat = "";
		String outputLog = "";
		
		for (int x = 0; x < args.length; x++) {
			
			switch(x) {
				
			case 0: // File import location
				inputFileName = args[x];
				break;
			case 1: // Log format specification
				logFormat = args[x].split("-")[1];
				break;
			case 2:
				break;
			case 3: // Output log location 
				outputLog = args[x];
				break;
			default:
				System.out.println("Ignoring: Argument: " + args[x] + " unknown");
			}
			
			System.out.println(args[x]);
		}
		
		WordTracker wt = new WordTracker(inputFileName, outputLog, logFormat, outputLog.length() > 0);
		wt.BeginProcess();
		
	}
	
}
