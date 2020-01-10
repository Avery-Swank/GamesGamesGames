package Mancala;

public class Player {

	private String name;
	private String type;
	
	public Player(String _name, String _type) {
		name = _name;
		type = _type;
	}
	
	public int getMove(int[] playerSpaces) {
		
		switch(type) {
			case "random":
				return Strategies.random(playerSpaces);
			case "strategic":
				return Strategies.strategic(playerSpaces);
			default:
				throw new Error("Invalid Type: " + type);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return "Player: " + name + " Type: " + type; 
	}
}
