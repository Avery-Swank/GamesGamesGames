package BattleShip;

public class Strategies {

	/**
	 * @description Return a random spot that is not a 'h' or 'm'
	 * 				Random range row: [1,9] and column ["A", "K"]
	 */
	public static int[] random(TargetGrid targetGrid) {
		
		int randRow = (int)(Math.random() * (BattleShipProps.length)) + 1;
		int randCol = (int)(Math.random() * BattleShipProps.width);
		String randColumn = BattleShipProps.getLetter(randCol);
		
		String spot = targetGrid.getSpot(randRow, randColumn);
		
		// keep trying to find an empty spot until one is found
		while(!spot.equals(BattleShipProps.empty)) {
			randRow = (int)(Math.random() * (BattleShipProps.length)) + 1;
			randCol = (int)(Math.random() * BattleShipProps.width);
			randColumn = BattleShipProps.getLetter(randCol);
			spot = targetGrid.getSpot(randRow, randColumn);
		}
		
		return new int[] {randRow, randCol};
	}
}
