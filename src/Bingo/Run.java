package Bingo;

public class Run {

	public static void main(String[] args) {

		Game classicGame = new Game(100, "classic");
		Game fourCornersGame = new Game(100, "four corners");
		Game blackoutGame = new Game(100, "blackout");
		
		classicGame.play();
		fourCornersGame.play();
		blackoutGame.play();
	}

}
