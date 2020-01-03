package BlackJack;

public class Run {

	public static void main(String[] args) {

		int numPlayers = 3;
		int numDecks = 1;
		boolean hitSoft17 = true;
		
		Game blackjack = new Game(numPlayers, numDecks, hitSoft17);
		blackjack.play();
	}

}
