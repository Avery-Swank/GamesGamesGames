package Monopoly;

import java.util.ArrayList;

public class Board {

	ArrayList<Object> spaces;
	
	public Board() {
		spaces = new ArrayList<Object>();
		addSpaces();
	}
	
	// Property START ---------------------------------------------------
	public boolean isProperty(int boardPosition) {
		try {
			Property prop = (Property)spaces.get(boardPosition);
			return prop != null;
		} catch (ClassCastException e) {
			return false;
		}
	}
	
	public Property getProperty(int boardPosition) {
		return (Property)spaces.get(boardPosition);
	}
	// Property END -----------------------------------------------------
	
	// Railroad START ---------------------------------------------------
	public boolean isRailroad(int boardPosition) {
		try {
			Railroad rr = (Railroad)spaces.get(boardPosition);
			return rr != null;
		} catch (ClassCastException e) {
			return false;
		}
	}
	
	public Railroad getRailroad(int boardPosition) {
		return (Railroad)spaces.get(boardPosition);
	}
	// Railroad END -----------------------------------------------------
	
	// Other Strings START ----------------------------------------------
	public boolean isString(int boardPosition, String s) {
		try {
			String spaceString = (String)spaces.get(boardPosition);
			return spaceString.equals(s);
		} catch (ClassCastException e) {
			return false;
		}
	}
	
	public String getString(int boardPosition) {
		return (String) spaces.get(boardPosition);
	}
	// Other Strings END ------------------------------------------------
	
	/**
	 * @description Add all of the monopoly spaces to the board
	 */
	private void addSpaces() {
		
		// Add the corner "GO"
		spaces.add("GO");
		
		// Add the first row
		spaces.add(new Property("Mediterranean Avenue", "brown", 2, 4, 10, 30, 90, 160, 50, 50, 50));
		spaces.add("COMMUNITY CHEST");
		spaces.add(new Property("Baltic Avenue", "brown", 4, 8, 20, 60, 180, 320, 50, 50, 50));
		spaces.add("INCOME TAX");
		spaces.add(new Railroad("Reading R.R.", 25, 50, 100, 200, 100));
		spaces.add(new Property("Oriental Avenue", "light blue", 6, 12, 30, 90, 270, 400, 50, 50, 50));
		spaces.add("CHANCE");
		spaces.add(new Property("Vermont Avenue", "light blue", 6, 12, 30, 90, 270, 400, 50, 50, 50));
		spaces.add(new Property("Connecticut Avenue", "light blue", 8, 16, 40, 100, 300, 450, 50, 50, 50));
		
		// Add the corner "VISITING JAIL"
		spaces.add("VISITING JAIL");
		
		// Add the second row
		spaces.add(new Property("St. Charles Place", "purple", 10, 20, 50, 150, 450, 625, 100, 100, 100));
		spaces.add("ELECTRIC COMPANY");
		spaces.add(new Property("States Avenue", "purple", 10, 20, 50, 150, 450, 625, 100, 100, 100));
		spaces.add(new Property("Virginia Avenue", "purple", 12, 24, 60, 180, 500, 700, 100, 100, 100));
		spaces.add(new Railroad("Pennsylvania R.R.", 25, 50, 100, 200, 100));
		spaces.add(new Property("St. James Place", "orange", 14, 28, 70, 200, 550, 750, 100, 100, 100));
		spaces.add("COMMUNITY CHEST");
		spaces.add(new Property("Tennessee Avenue", "orange", 14, 28, 70, 200, 550, 750, 100, 100, 100));
		spaces.add(new Property("New York Avenue", "orange", 16, 32, 80, 220, 600, 800, 100, 100, 100));
		
		// Add the corner "FREE PARKING"
		spaces.add("FREE PARKING");
		
		// Add the third row
		spaces.add(new Property("Kentucky Avenue", "red", 18, 36, 90, 250, 700, 875, 150, 150, 150));
		spaces.add("CHANCE");	
		spaces.add(new Property("Indiana Avenue", "red", 18, 36, 90, 250, 700, 875, 150, 150, 150));
		spaces.add(new Property("Illinois Avenue", "red", 20, 40, 100, 300, 750, 925, 150, 150, 150));
		spaces.add(new Railroad("B & O R.R.", 25, 50, 100, 200, 100));
		spaces.add(new Property("Atlantic Avenue", "yellow", 22, 44, 110, 330, 800, 975, 150, 150, 150));
		spaces.add(new Property("Ventnore Avenue", "yellow", 22, 44, 110, 330, 800, 975, 150, 150, 150));
		spaces.add("WATER WORKS");	
		spaces.add(new Property("Marvin Gardens", "yellow", 24, 48, 120, 360, 850, 1025, 150, 150, 150));
		
		// Add the corner "GO TO JAIL"
		spaces.add("GO TO JAIL");
		
		// Add the fourth row
		spaces.add(new Property("Pacific Avenue", "green", 50, 200, 600, 1400, 1700, 200, 200, 200, 200));
		spaces.add(new Property("North Carolina Avenue", "green", 50, 200, 600, 1400, 1700, 200, 200, 200, 200));
		spaces.add("COMMUNITY CHEST");
		spaces.add(new Property("Pennsylvania Avenue", "green", 50, 200, 600, 1400, 1700, 200, 200, 200, 200));
		spaces.add(new Railroad("Short Line.", 25, 50, 100, 200, 100));
		spaces.add("CHANCE");
		spaces.add(new Property("Park Place", "blue", 50, 200, 600, 1400, 1700, 200, 200, 200, 200));
		spaces.add("LUXURY TAX");
		spaces.add(new Property("Boardwalk", "blue", 50, 200, 600, 1400, 1700, 200, 200, 200, 200));
		
	}
	
	public int getSize() {
		return spaces.size();
	}
}
