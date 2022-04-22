package utilities;

import java.util.ArrayList;

public class Word {

	public String word;
	public ArrayList<Location> locations;
	
	public Word(String word) {
		this.locations = new ArrayList<Location>();
		this.word = word;
	}
	
	public void addLocation(Location location) {
		this.locations.add(location);
	}
	
}
