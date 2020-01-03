package BlackJack;

import java.util.ArrayList;
import java.util.Scanner;
import Core.Card;

public class Strategies {
	
	// Strategies for BlackJack
	String hit = "h";
	String stand = "s";
	String doubleDown = "d";
	String split = "p";
	
	// Basic Strategy Card ----------------------------------------------------------------------------------
	
	// Starting at 8 up to 21					   A    2    3    4    5    6    7    8    9    10
	public static String[][] hardStrategies = 	{{"h", "h", "h", "h", "h", "h", "h", "h", "h", "h"},	// <=8
												 {"h", "h", "d", "d", "d", "d", "h", "h", "h", "h"},	// 9
												 {"h", "d", "d", "d", "d", "d", "d", "d", "d", "h"},	// 10
												 {"d", "d", "d", "d", "d", "d", "d", "d", "d", "d"},	// 11
												 {"h", "h", "h", "s", "s", "s", "h", "h", "h", "h"},	// 12
												 {"h", "s", "s", "s", "s", "s", "h", "h", "h", "h"},	// 13
												 {"h", "s", "s", "s", "s", "s", "h", "h", "h", "h"},	// 14
												 {"h", "s", "s", "s", "s", "s", "h", "h", "h", "h"},	// 15
												 {"h", "s", "s", "s", "s", "s", "h", "h", "h", "h"},	// 16
												 {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"}, 	// 17
												 {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},	// 18
												 {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},	// 19
												 {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},	// 20
												 {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"}};	// 21
	
	// Starting at A,2 up to A,9				   A    2    3    4    5    6    7    8    9    10
	public static String[][] softStrategies = 	{{"h", "h", "h", "h", "d", "d", "h", "h", "h", "h"},	// A,2
												 {"h", "h", "h", "h", "d", "d", "h", "h", "h", "h"},	// A,3
												 {"h", "h", "h", "d", "d", "d", "h", "h", "h", "h"},	// A,4
												 {"h", "h", "h", "d", "d", "d", "h", "h", "h", "h"},	// A,5
												 {"h", "h", "d", "d", "d", "d", "h", "h", "h", "h"},	// A,6
												 {"h", "d", "d", "d", "d", "d", "s", "s", "h", "h"},	// A,7
												 {"s", "s", "s", "s", "s", "d", "s", "s", "s", "s"},	// A,8
												 {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"}};	// A,9
	
	// Splitting Hands A,A up to T,T			   A    2    3    4    5    6    7    8    9    10
	public static String[][] splitHands = 		{{"p", "p", "p", "p", "p", "p", "p", "p", "p", "p"},	// A,A
												 {"s", "p", "p", "p", "p", "p", "p", "s", "s", "s"},	// 2,2
												 {"s", "p", "p", "p", "p", "p", "p", "s", "s", "s"},	// 3,3
												 {"s", "s", "s", "s", "p", "p", "s", "s", "s", "s"},	// 4,4
												 {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},	// 5,5
												 {"s", "p", "p", "p", "p", "p", "s", "s", "s", "s"},	// 6,6
												 {"s", "p", "p", "p", "p", "p", "p", "s", "s", "s"},	// 7,7
												 {"p", "p", "p", "p", "p", "p", "p", "p", "p", "p"},	// 8,8
												 {"s", "p", "p", "p", "p", "p", "s", "p", "p", "s"},	// 9,9
												 {"s", "s", "s", "s", "s", "s", "s", "s", "s", "s"},};	// T,T
	
	// ---------------------------------------------------------------------------------------------------------

	/**
	 * @description Return basic strategy move
	 */
	public static String getBasicStrategy(Hand playerHand, Card dealerCard) {
		
		ArrayList<Card> cards = playerHand.getHand();
		
		if(playerHand.canSplit()) {
			int playerIndex = BlackJackProps.getSoftValue(cards.get(0));
			int dealerIndex = BlackJackProps.getSoftValue(dealerCard);
			return splitHands[playerIndex-1][dealerIndex-1];
		} else if (playerHand.isSoft()){
			
			// Get the non-ace card value
			// Counts every cards soft value besides the ace
			int nonAceCount = -1;
			for(Card card : playerHand.getHand()) {
				nonAceCount += BlackJackProps.getSoftValue(card);
			}
			
			int dealerIndex = BlackJackProps.getSoftValue(dealerCard);
			
			// if there are multiple stacked 7s, 8s, 9s, 10s that made the player break 21
			if(nonAceCount > 7) {
				return "s";
			} else {
				return softStrategies[nonAceCount-1][dealerIndex-1];
			}
			
		} else {
			
			// if the hand has a hard value less then 8, always hit
			if(playerHand.getHardValue() < 8) {
				return "h";
			}
			
			// Otherwise, refer to hard strategies table
			int playerIndex = Math.max(playerHand.getHardValue() - 8, 0);
			int dealerIndex = BlackJackProps.getSoftValue(dealerCard);
			
			// if the player is above 21
			if(playerIndex > 13) {
				return "s";
			} else {
				return hardStrategies[playerIndex][dealerIndex-1];
			}
		}
	}
	
	/**
	 * @description The Dealer Strategy is to hit every hard count up to 17
	 * 				Some Casino's make the dealer hit on soft 17
	 */
	public static String getDealerStrategy(Hand dealerHand, boolean hitSoft17) {
		
		// if hard or soft count is less than 17 then hit
		if(dealerHand.getHardValue() < 17 || dealerHand.getSoftValue() < 17) {
			return "h";
		}
		
		// if soft count is 17 and the dealer is suppose to hit soft 17 then hit
		if(dealerHand.getSoftValue() <= 17 && hitSoft17) {
			return "h";
		}
			
		// Otherwise, stand
		return "s";
	}
	
	/**
	 * @description Return a human input move
	 */
	public static String getHumanStrategy(Hand playerHand) {
		
		System.out.println("Enter A Move: ");
		System.out.println("[Hit \"h\"] [Stand \"s\"] [Double Down \"d\"] [Split \"p\"]");
		
		// Get first input
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		// The player can hit or stand at any time
		// Check the player only splits after the first deal
		// Check the player only splits hands that can split
		boolean validInput = input.contentEquals("h") || 
							 input.contentEquals("s") || 
							 (input.contentEquals("d") && playerHand.canDouble()) || 
							 (input.contentEquals("p") && playerHand.canSplit());
		
		// Keep iterating until the player types the correct inputs
		while(!validInput) {
			
			if(input.contentEquals("d") && !playerHand.canDouble()) System.out.println("Cannot Double Down after the third card.");
			else if(input.contentEquals("p") && !playerHand.canSplit()) System.out.println("Cannot Split two different cards.");
			else System.out.println("Invalid Input: " + input + ". Try Again.");
			
			System.out.println("[Hit \"h\"] [Stand \"s\"] [Double Down \"d\"] [Split \"p\"]");
			input = scan.nextLine();
		}
		
		return input;
	}
}
