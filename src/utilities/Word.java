package utilities;

import java.io.Serializable;
import java.util.ArrayList;

public class Word implements Comparable<Word>, Serializable {

	public String word;
	public ArrayList<Location> locations;
	
	public Word(String word) {
		this.locations = new ArrayList<Location>();
		this.word = word;
	}
	
	public void addLocation(Location location) {
		this.locations.add(location);
	}

	@Override
	public int compareTo(Word o) {
		// TODO Auto-generated method stub
		return this.word.compareTo(o.word);
	}
	
}
