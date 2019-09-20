package com.internal.tictactoe.ui;

import com.internal.tictactoe.utils.GridUtils;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GraphicUserInterfaceGenerator {

	String[][] dataGrid;

	public GraphicUserInterfaceGenerator(String[][] grid) {
		this.dataGrid = grid;
	}

	public void display(Stage primaryStage) throws Exception {

		GridPane pane = new GridPane(); 

		for (int i = 0; i < GridUtils.getGridColumnLength(dataGrid); i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(100.0 / GridUtils.getGridColumnLength(dataGrid));
			pane.getColumnConstraints().add(columnConstraints);
		}

		for (int i = 0; i < GridUtils.getGridRowLength(dataGrid); i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(100.0 / GridUtils.getGridRowLength(dataGrid));
			pane.getRowConstraints().add(rowConstraints);
		}

		for (int i = 0; i < GridUtils.getGridRowLength(dataGrid); i++) {
			for (int j = 0; j < GridUtils.getGridColumnLength(dataGrid); j++) {
				Text text = new Text(dataGrid[i][j]);
				pane.add(text, j, i);
				GridPane.setHalignment(text, HPos.CENTER);
			}
		}

		pane.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true; -fx-vgap: 1; -fx-hgap: 1; -fx-padding: 1");
		pane.setAlignment(Pos.CENTER); 
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		primaryStage.setScene(new Scene(borderPane, 300, 250));
		primaryStage.show();
	}

}