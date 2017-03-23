package torpedo;

import java.util.Scanner;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class UserInput {
//Ask for a valid character and a valid number as Input and returns it in a string
//Valid characters/numbers are based on the size of the map

	static boolean debug = false;

	static boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) < 48 || s.charAt(i) > 57) {
				return false;
			}
		}
		//Only reach here if all characters passed in the string is a number
		return true;
	}

	/**
	 * Asks for user input from the keyboard. Validates the input based on the provided map size. First character must be a valid letter, second character must be a valid number.
	 * X and Y coordinates start from value 1 and not 0!
	 * Example: A3
	 * A is the Y coordinate, value is "1"
	 * 3 is the X coordinate, value is "3"
	 * @param m
	 * @return int[0] = the X position, int[1] = the Y position
	 */
	public static int[] userInput(Map m) {
		boolean isInputValid = false;
		int[] returnCoords = new int[2];
		//If Y coordinates are greater than 10, then input length is expected to be 3 chars A10
		//Only the Y coordinate is relevant as it is going to be a number
		int maxNumberDigits = (m.getEndY() < 10) ? 2 : 3;
		if (debug) {
			System.out.println("max Number Digits: " + maxNumberDigits);
		}
		int yCharMin = 65; //Always start with 'A'
		int yCharMax = yCharMin + m.getEndY() - 1; //-1, mert a számozás 1-től indul, nem 0-tól
		Scanner input = new Scanner(System.in);
		String inputString; //Declare outside of do loop so that it will retain its value
		do {
			System.out.printf("Hová lősz? [%s-%s][%d-%d]:",
					String.valueOf((char) yCharMin),
					String.valueOf((char) yCharMax),
					m.getStartX(),
					m.getEndX()
			);
			inputString = input.next();
			//Check input length. It must be either 2 or 3 character long
			if (inputString.length() > maxNumberDigits || inputString.length() < 2) {
				if (debug) {
					System.out.printf("inputString.lenght:%d%nexpectedLength:%n", inputString.length(), maxNumberDigits);
				}
				System.out.printf("Hiba! A bevitt adat egy betű %s-%s és egy szám %d-%d között legyen!%n",
						String.valueOf((char) yCharMin),
						String.valueOf((char) yCharMax),
						m.getStartX(),
						m.getEndX()
				);
				continue; //To skip further validation
			}
			//Check first character in input is a valid character
			if (inputString.toUpperCase().charAt(0) < (char) yCharMin || inputString.toUpperCase().charAt(0) > (char) yCharMax) {
				System.out.println("Hiba!");
				System.out.printf("Az első betűnek %c-%c között kell lennie", (char) yCharMin, (char) yCharMax);
				continue; //To make sure validation runs again
			} else if (debug) {
				System.out.println("Első karakter validálás sikeres");
			}

			//Check if numbers are correct A1-A99
			//Numbers can be 1 or 2 digits
			//Check if the input is a valid number
			int inputNumber = -1; //Value must change if a valid number was entered
			switch (inputString.length()) {
				case 2: //Number has 1 digit
					if (isNumber(inputString.substring(1, 2))) {
						inputNumber = Integer.parseInt(inputString.substring(1, 2));
					}
					break;
				case 3: //Number has 2 digits
					if (debug) {
						System.out.printf("isNumber:" + isNumber(inputString.substring(1, 3)));
					}
					if (isNumber(inputString.substring(1, 3))) {
						inputNumber = Integer.parseInt(inputString.substring(1, 3));
					}
					break;
				case 1: //Input string to short or long
					System.out.println("Hiba! A bevitt adat túl kevés karaktert tartalmaz!");
					break;
				default:
					System.out.println("Hiba! A bevitt adat túl sok karaktert tartalmaz!");
					break;
			}
			if (debug) {
				System.out.printf("Input String lenght:%d%nInput Number: %d%n", inputString.length(), inputNumber);
			}
			if (inputNumber < m.getStartX() || inputNumber > m.getEndX()) {
				System.out.printf("Hiba! A számnak %d-%d között kell lennie!%n", m.getStartX(), m.getEndX());
			} else {
				if (debug) {
					System.out.println("Input number valid.");
				}

				//Returns the X coordinate based on the input number
				returnCoords[0] = inputNumber;

				//Returns the Y coordinate, which is based on the LETTER
					//The letter A should be the LAST number
					//If the character is A, unicod of A is 65, return code should be 1 (minY is 1!!)
					//If the character is B, unicode of B is 66, returnCoord should be 2
				returnCoords[1] = inputString.substring(0, 1).toUpperCase().charAt(0) - 64;
				isInputValid = true;
				debug=false;
				if (debug){
					System.out.println("Value of Y coord: " + returnCoords[1]);
				}
			}
		} while (!isInputValid);
		debug=false;
		if (debug) {
			System.out.println("User Input Method's return coords[] are");
			System.out.printf("X=returnCoords[0]:%d%nY=returnCoords[1]:%d%n", returnCoords[0], returnCoords[1]);
		}
		return returnCoords;
	}
//For testing purposes only

	public static void main(String[] args) {
		Map m = new Map(1, 5, 1, 20);
//		Map m = new Map(1, 20, 1, 20);
		userInput(m);
	}
}
