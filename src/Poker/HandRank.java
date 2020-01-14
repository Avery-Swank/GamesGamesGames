package Poker;

public class HandRank {

	private int rank;
	private String highCardType;	// tie-breaker
	private String rankString;
	
	public HandRank(int _rank, String _highCardType, String _rankString) {
		rank = _rank;
		highCardType = _highCardType;
		rankString = _rankString;
	}
	
	public int getRank() {
		return rank;
	}
	
	public String getHighCardType() {
		return highCardType;
	}
	
	public String getRankString() {
		return rankString;
	}
}
