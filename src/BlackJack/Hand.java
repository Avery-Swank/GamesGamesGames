package BlackJack;

import java.util.ArrayList;
import Core.Card;

public class Hand {

	private ArrayList<Card> cards;
	private int softValue;
	private int hardValue;
	private boolean firstDeal;	// opening deal for the first two cards in a hand, keeps tracking of doubling down
	
	public Hand() {
		cards = new ArrayList<Card>();
		softValue = 0;
		hardValue = 0;
		firstDeal = true;
	}
	
	public void deal(Card card) {
		cards.add(card);
		softValue += BlackJackProps.getSoftValue(card);
		hardValue += BlackJackProps.getHardValue(card);
		
		// if the hand has more than two cards, then they cannot double
		if(cards.size() > 2) firstDeal = false;
	}
	
	public void emptyHand() {
		cards.clear();
		softValue = 0;
		hardValue = 0;
		firstDeal = true;
	}
	
	public boolean isSoft() {
		boolean hasAce = false;
		boolean lessthanEleven = softValue > 11;
		
		for(Card card : cards) {
			if(card.getType().equals("ace")) 
				hasAce = true;
		}
		return hasAce && lessthanEleven;
	}
	
	public boolean canDouble() {
		return firstDeal;
	}
	
	public boolean canSplit() {
		if(cards.size() == 2) 
			return cards.get(0).getType().equals(cards.get(1).getType());		
		else 
			return false;
	}
	
	public boolean isBlackjack() {
		return cards.size() == 2 && softValue == 11 && hardValue == 21;
	}
	
	public boolean isAbove21() {
		return hardValue > 21;
	}
	
	public int getSoftValue() {
		return softValue;
	}
	
	public int getHardValue() {
		return hardValue;
	}
	
	public ArrayList<Card> getHand(){
		return cards;
	}
	
	public String toString() {
		String s = "";
		s = s.concat("Soft Value:" + softValue + "\n");
		s = s.concat("Hard Value:" + hardValue + "\n");
		for(Card card : cards) {
			s = s.concat(card.toString() + "\n");
		}
		return s;
	}
	
}
