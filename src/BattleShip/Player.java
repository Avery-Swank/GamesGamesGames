package BattleShip;

public class Player {

	private String name;
	private String type;
	
	private TargetGrid targetGrid;
	private OceanGrid oceanGrid;
	
	public Player(String _name, String _type) {
		name = _name;
		type = _type;
		
		targetGrid = new TargetGrid();
		oceanGrid = new OceanGrid();
	}
	
	public int[] play() {
		
		int[] move;
		
		switch(type) {
			case "random":
				move = Strategies.random(targetGrid);
				break;
			default:
				throw new Error("Invalid Player Type: " + type);
		}
		
		return move;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public TargetGrid getTargetGrid() {
		return targetGrid;
	}
	
	public OceanGrid getOceanGrid() {
		return oceanGrid;
	}
	
	public String toString() {
		return "Player: " + name + " Type: " + type + "\n\n" +
			   targetGrid.toString() + "\n\n" + 
			   oceanGrid.toString();
	}
}
