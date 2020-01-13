package BattleShip;

public class BattleShipProps {
	
	public static String[] directions = {"up", "down", "left", "right"};
	public static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
	
	// Board Dimensions
	public static int length = 9;
	public static int width = 11;

	// Symbols
	public static String empty = "e";
	public static String hit = "h";
	public static String miss = "m";
	public static String ship = "s";
	
	// Get the column index in the grid associated with the letter like on a BattleShip board
	public static int getNumber(String x) {
		
		switch (x) {
			case "A":return 0;	
			case "B":return 1;
			case "C":return 2;	
			case "D":return 3;	
			case "E":return 4;	
			case "F":return 5;
			case "G":return 6;	
			case "H":return 7;
			case "I":return 8;
			case "J":return 9;
			case "K":return 10;
			default: throw new Error("Invalid Column Letter: " + x);
		}
	}
	
	// Get the letter associated with the column index
	public static String getLetter(int x) {
			
		switch (x) {
			case 0:return "A";	
			case 1:return "B";
			case 2:return "C";	
			case 3:return "D";	
			case 4:return "E";	
			case 5:return "F";
			case 6:return "G";	
			case 7:return "H";
			case 8:return "I";
			case 9:return "J";
			case 10:return "K";
			default: throw new Error("Invalid Integer: " + x);
		}
	}
}
