package Uno;

import java.util.ArrayList;

public class Stack {

	private ArrayList<Card> cards;
	
	public Stack() {
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public Card getTopCard() {
		return cards.remove(cards.size()-1);
	}
	
	public int getSize() {
		return cards.size();
	}
}
