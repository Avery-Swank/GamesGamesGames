package Poker;

import java.util.ArrayList;

import Core.Card;
import Core.Deck;

public class Run {

	public static void main(String[] args) {
		
		Player p1 = new Player("avery");
		Player p2 = new Player("luke");
		Player p3 = new Player("sheri");
		Player p4 = new Player("todd");
		Player[] players = {p1, p2, p3, p4};
		
		// One deck for poker
		Deck deck = new Deck(1);
		deck.shuffle();
		ArrayList<Card> cards = deck.toList();
		
		// Deal the flop (3 cards) to each player's hand
		for(int i = 0; i < 3; i++) {
			for(Player player : players) {
				player.dealCard(cards.remove(cards.size()-1));
				System.out.println("Dealing card to " + player.getName() + "...");
			}
		}
		
		// Deal the turn (1 card) to each player's hand
		for(int i = 0; i < 1; i++) {
			for(Player player : players) {
				player.dealCard(cards.remove(cards.size()-1));
				System.out.println("Dealing card to " + player.getName() + "...");
			}
		}
		
		// Deal the river (1 card) to each player's hand
		for(int i = 0; i < 1; i++) {
			for(Player player : players) {
				player.dealCard(cards.remove(cards.size()-1));
				System.out.println("Dealing card to " + player.getName() + "...");
			}
		}
		System.out.println();
		
		// Print each player's best hand rank
		for(Player player : players) {
			HandRank handRank = PokerProps.getHandRank(player.getHand());
			player.setHandRank(handRank);
			System.out.println(player.getName() + "'s hand: " + handRank.getRankString());
		}
		System.out.println();
		
		// Get the best hand rank of all the players
		int bestRank = -1;
		for(Player player : players) {
			if(player.getHandRank().getRank() > bestRank)
				bestRank = player.getHandRank().getRank();
		}
		
		// Determine what players have the best hand rank
		ArrayList<Player> bestRankPlayers = new ArrayList<Player>();
		for(Player player : players) {
			if(player.getHandRank().getRank() == bestRank)
				bestRankPlayers.add(player);
		}
		
		// At this point, determine if ties need to be broken by looking at high cards in flushes, straights, sets, pairs, high cards, etc.
		ArrayList<Player> bestPlayers = PokerProps.getBestPlayers(bestRankPlayers);
		
		if(bestPlayers.size() == 1) {
			System.out.println(bestPlayers.get(0).getName() + " won with " + bestPlayers.get(0).getHandRank().getRankString());
		} else {
			for(Player bestPlayer : bestPlayers) {
				System.out.println(bestPlayer.getName() + " split with " + bestPlayer.getHandRank().getRankString());
			}
		}
	}
	
}
