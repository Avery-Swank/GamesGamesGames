package Uno;

public class Game {

	private Stack stack;
	private Discard discard;
	private Player[] players;
	
	private boolean direction;	// direction (or order) the players play
	
	public Game(Player[] _players) {
		stack = new Stack();
		discard = new Discard();
		players = _players;
		
		direction = true;
	}
	
	public void play() {
		
		// Create Uno Deck and add to the initial stack
		Deck deck = new Deck();
		deck.shuffle();
		
		for(Card card : deck.getCards()) {
			stack.addCard(card);
		}
		
		// Initially distribute 7 cards to each player
		for(int i = 0; i < 7; i++) {
			for(Player player : players) {
				player.draw(stack.getTopCard());
			}
		}
		
		// Add the first card to the discard
		discard.addCard(stack.getTopCard());
		
		for(Player player : players) {
			System.out.println(player);
		}
		
		System.out.println(discard);
		
		// Play starting with the first player
		int currPlayerIndex = 0;
		while(true) {
			
			Player currPlayer = players[currPlayerIndex];
			
			// Play a card
			int cardToPlayIndex = currPlayer.play(stack, discard);
			discard.addCard(currPlayer.getCards().remove(cardToPlayIndex));
			
			System.out.println(currPlayer.getName() + " plays " + discard.getTopCard().toString() + "...");
			
			// Check if the player now has an uno
			if(currPlayer.isUno()) {
				System.out.println(currPlayer.getName() + " HAS UNO!...");
			}
			
			// Check if the player won the game
			if(currPlayer.isWinner()) {
				System.out.println(currPlayer.getName() + " WON UNO!...");
				break;
			}
			
			// Change the direction of the game if a 'reverse' card is played
			if(discard.getTopCard().getType().contains("reverse")) {
				direction = !direction;
				System.out.println("Switching directions...");
			}
			
			// Change the player index based on the direction of the game
			if(direction) {
				currPlayerIndex++;
			} else {
				currPlayerIndex--;
			}
			
			// If the player index goes beyond the list then correct it on its end values: 0, length-1
			if(currPlayerIndex < 0) {
				currPlayerIndex = players.length-1;
			} else if(currPlayerIndex > players.length-1) {
				currPlayerIndex = 0;
			}
			
			// if any skip card is played, then update the direction of the game AGAIN
			if(discard.getTopCard().getType().contains("skip")) {
				
				System.out.println("Skipping " + players[currPlayerIndex].getName() + "...");
				
				// Change the player index based on the direction of the game
				if(direction) {
					currPlayerIndex++;
				} else {
					currPlayerIndex--;
				}
				
				// If the player index goes beyond the list then correct it on its end values: 0, length-1
				if(currPlayerIndex < 0) {
					currPlayerIndex = players.length-1;
				} else if(currPlayerIndex > players.length-1) {
					currPlayerIndex = 0;
				}
			}
			
			Player nextPlayer = players[currPlayerIndex];
			
			// Make next player draw two cards from the previous player's "draw two"
			if(discard.getTopCard().getType().equals("draw two")) {
				nextPlayer.draw(stack.getTopCard());
				nextPlayer.draw(stack.getTopCard());
				System.out.println(nextPlayer.getName() + " draws two cards...");
			}
			
			// Make next player draw four cards from the previous player's "wild draw four"
			if(discard.getTopCard().getType().equals("wild draw four")) {
				nextPlayer.draw(stack.getTopCard());
				nextPlayer.draw(stack.getTopCard());
				nextPlayer.draw(stack.getTopCard());
				nextPlayer.draw(stack.getTopCard());
				System.out.println(nextPlayer.getName() + " draws four cards cards...");
			}
			
			// if the stack is getting low, then put the rest of the discard cards underneath the top card back onto
			// the stack again
			if(stack.getSize() < 20) {
				for(int i = 0; i < discard.getCards().size()-2; i++) {
					stack.addCard(discard.getCards().remove(0));
				}
				System.out.println("Refilling Stack from Discards...");
			}
		}
	}
}
