package Poker;

import Core.Card;

public class Player {

	private String name;
	private Hand hand;
	private HandRank handRank;
	
	public Player(String _name) {
		name = _name;
		hand = new Hand();
	}
	
	public void dealCard(Card card) {
		hand.dealCard(card);
	}
	
	public void setHandRank(HandRank _handRank) {
		handRank = _handRank;
	}
	
	public String getName() {
		return name;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public HandRank getHandRank() {
		return handRank;
	}
	
	public String toString() {
		String s = "Player: " + name + "\n";
		s = s.concat(hand.toString());
		return s;
	}
}
