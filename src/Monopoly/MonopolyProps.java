package Monopoly;

public class MonopolyProps {

	public static String[] pieces = {"scottie dog", "top hat", "thimble", "boot", "wheelbarrow", "cat", "racing car", "battleship"};
	
	// Board properties corresponding to the monopolies on the board
	public static String[] colors = {"brown", "light blue", "purple", "orange", "red", "yellow", "green", "blue"};
	public static int[] numColors = {2, 3, 3, 3, 3, 3, 3, 2};
	
	public static int getNumColors(String color) {
		int colorIndex = -1, i = 0;
		for(String c : MonopolyProps.colors) {
			if(color.equals(c)) {
				colorIndex = i;
				break;
			}
			i++;
		}
		return colorIndex;
	}
}
