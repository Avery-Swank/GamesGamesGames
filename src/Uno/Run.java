package Uno;

public class Run {

	public static void main(String[] args) {
		
		Player p1 = new Player("bob", "random");
		Player p2 = new Player("jack", "strategic");
		Player p3 = new Player("avery", "random");
		Player p4 = new Player("sheri", "strategic");
		Player p5 = new Player("todd", "random");
		Player p6 = new Player("amanda", "strategic");
		Player[] players = {p1, p2, p3, p4, p5, p6};
		
		Game unoGame = new Game(players);
		unoGame.play();
	}
}
