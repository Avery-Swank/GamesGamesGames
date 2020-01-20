package Monopoly;

public class Railroad {

	private String name;
	
	private int rent;
	private int rentTwoRR;
	private int rentThreeRR;
	private int rentFourRR;
	
	private int morgageValue;
	
	public Railroad(String _name, int _rent, int _rentTwoRR, int _rentThreeRR, int _rentFourRR, int _morgageValue) {
		name = _name;
		
		rent = _rent;
		rentTwoRR = _rentTwoRR;
		rentThreeRR = _rentThreeRR;
		rentFourRR = _rentFourRR;
		
		morgageValue = _morgageValue;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * @description Return the rent that a player would have to pay
	 */
	public int getRent(int numRR) {
		
		switch(numRR) {
			case 1: return rent;
			case 2: return rentTwoRR;
			case 3: return rentThreeRR;
			case 4: return rentFourRR;
			default: 
				throw new Error("Invalid Number of Railroads");
		}
	}
	
	public int getMorgageValue() {
		return morgageValue;
	}
	
	public String toString() {
		String s = "Property: " + name + "\n";
		
		s = s.concat("Rent                    $" + rent + ".\n");
		s = s.concat("If 2 R.R.'s are owned    " + rentTwoRR + ".\n");
		s = s.concat("If 3   '   '   '         " + rentThreeRR + ".\n");
		s = s.concat("If 4   '   '   '         " + rentFourRR + ".\n");
		
		s = s.concat("Morgage Value            " + morgageValue + ".\n");
		
		return s;
	}
}
