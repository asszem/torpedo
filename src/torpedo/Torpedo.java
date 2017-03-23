/*		//1. Új játék kezdése
		//2. Játék betöltése
		//3. Beállítások

1. Új játék kezdése - new Game objektum létrehozása
	Beállítások
		Játékosok száma
		Játékosok neve
		Settings kiírása
		Settings változtatása
		Settings elfogadása
	Játék indítása
		Térkép generálása
		Hajók generálása
	Játék menet
		Player n lő
		Visszajelzés
			Győzelem?
		Repeat until győzelem
	Játék vége
		Eredmény kiírása


BACKLOG

- Player can set their ship position at the beginning of the game
- More than two players. Every player will shoot at every other player's map.
- Save/load game
- AI player
- Multiple AI players playing against each other

 */
package torpedo;

import java.util.Random;
import java.util.Scanner;

public class Torpedo {

	public static void main(String[] args) {
		int testCase = 1; //0 - Default settings 1 - custom settings
		if (testCase == 0) {
			System.out.println("Default Settings test");
			Settings defaultSettings = new Settings();
			defaultSettings.displaySettings();
			Game defaultGame = new Game(defaultSettings);
			defaultGame.drawPlayerMap(0, true, true);

			//Wowcsi
			//A default game [0][0] hajójának a [0] koordinátán lévő Point objektumának ownerét a settingsben lévő player tömb 0. elemére állítja...
//			defaultGame.ships[0][0].getShipCoords()[0].setShipOwner(defaultSettings.getPlayers()[0]);
//			System.out.println("Ship owner: "
//					+ defaultGame.ships[0][0].getShipCoords()[0].getShipOwnerOnPoint().getName());
			defaultGame.attack();
		}

		if (testCase == 1) {
			System.out.println("Custom Settings test");
			Settings customSettings = new Settings();
			customSettings.setPlayersNumber(3);
			customSettings.setPlayerName(0, "Andras");
			customSettings.setPlayerName(1, "Barni");
//			int[] t = {1, 2, 3, 4};
			int[] t = {1};
			customSettings.setAvailableShipsizes(t);
			customSettings.setShipSizeCount(1, 1);
//			customSettings.setShipSizeCount(2, 1);
//			int totalShips = customSettings.getTotalNumberOfShips();
			customSettings.displaySettings();
			Game customGame = new Game(customSettings);
//			customGame.drawPlayerMap(0);
			boolean continueGame = true;
			do {
				customGame.attack();
				Scanner continueQuestion = new Scanner(System.in);
				System.out.println("Folytassuk? I/N");
				String continueInput = continueQuestion.next();
				if (continueInput.toUpperCase().equals("N")) {
					continueGame = false;
				}
			} while (continueGame);
			boolean quickAttackTest = false;
			//<editor-fold desc="Quick attack test">
			if (quickAttackTest) {
				Point[] shipCords = customGame.ships[0][7].getShipCoords();
				System.out.println("Printing out [0][7] ship coordinates");
				System.out.println("Point[] array's length: " + shipCords.length);
				for (Point p : shipCords) {
					System.out.println("Ship Owner: " + p.getShipOwnerOnPoint().getName());
					System.out.println("Ship array index: " + p.getShipsArrayIndex());
					System.out.printf("X=%d y=%d%n", p.getX(), p.getY());
				}
				System.out.println("Point objektumból kinyerni a többi koordinátát");
				Point[] shipCordsFromAPoint = customGame.ships[0][shipCords[0].getShipsArrayIndex()].getShipCoords();
				//Tudjuk meg, hogy a 3.4 ponton van-e hajó és ha igen, mik a koordinátái
//				int[] testCoords = {3,4};
				int[] testCoords = new int[2];
				//This will pick the first Point object for the last ship for player 0
				testCoords[0] = customGame.ships[0][7].getShipCoords()[0].getX();
				testCoords[1] = customGame.ships[0][7].getShipCoords()[0].getY();

				System.out.printf("Teszt koordináták: X=%d, Y=%d%n", testCoords[0], testCoords[1]);
				if (customGame.checkTarget(0, testCoords)) {
					System.out.println("Van hajó");
				} else {
					System.out.printf("Nincs hajó", testCoords[0], testCoords[1]);
				}
			}
			//</editor-fold>
		}

	}

}
