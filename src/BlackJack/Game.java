package BlackJack;

import java.util.ArrayList;
import Core.Card;
import Core.Deck;

public class Game {

	public Dealer dealer;
	public Player[] players;
	
	private ArrayList<Card> shoe;
	private int numDecks;
	
	public Game(int numPlayers, int _numDecks, boolean dealerHitsSoft17) {
		dealer = new Dealer("Dealer", dealerHitsSoft17);
		
		players = new Player[numPlayers];
		for(int i = 0; i < players.length; i++) {
			players[i] = new Player("Player " + i);
		}
		
		numDecks = _numDecks;
		reShuffle();
	}
	
	/**
	 * @description Deal the top card from the shoe
	 */
	private Card dealCard() {
		return shoe.remove(shoe.size()-1);
	}
	
	/**
	 * @description Reshuffle the shoe when it gets low of cards
	 */
	private void reShuffle() {
		
		System.out.println("Reshuffling cards...");
		
		Deck deck = new Deck(numDecks);
		deck.shuffle();
		shoe = deck.toList();
		
		// discard the first card like an actual casino
		dealCard();
	}
	
	public void play() {
		
		int i = 0;
		while(i < 100) {
			
			// Deal a round
			round();
			
			// If the shoe has less than 10 cards, reshuffle
			if(shoe.size() < 20) {
				reShuffle();
			}
			i++;
		}
	}
	
	private void round() {
		
		// Deal the first two cards to ever player and the dealer
		for(Player player : players) { player.deal(dealCard()); }
		dealer.deal(dealCard());
		for(Player player : players) { player.deal(dealCard()); }
		dealer.deal(dealCard());
		
		// Print Dealer Show Card
		System.out.println("Dealer Showing: " + dealer.getShowCard().toString());
		
		// Go to each player and play their strategies accordingly
		for(Player player : players) {
			player.play(shoe, dealer.getShowCard());
		}
		
		// Dealer shows their hidden card and plays
		dealer.play(shoe);
		
		System.out.println("DEALLING DONE");
		
		Hand dealerHand = dealer.getHand();
		
		// Determine which hands win and which hands lose
		for(Player player : players) {
			for(Hand hand : player.getHands()) {
				if(hand != null) {

					if((!hand.isAbove21() && hand.getHardValue() > dealerHand.getHardValue()) || 
					   (!hand.isAbove21() && dealerHand.isAbove21())) {
						System.out.println("Hand: " + hand.getHardValue() + " Beat The Dealer's " + dealerHand.getHardValue());
					} else if (!hand.isAbove21() && hand.getHardValue() == dealerHand.getHardValue()) {
						System.out.println("Hand: " + hand.getHardValue() + " Pushed The Dealer's " + dealerHand.getHardValue());
					} else if (hand.isAbove21()) {
						System.out.println("Hand: " + hand.getHardValue() + " Broke 21");
					} else {
						System.out.println("Hand: " + hand.getHardValue() + " Lost To The Dealer's " + dealerHand.getHardValue());
					}
				}
			}
		}
		
		// Empty the table
		for(Player player : players) { player.emptyHand(); }
		dealer.emptyHand();
	}
}
