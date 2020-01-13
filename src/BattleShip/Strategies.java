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
	
	/**
	 * @description Scan the board for any spots marked 'h' and hit ever adjacent space around it
	 * 				in a sort of breadth first fashion. Otherwise, play randomly
	 * 				Pros: This is significantly better than random as it focuses on hitting around any randomly
	 * 				stumbled 'h'.
	 * 				Cons: It will eventually sink a battleship but will still check all adjacent edges afterwards
	 */
	public static int[] breadth(TargetGrid targetGrid) {
		
		for(int i = 1; i <= BattleShipProps.length; i++) {
			for(int j = 0; j< BattleShipProps.letters.length; j++) {
				
				String spotString = targetGrid.getSpot(i, BattleShipProps.letters[j]);
				
				// if the spot is a 'hit' then hit any empty, adjacent pieces
				if(spotString.equals(BattleShipProps.hit)) {
					
					// try the space above the 'hit'
					try {
						String adjSpot = targetGrid.getSpot(i-1, BattleShipProps.letters[j]);
						if(adjSpot.equals(BattleShipProps.empty)) {
							return new int[] {i-1, j};
						}
					} catch (Error e) {/* ignore */}
					  catch (Exception e) {/* ignore */}
					
					// try the space below the 'hit'
					try {
						String adjSpot = targetGrid.getSpot(i+1, BattleShipProps.letters[j]);
						if(adjSpot.equals(BattleShipProps.empty)) {
							return new int[] {i+1, j};
						}
					} catch (Error e) {/* ignore */}
					  catch (Exception e) {/* ignore */}
					
					// try the space left to the 'hit'
					try {
						String adjSpot = targetGrid.getSpot(i, BattleShipProps.letters[j-1]);
						if(adjSpot.equals(BattleShipProps.empty)) {
							return new int[] {i, j-1};
						}
					} catch (Error e) {/* ignore */}
					  catch (Exception e) {/* ignore */}
					
					// try the space right to the 'hit'
					try {
						String adjSpot = targetGrid.getSpot(i, BattleShipProps.letters[j+1]);
						if(adjSpot.equals(BattleShipProps.empty)) {
							return new int[] {i, j+1};
						}
					} catch (Error e) {/* ignore */}
					  catch (Exception e) {/* ignore */}
				}
			}
		}
		
		return random(targetGrid);
	}
}
