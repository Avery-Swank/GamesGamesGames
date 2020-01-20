package Monopoly;

import Core.GameProps;

public class Game {

	private Player[] players;
	private Board board;
	
	private boolean isPlaying;
	
	public Game(Player[] _players, Board _board) {
		players = _players;
		board = _board;
		
		isPlaying = true;
	}
	
	public void play() {
		
		System.out.println("Playing a game of Monopoly with " + players.length + " players...");
		
		// Each player rolls the die to see who goes first
		players = getPlayerOrder();
		
		// Print out the new player ordering
		System.out.print("Monopoly Playing Order: ");
		for(int i = 0; i < players.length; i++) {
			if(i < players.length-1)
				System.out.print(players[i].getName() + ", ");
			else
				System.out.println(players[i].getName());
		}
		
		int currPlayerIndex = 0;
		while(isPlaying) {
			
			Player currPlayer = players[currPlayerIndex % players.length];
			
			// Each player rolls a pair of dies
			// - If they roll a double go again
			// - If they roll three doubles go to jail
			int firstDie = GameProps.rollDie();
			int secondDie = GameProps.rollDie();
			
			// Get the players roll information
			
			if(GameProps.isDoubles(firstDie, secondDie))
				System.out.println(currPlayer.getName() + " rolled doubles! A pair of " + firstDie + "'s...");
			else
				System.out.println(currPlayer.getName() + " rolled a " + firstDie + " and " + secondDie + "...");	
			
			// Move the player to that position
			// If the player goes beyond the size of the board, then the player passes GO and starts over on the board
			int currPosition = currPlayer.getBoardPosition();
			if(currPosition + firstDie + secondDie >= board.getSize()) {
				System.out.println(currPlayer.getName() + " has passed GO. Will collect $200...");
				int offset = currPosition + firstDie + secondDie - board.getSize();
				currPlayer.setBoardPosition(offset);
			} else {
				currPlayer.setBoardPosition(currPosition + firstDie + secondDie);
			}
			
			// Get the Property, Railroad, or String that the player has landed on
			int newPosition = currPlayer.getBoardPosition();
			if(board.isProperty(newPosition)) {
				Property prop = board.getProperty(newPosition);
				System.out.println(currPlayer.getName() + " has landed on Property: " + prop.getName() + "...");
			} else if (board.isRailroad(newPosition)) {
				Railroad rr = board.getRailroad(newPosition);
				System.out.println(currPlayer.getName() + " has landed on Railroad: " + rr.getName() + "...");
			} else {
				String s = board.getString(newPosition);
				System.out.println(currPlayer.getName() + " has landed on: " + s + "...");
			}
			
			// If the player is in jail, then they either roll a double or pay the fine to get out
			
			// If lands on a chance or treasure card, do as it instructs
			// If land on a property that is not owned, buy it
			// If land on a property that is owned, pay the rent
			
			// The exit case is if a player has a monopoly on every color of the game
			// or there is only one player remaining
			
			// Switch to the next player
			currPlayerIndex++;
			if(currPlayerIndex == players.length) currPlayerIndex = 0;
		}
	}
	
	/**
	 * @description Determine the ordering of the game by making each player roll a die and making the
	 * 				player with the highest roll go first down and in descending order.
	 * 				Any ties are broken by whoever rolls first.
	 */
	public Player[] getPlayerOrder() {
		
		// Roll a die for each player
		int[] rolls = new int[players.length];
		for(int i = 0; i < rolls.length; i++) {
			rolls[i] = GameProps.rollDie();
			System.out.println(players[i].getName() + " starting roll is a " + rolls[i] + "...");
		}
		
		// Reorder based on highest rolls first
		for(int i = 0; i < rolls.length; i++) {
			int max = rolls[i];
			int maxIndex = i;
			for(int j = i+1; j < rolls.length; j++) {
				if(rolls[j] > max) {
					max = rolls[j];
					maxIndex = j;
				}
			}
			
			// Swap highest roll with the next in the list
			int tempRoll = rolls[i];
			rolls[i] = max;
			rolls[maxIndex] = tempRoll;
			
			// Swap highest player with the next in the list
			Player tempPlayer = players[i];
			players[i] = players[maxIndex];
			players[maxIndex] = tempPlayer;
		}
		
		return players;
	}
}
