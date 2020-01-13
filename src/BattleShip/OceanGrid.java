package BattleShip;

import java.util.ArrayList;

public class OceanGrid {

	private String[][] grid;
	private ArrayList<Ship> ships;
	
	public OceanGrid() {
		grid = new String[BattleShipProps.length][BattleShipProps.width];
		ships = new ArrayList<Ship>();
		
		emptyGrid();
	}
	
	public void addShip(int row, String column, String direction, Ship ship) {
		
		int col = BattleShipProps.getNumber(column);
		int shipLength = ship.getLength();
		
		switch(direction) {
			case "up":
				for(int i = row-1; i < row + shipLength-1; i++) {
					grid[i][col] = BattleShipProps.ship;
				}
				break;
			case "down":
				for(int i = row-1; i > row - shipLength-1; i--) {
					grid[i][col] = BattleShipProps.ship;
				}
				break;
			case "left":
				for(int i = col; i > col - shipLength; i--) {
					grid[row-1][i] = BattleShipProps.ship;
				}
				break;
			case "right":
				for(int i = col; i < col + shipLength; i++) {
					grid[row-1][i] = BattleShipProps.ship;
				}
				break;
			default : 
				throw new Error("Invalid Direction: " + direction);
		}
		
		ship.setLocation(row-1, column, direction);
		ships.add(ship);
	}
	
	public boolean canAddShip(int row, String column, String direction, Ship ship) {
		
		int col = BattleShipProps.getNumber(column);
		int shipLength = ship.getLength();
		boolean isValid = true;
		
		try {
			switch(direction) {
				case "up":
					for(int i = row-1; i < row + shipLength-1; i++) {
						isValid = isValid && grid[i][col].equals(BattleShipProps.empty);
					}
					break;
				case "down":
					for(int i = row-1; i > row - shipLength-1; i--) {
						isValid = isValid && grid[i][col].equals(BattleShipProps.empty);
					}
					break;
				case "left":
					for(int i = col; i > col - shipLength; i--) {
						isValid = isValid && grid[row-1][i].equals(BattleShipProps.empty);
					}
					break;
				case "right":
					for(int i = col; i < col + shipLength; i++) {
						isValid = isValid && grid[row-1][i].equals(BattleShipProps.empty);
					}
					break;
				default : 
					throw new Error("Invalid Direction: " + direction);
			}
			
			return isValid;
				
		// if the bounds go outside the grid, return false
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @description Update the ship objects with the symbols on the current grid
	 */
	public void refreshShips() {
		
		for(Ship ship : ships) {
			
			String newShip = "";
			
			int row = ship.getPosX();
			int col = BattleShipProps.getNumber(ship.getPosY());
			String direction = ship.getDirection();
			
			int shipLength = ship.getLength();
			
			switch(direction) {
				case "up":
					for(int i = row-1; i < row + shipLength-1; i++) {
						newShip = newShip.concat(grid[i][col]);
					}
					break;
				case "down":
					for(int i = row-1; i > row - shipLength-1; i--) {
						newShip = newShip.concat(grid[i][col]);
					}
					break;
				case "left":
					for(int i = col; i > col - shipLength; i--) {
						newShip = newShip.concat(grid[row-1][i]);
					}
					break;
				case "right":
					for(int i = col; i < col + shipLength; i++) {
						newShip = newShip.concat(grid[row-1][i]);
					}
					break;
				default : 
					throw new Error("Invalid Direction: " + direction);
			}
			
			ship.setShip(newShip);
		}
	}
	
	public ArrayList<Ship> getSunkenShips(){
		ArrayList<Ship> sunkenShips = new ArrayList<Ship>();
		for(int i = 0; i < ships.size(); i++) {
			if(ships.get(i).isSunk())
				sunkenShips.add(ships.get(i));
		}
		return sunkenShips;
	}
	
	public int getNumberOfSinks() {
		return getSunkenShips().size();
	}
	
	public boolean getSunkAllShips() {
		return getNumberOfSinks() == ships.size();
	}
	
	public void setSpot(int row, String column, String symbol) {
		
		int col = BattleShipProps.getNumber(column);
		
		if(row < 1 || row > grid.length)
			throw new Error("Invalid Row: " + row);
		
		if(symbol.equals(BattleShipProps.empty) || symbol.isBlank())
			throw new Error("Invalid Cannot Assign: 'e' or '' or ' ' To An Already Empty Spot");
		
		grid[row-1][col] = symbol;
		refreshShips();
	}
	
	public boolean isHit(int row, String column) {
		
		int col = BattleShipProps.getNumber(column);
		
		if(grid[row-1][col].equals(BattleShipProps.hit) || grid[row-1][col].equals(BattleShipProps.miss))
			throw new Error("Invalid. Already Picked This Spot");
		
		return grid[row-1][col].equals(BattleShipProps.ship);
	}
	
	public boolean isMiss(int row, String column) {
		
		int col = BattleShipProps.getNumber(column);
		
		if(grid[row-1][col].equals(BattleShipProps.hit) || grid[row-1][col].equals(BattleShipProps.miss))
			throw new Error("Invalid. Already Picked This Spot");
		
		return grid[row-1][col].equals(BattleShipProps.empty);
	}
	
	/**
	 * @description Set the whole grid to be 'empty'
	 */
	public void emptyGrid() {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = BattleShipProps.empty;
			}
		}
	}
	
	public String toString() {
		String s = "-----Ocean Grid-----\n";
		for(int i = grid.length-1; i >= 0; i--) {
			
			// Add side numbers 1-9
			s = s.concat("" + (i+1) + " ");
			
			for(int j = 0; j < grid[i].length; j++) {
				s = s.concat(grid[i][j] + " ");
			}
			
			s = s.concat("\n");
		}
		
		// Add bottom row letters
		s = s.concat("  A B C D E F G H I J K \n\n");
		
		// Add each Ship and its information
		s = s.concat("-----Ships-----\n");
		for(Ship ship : ships) {
			s = s.concat(ship.toString());
			s = s.concat("\n\n");
		}
		
		return s;
	}
}
