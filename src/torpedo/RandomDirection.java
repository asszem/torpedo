//Returns 0 if none, or randomly chooses option 1/2/3/4 assuming given option has non zero argument
package torpedo;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class RandomDirection {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			int a = (int) (Math.random() * 2);
			int b = (int) (Math.random() * 2);
			int c = (int) (Math.random() * 2);
			int d = (int) (Math.random() * 2);
			int e = randomDirection(a, b, c, d);
			System.out.printf("Iteration #%d. params: %d,%d,%d,%d result:%d%n%n", i, a, b, c, d, e);
		}

	}

	//Returns 0 if none, or randomly chooses option 1/2/3/4 assuming given option has non zero argument
	/**
	 * Selects one of the arguments randomly from the arguments that have value 1. Returns 0 if no selected 
	 * @param a - upward direction - returns 1 if selected
	 * @param b - downward direction - returns 2 if selected
	 * @param c - right direction - returns 3 if selected
	 * @param d - left direction - returns 4 if selected
	 * @return 0 if no direction selected, 1-2-3-4 if upward, downward, right, left selected
	 */
	public static int randomDirection(int a, int b, int c, int d) {
		int availableOptions = a + b + c + d;
		if (availableOptions == 0) {
			return 0;
		}
		String[] selectionPool = new String[availableOptions];
		boolean aChecked = false;
		boolean bChecked = false;
		boolean cChecked = false;
		boolean dChecked = false;
		for (int i = 0; i < selectionPool.length; i++) {
			if (!aChecked) {
				aChecked = true;
				if (a == 1) {
					selectionPool[i] = "A";
					continue;
				}
			}
			if (!bChecked) {
				bChecked = true;
				if (b == 1) {
					selectionPool[i] = "B";
					continue;
				}
			}
			if (!cChecked) {
				cChecked = true;
				if (c == 1) {
					selectionPool[i] = "C";
					continue;
				}
			}
			if (!dChecked) {
				dChecked = true;
				if (d == 1) {
					selectionPool[i] = "D";
				}
			}
		}
		int rndNumber = (int) (Math.random() * availableOptions); //will be used for array index!
		boolean debugInfo = false;
		if (debugInfo) {
			System.out.printf("Selection Pool size (%d)%n", availableOptions);
			System.out.println("Selection pool content:");
			for (String s : selectionPool) {
				System.out.printf("[%s] ", s);
			}
			System.out.println("");
			System.out.println("Random number selected:" + rndNumber);
			//Pick the winner from the selection pool based on the random number that will be the index
			System.out.println("Selected value: " + selectionPool[rndNumber]);
		}
		switch (selectionPool[rndNumber]) {
			case "A":
				return 1;
			case "B":
				return 2;
			case "C":
				return 3;
			case "D":
				return 4;
		}
		//If this is reached, no number can be returned
		return 0;
	}
}
