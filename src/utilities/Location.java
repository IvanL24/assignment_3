package utilities;

import java.io.Serializable;

public class Location implements Serializable {

	String fileName;
	int lineNumber;
	
	public Location(String fileName, int lineNumber) {
		this.lineNumber = lineNumber;
		this.fileName = fileName;
	}
	
}
