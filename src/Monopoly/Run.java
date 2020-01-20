package Monopoly;

public class Run {

	public static void main(String[] args) {

		//Property boardwalk = new Property("Boardwalk", "blue", 50, 200, 600, 1400, 1700, 2000, 200, 200, 200);
		//Railroad pennsylvania = new Railroad("Pensylvannia R.R.", 25, 50, 100, 200, 100);
		
		// Each player starts with a board piece and a starting $1500
		Player p1 = new Player("Avery", MonopolyProps.pieces[0], 1500);
		Player p2 = new Player("Luke", MonopolyProps.pieces[1], 1500);
		Player p3 = new Player("Todd", MonopolyProps.pieces[2], 1500);
		Player p4 = new Player("Sheri", MonopolyProps.pieces[3], 1500);
		Player[] players = {p1, p2, p3, p4};
		
		// Build the Monopoly board
		Board board = new Board();
		
		Game monopoly = new Game(players, board);
		monopoly.play();
	}
}
