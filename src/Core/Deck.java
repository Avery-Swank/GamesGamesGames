package Core;

import java.util.ArrayList;

public class Deck {

	public Card[] cards;
	
	public Deck(int _numDecks) {
		createDeck(_numDecks);
	}
	
	/**
	 * @description Create 'numDecks' number of decks of cards. Created in order.
	 * @param numDecks
	 * @return List of Cards containing a list of standard 52-card decks
	 */
	private void createDeck(int numDecks) {
		
		cards = new Card[numDecks * 52];
		
		for(int d = 0; d < numDecks; d++) {
			for(int i = 0; i < CardProps.SUITES.length; i++) {
				for(int j = 0; j < CardProps.TYPES.length; j++) {
					cards[(d*52) + (i*13) + j] = new Card(CardProps.TYPES[j], CardProps.SUITES[i]);
				}
			}
		}
	}
	
	/**
	 * @description Shuffle the cards randomly via swapping each card in the deck with a random card
	 */
	public void shuffle() {
		for(int i = 0; i < cards.length; i++) {
			int rand = (int)(Math.random() * cards.length);
			Card temp = cards[rand];
			cards[rand] = cards[i];
			cards[i] = temp;
		}
	}
	
	/**
	 * @description Create a new array of Cards as an ArrayList type
	 */
	public ArrayList<Card> toList() {
		ArrayList<Card> cardsList = new ArrayList<Card>();
		for(int i = 0; i < cards.length; i++) {
			cardsList.add(cards[i]);
		}
		return cardsList;
	}
	
	/**
	 * @description Print the contents of the cards
	 */
	public String toString() {
		String s = "";
		for(int i = 0; i < cards.length; i++) {
			s = s.concat(cards[i].toString() + '\n');
		}
		return s;
	}
}
