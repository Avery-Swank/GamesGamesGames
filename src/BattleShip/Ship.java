package BattleShip;

public class Ship {

	// Ship properties
	private String name;
	private String ship;
	
	// Ship location properties
	private int positionX;
	private String positionY;
	private String direction;
	
	public Ship(String _name, int length) {
		name = _name;
		
		if(length < 0 || (length > BattleShipProps.length && length > BattleShipProps.width))
			throw new Error("Invalid Ship Length: " + length);
		
		// First update to the ship is all 's' ship spots
		ship = BattleShipProps.ship;
		for(int i = 1; i < length; i++) {
			ship = ship.concat(BattleShipProps.ship);
		}
	}
	
	public void setShip(String _ship) {
		ship = _ship;
	}
	
	public void setLocation(int posX, String posY, String dir) {
		positionX = posX;
		positionY = posY;
		direction = dir;
	}
	
	/**
	 * @description Return true if the entire ship is hit (ie sunk)
	 */
	public boolean isSunk() {
		for(int i = 0; i < ship.length(); i++) {
			String c = "" + ship.charAt(i);
			if(c.equals(BattleShipProps.ship))
				return false;
		}
		return true;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLength() {
		return ship.length();
	}
	
	public int getPosX() {
		return positionX+1;
	}
	
	public String getPosY() {
		return positionY;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public String toString() {
		return "Name: '" + name + "' Status: " + ship + "\n" + 
			   "Location: " + (positionX+1) + "" + positionY + " Direction: " + direction;
	}
	
}
