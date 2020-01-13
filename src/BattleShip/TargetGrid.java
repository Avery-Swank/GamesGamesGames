package BattleShip;

public class TargetGrid {

	private String[][] grid;
	
	public TargetGrid() {
		grid = new String[BattleShipProps.length][BattleShipProps.width];
		
		emptyGrid();
	}
	
	public void setSpot(int row, String column, String symbol) {
		
		int col = BattleShipProps.getNumber(column);
		
		if(row < 1 || row > grid.length)
			throw new Error("Invalid Row: " + row);
		
		if(symbol.equals(BattleShipProps.empty) || symbol.isBlank())
			throw new Error("Invalid Cannot Assign: 'e' or '' or ' ' To An Already Empty Spot");
		
		grid[row-1][col] = symbol;
	}
	
	public String getSpot(int row, String column) {
		
		int col = BattleShipProps.getNumber(column);
		
		if(row < 1 || row > grid.length)
			throw new Error("Invalid Row: " + row);
		
		return grid[row-1][col];
	}
	
	public void emptyGrid() {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = BattleShipProps.empty;
			}
		}
	}
	
	public int getLength() {
		return grid.length;
	}
	
	public int getWidth() {
		return grid[0].length;
	}
	
	public String toString() {
		String s = "-----Target Grid-----\n";
		for(int i = grid.length-1; i >= 0; i--) {
			
			// Add side numbers 1-9
			s = s.concat("" + (i+1) + " ");
			
			for(int j = 0; j < grid[i].length; j++) {
				s = s.concat(grid[i][j] + " ");
			}
			
			s = s.concat("\n");
		}
		
		// Add bottom row letters
		s = s.concat("  A B C D E F G H I J K \n");
		
		return s;
	}
}
