package War;

import java.util.ArrayList;

import Core.Card;

public class Player {

	private String name;
	private ArrayList<Card> downCards;	// The cards delt to the player
	private ArrayList<Card> myCards;	// The cards the player wins in WAR
	
	public Player(String _name) {
		name = _name;
		downCards = new ArrayList<Card>();
		myCards = new ArrayList<Card>();
	}
	
	/**
	 * @description Deal a card to the down facing cards
	 */
	public void dealCard(Card card) {
		downCards.add(card);
	}
	
	/**
	 * @descriptin Deal a card to the player's winning stack of cards
	 */
	public void dealWinCard(Card card) {
		myCards.add(card);
	}
	
	/**
	 * @description Shuffle down cards
	 */
	public void shuffleDownCards() {
		for(int i = 0; i < downCards.size(); i++) {
			int randIndex = (int)(Math.random() * downCards.size());
			Card temp = downCards.get(randIndex);
			downCards.set(randIndex, downCards.get(i));
			downCards.set(i, temp);
		}
	}
	
	/**
	 * @description Move all of the cards won by the player to the now down facing cards
	 * 				 - Shuffle the cards when restarting to prevent infinite loops
	 */
	public void moveWinCards() {
		for(int i = myCards.size()-1; i >= 0; i--) {
			dealCard(myCards.remove(i));
		}
		shuffleDownCards();
	}
	
	public String getName() {
		return name;
	}
	
	public Card getTopCard() {
		
		// if the player runs out of cards, then move the win cards down and play the next top card
		if(downCards.size() == 0)
			moveWinCards();
		
		return downCards.remove(downCards.size()-1);
	}
	
	public int getNumDownCards() {
		return downCards.size();
	}
	
	public int getNumCards() {
		return downCards.size() + myCards.size();
	}
	
	public String toString() {
		return "Player: " + name + " Number of Cards: " + getNumCards();
	}
}
