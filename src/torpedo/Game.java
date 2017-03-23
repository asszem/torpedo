package torpedo;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Game {

	private static boolean debug = false;

	{
		if (debug) {
			System.out.println("******");
			System.out.println("Debug in Class GAME is ON");
			System.out.println("******");
		}
	}
	private static int gamesCount = 0; //Static counter to keep track of games
	private int gameID; //To identify games by count
	private int numPlayers;
	private int numShips; //The number of ships per player
	private Player[] players; //ARRAY to hold the player objects
	private Map[] maps; //ARRAY to hold all maps for all players

	//ARRAY to hold all ships for a game for each player
	// ships[0][0] = the 1st ship object for Player [0]
	//TODO change this to private later
	public Ship[][] ships;

	/**
	 *
	 * @param playerIndex Draws the map for the specified player identified by PlayerIndex
	 * @param displayShips True - displays the ships on the map 
	 * @param displayHits True - display the hits on the map
	 */
	public void drawPlayerMap(int playerIndex, boolean displayShips, boolean displayHits) {
		maps[playerIndex].drawMap(displayShips, displayHits);
	}

	/**
	 * Constructor to create a Game object based on a Settings object *
	 */
	public Game(Settings settings) {
		//Initialize the player variables
		gameID = gamesCount++;
		players = settings.getPlayers(); //getPlayers returns an ARRAY already
		numPlayers = players.length;

		//Initialize maps array and map objects
		maps = new Map[numPlayers]; //Create an array to store a map for each players
		//create the new Map objects in the maps array
		//map Index == player Index
		for (int i = 0; i < maps.length; i++) {
			maps[i] = new Map(1, settings.getMaxX(), 1, settings.getMaxY());
			if (debug) {
				System.out.printf("Maps object maps[%d] created.%n", i);
			}
		}
		//Initialize ships array and ship objects
		numShips = settings.getTotalNumberOfShips();
		int[] availableShipSizes = settings.getAvailableShipSizes();
		int[] numberOfShipsToCreateForASize = settings.getShipCountPerSize();
		//Create the ships Array to hold ALL ship opjects for ALL players
		//First index identifies the player, second index a ship object for the player
		//Example: ships[1][2] - identified the 3rd ship created for the 2nd player
		ships = new Ship[numPlayers][numShips];

		//Create the ship OBJECTS
		//when ships are created, each ship need to assigned to a Player as owner
		//Loop through for each player.  
		for (int playerIndex = 0; playerIndex < numPlayers; playerIndex++) {
			int totalNumberOfShipsCreatedForACertainPlayer = 0;
			//Loop through all available ship sizes
			for (int shipSizeIndex = 0; shipSizeIndex < availableShipSizes.length; shipSizeIndex++) {
				if (debug) {
					System.out.println("Creating ships of size: " + availableShipSizes[shipSizeIndex]);
				}
				//Create as many ship objects as required for the size
				//totalNumberOfShipsCreatedForACertainPlayer will be the INDEX for each ship for a player!
				for (int shipCreatedIndex = 0; shipCreatedIndex < numberOfShipsToCreateForASize[shipSizeIndex]; shipCreatedIndex++) {
					/*
					ships[playerIndex][totalNumberOfShipsCreatedForACertainPlayer++] 	
					new Ship(
						maps[playerIndex],
						availableShipSizes[shipSizeIndex], 
						players[playerIndex]
						totalNumberOfShipsCreatedForACertainPlayer);
					 */
 /*
					Create the Ship Object.  This will: 
					- update the MAP object XY coordinates with the index of the ship
					- update the POINT object with the index of the ship
					- set the map.shipStatus[X][Y] coordinate to 0 as not hit
					 */
					ships[playerIndex][totalNumberOfShipsCreatedForACertainPlayer] = new Ship(maps[playerIndex], availableShipSizes[shipSizeIndex], players[playerIndex], totalNumberOfShipsCreatedForACertainPlayer);
					//Increment the number of Ships created (and the Index of ship)
					totalNumberOfShipsCreatedForACertainPlayer++;
					if (debug) {
						System.out.printf("PlayerIndex[%d], Ship size[%d], Ship number[%d]%n", playerIndex, availableShipSizes[shipSizeIndex], shipCreatedIndex);
					}//shipcreationdebug
				} //shipCreated loop
			} //shipSize loop
		} //playerIndex loop
		//to print out and test the created ship objects
		boolean arraydebug = false;
		if (arraydebug) {
			System.out.println("**********");
			System.out.println("Iterate over the ships[][[] array and its objects");
			for (int playerIndex = 0; playerIndex < ships.length; playerIndex++) {
				System.out.println("Player: " + players[playerIndex].getName());
				for (int shipIndex = 0; shipIndex < ships[playerIndex].length; shipIndex++) {
					System.out.println("Ship #" + (shipIndex + 1));
					System.out.printf("Size:%d, status:%d. Coords:%n", ships[playerIndex][shipIndex].getShipSize(), ships[playerIndex][shipIndex].getFullShipStatusHitOrSunk());
					for (Point c : ships[playerIndex][shipIndex].getShipCoords()) {
						System.out.printf("<%d-%d>%n", c.getX(), c.getY());
					}
				}
			}
			System.out.println("**********");
		}//arraydebug
		if (debug) {
			System.out.println("Number of players: " + numPlayers);
			System.out.println("Maps array size: " + maps.length);
			System.out.println("Ships array size: " + ships.length);
			System.out.println("settings.getTotalShips=" + settings.getTotalNumberOfShips());
		}

	} //end of Game constructor

	/**
	 * Method to calculate an attack on a map object for a certain player. The method asks for user input for target player and target coordinates Then validates whether the target
	 * cooridnates is a hit or not calling the CheckTarget method.
	 *
	 * @return
	 */
	public boolean attack() {
		debug = false;
		if (debug) {
			System.out.println("Debug on in game.attack() mehtod. \n Shoot at B2. Coords: 2 - 2");
			System.out.println("game.attack method called.");
			//This line assumes Player 0 is target, and displays 2.2 coord
			System.out.println("Is there ship at 2,2: " + maps[0].isThereShip(2, 2));
		}
		/*Select the target player*/
		int targetPlayerIndex;
		boolean validTarget = false;
		do {
			System.out.println("Kire lősz?");
			for (int i = 0; i < players.length; i++) {
				System.out.printf("[%d] - %s%n", i, players[i].getName());
			}
			try {
				System.out.println("Írd be a számát:");
				Scanner input = new Scanner(System.in);
				targetPlayerIndex = input.nextInt();
				if (targetPlayerIndex < 0 || targetPlayerIndex > players.length - 1) {
					System.out.printf("Hiba! A számnak [%d] és [%d] között kell lennie", 0, players.length - 1);
				} else {
					System.out.println("A célpont: " + players[targetPlayerIndex].getName());
					//TODO ide olyan térképet kirajzoltatni, ami nem mutatja a hajókat, csak a lövéseket
					//Draw the map BEFORE the hit NOT displaying the ships, only the HITS
					drawPlayerMap(targetPlayerIndex, false, true);
					//Ezzel a kiválasztott játékoshoz tartozó térképre lövünk
					//targetCoords[0]=X, targetCoords[1]=Y
					int[] targetCoords = UserInput.userInput(maps[targetPlayerIndex]);
					//Set the coordinate to be Hit by player. No ship validation yet
					maps[targetPlayerIndex].setHitByPlayer(targetCoords[0], targetCoords[1]);
					//Check if the coordinate has ship on it and update accordingly
					checkTarget(targetPlayerIndex, targetCoords);
					//Draw the map AFTER the hit
					drawPlayerMap(targetPlayerIndex, true, true);
					System.out.println("");
					validTarget = true; //exits the loop
					return true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Hiba! Számot kell beírni!");
			}
			//Validate if input is a valid number
		} while (!validTarget);
		return false;
	}

	/**
	 * The method checks whether there is a Ship at the Given Coordinates for the given players[Index] If yes, it calls hitShipStatus() method do tetermine whether the hit is sink
	 * or not
	 *
	 * @param playerIndex the index of player whose maps[playerIndex] to be checked
	 * @param targetCoords the input coordinates X-Y to be checked. targetCoords[0] = X, targetCoords[1]=y
	 * @return true if target hit was successfull, false if not
	 */
	public boolean checkTarget(int playerIndex, int[] targetCoords) {
		boolean debug = false;
		if (debug) {
			System.out.println("****checkTarget method debug on");
			System.out.printf("Target coordinates:%nx=[%d] y=[%d]%n", targetCoords[0], targetCoords[1]);
			System.out.println("****checkTarget method over");
		}
		//isThereShip(X,Y)
		if (maps[playerIndex].isThereShip(targetCoords[0], targetCoords[1])) {
			boolean hitOrSunk = hitShipStatus(playerIndex, targetCoords);
			System.out.println(hitOrSunk ? "Talált, süllyedt!" : "Talált, de nem süllyedt!");
			return true;
		} else {
			System.out.println("Nem talált!");
			return false;
		}
	}

	/**
	 * To check if the ship that was hit is SUNK or just HIT and to SET the hit status
	 *
	 *
	 * This method is called from Attack!
	 *
	 * @param playerIndex The index of Player
	 * @param targetCoords The coordinates (XY) to check
	 * @return true - if the ship is SUNK or false if HIT, but not sunk
	 */
	public boolean hitShipStatus(int playerIndex, int[] targetCoords) {
		boolean isShipSink = false; //this will set to true only if all ship coordinates are hit
		int tX = targetCoords[0];
		int tY = targetCoords[1];
		//Status 1 = hit
		maps[playerIndex].setShipCoordStatusOnMap(tX, tY,1);
		int shipIndex = maps[playerIndex].getShipIndexFromMap(tX, tY);
		debug = false;
		if (debug) {
			System.out.println("Game.hitShipStatus() called, DEBUG mode on");
			System.out.printf("Index of the Ship on %d-%d:%d%n", tX, tY, shipIndex);
		}

//update the ship status to isShipHit = true on the target coordinates
//update the MAP object shipStatus to 1 so it can be drawn as hit
		int numberOfHitCoordinates = 0;
		for (Point p : ships[playerIndex][shipIndex].getShipCoords()) {
			if (p.getX() == tX && p.getY() == tY) {
				p.setShipHit(); //This set the coordinate for the ship to TRUE
				//Set the ship status to HIT
				ships[playerIndex][shipIndex].setFullShipStatusHitOrSunk(1);
			}
			//Increment number of hits, if current coordinate isShipHit status is TRUE
			if (p.isShipHit() == true) {
				numberOfHitCoordinates++;
			}

			if (debug) {
				System.out.printf("X=%d, Y=%d, isHit:%b, totalHit:%d%n", p.getX(), p.getY(), p.isShipHit(), numberOfHitCoordinates);
			}
		}
//if all coordinates is TRUE for hit, then the ship is SUNK
		int fullShipStatus = ships[playerIndex][shipIndex].getFullShipStatusHitOrSunk();
		//if ship is SUNK
		if (numberOfHitCoordinates == ships[playerIndex][shipIndex].getShipCoords().length) {
			isShipSink = true;
		}
		if (debug) {
			System.out.println("Number of Hit Coordinates: " + numberOfHitCoordinates);
			System.out.println("getShipsCoords().length: " + ships[playerIndex][shipIndex].getShipCoords().length);
			System.out.println("isShipSink=" + isShipSink);
			System.out.println("Status of the ship: " + fullShipStatus);
		}

		return isShipSink; //True if Sink, False if not yet
	}
}
