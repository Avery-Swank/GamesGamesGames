package BlackJack;

import Core.Card;

public class BlackJackProps {
	
	public static int getSoftValue(Card card) {
		switch(card.getType()) {
			case "ace": return 1;
			case "two": return 2;
			case "three": return 3;
			case "four": return 4;
			case "five": return 5;
			case "six": return 6;
			case "seven": return 7;
			case "eight": return 8;
			case "nine": return 9;
			case "ten":
			case "jack":
			case "queen":
			case "king":
				return 10;
			default:
				throw new Error("Invalid Card Type: " + card.getType());
		}
	}
	
	public static int getHardValue(Card card) {
		switch(card.getType()) {
			case "ace": return 11;
			case "two": return 2;
			case "three": return 3;
			case "four": return 4;
			case "five": return 5;
			case "six": return 6;
			case "seven": return 7;
			case "eight": return 8;
			case "nine": return 9;
			case "ten":
			case "jack":
			case "queen":
			case "king":
				return 10;
			default:
				throw new Error("Invalid Card Type: " + card.getType());
		}
	}
}

