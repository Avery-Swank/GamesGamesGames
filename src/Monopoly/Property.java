package Monopoly;

public class Property {

	private String name;
	private String color;
	
	private int rent;
	private int rentOneHouse;
	private int rentTwoHouses;
	private int rentThreeHouses;
	private int rentFourHouses;
	private int rentHotel;
	
	private int morgageValue;
	private int housesCost;
	private int hotelsCost;
	
	private int numHouses;
	private int numHotels;
	
	public Property(String _name, String _color, int _rent, int _rentOneHouse, int _rentTwoHouses, int _rentThreeHouses, int _rentFourHouses, int _rentHotel, int _morgageValue, int _houseCost, int _hotelsCost) {
		name = _name;
		color = _color;
		rent = _rent;
				
		rentOneHouse = _rentOneHouse;
		rentTwoHouses = _rentTwoHouses;
		rentThreeHouses = _rentThreeHouses;
		rentFourHouses = _rentFourHouses;
		rentHotel = _rentHotel;
		
		morgageValue = _morgageValue;
		housesCost = _houseCost;
		hotelsCost = _hotelsCost;
		
		numHouses = 0;
		numHotels = 0;
	}
	
	public void buyHouse() {
		numHouses++;
	}
	
	public void buyHotel() {
		numHotels++;
	}
	
	public boolean canBuyHotel() {
		return numHouses == 4;
	}
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
	
	/**
	 * @description Return the rent that a player would have to pay if the property contains 
 	 *				certain number of houses or hotels
	 */
	public int getRent() {

		// if the property has four houses, then it can have hotels
		if(numHotels > 0)
			return rentHotel;
		
		switch(numHouses) {
			case 0: return rent;
			case 1: return rentOneHouse;
			case 2: return rentTwoHouses;
			case 3: return rentThreeHouses;
			case 4: return rentFourHouses;
			default: 
				throw new Error("Invalid Number of Houses");
		}
	}
	
	public int getMorgageValue() {
		return morgageValue;
	}
	
	public int getMorgageHousesCost() {
		return housesCost;
	}
	
	public int getMorgageHotelsCost() {
		return hotelsCost;
	}
	
	public int getNumHouses() {
		return numHouses;
	}
	
	public int getNumHotels() {
		return numHotels;
	}
	
	public String toString() {
		String s = "Property: " + name + " Color: " + color + "\n";
		
		s = s.concat("\t RENT $" + rent + "\n");
		s = s.concat("With 1 House \t $" + rentOneHouse + "\n");
		s = s.concat("With 2 Houses \t $" + rentTwoHouses + "\n");
		s = s.concat("With 3 Houses \t $" + rentThreeHouses + "\n");
		s = s.concat("With 4 Houses \t $" + rentFourHouses + "\n");
		s = s.concat("\t With HOTEL $" + rentHotel + "\n");
		
		s = s.concat("Morgage Value $" + morgageValue + "\n");
		s = s.concat("Houses Cost $" + housesCost + "\n");
		s = s.concat("Hotels Cost $" + hotelsCost + "\n");
		
		return s;
	}
}
