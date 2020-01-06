package Uno;

import java.util.ArrayList;

public class Player {

	private String name;
	private String type;
	private ArrayList<Card> cards;
	
	public Player(String _name, String _type) {
		name = _name;
		type = _type;
		
		cards = new ArrayList<Card>();
	}
	
	public int play(Stack stack, Discard discard) {
		
		switch(type) {
			case "random":
				return Strategies.random(this, stack, discard);
			default:
				throw new Error("Invalid Player Type: " + type);
		}
	}
	
	public void draw(Card card) {
		cards.add(card);
	}
	
	public boolean isUno() {
		return cards.size() == 1;
	}
	
	public boolean isWinner() {
		return cards.size() == 0;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		String s = "Player: " + name + " Type: " + type + "\n";
		for(Card card : cards) {
			s = s.concat(card.toString() + "\n");
		}
		return s;
	}
}
