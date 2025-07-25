package org.example;

import java.util.Objects;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Main extends Application {

	/* globally available */
	private Stage stage;
	private Scene curr_Scene;
	private boolean check_if_clear_needed = false;

	/* Scaling */
	private final double WIDTH = 500;
	private final double HEIGHT = 600;
	private final double NUMBER_ROW_HEIGHT = HEIGHT / 5;
	private final double NUMBER_ROW_WIDTH = WIDTH / 4;

	/* Icon from: https://www.flaticon.com/free-icon/calculator_1011863 */
	private final Image logo = new Image("https://shorturl.at/uParh");

	/* contains all number and operation rows */
	private final VBox calculator = new VBox();

	/* TextArea functions as a display for input/output */
	private final TextArea display_input_and_solution = new TextArea();

	/* HBoxes for the buttons */
	private HBox input_and_solution;
	private HBox operations_row;
	private HBox first_number_row;
	private HBox second_number_row;
	private HBox third_number_row;
	private HBox fourth_number_row;

	/* Buttons for number input */
	private final Button b0 = new Button("0");
	private final Button b1 = new Button("1");
	private final Button b2 = new Button("2");
	private final Button b3 = new Button("3");
	private final Button b4 = new Button("4");
	private final Button b5 = new Button("5");
	private final Button b6 = new Button("6");
	private final Button b7 = new Button("7");
	private final Button b8 = new Button("8");
	private final Button b9 = new Button("9");

	/* Buttons for operations */
	private final Button b_plus  = new Button("+");
	private final Button b_minus  = new Button("-");
	private final Button b_division  = new Button("/");
	private final Button b_multiplication  = new Button("*");
	private final Button b_comma  = new Button(",");
	private final Button b_root  = new Button("root"); // TODO: root sign
	private final Button b_pow  = new Button("pow"); // TODO: pow sign
	private final Button b_enter = new Button("=");
	private final Button b_delete = new Button("del"); // TODO: delete sign
	private final Button b_left_bracket = new Button("("); // TODO: Add this button
	private final Button b_right_bracket = new Button(")");
	private final Button b_clear = new Button("C"); // TODO: Add this button

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		init_calculator_ui();

		/* Stage setup */
		this.stage = primaryStage;
		this.stage.setTitle("");
		this.stage.setResizable(false);
		this.stage.setWidth(WIDTH);
		this.stage.setHeight(HEIGHT);
		this.stage.getIcons().add(this.logo);

		/* Scene setup */
		this.curr_Scene = new Scene(displayCalculator());
		this.curr_Scene.getStylesheets().add(Objects.requireNonNull(
			getClass().getResource("/style.css")).toExternalForm());


		this.stage.setScene(this.curr_Scene);
		this.stage.show();
	}

	/* returns the calculator */
	private VBox displayCalculator() {
		return this.calculator;
	}

	/* initializes the calculator */
	private void init_calculator_ui(){
		init_first_number_row();
		init_second_number_row();
		init_third_number_row();
		init_fourth_number_row();

		init_operations_row();

		init_textarea();

		init_calculator();
	}

	/* init calculator */
	private void init_calculator() {
		this.calculator.setPrefWidth(WIDTH);
		this.calculator.setPrefHeight(HEIGHT);
		this.calculator.getChildren().addAll(this.input_and_solution, this.operations_row,
			this.first_number_row, this.second_number_row,
			this.third_number_row, this.fourth_number_row
		);
	}

	/* init TextArea field */
	private void init_textarea() {
		this.display_input_and_solution.setEditable(false);
		this.display_input_and_solution.setWrapText(true);
		this.display_input_and_solution.setFocusTraversable(false);
		this.display_input_and_solution.setPrefWidth(WIDTH);
		this.display_input_and_solution.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.display_input_and_solution.getStyleClass().add("my-textarea");

		this.input_and_solution = new HBox(this.display_input_and_solution);
		this.input_and_solution.setPrefWidth(WIDTH);
		this.input_and_solution.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	/* init operations row */
	private void init_operations_row() {
		this.b_plus.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_plus.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_plus);
		button_pressed_action_for_operations_and_numbers(this.b_plus, "+");

		this.b_minus.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_minus.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_minus);
		button_pressed_action_for_operations_and_numbers(this.b_minus, "-");

		this.b_multiplication.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_multiplication.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_multiplication);
		button_pressed_action_for_operations_and_numbers(this.b_multiplication, "*");

		this.b_division.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_division.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_division);
		button_pressed_action_for_operations_and_numbers(this.b_division, "/");

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
		button_pressed_action_for_operations_and_numbers(this.b1, "1");

		this.b2.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b2.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b2);
		button_pressed_action_for_operations_and_numbers(this.b2, "2");

		this.b3.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b3.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b3);
		button_pressed_action_for_operations_and_numbers(this.b3, "3");

		this.b_root.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_root.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_root);
		button_pressed_action_for_operations_and_numbers(this.b_root, "sqrt(");

		this.first_number_row = new HBox(this.b1, this.b2, this.b3, this.b_root);
		this.first_number_row.setPrefWidth(WIDTH);
		this.first_number_row.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	/* init second number row */
	private void init_second_number_row() {
		this.b4.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b4.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b4);
		button_pressed_action_for_operations_and_numbers(this.b4, "4");

		this.b5.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b5.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b5);
		button_pressed_action_for_operations_and_numbers(this.b5, "5");

		this.b6.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b6.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b6);
		button_pressed_action_for_operations_and_numbers(this.b6, "6");

		this.b_pow.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_pow.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_pow);
		button_pressed_action_for_operations_and_numbers(this.b_pow, "^2");

		this.second_number_row = new HBox(this.b4, this.b5, this.b6, this.b_pow);
		this.second_number_row.setPrefWidth(WIDTH);
		this.second_number_row.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	/* init third number row */
	private void init_third_number_row() {
		this.b7.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b7.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b7);
		button_pressed_action_for_operations_and_numbers(this.b7, "7");

		this.b8.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b8.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b8);
		button_pressed_action_for_operations_and_numbers(this.b8, "8");

		this.b9.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b9.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b9);
		button_pressed_action_for_operations_and_numbers(this.b9, "9");

		this.b_delete.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_delete.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_delete);
		button_pressed_action_for_operations_and_numbers(this.b_delete, "del");

		this.third_number_row = new HBox(this.b7, this.b8, this.b9, this.b_delete);
		this.third_number_row.setPrefWidth(WIDTH);
		this.third_number_row.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	/* init fourth number row */
	private void init_fourth_number_row() {
		this.b0.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b0.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b0);
		button_pressed_action_for_operations_and_numbers(this.b0, "0");

		this.b_comma.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_comma.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_comma);
		button_pressed_action_for_operations_and_numbers(this.b_comma, ".");

		this.b_enter.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_enter.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_enter);
		button_pressed_action_for_operations_and_numbers(this.b_enter, "=");

		this.b_right_bracket.setPrefHeight(NUMBER_ROW_HEIGHT);
		this.b_right_bracket.setPrefWidth(NUMBER_ROW_WIDTH);
		style_button(this.b_right_bracket);
		button_pressed_action_for_operations_and_numbers(this.b_right_bracket, ")");

		this.fourth_number_row = new HBox(this.b_comma, this.b0, this.b_right_bracket, this.b_enter);
		this.fourth_number_row.setPrefWidth(WIDTH);
		this.fourth_number_row.setPrefHeight(NUMBER_ROW_HEIGHT);
	}

	/* styling for buttons using css (resources/style.css) */
	private void style_button(Button b) {
		b.getStyleClass().add("my-button");
	}

	/* function to handle all button events */
	private void button_pressed_action_for_operations_and_numbers(Button b, String action) {
		b.setOnAction((event) -> {
			/* User pressed equals button */
			if(b.equals(this.b_enter))
				handle_enter_action();

			/* User pressed delete button */
			else if(b.equals(this.b_delete))
				handle_delete_action();

			/* User pressed other button */
			else
				handle_button(action);
		});
	}

	/* help functions for button events */
	private void handle_button(String action) {
		if(this.check_if_clear_needed)
			this.display_input_and_solution.clear();
		this.check_if_clear_needed = false;
		this.display_input_and_solution.appendText(action);
	}

	private void handle_delete_action() {
		String input = this.display_input_and_solution.getText();
		if(input.isEmpty())
			return;
		char[] input_as_char = input.toCharArray();
		char[] sol = new char[input_as_char.length-1];

		/*
		same as: (copy array but without the last index)
		for(int i = 0; i < input_as_char.length-1; i++) {
			sol[i] = input_as_char[i];
		}
		*/
		System.arraycopy(input_as_char, 0, sol, 0, input_as_char.length - 1);

		this.display_input_and_solution.setText(String.valueOf(sol));
	}

	private void handle_enter_action() {
		String input = this.display_input_and_solution.getText();
		try {
			Expression exp = new ExpressionBuilder(input).build();
			this.display_input_and_solution.clear();
			this.display_input_and_solution.setText(String.valueOf(exp.evaluate()));
			this.check_if_clear_needed = true;
		} catch (Exception e) {
			System.err.println("Error calculating the solution: " + e.getMessage());
		}
	}
}