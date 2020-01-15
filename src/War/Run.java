package War;

import java.util.ArrayList;

import Core.Card;
import Core.Deck;

public class Run {

	public static void main(String[] args) {
		
		Player p1 = new Player("Avery");
		Player p2 = new Player("Luke");
		
		// One deck for war
		// Reshuffling when the players run out of cards to deal is crucial for preventing infinite loops and speeds up the war
		Game war = new Game(p1, p2, 1);
		war.play();
	}
}
