package org.example;

import java.util.Objects;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Main extends Application {

	private Stage stage;
	private Scene curr_Scene;

	/* Icon from: https://www.flaticon.com/free-icon/calculator_1011863 */
	private Image icon = new Image("https://shorturl.at/uParh");

	/* Scaling */
	private final double WIDTH = 500;
	private final double HEIGHT = 600;
	private final double NUMBER_ROW_HEIGHT = HEIGHT / 5;
	private final double NUMBER_ROW_WIDTH = WIDTH / 4;

	private TextArea display_input_and_solution = new TextArea();

	/* contains all number and operation rows */
	private VBox calculater = new VBox();

	/* HBoxes for the buttons */
	private HBox input_and_solution;
	private HBox operations_row;
	private HBox first_number_row;
	private HBox second_number_row;
	private HBox third_number_row;
	private HBox fourth_number_row;

	/* Buttons for number input */
	private Button b1 = new Button("1");
	private Button b2 = new Button("2");
	private Button b3 = new Button("3");
	private Button b4 = new Button("4");
	private Button b5 = new Button("5");
	private Button b6 = new Button("6");
	private Button b7 = new Button("7");
	private Button b8 = new Button("8");
	private Button b9 = new Button("9");
	private Button b0 = new Button("0");
	private Button lol = new Button("lol");

	/* Buttons for operations */
	private Button b_plus  = new Button("+");
	private Button b_minus  = new Button("-");
	private Button b_division  = new Button("/");
	private Button b_multiplication  = new Button("*");
	private Button b_comma  = new Button(",");
	private Button b_square  = new Button("root");
	private Button b_pow  = new Button("pow");
	private Button b_enter = new Button("enter");

	public static void main(String[] args) {
		launch(args);
	}

	public void init_rows(){
		init_first_number_row();
		init_second_number_row();
		init_third_number_row();
		init_fourth_number_row();

		init_operations_row();

		/* init TextArea field */
		this.display_input_and_solution.setEditable(false);
		this.display_input_and_solution.setPrefWidth(WIDTH);
		this.display_input_and_solution.setPrefHeight(NUMBER_ROW_HEIGHT);

		this.input_and_solution = new HBox(this.display_input_and_solution);
		this.input_and_solution.setPrefWidth(WIDTH);
		this.input_and_solution.setPrefHeight(NUMBER_ROW_HEIGHT);

		/* init calculator */
		this.calculater.setPrefWidth(WIDTH);
		this.calculater.setPrefHeight(HEIGHT);
		this.calculater.getChildren().addAll(this.input_and_solution, this.operations_row,
			this.first_number_row, this.second_number_row,
			this.third_number_row, this.fourth_number_row
		);
	}

	@Override
	public void start(Stage primaryStage) {
		init_rows();

		/* Stage setup */
		this.stage = primaryStage;
		this.stage.setTitle("");
		this.stage.setResizable(false);
		this.stage.setWidth(WIDTH);
		this.stage.setHeight(HEIGHT);
		this.stage.getIcons().add(this.icon);

		/* Scene setup */
		this.curr_Scene = new Scene(createContent());
		this.curr_Scene.getStylesheets().add(Objects.requireNonNull(
			getClass().getResource("/style.css")).toExternalForm());


		this.stage.setScene(this.curr_Scene);
		this.stage.show();
	}

	private VBox createContent() {
		return this.calculater;
	}

	/* init operations row */
	private void init_operations_row() {
		this.b_plus.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_plus.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_plus);

		this.b_minus.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_minus.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_minus);

		this.b_multiplication.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_multiplication.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_multiplication);

		this.b_division.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_division.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_division);

		this.operations_row = new HBox(this.b_plus,
			this.b_minus, this.b_multiplication, this.b_division
		);
		this.operations_row.setPrefWidth(WIDTH);
		this.operations_row.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	/* init first number row */
	private void init_first_number_row() {
		this.b1.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b1.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b1);

		this.b2.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b2.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b2);

		this.b3.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b3.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b3);

		this.b_square.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_square.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_square);

		this.first_number_row = new HBox(this.b1, this.b2, this.b3, this.b_square);
		this.first_number_row.setPrefWidth(WIDTH);
		this.first_number_row.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	/* init second number row */
	private void init_second_number_row() {
		this.b4.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b4.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b4);

		this.b5.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b5.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b5);

		this.b6.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b6.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b6);

		this.b_pow.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_pow.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_pow);

		this.second_number_row = new HBox(this.b4, this.b5, this.b6, this.b_pow);
		this.second_number_row.setPrefWidth(WIDTH);
		this.second_number_row.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	/* init third number row */
	private void init_third_number_row() {
		this.b7.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b7.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b7);

		this.b8.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b8.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b8);

		this.b9.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b9.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b9);

		this.third_number_row = new HBox(this.b7, this.b8, this.b9);
		this.third_number_row.setPrefWidth(WIDTH);
		this.third_number_row.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	/* init fourth number row */
	private void init_fourth_number_row() {
		this.b0.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b0.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b0);

		this.b_comma.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_comma.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_comma);

		this.lol.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.lol.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.lol);

		this.b_enter.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_enter.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_enter);

		this.fourth_number_row = new HBox(this.b_comma, this.b0, this.lol, this.b_enter);
		this.fourth_number_row.setPrefWidth(WIDTH);
		this.fourth_number_row.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	private void style_button(Button b) {
		b.getStyleClass().add("my-button");
	}
}