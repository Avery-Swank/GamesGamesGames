package Uno;

public class Card {

	private String type;
	private String color;
	
	public Card(String _type, String _color) {
		type = _type;
		color = _color;
	}
	
	public String getType() {
		return type;
	}
	
	public String getColor() {
		return color;
	}
	
	public String toString() {
		return color + " " + type;
	}
}
