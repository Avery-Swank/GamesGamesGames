package Core;

public class GameProps {
	
	/**
	 * @description Flip a coin
	 */
	public static boolean flipCoin() {
		return Math.random() < 0.5;
	}
	
	/**
	 * @description Roll a single die
	 */
	public static int rollDie() {
		return (int)(Math.random() * 6) + 1;
	}
	
	/**
	 * @description Return true if two dice are doubles (ie the same)
	 */
	public static boolean isDoubles(int x, int y) {
		return x == y;
	}

}
