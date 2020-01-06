package Uno;

import java.util.ArrayList;

public class Strategies {

	
	/**
	 * @description Return the list of cards the player can play
	 *  The player can play if any of their cards:
	 *   - match type including numbers or special cards
	 *   - match color
	 */
	public static ArrayList<Card> getPlayableCards(Player player, Discard discard) {
		
		Card topCard = discard.getTopCard();
		ArrayList<Card> playerCards = player.getCards();
		ArrayList<Card> playableCards = new ArrayList<Card>();
		
		for(int i = 0; i < playerCards.size(); i++) {
			
			Card currCard = playerCards.get(i);
			
			// Matching color OR matching type OR wild
			if(currCard.getColor().equals(topCard.getColor()) || currCard.getType().equals(topCard.getType()) || currCard.getType().contains("wild")) {
				playableCards.add(currCard);
			}
		}
		
		return playableCards;
	}
	
	/**
	 * @description Return true if the play can play, otherwise false
	 */
	public static boolean canPlay(Player player, Discard discard) {
		return getPlayableCards(player, discard).size() > 0;
	}
	
	/**
	 * @description Play randomly
	 *  - if the player can play a card, pick one randomly'
	 *  - otherwise draw from the stack
	 * @return Index of card to play in player's hand
	 */
	public static int random(Player player, Stack stack, Discard discard) {
		
		// draw cards until the player can player
		while(!canPlay(player, discard)) {
			player.draw(stack.getTopCard());
			System.out.println(player.getName() + " draws one card...");
		}
		
		ArrayList<Card> playableCards = getPlayableCards(player, discard);
		Card randCard = playableCards.get((int)(Math.random() * playableCards.size()));
		
		for(int i = 0; i < player.getCards().size(); i++) {
			if(player.getCards().get(i).equals(randCard)) {
				return i;
			}
		}
		
		return -1;
	}
}
