package Core;

public class Card {

	public String type;
	public String suite;
	
	public Card(String _type, String _suite) {
		type = _type;
		suite = _suite;
	}
	
	public boolean isRed() {
		return suite.equals("diamond") || suite.equals("heart");
	}
	
	public boolean isBlack() {
		return suite.equals("spade") || suite.equals("club");
	}
	
	public String getType() {
		return type;
	}
	
	public String getSuite() {
		return suite;
	}
	
	public String toString() {
		return type + " of " + suite + "s";
	}
}
