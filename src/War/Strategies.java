package War;

import Core.Card;

public class Strategies {

	/**
	 * @description Return an integer representation of the cards. War is ace-high so ace has the highest value
	 */
	public static int getCardValue(Card card) {
		
		switch(card.getType()) {
			case "ace": return 14;
			case "two": return 2;
			case "three": return 3;
			case "four": return 4;
			case "five": return 5;
			case "six": return 6;
			case "seven": return 7;
			case "eight": return 8;
			case "nine": return 9;
			case "ten": return 10;
			case "jack": return 11;
			case "queen": return 12;
			case "king": return 13;
			default: throw new Error("Invalid Card Type: " + card.getType());
		}
	}
}
