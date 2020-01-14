package Poker;

import java.util.ArrayList;

import Core.Card;

public class PokerProps {
	
	/**
	 * @description Return the index corresponding to the card type. Used for calculating the hand rank
	 */
	public static int getTypeIndex(String type) {
		
		switch(type) {
			case "ace": return 0;
			case "two": return 1;
			case "three": return 2;
			case "four": return 3;
			case "five": return 4;
			case "six": return 5;
			case "seven": return 6;
			case "eight": return 7;
			case "nine": return 8;
			case "ten": return 9;
			case "jack": return 10;
			case "queen": return 11;
			case "king": return 12;
			default: throw new Error("Invalid Card Type: " + type);
		}
	}
	
	/**
	 * @description Return the type corresponding to the array index. Used for calculating the hand rank
	 */
	public static String getType(int type) {
		
		switch(type) {
			case 0: return "ace";
			case 1: return "two";
			case 2: return "three";
			case 3: return "four";
			case 4: return "five";
			case 5: return "six";
			case 6: return "seven";
			case 7: return "eight";
			case 8: return "nine";
			case 9: return "ten";
			case 10: return "jack";
			case 11: return "queen";
			case 12: return "king";
			default: throw new Error("Invalid Array Index: " + type);
		}
	}
	
	
	
	/**
	 * @description Return the index corresponding to the suite. Used for calculating the hand rank
	 */
	public static int getSuiteIndex(String suite) {
		
		switch(suite) {
			case "diamond": return 0;
			case "heart": return 1;
			case "spade": return 2;
			case "club": return 3;
			default: throw new Error("Invalid Card Suite: " + suite);
		}
	}
	
	/**
	 * @description Return the suite corresponding to the array index. Used for calculating the hand rank
	 */
	public static String getSuite(int type) {
		
		switch(type) {
			case 0: return "diamond";
			case 1: return "heart";
			case 2: return "spade";
			case 3: return "club";
			default: throw new Error("Invalid Array Index: " + type);
		}
	}
	
	/**
	 * @description Return true if there is one ten, jack, queen, king, and ace
	 *				Otherwise false
	 */
	public static boolean isRoyal(int[] countTypes) {
		return countTypes[getTypeIndex("ten")] == 1 && 
			   countTypes[getTypeIndex("jack")] == 1 && 
			   countTypes[getTypeIndex("queen")] == 1 && 
			   countTypes[getTypeIndex("king")] == 1 && 
			   countTypes[getTypeIndex("ace")] == 1;
	}
	
	/**
	 * @description Return a list of Four Of A Kind
	 *				Otherwise return an empty list
	 */
	public static String getFourOfAKind(int[] countTypes) {
		for(int i = 0; i < countTypes.length; i++) {
			if(countTypes[i] == 4)
				return getType(i);
		}
		return null;
	}
	
	/**
	 * @description Return true if there is a Four Of A Kind (or 4 of the same card type).
	 *				Otherwise false
	 */
	public static boolean isFourOfAKind(int[] countTypes) {
		String four = getFourOfAKind(countTypes);
		return four != null;
	}
	
	/**
	 * @description Return true if there is a Full House (1 set and 1 pair).
	 *				Otherwise false
	 */
	public static boolean isFullHouse(int[] countTypes) {
		return isOnePair(countTypes) && isSet(countTypes);
	}
	
	/**
	 * @description Return the suite that represents a flush
	 * 				Otherwise null
	 */
	public static String getFlush(int[] countSuites) {
		for(int i = 0; i < countSuites.length; i++) {
			if(countSuites[i] == 5) {
				return getSuite(i);
			}
		}
		return null;
	}
	
	/**
	 * @description Return true if there is a flush. All cards are the same suite
	 *				Otherwise false
	 */
	public static boolean isFlush(int[] countSuites) {
		String flush = getFlush(countSuites);
		return flush != null;
	}
	
	/**
	 * @description Return the highest card in the straight if there is an existing straight
	 * 				Otherwise null
	 * 
	 * 				Grabs the best straight first
	 */
	public static String getStraight(int[] countTypes) {
		
		// Check the out-of-order ten, jack, queen, king, ace straight first
		if(isRoyal(countTypes)) {
			return "ace";
		}

		// Check all in-order straights from king -> two
		for(int i = countTypes.length-1; i >= 4; i--) {
			if(countTypes[i] == 1 && countTypes[i-1] == 1 && countTypes[i-2] == 1 && countTypes[i-3] == 1 && countTypes[i-4] == 1) {
				return getType(i);
			}
		}
		
		return null;
	}
	
	/**
	 * @description Return true if there is a straight 
	 * 				Otherwise false
	 */
	public static boolean isStraight(int[] countTypes) {
		String highStraightCard = getStraight(countTypes);
		return highStraightCard != null;
	}
	
	/**
	 * @description Return the set if one exists
	 *				Otherwise null
	 */
	public static String getSet(int[] countTypes) {
		for(int i = 0; i < countTypes.length; i++) {
			if(countTypes[i] == 3)
				return getType(i);
		}
		return null;
	}
	
	/**
	 * @description Return true if there is a set (or 3 of the same card type).
	 *				Otherwise false
	 */
	public static boolean isSet(int[] countTypes) {
		String set = getSet(countTypes);
		return set != null;
	}
	
	/**
	 * @description Return a list of pairs
	 *				Otherwise return an empty list
	 */
	public static ArrayList<String> getPairs(int[] countTypes) {
		ArrayList<String> pairs = new ArrayList<String>();
		for(int i = 0; i < countTypes.length; i++) {
			if(countTypes[i] == 2)
				pairs.add(getType(i));
		}
		return pairs;
	}
	
	/**
	 * @description Return if the hand is a two-pair
	 */
	public static boolean isTwoPair(int[] countTypes) {
		ArrayList<String> pairs = getPairs(countTypes);
		return pairs.size() == 2;
	}
	
	/**
	 * @description Return if the hand is a two-pair
	 */
	public static boolean isOnePair(int[] countTypes) {
		ArrayList<String> pairs = getPairs(countTypes);
		return pairs.size() == 1;
	}
	
	/**
	 * @description Return the card with the highest value
	 *				Start with the ace because poker is ace high, then go down starting from king until one is found
	 */
	public static String getHighCard(int[] countTypes) {
		
		if(countTypes[getTypeIndex("ace")] == 1)
			return "ace";
		
		for(int i = countTypes.length-1; i >= 0; i--) {
			if(countTypes[i] == 1)
				return getType(i);
		}
		return null;
	}

	/**
	 * @description Return the hands poker ranking.
	 * 		Poker Rankings In Order:
	 * 		 - Royal Flush, Straight Flush, Four of a Kind, Full House, Flush, Straight, Three of a Kind, Two Pair, One Pair, High Card
	 * 
	 * 	This function counts the number of each card type and suite in the hand in
	 *  order to determine the poker ranking
	 */
	public static HandRank getHandRank(Hand hand) {
		
		ArrayList<Card> cards = hand.getCards();
		
		if(cards.size() != 5)
			throw new Error("Invalid Hand Size: " + cards.size());
		
		int[] countTypes = new int[13];
		int[] countSuites = new int[4];
		
		// Count the number of each card type and each card suite in the hand
		for(Card card : cards) {
			String cardType = card.getType();
			String cardSuite = card.getSuite();
			
			countTypes[getTypeIndex(cardType)]++;
			countSuites[getSuiteIndex(cardSuite)]++;
		}
		
		// Calculate All the different hand ranks within the hand
		boolean isCardsRoyal = isRoyal(countTypes);
		boolean isCardsFourOfAKind = isFourOfAKind(countTypes);
		boolean isCardsFullHouse = isFullHouse(countTypes);
		boolean isCardsFlush = isFlush(countSuites);
		boolean isCardsStraight = isStraight(countTypes);
		boolean isCardsSet = isSet(countTypes);
		boolean isCardsTwoPair = isTwoPair(countTypes);
		boolean isCardsOnePair = isOnePair(countTypes);
		
		// Return the string according to the best hand rank (checks in-order)
		if(isCardsRoyal && isCardsFlush) {
			return new HandRank(8, null, "royal flush with " + getFlush(countSuites) + "s");
		} else if(isCardsFourOfAKind) {
			return new HandRank(7, getFourOfAKind(countTypes), "four of a kind with " + getFourOfAKind(countTypes) + "s");
		} else if(isCardsFullHouse) {
			return new HandRank(6, getSet(countTypes), "full house with three " + getSet(countTypes) + "s and two " + getPairs(countTypes).get(0) + "s");
		} else if(isCardsFlush) {
			return new HandRank(5, getHighCard(countTypes), "flush of " + getFlush(countSuites) + "s with " + getHighCard(countTypes) + " high");
		} else if(isCardsStraight) {
			return new HandRank(4,  getStraight(countTypes), "straight with " + getStraight(countTypes) + " high");
		} else if(isCardsSet) {
			return new HandRank(3,  getSet(countTypes), "set with " + getSet(countTypes) + "s");
		} else if(isCardsTwoPair) {
			
			// Get the higher pair
			String higherPair;
			if(getPairs(countTypes).get(0).equals("ace"))
				higherPair = "ace";
			else
				higherPair = getPairs(countTypes).get(1);
			
			return new HandRank(2,  higherPair, "two pair of " + getPairs(countTypes).get(0)  + "s and " + getPairs(countTypes).get(1) + "s");
		} else if(isCardsOnePair) {
			return new HandRank(1,  getPairs(countTypes).get(0), "pair of " + getPairs(countTypes).get(0)  + "s");
		} else {
			return new HandRank(0,  getHighCard(countTypes), getHighCard(countTypes) + " high");
		}
	}
	
	/**
	 * @description Determine the player with the best hand
	 *				
	 *				At this point all of the players have the same hand rank: all players have pairs, two pairs, or straights, etc.
	 *				This distinguishes who has the better two pair or better straight or better flush etc.
	 *
	 *				If there is more than one player with the best best hand. Ex: If two players have a pair of aces, then they split
	 */
	public static ArrayList<Player> getBestPlayers(ArrayList<Player> players) {
		
		if(players.size() == 1)
			return players;
		
		// Determine the best high card
		int bestHighCard = -1;
		for(Player player : players) {
			String highCard = player.getHandRank().getHighCardType();
			
			int cardIndex = getTypeIndex(highCard);
			
			// Poker is ace high so ace is given the best value
			if(cardIndex == 0)
				cardIndex = 13;
			
			if(cardIndex > bestHighCard) {
				bestHighCard = cardIndex;
			}
		}
		
		ArrayList<Player> bestPlayers = new ArrayList<Player>();
		for(Player player : players) {
			String highCard = player.getHandRank().getHighCardType();
			
			int cardIndex = getTypeIndex(highCard);
			
			// Poker is ace high so ace is given the best value
			if(cardIndex == 0)
				cardIndex = 13;
						
			if(cardIndex == bestHighCard) {
				bestPlayers.add(player);
			}
		}
		
		return bestPlayers;
	}
}
