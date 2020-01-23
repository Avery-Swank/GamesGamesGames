package Monopoly;

import java.util.ArrayList;

public class Run {

	public static void main(String[] args) {
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		// Each player starts with a board piece and a starting $1500
		players.add(new Player("Avery", MonopolyProps.pieces[0], 1500));
		players.add(new Player("Luke", MonopolyProps.pieces[1], 1500));
		players.add(new Player("Todd", MonopolyProps.pieces[2], 1500));
		players.add(new Player("Sheri", MonopolyProps.pieces[3], 1500));
		
		// Build the Monopoly board
		Board board = new Board();
		
		Game monopoly = new Game(players, board);
		monopoly.play();
	}
}
