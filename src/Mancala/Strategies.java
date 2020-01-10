package Mancala;

import java.util.ArrayList;

public class Strategies {

	/**
	 * @description Return a list of indeces the player can play
	 * @param row
	 */
	public static ArrayList<Integer> getAvailableSpaces(int row[]) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < row.length; i++) {
			if(row[i] > 0) {
				list.add(i);
			}
		}
		return list;
	}
	
	/**
	 * @description Play a random move
	 */
	public static int random(int row[]) {
		
		ArrayList<Integer> moves = getAvailableSpaces(row);
		
		if(moves.size() == 0) return -1;
		
		return moves.get((int)(Math.random() * moves.size()));
	}
	
	/**
	 * @description Play a move that guarantees that the stones end in that players store. This guarantees that the
	 * 				player will get to go at least a second time.
	 * 
	 * 				Optimal Mancala:
	 * 				    1  2  3  4  5  6  
					23					  25
    					6  5  4  3  2  1  
	 * 
	 * 				Best to start closest to the store and move further back to place the optimal number of stones in your store
	 * 				If there is not such a move, then pick randomly
	 */
	public static int strategic(int row[]) {
		
		// Check for a move that guarantees the player another turn
		// Starting closest to farthest for the best strategy
		for(int i = row.length-1; i >= 0; i--) {
			if(row[i] == row.length - i) {
				return i;
			}
		}
		
		return random(row);
	}
}
