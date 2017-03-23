package torpedo;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 *
 */
public class Map {
//A map object need to be created for each game. 
//It will have the boundaries and the position of ships

	//coordinates of a map
	private int startX;
	private int endX;
	private int startY;
	private int endY;
	private boolean[][] isThereShip; //array to determine if there is a ship on a given coordinte
	private boolean[][] isHitByPlayer; //the coordinates where a player has fired (true)
	private int[][] shipStatus; //[][]=XY coords. 0 - not hit. 1 - hit
	private int[][] shipIndex; //to store the ships[][index] index of the ship on a given map's given coordinate. Example. shipIndex[3][4]=4 means the ship index on X=3Y=4 coordinate is 4

	/*
	Status=
	0 - no ship
	1 - ship
	2 - sunk ship
	3 - hit by other player
	 */
	/**
	 * Constructor to create the map for the game. Parameters are:
	 *
	 * @param startXc
	 * @param endXc
	 * @param startYc
	 * @param endYc
	 */
	public Map(int startXc, int endXc, int startYc, int endYc) {
		startX = startXc;
		startY = startYc;
		endX = endXc;
		endY = endYc;
		isThereShip = new boolean[endX + 1][endY + 1]; //ships X-Y coordinate
		isHitByPlayer = new boolean[endX + 1][endY + 1]; //the coordinates where a player has fired
		shipStatus = new int[endX + 1][endY + 1]; //0 - not hit, 1 - hit
		shipIndex = new int[endX + 1][endY + 1]; //game.ships[playerIndex][shipIndex]
	}

	/**
	 * @param showShips	if true than ship locations will be shown, if not, then only hit ships
	 * @param showHits if true the locations to where a player has already hit is shown
	 */
	public void drawMap(boolean showShips, boolean showHits) {
		String hitWaterOnly = "..";
		String notHitShip = "SS";
		String hitShip = "xx";
		String water = "~~";
//		isThereShip[10][10] = true;
//		isThereShip[3][2]=true;
//Create a string array to display A-Z characters for Y coordinates
		int minYChar = 65; //The minimum unicode of Y coord character
		int maxYChar = minYChar + (endY - startY + 1); //Only that many character to be used as long the map is
		String[] yCoordChars = new String[maxYChar - minYChar];
		int counter = 0;
		for (int i = minYChar; i < maxYChar; i++) {
			yCoordChars[counter++] = Character.toString((char) i);
//			System.out.println("Counter:"+counter+" "+yCoordChars[counter-1]);
		}
		counter = 0;
//Draw the map
		System.out.println("");
		for (int i = endY; i >= 1; i--) {
//			System.out.printf("%s(%02d)", yCoordChars[yCoordChars.length - (++counter)], endY-i); //start from the end of the array
			System.out.printf("%s(%02d)", yCoordChars[yCoordChars.length - (++counter)], i); //start from the end of the array
			for (int k = 1; k <= endX; k++) {
				if (isThereShip[k][i]) {
					//If we want to show the location of Ships
					if (showShips) {
						System.out.print(shipStatus[k][i] == 1 ? hitShip : notHitShip);
					} 
					//showShips=FALSE	
					else //Three possible cases: water only, hit only, hit ship
					//Case: there is an already hit ship at the position and showShips=FALSE
					if (showHits == true && isHitByPlayer[k][i] == true && shipStatus[k][i]==1) {
						System.out.print(hitShip);
					//Case: there is no ship, but already hit at the position
					} else if (showHits==true && isHitByPlayer[k][i]==true && shipStatus[k][i]!=1){
						System.out.print(hitWaterOnly);
					}
					//Case: ship is there, ShowShips:False, isHItByPlayer: False
					else {
						System.out.print(water);
					}

//					System.out.print(shipStatus[k][i] + ".");
				} else {
					//Display either simple water or already hit
					System.out.print((showHits == true && isHitByPlayer[k][i] == true) ? hitWaterOnly : water);
				}
			}
			System.out.println("");
		}
		System.out.printf("     ");
		for (int k = 1; k <= endX; k++) {
			System.out.printf("%02d", k);
		}
		System.out.println("");
	}

	/**
	 * @param x The X coordinate of the Map object
	 * @param y The Y coordinate of the Map object
	 * @return returns the Index of the ship on the X-Y coordinate on the map If the X-Y coordinate is invalid, returns -1
	 */
	public int getShipIndexFromMap(int x, int y) {
		//Error handling
		if (x < startX || x > endX || y < startY || y > endY) {
			return -1;
		}
		return shipIndex[x][y];
	}

	/**
	 * The method sets the Index of Ship on a given Map coordinate
	 *
	 * @param x The X coordinate of the Map object
	 * @param y The Y coordinate of the Map object
	 * @param inputIndex the Index of the ship that is to be stored to the XY coordinate
	 * @return true if set Ship was successfull, false if not
	 */
	public boolean setShipIndexOnMap(int x, int y, int inputIndex) {
		boolean debug = false;
		if (debug) {
			System.out.printf("Debug on in Map.setShipIndexOnMap method%nInputs are:%nx=%d%ny=%d%ninputIndex=%d%nEnd of debug%n", x, y, inputIndex);
		}
		//Error handling of X-Y coordinates
		if (x < startX || x > endX || y < startY || y > endY) {
			return false;
		}
		shipIndex[x][y] = inputIndex;
		return true;
	}

	public int getStartX() {
		return startX;
	}

	public int getEndX() {
		return endX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndY() {
		return endY;
	}

	public boolean isThereShip(int x, int y) {
		//verify if arguments are valid array index
		if (x > endX || y > endY || x < startX || y < startY) {
			return false;
		}
		//isThereShip is a boolean that can be true or false
		//This method simply returns the value
		//If there was no ship created at the given position it returns false
		return isThereShip[x][y];
	}

	public void setShipCoordStatusOnMap(int x, int y, int status) {
		shipStatus[x][y] = status;
	}

	//Set a given coordinate as occupied by a ship
	public void setShipPosition(int x, int y) {
		//Itt lehet array index out of bound ha elbaszom. 
		isThereShip[x][y] = true;
		shipStatus[x][y] = 1;
	}

	/**
	 * Sets the coordinates where a player has fired to 1
	 */
	public void setHitByPlayer(int x, int y) {
		isHitByPlayer[x][y] = true;
	}

	/**
	 *
	 * @return 1 - if the input coordinate was already hit by the player and 0 if dont
	 */
	public boolean getHitByPlayer(int x, int y) {
		return isHitByPlayer[x][y];
	}

	//only to test the map generation method
	public static void main(String[] args) {
		Map t = new Map(1, 10, 1, 10);
		t.setShipPosition(1, 1);
		t.setShipCoordStatusOnMap(1, 1, 0);
		t.drawMap(true, true);

//		System.out.println(t.isThereShip(10, 10));
	}
}
