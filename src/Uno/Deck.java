package Uno;

import java.util.ArrayList;

public class Deck {

	public ArrayList<Card> cards;
	
	public Deck() {
		createDeck();
	}
	
	/**
	 * @description Create Standard 108-Card Uno Deck
	 *  For each color:
	 *   - one 0s
	 *   - two 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, draw two, skip, reverse, wild, wild draw four
	 */
	private void createDeck() {
		
		cards = new ArrayList<Card>();
		
		for(int c = 0; c < UnoProps.COLORS.length; c++) {
			
			// Add one zero
			cards.add(new Card("0", UnoProps.COLORS[c]));
			
			// Add two of all numbers 1-9
			for(int n = 1; n < UnoProps.NUMBERS.length; n++) {
				cards.add(new Card(UnoProps.NUMBERS[n], UnoProps.COLORS[c]));
				cards.add(new Card(UnoProps.NUMBERS[n], UnoProps.COLORS[c]));
			}
			
			// Add two of all special cards
			for(int s = 0; s < UnoProps.SPECIALS.length; s++) {
				cards.add(new Card(UnoProps.SPECIALS[s], UnoProps.COLORS[c]));
				cards.add(new Card(UnoProps.SPECIALS[s], UnoProps.COLORS[c]));
			}
		}
	}
	
	/**
	 * @description Shuffle the cards randomly via swapping each card in the deck with a random card
	 */
	public void shuffle() {
		for(int i = 0; i < cards.size(); i++) {
			int rand = (int)(Math.random() * cards.size());
			Card temp = cards.get(rand);
			cards.set(rand, cards.get(i));
			cards.set(i, temp);
		}
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	/**
	 * @description Print the contents of the cards
	 */
	public String toString() {
		String s = "";
		for(int i = 0; i < cards.size(); i++) {
			s = s.concat(cards.get(i).toString() + '\n');
		}
		return s;
	}
}
