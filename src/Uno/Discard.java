package Uno;

import java.util.ArrayList;

public class Discard {

	private ArrayList<Card> cards;
	
	public Discard() {
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public Card getTopCard() {
		return cards.get(cards.size()-1);
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	public String toString() {
		String s = "Discard: \n";
		for(int i = cards.size()-1; i >= 0; i--) {
			s = s.concat(cards.get(i).toString() + "\n");
		}
		return s;
	}
}
