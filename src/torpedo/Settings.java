package torpedo;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Settings {

	private static boolean debug = true;

	private int totalShips; //calculated by total of shipCountPerSize
	private int[] availableShipSizes; //Array to hold all available ship sizes
	//Array to hold number of ships for a size, corresponding to shipSize index
	//shipCount[0] = 3 - have 3 ships of size specified at index0 of shipsize
	//shipCount[1] = 2 - have 2 ships of size shipSize[1]
	private int shipCountPerSize[];
	private int maxX;
	private int maxY;
	private Player[] players;

	//Initialise DEFAULT settings
	{
		players = new Player[1]; //create the array for the Players object
		players[0] = new Player(); //create the Player object itself
		players[0].setName("Player 1 default");
		maxX = 10;
		maxY = 10;
		availableShipSizes = new int[4];
		availableShipSizes[0] = 1;
		availableShipSizes[1] = 2;
		availableShipSizes[2] = 3;
		availableShipSizes[3] = 4;
		shipCountPerSize = new int[getAvailableShipSizes().length];
		shipCountPerSize[0] = 4;
		shipCountPerSize[1] = 3;
		shipCountPerSize[2] = 2;
		shipCountPerSize[3] = 1;
		int totalNumber = 0;
		for (int i = 0; i < getShipCountPerSize().length; i++) {
			totalNumber += getShipCountPerSize()[i];
		}
		totalShips = totalNumber;
	}

//Ship related methods
	/**
	 * @return the total number of ships based on the amount of ships for each size
	 */
	public int getTotalNumberOfShips() {
		int totalNumber = 0;
		for (int i = 0; i < getShipCountPerSize().length; i++) {
			totalNumber += getShipCountPerSize()[i];
		}
		return totalNumber;
	}

	/**
	 * @return an int[] array with the availableShipSizes
	 */
	public int[] getAvailableShipSizes() {
		return availableShipSizes;
	}

	/**
	 * @return an array of ship counts per sizes.
	 *
	 * The index of this array should correspond with the index of shipcount
	 */
	public int[] getShipCountPerSize() {
		return shipCountPerSize;
	}

	/**
	 * Set the number of ships of the given size
	 *
	 * @param inputSize	the size of the ship that needs to be set.
	 * @param inputCount the new amount of ships of the given size
	 * @return returns true if the setting was completed and false if there is no ship with the given size
	 */
	public boolean setShipSizeCount(int inputSize, int inputCount) {
		//Validate whether there is an existing size of that ship
		for (int i = 0; i < getAvailableShipSizes().length; i++) {
			if (getAvailableShipSizes()[i] == inputSize) {
				shipCountPerSize[i] = inputCount;
				return true;
			}
		}
		if (debug) {
			System.out.println("Ship size does not exists");
		}
		return false;
	}

	/**
	 * Sets the available ship sizes array
	 *
	 * @param shipSizes	An array that holds the available ship sizes
	 *
	 * Example: {2,4,5,6} - the map will have ships of size 2, 4, 5 and 6
	 */
	public void setAvailableShipsizes(int[] shipSizes) {
		this.availableShipSizes = new int[shipSizes.length]; //set the array to the size provided 
		//set the values of availableShipSizes array to the provided values
		for (int i = 0; i < shipSizes.length; i++) {
			this.availableShipSizes[i] = shipSizes[i];
		}
	}

//Player related methods
	/**
	 * @return an ARRAY that holds references to all players object
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * @param playersNumber	the new number of available players
	 */
	public void setPlayersNumber(int playersNumber) {
		//Create the array with the size of number of players
		players = new Player[playersNumber];
		//Also create the new PlayerObjects for each new players
		for (int i = 0; i < playersNumber; i++) {
			this.players[i] = new Player();
		}
	}

	/**
	 * @param setIndex the index of the player to be set
	 * @param newName the new name for the player
	 */
	public void setPlayerName(int setIndex, String newName) {
		//Verify if there is a valid object reference on the index
		if (players[setIndex] != null) {
			this.players[setIndex].setName(newName);
		} else {
			//TODO verify that no indexoutofbounds exception to be thrown- player[].length<=setIndex
			// Create a new player object
			players[setIndex] = new Player();
			this.players[setIndex].setName(newName);
		}
	}

//Map related methods
	/**
	 * @return the maxX
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * @param maxX the maxX to set
	 */
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	/**
	 * @return the maxY
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * @param maxY the maxY to set
	 */
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

//Display the settings
	public void displaySettings() {
		System.out.println("Beállítások kiírása");
		System.out.println("----------------------------");
		System.out.printf("Pálya mérete: %dx%d%n", maxX, maxY);
		System.out.println("");
		System.out.println("Játékosok száma: " + players.length);
		int counter = 1;
		for (Player actualPlayer : players) {
			//validate if reference is not null
			if (actualPlayer != null) {
				System.out.printf("%d. játékos neve:%s%n", counter++, actualPlayer.getName());
			} else {
				System.out.printf("%d. játékosnak még nincs neve%n", counter++);
			}
		}
		System.out.println("");
		System.out.println("Hajók száma: " + getTotalNumberOfShips());
		for (int i = 0; i < getAvailableShipSizes().length; i++) {
			System.out.printf("%d-as méretű hajóból van:%d%n", getAvailableShipSizes()[i], getShipCountPerSize()[i]);
		}
		System.out.println("\nBeállítások kiírásának vége");
		System.out.println("----------------------------");
	}
}
