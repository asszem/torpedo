package torpedo;

import java.util.Random;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Ship {

	Random rnd = new Random();
	private Player shipOwner; //A Player object reference, who owns the ship
	private int shipSize;
	private Point[] shipCoords; //Every ship object will have an array holding its coordinates
	private int fullShipStatusHitOrSunk; //Status: 0 - no hit, 1 - hit, but not sunk, 2 - sunk
	private int indexOfThisShip; //The game.ships[player][ship] index number

//Constructor to create new ship
	/**
	 * @param map
	 * @param size
	 * @param shipOwner
	 * @param indexOfShip - this is the array index in game.ships[player][SHIP!] to identify the array reference of the ship object This information will be stored upon ship
	 * generation at each point object, so that by knowing an object coordinate the ship will also be identified on that specific coordinate
	 *
	 */
	public Ship(Map map, int size, Player shipOwner, int indexOfShip) {
		this.shipOwner = shipOwner;
		this.shipSize = size;
		shipCoords = new Point[size];
		indexOfThisShip = indexOfShip;

		boolean debug = false;
		if (debug) {
			System.out.println("\nShip Creation V3.1 constructor started...");
			System.out.println("Ship size: " + size);
			System.out.printf("Map size (x-X, y-Y): %d-%d, %d-%d%n", map.getStartX(), map.getEndX(), map.getStartY(), map.getEndY());
		}
		boolean isShipCreated = false;
		while (!isShipCreated) {
//Pick a random start point for new ship
			Point start = new Point(1 + rnd.nextInt(map.getEndX()), 1 + rnd.nextInt(map.getEndY()));
			if (debug) {
				System.out.printf("Random start point: <%d>,<%d>%n", start.getX(), start.getY());
			}
//Check if there is another ship at selectet start point
			boolean isStartFree = map.isThereShip(start.getX(), start.getY());
			if (debug) {
				System.out.printf("Another ship at start point: %b%n", isStartFree);
			}
			if (isStartFree) {
				if (debug) {
					System.out.println("Restarting while because starting point not valid");
				}
//				continue; //To restart the while loop becasue startng pont is not valid
			}
//Check if a direction does not overlap the map borders
			int isUpwardPossible = start.getY() + (size - 1) <= map.getEndY() ? 1 : 0;
			int isDownwardPossible = start.getY() - (size - 1) >= map.getStartY() ? 1 : 0;
			int isRightPossible = start.getX() + (size - 1) <= map.getEndX() ? 1 : 0;
			int isLeftPossible = start.getX() - (size - 1) >= map.getStartX() ? 1 : 0;
			if (debug) {
				System.out.println("");
				System.out.printf("\tCoord\tSize(%d-1)\tLimit\tPossible%n", size);
				System.out.printf("Up\tY:%d\t%d\t\t%d\t%d%n", start.getY(), start.getY() + size - 1, map.getEndY(), isUpwardPossible);
				System.out.printf("Down\tY:%d\t%d\t\t%d\t%d%n", start.getY(), start.getY() - size + 1, map.getStartY(), isDownwardPossible);
				System.out.printf("Right\tX:%d\t%d\t\t%d\t%d%n", start.getX(), start.getX() + size - 1, map.getStartX(), isRightPossible);
				System.out.printf("Left\tX:%d\t%d\t\t%d\t%d%n", start.getX(), start.getX() - size + 1, map.getStartX(), isLeftPossible);
				System.out.printf("Possible directions (Up,Down,Right,Left): %d,%d,%d,%d", isUpwardPossible, isDownwardPossible, isRightPossible, isLeftPossible);
				System.out.println("");
			}
//Check if there is a ship in the direction and in the neighbours
			//Check Upward 
			for (int c = 0; c <= size; c++) { // size=3, c=0,1,2,3, checked: position, +1,+2,+3
				//Check at Y-1 to make sure no ship directly downward
				if (map.isThereShip(start.getX(), start.getY() - 1)) {
					isUpwardPossible = 0;
				}
				//Check at XY and iterate with c
				if (map.isThereShip(start.getX(), start.getY() + c)) {
					isUpwardPossible = 0;
				}
				//Check at X-1 and iterate with c
				if (map.isThereShip(start.getX() - 1, start.getY() + c)) {
					isUpwardPossible = 0;
				}
				//Check at X+1 and iterate with c
				if (map.isThereShip(start.getX() + 1, start.getY() + c)) {
					isUpwardPossible = 0;
				}
			}
			if (debug) {
				System.out.println("Upward ship collision checked. Result: " + isUpwardPossible);
			}
			//Check Downward 
			for (int c = 0; c <= size; c++) { // size=3, c=0,1,2,3, checked: position, +1,+2,+3
				//Check at Y+1 to make sure no ship directly upward
				if (map.isThereShip(start.getX(), start.getY() + 1)) {
					isDownwardPossible = 0;
				}
				//Check at X
				if (map.isThereShip(start.getX(), start.getY() + c * -1)) {
					isDownwardPossible = 0;
				}
				//Check at X-1
				if (map.isThereShip(start.getX() - 1, start.getY() + c * -1)) {
					isDownwardPossible = 0;
				}
				//Check at X+1
				if (map.isThereShip(start.getX() + 1, start.getY() + c * -1)) {
					isDownwardPossible = 0;
				}
			}
			if (debug) {
				System.out.println("Downward ship collision checked. Result: " + isDownwardPossible);
			}
			//Check Right
			for (int c = 0; c <= size; c++) { // size=3, c=0,1,2,3, checked: position, +1,+2,+3
				//Check at X+1 to make sure no ship directly LEFT from start point
				if (map.isThereShip(start.getX() - 1, start.getY())) {
					isRightPossible = 0;
				}
				//Check at X
				if (map.isThereShip(start.getX() + c, start.getY())) {
					isRightPossible = 0;
				}
				//Check at X-1
				if (map.isThereShip(start.getX() + c, start.getY() - 1)) {
					isRightPossible = 0;
				}
				//Check at X+1
				if (map.isThereShip(start.getX() + c, start.getY() + 1)) {
					isRightPossible = 0;
				}
			}
			if (debug) {
				System.out.println("Right ship collision checked. Result: " + isRightPossible);
			}
			//Check Left
			for (int c = 0; c <= size; c++) { // size=3, c=0,1,2,3, checked: position, +1,+2,+3
				//Check at X-1 to make sure no ship directly RIGHT
				if (map.isThereShip(start.getX() + 1, start.getY())) {
					isLeftPossible = 0;
				}
				//Check at X
				if (map.isThereShip(start.getX() + c * -1, start.getY())) {
					isLeftPossible = 0;
				}
				//Check at X-1
				if (map.isThereShip(start.getX() + c * -1, start.getY() - 1)) {
					isLeftPossible = 0;
				}
				//Check at X+1
				if (map.isThereShip(start.getX() + c * -1, start.getY() + 1)) {
					isLeftPossible = 0;
				}
			}
			if (debug) {
				System.out.println("Left ship collision checked. Result: " + isLeftPossible);
			}
//Pick possible random direction
			int selectedDir = RandomDirection.randomDirection(isUpwardPossible, isDownwardPossible, isRightPossible, isLeftPossible);
			if (debug) {
				System.out.println("Selected Random Direction: " + selectedDir);
			}
			if (selectedDir == 0) {
				continue; //Create new random start point if no direction is allowed
			}
//Create the new ship. Should reach this only if there is a valid direction	
			int extendY = 0;
			int extendX = 0;
			switch (selectedDir) {
				case 1: //UP - extend upwards (y+)
					extendY = 1;
					extendX = 0;
					break;
				case 2: //DOWN - extend downwards (y-)
					extendY = -1;
					extendX = 0;
					break;
				case 3: //RIGHT - extend right (x+)
					extendY = 0;
					extendX = 1;
					break;
				case 4: //LEFT - extend left (x-)
					extendY = 0;
					extendX = -1;
					break;
			}//end switch
//HERE IS THE ACTUAL SHIP CREATION
			//And finally, create the ship to the selected direction
			for (int w = 0; w < size; w++) { // size=3, 3 loops 0-2
				shipCoords[w] = new Point(start.getX() + extendX * w, start.getY() + extendY * w);

				//this sets the coordStatus and isThereShip booleans in Map object to 1 and true
				map.setShipPosition(shipCoords[w].getX(), shipCoords[w].getY());
				map.setShipCoordStatusOnMap(shipCoords[w].getX(), shipCoords[w].getY(),0);
				fullShipStatusHitOrSunk = 0;
				//TO BE VERIFIED, NOT SURE IF CORRECT
				//SET SHIPowner for the given coordinate
				shipCoords[w].setShipOwner(shipOwner);
				//Store the referencing array index for the ship coordinate Point object
				shipCoords[w].setShipsArrayIndex(indexOfThisShip);
				//Update the MAP object, setting the ship index to the XYcoordinate
				map.setShipIndexOnMap(shipCoords[w].getX(), shipCoords[w].getY(), indexOfThisShip);
			}
			if (debug) {
				System.out.println("Ship created at the coordinates:");
				for (Point p : shipCoords) {
					System.out.printf("[%d],[%d] ", p.getX(), p.getY());
				}
			}
			isShipCreated = true;
		}//end while
	} //end ship constructor

	/**
	 * @return the shipOwner
	 */
	public Player getShipOwner() {
		return shipOwner;
	}

	/**
	 * @return the shipSize
	 */
	public int getShipSize() {
		return shipSize;
	}

	/**
	 * @return the shipCoords
	 */
	public Point[] getShipCoords() {
		return shipCoords;
	}

	/**
	 * @return the fullShipStatusHitOrSunk. 1-not hit 2-hit, but not sunk, 3-sunk
	 */
	public int getFullShipStatusHitOrSunk() {
		return fullShipStatusHitOrSunk;
	}

	/**
	 * @param newStatus 1- hit 2- sunk*/
	public void setFullShipStatusHitOrSunk(int newStatus) {
		fullShipStatusHitOrSunk=newStatus;
	}

	/**
	 * @return the second index of the ship to identify in the game.ships[][ship] array
	 */
	public int getIndexOfThisShip() {
		return indexOfThisShip;
	}

} //end class
