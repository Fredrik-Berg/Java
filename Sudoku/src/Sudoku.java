package sudoku;

import java.util.HashSet;

public class Sudoku {
	private int[][] matrix;

	/** Creates a empty Sudoku **/
	public Sudoku() {
		matrix = new int[9][9];
	}

	/** Returns the value of square i, j 
	 @param1 int i coordinate row
	 @param2 int j coordinate column
	 @return matrix value at postition i, j**/
	public int getValue(int i, int j) {
		return matrix[i][j];
	}

	/** Sets value k at i, j
	@param1 int i coordinate row
	 @param2 int j coordinate column
	 @return matrix value at postition i, j**/
	public void setValue(int i, int j, int k) {
		matrix[i][j] = k;
	}

	/**
	 * Checks to see whether the inputs of the Sudoku creates a solvable Sudoku
	 * or not
	 **/
	private boolean checkSolveAbleInput() {
		for (int i = 0; i < 9; i++) {
			// Checks all the rows and all the columns
			HashSet<Integer> set = new HashSet<>();
			HashSet<Integer> set1 = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				if (matrix[i][j] != 0) {
					if (!set.add(matrix[i][j])) {
						return false;
					}
				}
				if (matrix[j][i] != 0) {
					if (!set1.add(matrix[j][i])) {
						return false;
					}
				}
			}
		}
		// Checks the smaller 3x3 squares
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				HashSet<Integer> set = new HashSet<>();
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						if (matrix[i * 3 + k][j * 3 + l] != 0) {
							if (!set.add(matrix[i * 3 + k][j * 3 + l])) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Checks to see if the Sudoku. If Soduku is solvable it returns true,
	 * otherwise false. The int matrix will be updated with a possible solution
	 * @return true if sudoku is solvable, else false
	 **/
	public boolean solve() {
		if (!checkSolveAbleInput()) {
			return false;
		}
		return solve(0, 0);
	}

	/** Returns a string representation of the Sudoku 
	 * @return a string representation of the Sudoku**/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < 9; k++) {
			if (k % 3 == 0) {
				sb.append('\n');
				sb.append("--------------------");
			}
			sb.append('\n');
			for (int i = 0; i < 9; i++) {
				if (i % 3 == 0) {
					sb.append('|');
				}
				sb.append(matrix[k][i]);
				sb.append(' ');

			}

		}
		return sb.toString();
	}

	/**
	 * Tries all the possible numbers for square i, j, if no numbers where
	 * possible it returns false
	 **/
	private boolean solve(int i, int j) {
		if (j == 9 || i == 9) {
			return true;
		}
		boolean solvable = false;
		if (matrix[i][j] != 0) {
			if (j == 8) { // End of a row
				solvable = solve(i + 1, 0);
			} else {
				solvable = solve(i, j + 1);
			}
		} else {
			// Checks possible values for square i, j
			boolean[] temp = new boolean[10];
			for (int k = 0; k < 9; k++) {
				temp[matrix[k][j]] = true;
			}
			for (int k = 0; k < 9; k++) {
				temp[matrix[i][k]] = true;
			}
			for (int k = i - (i % 3); k < i - (i % 3) + 3; k++) {
				for (int l = j - (j % 3); l < j - (j % 3) + 3; l++) {
					temp[matrix[k][l]] = true;
				}
			}
			// Tries all possible values for square i, j
			for (int k = 1; k < temp.length; k++) {
				if (temp[k] == false) {
					matrix[i][j] = k;
					if (j == 8 && i == 8) {
						return true;
					} else if (j == 8) {
						if (solve(i + 1, 0)) {
							return true;
						}
					} else {
						if (solve(i, j + 1)) {
							return true;
						}
					}
				}

			}
			matrix[i][j] = 0;
		}
		return solvable;
	}

}