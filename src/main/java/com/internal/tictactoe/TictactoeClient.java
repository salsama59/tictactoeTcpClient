package com.internal.tictactoe;

import java.util.Scanner;

import com.internal.tictactoe.constants.GridConstants;
import com.internal.tictactoe.enums.CoordinateEnum;
import com.internal.tictactoe.enums.PlayerIdEnum;
import com.internal.tictactoe.utils.GridUtils;

public class TictactoeClient{

	public static void main(String[] args) {
		int rowLength = 4;
		int columnLength = 4;
		String [][] grid = new String [rowLength][columnLength];
		initializeGrid(grid);
		display(grid);

		int playerNumber = PlayerIdEnum.NO_PLAYER.ordinal();
		String symbol = null;
		StringBuilder stringBuilder = null;

		boolean isWrongValue = false;
		boolean isGameOver = false;

		Scanner scanner = null;

		while(!isGameOver) {

			stringBuilder = new StringBuilder();

			if(!isWrongValue) {
				if(playerNumber == PlayerIdEnum.NO_PLAYER.ordinal()) {
					playerNumber = PlayerIdEnum.PLAYER_1.ordinal();
					symbol = GridConstants.PLAYER_1_SYMBOL;
				}
				else if(playerNumber == PlayerIdEnum.PLAYER_1.ordinal()) {
					playerNumber = PlayerIdEnum.PLAYER_2.ordinal();
					symbol = GridConstants.PLAYER_2_SYMBOL;
				}
				else if(playerNumber == PlayerIdEnum.PLAYER_2.ordinal()) {
					playerNumber = PlayerIdEnum.PLAYER_1.ordinal();
					symbol = GridConstants.PLAYER_1_SYMBOL;
				}
			}

			stringBuilder.append("Player ").append(playerNumber).append(" it is your turn!!").append("\n")
			.append("You may choose between the following coordinates, for the rows : ").append(1).append(" to ").append(GridUtils.getGridRowLenth(grid)).append(".\n")
			.append("For the columns you may choose from ").append(1).append(" to ").append(GridUtils.getGridColumnLenth(grid)).append(".\n")
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
						isGameOver = true;
					}
				}
			}
		}
		
		scanner.close();
	}

	public static String [][] initializeGrid(String [][] grid){
		for(int row = 0; row < GridUtils.getGridRowLenth(grid); row++){
			for(int column = 0; column < GridUtils.getGridColumnLenth(grid); column++){
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
		for(int row = 0; row < GridUtils.getGridRowLenth(gridToDisplay); row++){
			for(int column = 0; column < GridUtils.getGridColumnLenth(gridToDisplay); column++){
				stringBuilder.append(GridConstants.GRID_SEPARATOR)
				.append(gridToDisplay[row][column]);
			}
			stringBuilder.append(GridConstants.GRID_SEPARATOR)
			.append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

}