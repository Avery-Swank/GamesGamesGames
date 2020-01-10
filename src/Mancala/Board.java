package Mancala;

public class Board {
	
	private int numStones = 48;
	private int spacesPerPlayer = 6;
	private int stonesPerSpace = numStones / (spacesPerPlayer * 2);

	private int[] playerSpaces;
	private int[] opponentSpaces;
	
	private int playerStore;
	private int opponentStore;
	
	public Board() {
		playerSpaces = new int[spacesPerPlayer];
		opponentSpaces = new int[spacesPerPlayer];
		
		playerStore = 0;
		opponentStore = 0;
		
		fillBoard();
	}
	
	private void fillBoard() {
		
		// Fill opponents spaces with 4 stones each
		for(int i = 0; i < opponentSpaces.length; i++) {
			opponentSpaces[i] = stonesPerSpace;
		}
		
		// Fill player spaces with 4 stones each
		for(int i = 0; i < playerSpaces.length; i++) {
			playerSpaces[i] = stonesPerSpace;
		}
	}
	
	/**
	 * @description When the game is over, fill the stores with the rest of what is in each
	 * 				player's spaces.
	 */
	public void fillStores() {
		
		System.out.println("Game Finsihed. Filling Stores with Remaining Stones...");
		
		for(int i = 0; i < opponentSpaces.length; i++) {
			opponentStore += opponentSpaces[i];
			opponentSpaces[i] = 0;
		}
				
		for(int i = 0; i < playerSpaces.length; i++) {
			playerStore += playerSpaces[i];
			playerSpaces[i] = 0;
		}
	}
	
	/**
	 * @description Cycle around the Mancala starting from the player's index 'index' and keep cycling until
	 * 				the player hits an empty space or its own mancala
	 * 
	 *  - if the player runs out of stones in their store, return true
		- if the player runs out of stones in an empty space, return false
	 */
	public boolean playPlayerSpace(int index) {
		
		// Get first stones
		int numStones = playerSpaces[index];
		
		// go to the next space
		playerSpaces[index] = 0;
		index++;
		
		while(numStones > 0) {
			
			// Add stones to the player's rows
			while(index < playerSpaces.length && numStones > 0) {
				playerSpaces[index]++;
				numStones--;
				
				// if out of stones and current space has more stones, pick up the stones in this space and keep going
				if(numStones == 0 && playerSpaces[index] > 1) {
					numStones = playerSpaces[index];
					playerSpaces[index] = 0;
				}
				
				index++;
			}
			
			// Add a stone to the player's store
			if(numStones > 0) {
				playerStore++;
				numStones--;
				
				if(numStones == 0) {
					return true;
				}
			}
			
			// Add stones to the opponent's rows
			index = 0;
			while(index < opponentSpaces.length && numStones > 0) {
				opponentSpaces[index]++;
				numStones--;
				
				// if out of stones and current space has more stones, pick up the stones in this space and keep going
				if(numStones == 0 && opponentSpaces[index] > 1) {
					numStones = opponentSpaces[index];
					opponentSpaces[index] = 0;
				}
				
				index++;
			}
			
			// DO NOT add a stone to the opponent's store
			
			index = 0;
		}
		
		return false;		
	}
	
	/**
	 * @description Cycle around the Mancala starting from the opponent's index 'index' and keep cycling until
	 * 				the opponent hits an empty space or its own mancala
	 * 
	 *  - if the opponent runs out of stones in their store, return true
		- if the opponent runs out of stones in an empty space, return false
	 */
	public boolean playOpponentSpaces(int index) {
		
		// Get first stones
		int numStones = opponentSpaces[index];
		
		// go to the next space
		opponentSpaces[index] = 0;
		index++;
		
		while(numStones > 0) {
			
			// Add stones to the opponent's rows
			while(index < opponentSpaces.length && numStones > 0) {
				opponentSpaces[index]++;
				numStones--;
				
				// if out of stones and current space has more stones, pick up the stones in this space and keep going
				if(numStones == 0 && opponentSpaces[index] > 1) {
					numStones = opponentSpaces[index];
					opponentSpaces[index] = 0;
				}
				
				index++;
			}
			
			// Add a stone to the opponent's store
			if(numStones > 0) {
				opponentStore++;
				numStones--;
				
				if(numStones == 0) return true;
			}
			
			// Add stones to the player's rows
			index = 0;
			while(index < playerSpaces.length && numStones > 0) {
				playerSpaces[index]++;
				numStones--;
				
				// if out of stones and current space has more stones, pick up the stones in this space and keep going
				if(numStones == 0 && playerSpaces[index] > 1) {
					numStones = playerSpaces[index];
					playerSpaces[index] = 0;
				}
				
				index++;
			}
			
			// DO NOT add a stone to the player's store
			
			index = 0;
		}
		
		return false;		
	}
	
	/**
	 * @description Return if the row is empty. This is what determines if the game ends
	 */
	private boolean isRowEmpty(int[] row) {
		for(int i = 0; i < row.length; i++) {
			if(row[i] > 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isPlayerRowEmpty() {
		return isRowEmpty(playerSpaces);
	}
	
	public boolean isOpponentRowEmpty() {
		return isRowEmpty(opponentSpaces);
	}
	
	public int[] getPlayerSpaces() {
		return playerSpaces;
	}
	
	public int[] getOpponentSpaces() {
		return opponentSpaces;
	}
	
	public int getPlayerStore() {
		return playerStore;
	}
	
	public int getOpponentStore() {
		return opponentStore;
	}
	
	public int getTotolStones() {
		int total = 0;
		
		for(int i = 0; i < opponentSpaces.length; i++) {
			total += opponentSpaces[i];
		}
				
		for(int i = 0; i < playerSpaces.length; i++) {
			total += playerSpaces[i];
		}
		
		return total + playerStore + opponentStore;
	}
	
	public String toString() {
		String s = "    ";
		
		// Add opponent values
		for(int i = opponentSpaces.length-1; i >= 0; i--) {
			if(opponentSpaces[i] < 10) {
				s = s.concat(opponentSpaces[i] + "  ");
			} else{
				s = s.concat(opponentSpaces[i] + " ");
			}
		}
		
		// Add opponent and player stores
		s = s.concat("\n");
		s = s.concat("" + opponentStore + "\t\t\t" + playerStore);
		s = s.concat("\n");
		s = s.concat("    ");
		
		// Add player values
		for(int i = 0; i < playerSpaces.length; i++) {
			if(playerSpaces[i] < 10) {
				s = s.concat(playerSpaces[i] + "  ");
			} else{
				s = s.concat(playerSpaces[i] + " ");
			}
		}
		
		s = s.concat("\n");
		s = s.concat("Stones: " + getTotolStones());
		
		return s;
	}
}
