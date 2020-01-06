package Bingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Balls {
	
	/**
	 * Get the letter associated with the number
	 * @param x
	 * @return
	 */
	public static String getLetter(int x) {
		if(x <= 15) {
			return "B";
		} else if(15 < x && x <= 30) {
			return "I";
		} else if(30 < x && x <= 45) {
			return "N";
		} else if(45 < x && x <= 60) {
			return "G";
		} else {
			return "O";
		}
	}

	/**
	 * Return a list of random numbers that are within the range [min,max]
	 *  - All numbers returned are unique
	 *  - All numbers range from min -> max
	 * Returns of length 'length' for flexibility and ease of use for building BINGO boards with all unique numbers
	 */
	public static int[] getRandomList(int min, int max, int length) {
		
		if(max < min) throw new Error("Cannot have max < min");
		if(length > max - min) throw new Error("Cannot have returned list length greater than list range");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i <= max - min; i++) {
			list.add(min + i);
		}
		
		Collections.shuffle(list);
		
		int[] uniqueList = new int[length];
		for(int i = 0; i < uniqueList.length; i++) {
			uniqueList[i] = list.get(i);
		}
		
		return uniqueList;
	}
}
