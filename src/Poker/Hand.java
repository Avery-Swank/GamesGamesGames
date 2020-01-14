package Poker;

import java.util.ArrayList;

import Core.Card;

public class Hand {

	private ArrayList<Card> cards;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void dealCard(Card card) {
		cards.add(card);
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	public String toString() {
		String s = "Hand:\n";
		for(Card card : cards) {
			s = s.concat(card.toString() + "\n");
		}
		return s;
	}
	
	
}
