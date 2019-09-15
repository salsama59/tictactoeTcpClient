package com.internal.tictactoe.managers;

import com.internal.tictactoe.constants.GridConstants;
import com.internal.tictactoe.enums.PlayerIdEnum;
import com.internal.tictactoe.utils.GridUtils;

/**
 * This class purpose is to calculate wich player win the game by verifying the grid combinations.
 * @author sauly
 */
public class GameRulesManager {

	/**
	 * Calculate an return the designated winner of the game
	 * @param grid the game grid to check wich player won
	 * @param playerIdEnum the player id enum
	 * @return the player id who has won.
	 */
	public int getTheWinnerId(String[][] grid, PlayerIdEnum playerIdEnum) {

		int winnerId = 0;

		winnerId = verifyWinnerByRow(grid, playerIdEnum);

		if(winnerId != 0) {
			return winnerId;
		}

		winnerId = verifyWinnerByColumn(grid, playerIdEnum);

		if(winnerId != 0) {
			return winnerId;
		}

		winnerId = verifyWinnerByDiagonals(grid, playerIdEnum);

		return winnerId;
	}

	/**
	 * Verify if a player won by checking the game grid rows
	 * @param grid the game grid
	 * @param playerIdEnum the player id enum
	 * @return the player id who has won for a row match.
	 */
	private int verifyWinnerByRow(String[][] grid, PlayerIdEnum playerIdEnum) {

		String playerSymbol = null;

		if(playerIdEnum.ordinal() == 1) {
			playerSymbol = GridConstants.PLAYER_1_SYMBOL;
		}
		else if(playerIdEnum.ordinal() == 2) {
			playerSymbol = GridConstants.PLAYER_2_SYMBOL;
		}
		int columnsCount = 0;

		for(int row = 0; row < GridUtils.getGridRowLength(grid); row++){
			for(int column = 0; column < GridUtils.getGridColumnLength(grid); column++){
				if(grid[row][column].equals(playerSymbol)) {
					columnsCount += 1;
					if((column == GridUtils.getGridColumnLength(grid) - 1) && this.isTargetColumnSymbolCountReached(grid, columnsCount)) {
						return playerIdEnum.ordinal();
					}
				}
			}
		}

		return PlayerIdEnum.NO_PLAYER.ordinal();

	}

	/**
	 * Verify if a player won by checking the game grid columns
	 * @param grid the game grid
	 * @param playerIdEnum the player id enum
	 * @return the player id who has won for a column match.
	 */
	private int verifyWinnerByColumn(String[][] grid, PlayerIdEnum playerIdEnum) {

		String playerSymbol = null;

		if(playerIdEnum.ordinal() == 1) {
			playerSymbol = GridConstants.PLAYER_1_SYMBOL;
		}
		else if(playerIdEnum.ordinal() == 2) {
			playerSymbol = GridConstants.PLAYER_2_SYMBOL;
		}

		int rowsCount = 0;

		for(int column = 0; column < GridUtils.getGridColumnLength(grid); column++){
			for(int row = 0; row < GridUtils.getGridRowLength(grid); row++){
				if(grid[row][column].equals(playerSymbol)) {
					rowsCount += 1;

					if((row == GridUtils.getGridRowLength(grid) - 1) && this.isTargetRowSymbolCountReached(grid, rowsCount)) {
						return playerIdEnum.ordinal();
					}
				}
			}
		}

		return PlayerIdEnum.NO_PLAYER.ordinal();

	}

	/**
	 * Verify if a player won by checking the game grid diagonals
	 * @param grid the game grid
	 * @param playerIdEnum the player id enum
	 * @return the player id who has won for one of the diagonals match.
	 */
	private int verifyWinnerByDiagonals(String[][] grid, PlayerIdEnum playerIdEnum) {

		String playerSymbol = null;

		if(playerIdEnum.ordinal() == 1) {
			playerSymbol = GridConstants.PLAYER_1_SYMBOL;
		}
		else if(playerIdEnum.ordinal() == 2) {
			playerSymbol = GridConstants.PLAYER_2_SYMBOL;
		}

		int cellCount = 0;
		int maximumDiagonalLength = GridUtils.getGridColumnLength(grid);
		int diagonalCount = 0;

		//Looking through the grid for the first diagonal
		while(diagonalCount < maximumDiagonalLength) {

			if(grid[diagonalCount][diagonalCount].equals(playerSymbol)) {
				cellCount++;
			}

			if(cellCount == maximumDiagonalLength) {
				return playerIdEnum.ordinal();
			}

			diagonalCount++;
		}

		int horizontalDiagonalCount = maximumDiagonalLength - 1;
		int verticalDiagonalCount = 0;
		cellCount = 0;

		//Looking through the grid for the second diagonal
		while(horizontalDiagonalCount >= 0 && verticalDiagonalCount < maximumDiagonalLength) {

			if(grid[horizontalDiagonalCount][verticalDiagonalCount].equals(playerSymbol)) {
				cellCount++;
			}

			if(cellCount == maximumDiagonalLength) {
				return playerIdEnum.ordinal();
			}

			horizontalDiagonalCount--;
			verticalDiagonalCount++;
		}

		return PlayerIdEnum.NO_PLAYER.ordinal();

	}

	private boolean isTargetRowSymbolCountReached(String[][] grid, int countValue) {
		return GridUtils.getGridRowLength(grid) == countValue;
	}

	private boolean isTargetColumnSymbolCountReached(String[][] grid, int countValue) {
		return GridUtils.getGridColumnLength(grid) == countValue;
	}
}
