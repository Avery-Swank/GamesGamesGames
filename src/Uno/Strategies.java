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
		
		// draw cards until the player can play
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
	
	/**
	 * @description Plays like a strategic player
	 * Some attributes of a strategic player may include:
	 *  - Hitting players that have UNO with a 'draw two' or 'wild draw four'
	 *  - Skipping players that have UNO
	 *  - Reversing on players that have UNO
	 *  - Playing your wild cards last because you are more likely draw when you have less cards
	 * @return
	 */
	public static int strategic(Player player, Player nextPlayer, Stack stack, Discard discard) {
		
		// draw cards until the player can play
		while(!canPlay(player, discard)) {
			player.draw(stack.getTopCard());
			System.out.println(player.getName() + " draws one card...");
		}
		
		ArrayList<Card> playableCards = getPlayableCards(player, discard);
		
		// if the next player has uno, then use any card the current play has 
		// to avoid another player from winning
		if(nextPlayer.isUno()) {
			for(int i = 0; i < playableCards.size(); i++) {
				Card currCard = playableCards.get(i);
				
				// if is a special card
				if(currCard.getType().equals("draw two") || currCard.getType().equals("skip") || currCard.getType().equals("wild") || currCard.getType().equals("wild draw four")) {
					for(int j = 0; i < player.getCards().size(); j++) {
						if(player.getCards().get(j).equals(currCard)) {
							return j;
						}
					}
				}
			}
		}
		
		// Otherwise, play any card that is not a wild
		for(int i = 0; i < playableCards.size(); i++) {
			Card currCard = playableCards.get(i);
			
			// if is not a wild card
			if(!currCard.getType().equals("wild") && !currCard.getType().equals("wild draw four")) {
				for(int j = 0; i < player.getCards().size(); j++) {
					if(player.getCards().get(j).equals(currCard)) {
						return j;
					}
				}
			}
		}
		
		// Otherwise, play a random card
		return random(player, stack, discard);
	}
}
