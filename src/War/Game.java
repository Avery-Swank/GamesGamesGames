package War;

import java.util.ArrayList;
import Core.Card;
import Core.Deck;

public class Game {

	private Player p1;
	private Player p2;
	
	private Deck deck;
	
	public Game(Player _p1, Player _p2, int numDecks) {
		p1 = _p1;
		p2 = _p2;
		
		deck = new Deck(numDecks);
		deck.shuffle();
	}
	
	public void play() {
		
		// Shuffle the deck and deal cards to the two players
		ArrayList<Card> cards = deck.toList();
		for(int i = 0; i < cards.size(); i+=2) {
			p1.dealCard(cards.get(i));
			p2.dealCard(cards.get(i+1));
		}
		
		System.out.println(p1);
		System.out.println(p2);
		
		while(p1.getNumCards() > 0 && p2.getNumCards() > 0) {
			
			ArrayList<Card> center = new ArrayList<Card>();
			
			try {
				// Each player deals a card
				Card p1Card = p1.getTopCard();
				Card p2Card = p2.getTopCard();
				center.add(p1Card);
				center.add(p2Card);
				
				// While the card types are the same, draw another three cards and re-draw another top card
				while(p1Card.getType().equals(p2Card.getType())) {
					
					System.out.println(p1.getName() + " and " + p2.getName() + " are going to war!...");
					
					// Make each player put three cards down
					for(int i = 0; i < 3; i++) {
						center.add(p1.getTopCard());
						center.add(p2.getTopCard());
						System.out.println(p1.getName() + " and " + p2.getName() + " deal a card face down...");
					}
					
					// Get a new top card to show
					p1Card = p1.getTopCard();
					p2Card = p2.getTopCard();
					center.add(p1Card);
					center.add(p2Card);
				}
				
				// Determine the winning card
				int p1Value = Strategies.getCardValue(p1Card);
				int p2Value = Strategies.getCardValue(p2Card);
				
				// Give the winning player all of the center cards
				for(int i = center.size()-1; i >= 0; i--) {
					if(p1Value > p2Value)
						p1.dealWinCard(center.remove(i));
					else
						p2.dealWinCard(center.remove(i));
				}
				
				if(p1Value > p2Value)
					System.out.println(p1.getName() + " won the hand with " + p1Card.getType() + " over " + p2Card.getType() + "...");
				else
					System.out.println(p2.getName() + " won the hand with " + p2Card.getType() + " over " + p1Card.getType() + "...");
				
				// Turn over all of the cards and go again
				// p1 and p2 should always have the same number of down cards
				if(p1.getNumDownCards() == 0) {
					p1.moveWinCards();
					p2.moveWinCards();
					System.out.println("ResShuffling down decks...");
				}
				
			// if an exception is thrown, then one of the players has run out of cards in the middle of a war (dealing 3 cards and repeating)
			} catch (Exception e) { /* ignore because the while-loop will catch it anyways */ }
		}
		
		if(p1.getNumCards() != 0) {
			System.out.println(p1.getName() + " Won WAR!");
		} else {
			System.out.println(p2.getName() + " Won WAR!");
		}
	}
}
