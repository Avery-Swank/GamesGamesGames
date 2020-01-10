package Mancala;

public class Run {

	public static void main(String[] args) {
		
		Board board = new Board();
		Player player = new Player("avery", "strategic");
		Player opponent = new Player("luke", "random");
		
		Game mancala = new Game(board, player, opponent);
		mancala.play();
		
	}
}
