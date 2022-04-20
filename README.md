# assignment_3
 Assignment 3 for CPRG-311
 
 Instructor-provided files:
 * BSTreeADT.java
 * Iterator.java

## Goals
* Write reference-based immplementation (BSTree.java and BSTreeNode.java) using instructor-provided files.
* Write cross-reference program (WordTracker.java) which constructs a binary search tree.
* Search tree includes:
   - all words from text file. (supplied at the command line)
   - records the line number of where the words were used.
* Store the tree in a binary file (repository.ser) using Java serialization techniques.
   - include class version UID to ensure backward compatibility.
* Program should check if binary file (repository.ser) exists every time it executes.
   - if file exists, restore the word tree.
   - after scanning the file, the results are to be inserted in the  appropriate nodes of the tree.
   - binary file (repository.ser) will contain all words occured in all files scanned with meta infromation about those words location.

## Running the Program
### java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]
* <input.txt> - path and filename of the file to be read by the WordTracker program.
* Exclusive options at command line
   - -pf - print all words in alphabetic order along with the correspoinding list of files in which the words occur.
   - -pl - print all words in alphabetic order along with corresponding list of files and numbers of the lines in which the word occur.
   - -po - print all words in alphabetic order all words along with the corresponding list of files, numbers of the lines in which the word occur and the frequency of the occurrence of the words.
* Optiona argument: redirect of the report in the previous step to the path and filename specified in <output.txt>.
