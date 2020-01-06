package Bingo;

import java.util.ArrayList;

public class Game {

	private Board boards[];
	private String bingoType;
	private ArrayList<Integer> balls;
	
	public Game(int numBoards, String _bingoType) {
		
		// Setup Boards
		boards = new Board[numBoards];
		for(int b = 0; b < boards.length; b++) {
			boards[b] = new Board(_bingoType);
		}
		bingoType = _bingoType;
		
		// Create Balls
		balls = new ArrayList<Integer>();
		createBalls(75);
	}
	
	private void createBalls(int max){
		for(int i = 1; i <= max; i++) {
			balls.add(i);
		}
	}
	
	public void play() {
		
		System.out.println("Playing " + bingoType + " game of BINGO with " + boards.length + " boards.");
		
		boolean noBingo = true;
		
		while(noBingo) {
			
			// draw a random ball from all the balls
			int drawnBall = balls.remove((int)(Math.random() * balls.size()));
			
			System.out.println(Balls.getLetter(drawnBall) + " " + drawnBall);
			
			for(Board board : boards) {
				// add the ball to each of the board
				board.addMark(drawnBall);
				
				// call out the first bingo spotted
				if(board.isBingo()) {
					System.out.println("BINGO!!!");
					System.out.println(board);
					
					noBingo = false;
					break;
				}
			}
		}
	}
}
