package torpedo;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Point {

	private int x;
	private int y;
	private boolean isShipThere;
	private boolean isShipHit;
	private Player shipOwner;
	private int shipsArrayIndex;

	Point(int startX, int startY) {
		x = startX;
		y = startY;
		isShipThere = true;
		isShipHit = false;
	}

	public void setShipsArrayIndex(int i) {
		shipsArrayIndex = i;
	}

	public int getShipsArrayIndex() {
		return shipsArrayIndex;
	}

	public boolean isShipHit() {
		return isShipHit;
	}

	public void setShipHit() {
		isShipHit = true;
	}

	public boolean isShipThere() {
		return isShipThere;
	}

	public void setShipThere(boolean isThereAShip) {
		isShipThere = isThereAShip;
	}

	public void setShipOwner(Player shipOwner) {
		this.shipOwner = shipOwner;
	}

	public Player getShipOwnerOnPoint() {
		return shipOwner;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int setX) {
		x = setX;
	}

	public void setY(int setY) {
		y = setY;
	}
}
