package BattleShip;

import java.util.ArrayList;

public class Game {

	private Player p1;
	private Player p2;
	
	private Ship[] shipTypes;
	
	public Game(Player _p1, Player _p2, Ship[] _shipTypes) {
		p1 = _p1;
		p2 = _p2;
		
		shipTypes = _shipTypes;
	}
	
	public void play() {
		
		boolean playing = true;
		
		Player currPlayer = p1;
		Player currOpponent = p2;
		
		// Start the game by adding ships to each player's boards
		addShips(currPlayer);
		addShips(currOpponent);
		
		while(playing) {
			
			// Get the current player's move
			int[] move = currPlayer.play();
			
			int row = move[0];
			int column = move[1];
			String col = BattleShipProps.getLetter(column);
			ArrayList<Ship> prevSunkenShips = currOpponent.getOceanGrid().getSunkenShips();
			
			System.out.println(currPlayer.getName() + " plays " + row + col + "...");
			
			// Get if it is a hit or a miss
			boolean isHit = currOpponent.getOceanGrid().isHit(row, col);
			
			// Add the move to the current player's target grid
			// Add the move to the opponent player's ocean grid
			if(isHit) {
				currPlayer.getTargetGrid().setSpot(row, col, BattleShipProps.hit);
				currOpponent.getOceanGrid().setSpot(row, col, BattleShipProps.hit);
				System.out.println("" + row + col + " is a hit...");
			} else {
				currPlayer.getTargetGrid().setSpot(row, col, BattleShipProps.miss);
				currOpponent.getOceanGrid().setSpot(row, col, BattleShipProps.miss);
				System.out.println("" + row + col + " is a miss...");
			}
			
			ArrayList<Ship> postSunkenShips = currOpponent.getOceanGrid().getSunkenShips();
			
			// Check if the player sunk a battleship
			if(prevSunkenShips.size() < postSunkenShips.size()) {
				Ship newSunkenShip = getNewSunkenShip(prevSunkenShips, postSunkenShips);
				System.out.println(currPlayer.getName() + " has sunken the '" + newSunkenShip.getName() + "'...");
			}
			
			boolean sunkAllShips = currOpponent.getOceanGrid().getSunkAllShips();
			if(sunkAllShips) {
				playing = false;
				System.out.println(currPlayer.getName() + " Won BattleShip!");
			}
			
			// Alternate turns
			if(currPlayer == p1) {
				currPlayer = p2;
				currOpponent = p1;
			} else {
				currPlayer = p1;
				currOpponent = p2;
			}
		}
		
		System.out.println(currPlayer);
		System.out.println(currOpponent);
		
	}
	
	/**
	 * @description Randomly add one of each ship type to the player's ocean board
	 * 				For our sake, the ships are placed *randomly*
	 */
	private void addShips(Player player) {
		
		OceanGrid oceanGrid = player.getOceanGrid();
		
		for(Ship _ship : shipTypes) {
			
			// Create a new instance so each ship on every board is independent
			Ship ship = new Ship(_ship.getName(), _ship.getLength());
			
			// Create random ship configuration
			int randRow = (int)(Math.random() * (BattleShipProps.length)) + 1;
			int randCol = (int)(Math.random() * BattleShipProps.width);
			String randDirection = BattleShipProps.directions[(int)(Math.random() * BattleShipProps.directions.length)];
			String randColumn = BattleShipProps.getLetter(randCol);
			
			// Keep trying random ship configurations until a valid one is found
			while(!oceanGrid.canAddShip(randRow, randColumn, randDirection, ship)) {
				randRow = (int)(Math.random() * (BattleShipProps.length)) + 1;
				randCol = (int)(Math.random() * BattleShipProps.width);
				randDirection = BattleShipProps.directions[(int)(Math.random() * BattleShipProps.directions.length)];
				randColumn = BattleShipProps.getLetter(randCol);
			}
			
			// Add this ship to the ship board
			oceanGrid.addShip(randRow, randColumn, randDirection, ship);
			System.out.println(player.getName() + " added a '" + ship.getName() + "' to their ocean grid...");
		}
	}
	
	private Ship getNewSunkenShip(ArrayList<Ship> prev, ArrayList<Ship> post) {
		
		for(int i = 0; i < prev.size(); i++) {
			Ship prevShip = prev.get(i);
			for(int j = 0; j < post.size(); j++) {
				Ship postShip = post.get(j);
				if(prevShip.getName().equals(postShip.getName())) {
					post.remove(j);
				}
			}
		}
		
		return post.get(0);
	}
}
