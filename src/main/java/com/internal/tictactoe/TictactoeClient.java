package com.internal.tictactoe;

import java.util.Scanner;

import com.internal.tictactoe.constants.GridConstants;
import com.internal.tictactoe.enums.CoordinateEnum;
import com.internal.tictactoe.enums.PlayerIdEnum;
import com.internal.tictactoe.managers.GameRulesManager;
import com.internal.tictactoe.utils.GridUtils;

public class TictactoeClient{
	
	public static final int MAXLENGTH = 5;

	public static void main(String[] args) {

		GameRulesManager gameRulesManager = new GameRulesManager();
		int rowLength = MAXLENGTH;
		int columnLength = MAXLENGTH;
		String [][] grid = new String [rowLength][columnLength];
		initializeGrid(grid);
		display(grid);
		PlayerIdEnum playerIdEnum = PlayerIdEnum.NO_PLAYER;
		String symbol = null;
		StringBuilder stringBuilder = null;

		boolean isWrongValue = false;
		boolean isGameOver = false;

		Scanner scanner = null;

		while(!isGameOver) {

			stringBuilder = new StringBuilder();

			if(!isWrongValue) {
				if(playerIdEnum.ordinal() == PlayerIdEnum.NO_PLAYER.ordinal()) {
					playerIdEnum = PlayerIdEnum.PLAYER_1;
					symbol = GridConstants.PLAYER_1_SYMBOL;
				}
				else if(playerIdEnum.ordinal() == PlayerIdEnum.PLAYER_1.ordinal()) {
					playerIdEnum = PlayerIdEnum.PLAYER_2;
					symbol = GridConstants.PLAYER_2_SYMBOL;
				}
				else if(playerIdEnum.ordinal() == PlayerIdEnum.PLAYER_2.ordinal()) {
					playerIdEnum = PlayerIdEnum.PLAYER_1;
					symbol = GridConstants.PLAYER_1_SYMBOL;
				}
			}

			stringBuilder.append("Player ").append(playerIdEnum.ordinal()).append(" it is your turn!!").append("\n")
			.append("You may choose between the following coordinates, for the rows : ").append(1).append(" to ").append(GridUtils.getGridRowLength(grid)).append(".\n")
			.append("For the columns you may choose from ").append(1).append(" to ").append(GridUtils.getGridColumnLength(grid)).append(".\n")
			.append("For example 1;1 or 1;0 etc.\n");

			System.out.println(stringBuilder.toString());

			scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			System.out.println();

			if(line != null && !line.isEmpty()) {
				String[] splitedLine = line.split(GridConstants.COORDINATE_SEPARATOR);

				if(splitedLine.length < 2) {
					System.out.println("The coordinates format are not correct");
					isWrongValue = true;
				}
				else {
					int targetRow = 0;
					int targetColumn = 0;
					try {
						targetRow = Integer.parseInt(splitedLine[CoordinateEnum.ROW.ordinal()]);
						targetColumn = Integer.parseInt(splitedLine[CoordinateEnum.COLUMN.ordinal()]);
						isWrongValue = !GridUtils.isGridCoordinateValid(grid, targetRow, targetColumn);
					} catch (NumberFormatException numberFormatException) {
						System.out.println("The coordinate must be composed of integer number");
						isWrongValue = true;
					}

					if(!isWrongValue) {
						grid = updateGrid(grid, targetRow, targetColumn, symbol);
						display(grid);
						int winnerId = gameRulesManager.getTheWinnerId(grid, playerIdEnum);

						if(winnerId != PlayerIdEnum.NO_PLAYER.ordinal()) {
							isGameOver = true;
							System.out.println("The winner is PLAYER " + winnerId + "!!!");
						}

					}
				}
			}
		}

		scanner.close();
	}

	public static String [][] initializeGrid(String [][] grid){
		for(int row = 0; row < GridUtils.getGridRowLength(grid); row++){
			for(int column = 0; column < GridUtils.getGridColumnLength(grid); column++){
				grid[row][column] = GridConstants.VOID_SYMBOL;
			}
		}
		return grid;
	}

	public static String [][] updateGrid(String [][] grid, int rowIndex, int columnIndex, String symbol){
		grid[rowIndex - 1][columnIndex - 1] = symbol;
		return grid;
	}

	public static void display(String [][] gridToDisplay) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int row = 0; row < GridUtils.getGridRowLength(gridToDisplay); row++){
			for(int column = 0; column < GridUtils.getGridColumnLength(gridToDisplay); column++){
				stringBuilder.append(GridConstants.GRID_SEPARATOR)
				.append(gridToDisplay[row][column]);
			}
			stringBuilder.append(GridConstants.GRID_SEPARATOR)
			.append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

}