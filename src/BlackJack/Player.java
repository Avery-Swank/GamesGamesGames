package BlackJack;

import java.util.ArrayList;

import Core.Card;

public class Player {

	public String name;
	private Hand[] hands;
	
	private int maxHands = 3;
	private int currHand;
	
	public Player(String _name) {
		name = _name;
		hands = new Hand[maxHands];
		
		currHand = 1;
		hands[currHand-1] = new Hand();
	}
	
	public int deal(Card card) { 
		hands[currHand-1].deal(card);
		return hands[currHand-1].getHardValue();
	}
	
	public void emptyHand() {
		for(int i = 0; i < currHand; i++) {
			hands[i].emptyHand();
		}
	}
	
	public void split() {
		currHand++;
	}
	
	/**
	 * @description Play the Basic Player Strategy
	 */
	public void play(ArrayList<Card> shoe, Card dealerCard) {
		
		String firstStrat = Strategies.getBasicStrategy(hands[currHand-1], dealerCard);
		
		// if suppose to double, deal one card
		if(firstStrat.equals("d")) {
			
			System.out.println("Player: " + name + " Doubling " + hands[currHand-1].getSoftValue() + "...");
			hands[currHand-1].deal(shoe.remove(shoe.size()-1));
			System.out.println("Player: " + name + " Now Has " + hands[currHand-1].getSoftValue() + "...");
			
		// if suppose to hit, then hit and keep checking if you have to hit again and again
		} else if(firstStrat.equals("h")) {
			
			// if the hand can hit or double then just hit, otherwise we would have already doubled
			while(Strategies.getBasicStrategy(hands[currHand-1], dealerCard).equals("h") || Strategies.getBasicStrategy(hands[currHand-1], dealerCard).equals("d")) {
				System.out.println("Player: " + name + " Hitting " + hands[currHand-1].getSoftValue() + "...");
				hands[currHand-1].deal(shoe.remove(shoe.size()-1));
				System.out.println("Player: " + name + " Now Has " + hands[currHand-1].getSoftValue() + "...");
			}
			
		// if suppose to split, then split into two hands
		} else if(firstStrat.equals("p")) {
			
			System.out.println("Player: " + name + " Splitting " + hands[currHand-1].getSoftValue() + "...");
			
		// otherwise the player stands
		} else {
			
			System.out.println("Player: " + name + " Standing On " + hands[currHand-1].getSoftValue() + "...");
		}
	}
	
	public Hand[] getHands() {
		return hands;
	}
	
	public String toString() {
		String s = "Player '" + name + "':\n";
		for(int i = 0; i < currHand; i++) {
			s = s.concat("Hand " + i + ":\n");
			s = s.concat(hands[i].toString());
		}
		return s;
	}
	
}
