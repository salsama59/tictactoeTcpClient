package com.internal.tictactoe.utils;

import com.internal.tictactoe.constants.GridConstants;

/**
 * A utility class to calculate various informations like :
 * <ul>
 * 	<li>Verify grid validity</li>
 * 	<li>get row lenght and column lenght</li>
 * </ul>
 * @author syeponde
 */
public class GridUtils {

	/**
	 * Verify if the grid pass as parameter is valid by verifying it row length and it column length.
	 * @param grid the grid to validate
	 * @return true if the row length nor the column length is equals 0, return false otherwise.
	 */
	public static boolean isGridValid(String[][] grid) {
		if(getGridRowLength(grid) == 0) {
			return false;
		}

		if(getGridColumnLength(grid) == 0) {
			return false;
		}

		return true;
	}

	/**
	 * Get the grid pass as parameter row length
	 * @param grid the grid from whom the row length must be calculated
	 * @return the row length of the grid.
	 */
	public static int getGridRowLength(String[][] grid) {
		return grid.length;
	}

	/**
	 * Get the grid pass as parameter column length
	 * @param grid the grid from whom the column length must be calculated
	 * @return the column length of the grid.
	 */
	public static int getGridColumnLength(String[][] grid) {
		return grid[0].length;
	}

	/**
	 * Check if the grid is valid by the following means :
	 * <ul>
	 * 	<li>Verify that the row value is inside the grid intervals</li>
	 * 	<li>Verify that the column value is inside the grid intervals</li>
	 * 	<li>Verify that the coordinate has not already been chosen</li>
	 * </ul>
	 * @param grid the grid needed for the validation calculation
	 * @param rowIndex the row index to validate
	 * @param columnIndex the column index to validate
	 * @return true if the coordinate is valid, false otherwise.
	 */
	public static boolean isGridCoordinateValid(String[][] grid, int rowIndex, int columnIndex) {

		boolean isValid = true;

		if(rowIndex - 1 < 0 || (rowIndex - 1) > getGridRowLength(grid) - 1) {
			System.out.println("the provided row value " + rowIndex + " is not correct");
			isValid = false;
		}

		if(columnIndex - 1 < 0 || (columnIndex - 1) > getGridColumnLength(grid) - 1) {
			System.out.println("the provided column value " + columnIndex + " is not correct");
			isValid = false;
		}

		if(isValid) {
			String coordinateValue = grid[rowIndex - 1][columnIndex - 1];
			isValid = coordinateValue.equals(GridConstants.VOID_SYMBOL);

			if(!isValid) {
				System.out.println("the provided coordinate has already been chosen");
			}
		}

		return isValid;
	}

}
