package BattleShip;

public class Run {

	public static void main(String[] args) {
		
		Player p1 = new Player("Avery", "breadth");
		Player p2 = new Player("Luke", "random");
		
		// Customize the BattleShip pieces to play here
		// A copy of each 'shipTypes' goes to each player
		Ship carrierShip = new Ship("Carrier", 5);
		Ship battleShip = new Ship("BattleShip", 4);
		Ship cruiserShip = new Ship("Cruiser", 3);
		Ship submarineShip = new Ship("Submarine", 3);
		Ship destroyerShip = new Ship("Destroyer", 2);
		Ship longBoiShip = new Ship("Long Boi", 10);
		Ship[] shipTypes = {longBoiShip, carrierShip, battleShip, cruiserShip, submarineShip, destroyerShip};
		
		Game battleship = new Game(p1, p2, shipTypes);
		battleship.play();
	}

}
