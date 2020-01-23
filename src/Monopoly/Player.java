package Monopoly;

import java.util.ArrayList;

public class Player {

	private String name;
	private String piece;
	private int money;
	
	private ArrayList<Property> properties;
	private ArrayList<Railroad> railroads;
	
	// Player properties relative to the monopoly board
	private int boardPosition;
	private boolean isInJail;
	
	public Player(String _name, String _piece, int _money) {
		name = _name;
		piece = _piece;
		money = _money;
		
		properties = new ArrayList<Property>();
		railroads = new ArrayList<Railroad>();
		
		boardPosition = 0;
		isInJail = false;
	}
	
	public void pay(int cost) {
		money -= cost;
	}
	
	public void sell(int cost) {
		money += cost;
	}
	
	public void buyProperty(Property prop) {
		properties.add(prop);
		pay(prop.getMorgageValue());
	}
	
	public boolean hasProperty(String name) {
		for(Property prop : properties) {
			if(prop.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public void sellProperty(Property prop) {
		properties.remove(prop);
		sell(prop.getMorgageValue());
	}
	
	public void buyRailroad(Railroad rr) {
		railroads.add(rr);
		pay(rr.getMorgageValue());
	}
	
	public boolean hasRailroad(String name) {
		for(Railroad rr : railroads) {
			if(rr.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public void sellRailroad(Railroad rr) {
		railroads.remove(rr);
		sell(rr.getMorgageValue());
	}
	
	public int getNumRailroadsOwned() {
		return railroads.size();
	}
	
	public boolean hasPropertyMonopoly(String color) {
		int numColors = MonopolyProps.getNumColors(color);
		int currNumColors = 0;
		for(Property prop : properties) {
			if(prop.getColor().equals(color))
				currNumColors++;
		}
		return numColors == currNumColors;
	}
	
	public boolean hasRailroadMonopoly() {
		return railroads.size() == 4;
	}
	
	public void setBoardPosition(int position) {
		boardPosition = position;
	}
	
	public void setInJail(boolean inJail) {
		isInJail = inJail;
	}
	
	/**
	 * @description Go Bankrupt. Remove all of the players properties and railroads
	 */
	public void goBankrupt() {
		properties.clear();
		railroads.clear();
	}
	
	public String getName() {
		return name;
	}
	
	public String getPiece() {
		return piece;
	}
	
	public int getMoney() {
		return money;
	}
	
	public ArrayList<Property> getProperties(){
		return properties;
	}
	
	public ArrayList<Railroad> getRailroads(){
		return railroads;
	}
	
	public int getBoardPosition() {
		return boardPosition;
	}
	
	public boolean getIsInJail() {
		return isInJail;
	}
	
	public String toString() {
		String s = "Player: " + name + " Piece: " + piece + " Money: " + money + "\n";
		for(Property prop : properties) {
			s = s.concat(prop.toString());
		}
		for(Railroad rr : railroads) {
			s = s.concat(rr.toString());
		}
		return s;
	}
}
