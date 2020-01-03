package BlackJack;

import java.util.ArrayList;

import Core.Card;

public class Dealer {

	public String name;
	public boolean hitsSoft17;
	
	private Hand hand;
	
	public Dealer(String _name, boolean _hitsSoft17) {
		name = _name;
		hitsSoft17 = _hitsSoft17;
		hand = new Hand();
	}
	
	public int deal(Card card) {
		hand.deal(card);
		return hand.getHardValue();
	}
	
	public void emptyHand() {
		hand.emptyHand();
	}
	
	/**
	 * @description Return the card that the dealer is showing. Always the first card delt
	 * @return
	 */
	public Card getShowCard() {
		
		if(hand.getHand().size() == 0) throw new Error("Dealer: Hand is empty.");
		
		return hand.getHand().get(0);
	}
	
	/**
	 * @description Play the Dealer strategy
	 */
	public void play(ArrayList<Card> shoe) {
		
		// deal card to the dealer until the dealer is not suppose to hit anymore
		while(Strategies.getDealerStrategy(hand, hitsSoft17).equals("h")) {
			System.out.println("Dealer: " + name + " Hitting " + hand.getSoftValue() + "...");
			hand.deal(shoe.remove(shoe.size()-1));
			System.out.println("Dealer: " + name + " Now Has " + hand.getSoftValue() + "...");
		}
	}
	
	public Hand getHand(){
		return hand;
	}
	
	public String toString() {
		String s = "Dealer:\n";
		s = s.concat(hand.getHand().get(0).toString() + "\n");
		return s;
	}
}
