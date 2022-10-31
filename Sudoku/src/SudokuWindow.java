package sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class SudokuWindow extends Application {
	private TextField[][] field;
	private Sudoku sod;
	private HBox root;
	private TilePane pane;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		sod = new Sudoku();
		primaryStage.setTitle("Sudoku Solver");
		root = new HBox();

		// Creating a board for the Sudoku
		pane = new TilePane();
		pane.setPrefColumns(3);
		pane.setPrefRows(4);
		pane.setStyle(
				"-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding: 2;");
		field = new TextField[9][9];
		redrawAll(pane);
		root.getChildren().add(pane);

		// Adding buttons
		HBox buttonBox = new HBox();
		Button quit = new Button("Quit");
		quit.setOnAction(event -> {
			if (Dialogs.confirmDialog("Confirm", "Quit?", "Are you sure you want to quit?")) {
				System.exit(0);
			}
		});
		buttonBox.getChildren().add(quit);
		Button solve = new Button("Solve");
		solve.setOnAction(event -> solveSudoku());
		buttonBox.getChildren().add(solve);
		Button clear = new Button("Clear");
		clear.setOnAction(event -> clearSudoku());
		buttonBox.getChildren().add(clear);
		root.getChildren().add(buttonBox);

		// Displaying board
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	/**
	 * Tries to solve the Sudoku with the given inputs in every textField. If
	 * input is solvable then a solution will will be presented.
	 **/
	private void solveSudoku() {
		if (checkInput()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					String res = field[i][j].getText();
					if (res.compareTo("") == 0) {
						res = "0";
					}
					int conv = Integer.valueOf(res);
					sod.setValue(i, j, conv);

				}
			}
			if (sod.solve()) {
				redrawAll(pane);
				Dialogs.alert("Congratulations!", "Your Sudoku was solvable", "Here is a solution");
			} else {
				Dialogs.alert("Failure!", "Failure!", "Your Sudoku was not solvable");
			}
		}
	}

	/**
	 * Clears the Sudoku and the input of every TextField to initial conditions.
	 **/
	private void clearSudoku() {
		if (Dialogs.confirmDialog("Confirm", "Clear Sudoku?", "Are you sure you want to clear the Sudoku?")) {
			;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sod.setValue(i, j, 0);
				}
			}
			redrawAll(pane);
		}
	}

	/** Updates the display with the current values from the Sudoku **/
	private void redrawAll(TilePane pane) {
		pane.getChildren().clear();
		// Adding 3x3 TilePanes to the input TilePane
		for (int bC = 0; bC < 3; bC++) {
			for (int bR = 0; bR < 3; bR++) {
				TilePane box = new TilePane();
				box.setPrefColumns(3);
				box.setPrefHeight(3);
				if ((bC + bR) % 2 == 0) {
					box.setStyle(
							"-fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding: 2;");
				}
				// Adding 3x3 TextFields to every TilePane, total of 81
				// TextFields
				for (int column = 0; column < 3; column++) {
					for (int row = 0; row < 3; row++) {
						if (sod.getValue(row + 3 * bR, column + 3 * bC) == 0) {
							field[row + bR * 3][3 * bC + column] = new TextField();
						} else {
							field[row + bR * 3][3 * bC + column] = new TextField(
									String.valueOf(sod.getValue(row + 3 * bR, column + 3 * bC)));
						}
						field[row + bR * 3][3 * bC + column].setPrefSize(50, 50);
						field[row + bR * 3][3 * bC + column].centerShapeProperty();
						if ((bC + bR) % 2 == 0) {
							field[row + bR * 3][3 * bC + column]
									.setStyle("-fx-background-color: black, -fx-control-inner-background");
						}
						box.getChildren().add(field[row + bR * 3][3 * bC + column]);
					}
				}
				pane.getChildren().add(box);
			}
		}
	}

	/**
	 * Checks if the inputs in every TextField follows the rules of a Sudoku
	 * solution
	 **/
	private boolean checkInput() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String res = field[i][j].getText();
				if (res.compareTo("") == 0) {
					continue;
				}
				try {
					int conv = Integer.valueOf(res);
					// Check whether input is acceptable
					if (conv <= 0 || conv > 9) {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException E) {
					Dialogs.alert("Error", null, "Invalid input: \"" + res + "\" is not a correct input");
					return false;
				}

			}
		}
		return true;
	}

}



