package Monopoly;

import java.util.ArrayList;

import Core.GameProps;

public class Game {

	private ArrayList<Player> players;
	private Board board;
	
	private boolean isPlaying;
	
	public Game(ArrayList<Player> _players, Board _board) {
		players = _players;
		board = _board;
		
		isPlaying = true;
	}
	
	public void play() {
		
		System.out.println("Playing a game of Monopoly with " + players.size() + " players...");
		
		// Each player rolls the die to see who goes first
		players = getPlayerOrder();
		
		// Print out the new player ordering
		System.out.print("Monopoly Playing Order: ");
		for(int i = 0; i < players.size(); i++) {
			if(i < players.size()-1)
				System.out.print(players.get(i).getName() + ", ");
			else
				System.out.println(players.get(i).getName() + "\n");
		}
		
		int currPlayerIndex = 0;
		while(players.size() > 1) {
			
			Player currPlayer = players.get(currPlayerIndex % players.size());
			
			if(!currPlayer.getIsInJail()) {
				
				// Player rolls a pair of dies
				int firstDie = GameProps.rollDie();
				int secondDie = GameProps.rollDie();
				
				// If they roll a double go again
				int numDoubles = 0;
				
				while(true) {
					
					if(GameProps.isDoubles(firstDie, secondDie))
						numDoubles++;
					
					// If they roll three doubles go to jail
					if(numDoubles == 3) {
						System.out.println(currPlayer.getName() + " has rolled 3 doubles in a roll. GO TO JAIL...");
						currPlayer.setInJail(true);
						currPlayer.setBoardPosition(10);
						break;
					}
					
					takeTurn(currPlayer, firstDie, secondDie);
					
					// if doubles, roll again
					if(GameProps.isDoubles(firstDie, secondDie)) {
						firstDie = GameProps.rollDie();
						secondDie = GameProps.rollDie();
					} else {
						break;
					}
				}
				
			} else {
				
				System.out.println(currPlayer.getName() + " is in jail. Rolling a double to try to break out...");
				
				// In order to break out of jail, the player has to roll a doubles
				int firstDie = GameProps.rollDie();
				int secondDie = GameProps.rollDie();
				
				if(GameProps.isDoubles(firstDie, secondDie)) {
					currPlayer.setInJail(false);
					System.out.println(currPlayer.getName() + " has broken out of jail!...");
					
					takeTurn(currPlayer, firstDie, secondDie);
				}
			}
			
			// Switch to the next player
			currPlayerIndex++;
			if(currPlayerIndex == players.size()) currPlayerIndex = 0;
		}
		
		
		// The remaining player is the winner because they were the last to go bankrupt
		Player winningPlayer = players.get(0);
		System.out.println(winningPlayer.getName() + " has won Monopoly!");
		System.out.println(winningPlayer);
	}
	
	private void takeTurn(Player currPlayer, int firstDie, int secondDie) {
		
		if(GameProps.isDoubles(firstDie, secondDie))
			System.out.println(currPlayer.getName() + " rolled doubles! A pair of " + firstDie + "'s...");
		else
			System.out.println(currPlayer.getName() + " rolled a " + firstDie + " and " + secondDie + "...");	
		
		// Move the player to that position
		// If the player goes beyond the size of the board, then the player passes GO and starts over on the board
		int currPosition = currPlayer.getBoardPosition();
		if(currPosition + firstDie + secondDie >= board.getSize()) {
			currPlayer.pay(200);
			int offset = currPosition + firstDie + secondDie - board.getSize();
			currPlayer.setBoardPosition(offset);
			System.out.println(currPlayer.getName() + " has passed GO. Will collect $200...");
		} else {
			currPlayer.setBoardPosition(currPosition + firstDie + secondDie);
		}
		
		// Get the Property, Railroad, or String that the player has landed on
		int newPosition = currPlayer.getBoardPosition();
		
		if(board.isProperty(newPosition)) {
			Property prop = board.getProperty(newPosition);
			System.out.println(currPlayer.getName() + " has landed on Property: " + prop.getName() + "...");
						
			// If the property is up for sale and the player can buy it, then buy it
			if(isPropertyForSale(prop) == null && currPlayer.getMoney() > prop.getMorgageValue()) {
				currPlayer.buyProperty(prop);
				System.out.println(currPlayer.getName() + " has purchased Property: " + prop.getName() + "...");
			}
			
			// If the property is owned by another player, then pay that property's rent
			if(isPropertyForSale(prop) != null && isPropertyForSale(prop) != currPlayer) {
				Player propertyOwner = isPropertyForSale(prop);
				currPlayer.pay(prop.getRent());
				propertyOwner.sell(prop.getRent());
				System.out.println(currPlayer.getName() + " has paid " + propertyOwner.getName() + " $" + prop.getRent() + " rent on Property: " + prop.getName() + "...");
			}
			
		} else if (board.isRailroad(newPosition)) {
			Railroad rr = board.getRailroad(newPosition);
			System.out.println(currPlayer.getName() + " has landed on Railroad: " + rr.getName() + "...");
			
			// If the railroad is up for sale and the player can buy it, then buy it
			if(isRailroadForSale(rr) == null && currPlayer.getMoney() > rr.getMorgageValue()) {
				currPlayer.buyRailroad(rr);
				System.out.println(currPlayer.getName() + " has purchased Property: " + rr.getName() + "...");
			}
			
			// If the railroad is owned by another player, then pay that railroad's rent
			if(isRailroadForSale(rr) != null && isRailroadForSale(rr) != currPlayer) {
				Player rrOwner = isRailroadForSale(rr);
				currPlayer.pay(rr.getRent(1));
				rrOwner.sell(rr.getRent(1));
				System.out.println(currPlayer.getName() + " has paid " + rrOwner.getName() + " $" + rr.getRent(1) + " rent on Railroad: " + rr.getName() + "...");
			}
			
		} else {
			String s = board.getString(newPosition);
			System.out.println(currPlayer.getName() + " has landed on: " + s + "...");
			
			switch(s) {
				case "COMMUNITY CHEST":
					System.out.println(currPlayer.getName() + " draws a 'Community Chest' card...");
					break;
				case "INCOME TAX":
					// Pay the minimum of: 10% of your money or $200
					int minIncomeTax = (int)Math.min(currPlayer.getMoney() * 0.10, 200);
					currPlayer.pay(minIncomeTax);
					System.out.println(currPlayer.getName() + " pays $" + minIncomeTax + " in Income Tax...");
					break;
				case "CHANCE":
					System.out.println(currPlayer.getName() + " draws a 'Chance' card...");
					break;
				case "VISITNG JAIL":
					break;
				case "GO TO JAIL":
					currPlayer.setBoardPosition(10);
					System.out.println(currPlayer.getName() + " is going to JAIL...");
				case "ELECTRIC COMPANY":
					break;
				case "FREE PARKING":
					break;
				case "WATER WORKS":
					break;
				case "LUXURY TAX":
					// Pay $75
					currPlayer.pay(75);
					System.out.println(currPlayer.getName() + " pays $75 in Luxury Tax...");
					break;
			}
		}
			
					
		// The exit case is if a player has a monopoly on every color of the game
		// or there is only one player remaining
		if(currPlayer.getMoney() < 0) {
			currPlayer.goBankrupt();
			System.out.println(currPlayer.getName() + " has gone bankrupt! Will sell put all of their properties and railroads...");
			players.remove(currPlayer);
		}
	}
	
	/**
	 * @description Return the player that owns this property. If no player owns it, return null
	 */
	public Player isPropertyForSale(Property prop) {
		for(Player player : players) {
			if(player.hasProperty(prop.getName()))
				return player;
		}
		return null;
	}
	
	/**
	 * @description Return the player that owns this railroad. If no player owns it, return null
	 */
	public Player isRailroadForSale(Railroad rr) {
		for(Player player : players) {
			if(player.hasRailroad(rr.getName()))
				return player;
		}
		return null;
	}
	
	/**
	 * @description Determine the ordering of the game by making each player roll a die and making the
	 * 				player with the highest roll go first down and in descending order.
	 * 				Any ties are broken by whoever rolls first.
	 */
	public ArrayList<Player> getPlayerOrder() {
		
		// Roll a die for each player
		int[] rolls = new int[players.size()];
		for(int i = 0; i < rolls.length; i++) {
			rolls[i] = GameProps.rollDie();
			System.out.println(players.get(i).getName() + " starting roll is a " + rolls[i] + "...");
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
			Player tempPlayer = players.get(i);
			players.set(i, players.get(maxIndex));
			players.set(maxIndex, tempPlayer);
		}
		
		return players;
	}
}
