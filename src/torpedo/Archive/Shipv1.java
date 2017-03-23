package torpedo.Archive;

import torpedo.Point;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Shipv1 {
/*
	private static int shipNumber = 3; //The number of total ships in the field
	private static Shipv1[] shipsObjects = new Shipv1[shipNumber]; //object reference array
	private static int shipSize = 3; //size of a ship
	private static int maxX = 7; //maximum of X coordinate starting from 0
	private static int maxY = 7; //maximumo of Y coordinates, starting from 0
	private static boolean[][] isThereAShip = new boolean[maxX+1][maxY+1]; //true if a coordinate is occupied by a ship

	private static int[] shipX = new int[shipNumber]; //a static array to store all X coordinates used by ships
	private static int[] shipY; //a static array to store all Y coordinates used by ships
	private int x; //Horizontal coordinates of a specific ship instance
	private int y; //vertical coordinates of a specific ship instance
	private Point[] shipCoords = new Point[shipSize]; //the number of points in a ship

	//Constructor to crate a new ship with random coordinates
	Shipv1() {
		boolean shipcreated = false;
		outwards: //jumps here if an invalid ship would have been created
		while (!shipcreated) { //exit the loop only when the ship is created 
//select a random point on the map. This will be the starting coordinate
//starting point must be furthest from the border to make sure ship does not overlap 
			int rndmX = (int) (Math.random() * maxX + 1); //select a random value between 0 and maxX
			int rndmY = (int) (Math.random() * maxY + 1); //select a random value between 0 and maxX
			System.out.printf("Random Starting Coordinate: X:%d, Y:%d%n%n", rndmX, rndmY);
//verifiy if it is not used by another ship - if yes, select another position
			if (isThereAShip[rndmX - 1][rndmY - 1]) {
				System.out.printf("There is a ship at %d:%d", rndmX, rndmY);
			}
//Select a random direction to extend
			int extend = (int) (1 + Math.random() * 4);
			System.out.println("Extend:" + extend);
			int extendX = 0; //variable to use to set the extension direction on the x coordinate
			int extendY = 0; //variable to use to set the extension direction on the y coordinate
			switch (extend) {
				case 1: //extend upwards (y+)
					extendY = 1;
					extendX = 0;
					break;
				case 2: //extend downwards (y-)
					extendY = -1;
					extendX = 0;
					break;
				case 3: //extend left (x-)
					extendY = 0;
					extendX = -1;
					break;
				case 4: //extend right (x+)
					extendY = 0;
					extendX = 1;
					break;
			}
//			rndmX=1;
//			rndmY=1;
//verify if any of the direction would be out of boundary - ifk
			for (int i = 0; i < shipCoords.length; i++) {
				if ((rndmX + i * extendX <= 0)
						|| (rndmX + i * extendX > maxX)
						|| (rndmY + i * extendY <= 0)
						|| (rndmY + i * extendY > maxY)) {
					System.out.println("Out of boundary error. Restart with new coordinates");
					continue outwards;
				}
			}
//if this is reached, then the ship can be created
			for (int i = 0; i < shipCoords.length; i++) {
				shipCoords[i] = new Point(rndmX + i * extendX, rndmY + i * extendY);
				isThereAShip[rndmX+i*extendX][rndmY+i*extendY]=true;
				System.out.printf("#%d cooridnates: X<%d>:Y<%d>%n", i, shipCoords[i].getX(), shipCoords[i].getY());
			}
			shipcreated = true;
		}

	}

	public static void drawShips() {
		for (int j = 0; j < maxY; j++) {
			System.out.printf("%d", j);
			for (int i = 0; i < maxX; i++) {
				if (isThereAShip[i][j]) {
					System.out.print("X");
				} else {
					System.out.printf("-");
				}
			}
			System.out.println("");
		}
		for (int i = 0; i < maxX; i++) {
			if (i == 0) {
				System.out.print(" ");
			}
			System.out.printf("%d", i);
		}
	}
*/
}
