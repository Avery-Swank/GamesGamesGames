package Mancala;

public class Game {
	
	private Board board;
	private Player player;
	private Player opponent;
	
	public Game(Board _board, Player _player, Player _opponent) {
		board = _board;
		player = _player;
		opponent = _opponent;
	}
	
	public void play() {
		
		Player currPlayer = player;
		
		while(!board.isPlayerRowEmpty() && !board.isOpponentRowEmpty()) {
			
			// Player plays
			if(currPlayer == player) {
				
				int currPlayerMove = currPlayer.getMove(board.getPlayerSpaces());
				System.out.println(currPlayer.getName() + " plays at index: " + currPlayerMove);
				
				// Keep playing while the current player ends in their own store
				if(currPlayerMove > -1) {
					while(board.playPlayerSpace(currPlayerMove)) {
							
						currPlayerMove = currPlayer.getMove(board.getPlayerSpaces());
						
						if(currPlayerMove == -1) break;
						System.out.println(currPlayer.getName() + " plays at index: " + currPlayerMove);
					}
				}
				
			// Opponent plays
			} else {
				
				int currPlayerMove = currPlayer.getMove(board.getOpponentSpaces());
				System.out.println(currPlayer.getName() + " plays at index: " + currPlayerMove);
				
				// Keep playing while the current player ends in their own store
				if(currPlayerMove > -1) {
					while(board.playOpponentSpaces(currPlayerMove)) {
							
						currPlayerMove = currPlayer.getMove(board.getOpponentSpaces());
						
						if(currPlayerMove == -1) break;
						System.out.println(currPlayer.getName() + " plays at index: " + currPlayerMove);
					}
				}
			}
			
			// Alternate moves
			currPlayer = (currPlayer == player) ? opponent : player;
		}
		
		// Fill in the last stones in their respective stores
		board.fillStores();
		
		System.out.println(board);
		
		// Get the winner
		if(board.getPlayerStore() == board.getOpponentStore()) {
			System.out.println("The Game is Tied!");
		} else if (board.getPlayerStore() > board.getOpponentStore()) {
			System.out.println(player.getName() + " Won Mancala!");
		} else {
			System.out.println(opponent.getName() + " Won Mancala!");
		}
	}
}
