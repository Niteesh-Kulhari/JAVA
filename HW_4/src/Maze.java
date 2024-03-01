/*
 * Submitted by Niteesh Kulhari
 * CS570 B - data Structures
 * Maze.java
 * */

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 */

public class Maze implements GridColors {
	/** The maze */
	private TwoDimGrid maze;

	public Maze(TwoDimGrid m) {
		maze = m;
	}

	/** Wrapper method. */
	public boolean findMazePath() {
		return findMazePath(0, 0); // (0, 0) is the start point.
	}

	/**
	 * Attempts to find a path through point (x, y).
	 * 
	 * @pre Possible path cells are in BACKGROUND color; barrier cells are in
	 *      ABNORMAL color.
	 * @post If a path is found, all cells on it are set to the PATH color; all
	 *       cells that were visited but are not on the path are in the TEMPORARY
	 *       color.
	 * @param x The x-coordinate of current point
	 * @param y The y-coordinate of current point
	 * @return If a path through (x, y) is found, true; otherwise, false
	 */

	// Added method for problem 1 here
	public boolean findMazePath(int x, int y) {
		// Base Condition for Recursive relation
		if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || maze.getColor(x, y) == TEMPORARY
				|| maze.getColor(x, y) == BACKGROUND) {
			return false;
		}
		
		// To check if the final block is reached
		else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			maze.recolor(x, y, PATH);
			return true;
		} else if (maze.getColor(x, y) == NON_BACKGROUND) {
			maze.recolor(x, y, TEMPORARY);
			// Recursive call with different parameters
			if (this.findMazePath(x + 1, y) || this.findMazePath(x - 1, y) || this.findMazePath(x, y + 1)
					|| this.findMazePath(x, y - 1)) {
				// Recoloring the block if it is a valid path
				maze.recolor(x, y, PATH);
				return true;
			}
		}

		return this.findMazePath(x + 1, y) || this.findMazePath(x - 1, y) || this.findMazePath(x, y + 1)
				|| this.findMazePath(x, y - 1);

	}

	// Added METHOD FOR PROBLEM 2 HERE
	@SuppressWarnings("unchecked")
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		// To check if inputs are within the limits
		if (!(x >= 0 && x < maze.getNCols() && y >= 0 && y < maze.getNRows())) {
			return;
		} else if (maze.getColor(x, y) != NON_BACKGROUND) {
			return;
		} else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			trace.push(new PairInt(x, y));
			ArrayList<PairInt> list = new ArrayList<PairInt>();
			list.addAll(trace);
			result.add(list);
			trace.clear();
		} else {
			trace.push(new PairInt(x, y));
			maze.recolor(x, y, PATH);
			findMazePathStackBased(x + 1, y, result, (Stack<PairInt>) trace.clone());
			findMazePathStackBased(x, y + 1, result, (Stack<PairInt>) trace.clone());
			findMazePathStackBased(x - 1, y, result, (Stack<PairInt>) trace.clone());
			findMazePathStackBased(x, y - 1, result, (Stack<PairInt>) trace.clone());
			maze.recolor(x, y, NON_BACKGROUND);
		}
	}

	// Method to find the possible ways to the the final state
	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
		ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
		Stack<PairInt> trace = new Stack<>();
		findMazePathStackBased(0, 0, result, trace);
		if (result.size() == 0) {
			ArrayList<PairInt> temp = new ArrayList<PairInt>();
			result.add(temp);
		}
		return result;
	}

	// ADDED METHOD FOR PROBLEM 3 HERE
	public ArrayList<PairInt> findMazePathMin(int x, int y) {
		ArrayList<ArrayList<PairInt>> result = findAllMazePaths(x, y);
		int intArray[] = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			intArray[i] = result.get(i).size();
		}
		int min = intArray[0];
		int minIndex = 0;
		for (int i = 1; i < intArray.length; i++) {
			if (intArray[i] < min) {
				min = intArray[i];
				minIndex = i;
			}
		}
		return result.get(minIndex);
	}

	/* <exercise chapter="5" section="6" type="programming" number="2"> */
	public void resetTemp() {
		maze.recolor(TEMPORARY, BACKGROUND);
	}
	/* </exercise> */

	/* <exercise chapter="5" section="6" type="programming" number="3"> */
	public void restore() {
		resetTemp();
		maze.recolor(PATH, BACKGROUND);
		maze.recolor(NON_BACKGROUND, BACKGROUND);
	}
	/* </exercise> */
}
