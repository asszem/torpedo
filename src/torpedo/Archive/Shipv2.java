package torpedo.Archive;

import java.util.Random;
import torpedo.Map;
import torpedo.Point;
import torpedo.RandomDirection;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Shipv2 {

	/*
	private int shipSize;
	private Point[] shipCoords; //array to store the coordinates of the ship
	//Each Point object has info whether that point is hit or not

//Ship Constructor - to create a ship an existing MAP object will be need to be supplied. 
	Shipv2(Map map, int size) {
		shipCoords = new Point[size];//Create an array for storing the coordinates
		//Generate the starting point
		//The starting point must be furthest from possible map borders
		int xStartLimit = map.getStartX() + (size - 1); //startx 1 = 1+3-1=3
		int xEndLimit = map.getEndX() - (size - 1);		//xend 7 = 7 - 3-1 = 5
		int yStartLimit = map.getStartY() + (size - 1);
		int yEndLimit = map.getEndY() - (size - 1);
		System.out.println("Ship creation started. Size: " + size);
		System.out.printf("%nCoordinate limits: Xmin:%d Xmax:%d   Ymin:%dYmax:%d%n", xStartLimit, xEndLimit, yStartLimit, yEndLimit);
		boolean shipCreated = false; //this will control the while loop
		int selectedDirection = 0; //this will result in a number corresponding the selected direction
		int randomX = 0; //This will be the starting X coordinate of the ship
		int randomY = 0; //This will be the starting Y coordinate of the ship
		while (!shipCreated) { //exit the loop only when the ship is created
			//Generate Random Starting Coordinate
			System.out.println("While started...");
			Random rand = new Random();
//			randomX = xStartLimit + (int) (Math.random() * (xEndLimit - xStartLimit + 1));
//			randomY = yStartLimit + (int) (Math.random() * (yEndLimit - yStartLimit + 1));
			randomX = xStartLimit + rand.nextInt(xEndLimit-xStartLimit+1);
			randomY = yStartLimit + rand.nextInt(yEndLimit-yStartLimit+1);
			System.out.printf("%nRandom Starting point candidate %d:%d%n", randomX, randomY);
			//Check collision with each direction considering NO direct contact allowed with another ship
			//The random selection will decide only from the allowed directions
			//if there are no allowed directions then restart the while loop with new starting point
//Direction check
			boolean upwardAllowed = true; //
			boolean downwardAllowed = true;
			boolean leftAllowed = true;
			boolean rightAllowed = true;
			//One loop to check all 4 directions
			for (int i = 0; i <= size; i++) //i<= because loop starts from 0 and size+1 needs to be checked
			{
				//Check upward
				int increment = i * 1;
				//Check the Y coordinate, the first increment is zero
				if (map.isThereShip(randomX, randomY + increment)) {
					upwardAllowed = false;
				}
				//Check the left x neighbour
				if (map.isThereShip(randomX - 1, randomY + increment)) {
					upwardAllowed = false;
				}
				//Check the right x neighbour
				if (map.isThereShip(randomX + 1, randomY + increment)) {
					upwardAllowed = false;
				}
				//Check downward
				increment = i * -1;
				//Check the Y coordinate
				if (map.isThereShip(randomX, randomY + increment)) {
					downwardAllowed = false;
				}
				//Check the left x neighbour
				if (map.isThereShip(randomX - 1, randomY + increment)) {
					downwardAllowed = false;
				}
				//Check the right x neighbour
				if (map.isThereShip(randomX + 1, randomY + increment)) {
					downwardAllowed = false;
				}
				//Check right
				increment = i * 1;
				//Check the Y coordinate
				if (map.isThereShip(randomX + increment, randomY)) {
					rightAllowed = false;
				}
				//Check the upper y neighbour
				if (map.isThereShip(randomX + increment, randomY - 1)) {
					rightAllowed = false;
				}
				//Check the downward y neighbour
				if (map.isThereShip(randomX + increment, randomY + 1)) {
					rightAllowed = false;
				}
				//Check left direction
				increment = i * -1;
				//Check the Y coordinate
				if (map.isThereShip(randomX + increment, randomY)) {
					leftAllowed = false;
				}
				//Check the upper y neighbour
				if (map.isThereShip(randomX + increment, randomY - 1)) {
					leftAllowed = false;
				}
				//Check the downward y neighbour
				if (map.isThereShip(randomX + increment, randomY + 1)) {
					leftAllowed = false;
				}
			} //end for 
			//Call the RandomDirection method based on results
			//Since I used boolean for determining direction validity, I have to convert booleans to ints
			selectedDirection = RandomDirection.randomDirection(
					upwardAllowed ? 1 : 0,
					downwardAllowed ? 1 : 0,
					rightAllowed ? 1 : 0,
					leftAllowed ? 1 : 0);
			System.out.println("Selected Direction value: " + selectedDirection);
			if (selectedDirection != 0) {
				System.out.println("Ship can be created");
				System.out.println("Selected direction: " + selectedDirection);
				shipCreated = true;
			}
			System.out.println("Restart the loop as shipCreated is still " + shipCreated);
		} //end while
		int extendX = 0; //variable to use to set the extension direction on the x coordinate
		int extendY = 0; //variable to use to set the extension direction on the y coordinate
		switch (selectedDirection) {
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
				extendX = -1;
				break;
			case 4: //LEFT - extend left (x-)
				extendY = 0;
				extendX = 1;
				break;
		}//end switch
		//And finally, create the ship to the selected direction
		for (int w = 0; w < size; w++) { // size=3, 3 loops 0-2
			shipCoords[w] = new Point(randomX + extendX * w, randomY + extendY * w);
			map.setShipPosition(shipCoords[w].getX(), shipCoords[w].getY());
		}
		for (Point p : shipCoords) {
			System.out.printf("[%d],[%d]", p.getX(), p.getY());
		}

	}//End Shipv2 Constructor
*/
}
