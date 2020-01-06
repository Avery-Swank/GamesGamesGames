package Bingo;

public class Board {

	private int[][] board;
	private boolean[][] marks;
	
	private String bingoType;
	
	public Board(String _bingoType) {
		marks = new boolean[5][5];
		resetMarks();
		board = createBoard();
		bingoType = _bingoType;
	}
	
	/**
	 * @description Generate a random BINGO board
	 *  - B: 1-15
	 *  - I: 16-30
	 *  - N: 31-45
	 *  - G: 46-60
	 *  - O: 61-75
	 *  - Free Space at the center
	 *  - All spaces are unique
	 * @return
	 */
	public int[][] createBoard(){
		
		int[][] newBoard = new int[5][5];
		
		// Build each column a list of unique numbers
		newBoard[0] = Balls.getRandomList(1, 15, 5);
		newBoard[1] = Balls.getRandomList(16, 30, 5);
		newBoard[2] = Balls.getRandomList(31, 45, 5);
		newBoard[3] = Balls.getRandomList(46, 60, 5);
		newBoard[4] = Balls.getRandomList(61, 75, 5);
		
		// Mark the middle space as FREE
		newBoard[2][2] = -1;
		marks[2][2] = true;
		
		return newBoard;
	}
	
	/**
	 * Add a mark to the board if the value exists on the card
	 * This could be done by brute force checking every number, however, knowing the range of values
	 * in each column it makes more sense to check the range first then search the column accordingly
	 */
	public void addMark(int x) {
		
		if(x <= 15) {
			for(int i = 0; i < board[0].length; i++) {
				if(board[0][i] == x) {
					marks[0][i] = true;
					break;
				}
			}
		} else if(15 < x && x <= 30) {
			for(int i = 0; i < board[1].length; i++) {
				if(board[1][i] == x) {
					marks[1][i] = true;
					break;
				}
			}
		} else if(30 < x && x <= 45) {
			for(int i = 0; i < board[2].length; i++) {
				if(board[2][i] == x) {
					marks[2][i] = true;
					break;
				}
			}
		} else if(45 < x && x <= 60) {
			for(int i = 0; i < board[3].length; i++) {
				if(board[3][i] == x) {
					marks[3][i] = true;
					break;
				}
			}
		} else if(60 < x && x <= 75) {
			for(int i = 0; i < board[4].length; i++) {
				if(board[4][i] == x) {
					marks[4][i] = true;
					break;
				}
			}
		}
	}
	
	public boolean isBingo() {
		
		switch(bingoType) {
			case "classic":
				
				// Check Rows
				for(int i = 0; i < marks.length; i++) {
					boolean rowBingo = true;
					for(int j = 0; j < marks[i].length; j++) {
						rowBingo = rowBingo && marks[i][j];
					}
					
					if(rowBingo)
						return true;
				}
				
				// Check Columns
				for(int i = 0; i < marks.length; i++) {
					boolean colBingo = true;
					for(int j = 0; j < marks[i].length; j++) {
						colBingo = colBingo && marks[j][i];
					}
					
					if(colBingo)
						return true;
				}
				
				// Check Diagonals: Left to Right
				boolean leftToRight = marks[0][0] && marks[1][1] && marks[2][2] && marks[3][3] && marks[4][4];
				
				if(leftToRight)
					return true;
				
				// Check Diagonals: Right to Left
				boolean rightToLeft = marks[0][4] && marks[1][3] && marks[2][2] && marks[3][1] && marks[4][0];
				
				if(rightToLeft)
					return true;
				
				// Otherwise, false
				return false;
				
			case "four corners":
				return marks[0][0] && marks[0][marks.length-1] && marks[marks.length-1][0] && marks[marks.length-1][marks.length-1];
			case "blackout":
				
				boolean bingo = true;
				for(int i = 0; i < marks.length; i++) {
					for(int j = 0; j < marks[i].length; j++) {
						bingo = bingo && marks[i][j];
					}
				}
				return bingo;
				
			default:
				throw new Error("Invalid Bingo Type: " + bingoType);
		}
	}
	
	public void resetMarks() {
		for(int i = 0; i < marks.length; i++) {
			for(int j = 0; j < marks[i].length; j++) {
				marks[i][j] = false;
			}
		}
	}
	
	public String toString() {
		String s = "B  I  N  G  O\n";
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				String num = "" + board[j][i];
				
				// append spaces according to length of number for better formatting
				if(num.length() == 1)
					s = s.concat("" + board[j][i] + "  ");
				else
					s = s.concat("" + board[j][i] + " ");
			}
			s = s.concat("\n");
		}
		
		s = s.concat("\n");
		
		for(int i = 0; i < marks.length; i++) {
			for(int j = 0; j < marks[i].length; j++) {
				String num = "" + marks[j][i];
				
				// append spaces according to length of number for better formatting
				if(num.length() == 4)
					s = s.concat("" + marks[j][i] + "  ");
				else
					s = s.concat("" + marks[j][i] + " ");
			}
			s = s.concat("\n");
		}
		
		
		
		return s;
	}
}
