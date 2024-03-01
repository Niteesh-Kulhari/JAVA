/*
 * Submitted by Chaitanya Ramesh Pawar
 * CS570 B - data Structures
 * Stevens Institute of Technology | Hoboken, New Jersey
 * PairInt.java
 * */

public class PairInt {
	private int x;
	private int y;

	//Defining constructor for PairInt
	public PairInt(int x, int y) {

		this.x = x;
		this.y = y;
	}

	//Setting Get Set Methods
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Object p) {
		if (!(p instanceof PairInt)) {
			return false;
		}

		else {
			PairInt pairint = (PairInt) p;
			return this.x == pairint.x && this.y == pairint.y;
		}
	}

	public String toString() {
		return "[" + String.valueOf(x) + "," + String.valueOf(y) + "]";
	}

	public PairInt copy() {
		PairInt solution = new PairInt(x, y);
		return solution;

	}
}
