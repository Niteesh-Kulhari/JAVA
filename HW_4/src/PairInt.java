/*
 * Submitted by Niteesh Kulhari
 * CS570 B - data Structures
 * MazeTest.java
 * */

public class PairInt {
	private int x;
	private int y;

	//Constructor for our PairInt Class
	public PairInt(int x, int y) {

		this.x = x;
		this.y = y;
	}

	//Get Method for X
	public int getX() {
		return x;
	}
	//Get Method for Y
	public int getY() {
		return y;
	}
	// Set Method for X
	public void setX(int x) {
		this.x = x;
	}
	//Set Method for Y
	public void setY(int y) {
		this.y = y;
	}
	//Boolenan method to check equality
	public boolean equals(Object p) {
		if (!(p instanceof PairInt)) {
			return false;
		}

		else {
			PairInt pairint = (PairInt) p;
			return this.x == pairint.x && this.y == pairint.y;
		}
	}
	// Method to convert to string
	public String toString() {
		return "[" + String.valueOf(x) + "," + String.valueOf(y) + "]";
	}
	//Method to copy
	public PairInt copy() {
		PairInt solution = new PairInt(x, y);
		return solution;

	}
}
