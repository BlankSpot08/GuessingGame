package start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.AlertBox;
import main.MainForm;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class GameForm {
    private final Random randomNumberGenerator = new Random();

    public GameForm(String difficulty, Stage window) {
        switch (difficulty) {
            case "Easy":
                setup(40, 10, 10, new Button[100]);
                break;
            case "Normal":
                setup(20, 12, 15, new Button[180]);
                break;
            case "Hard":
                setup(5, 20, 15, new Button[300]);
                break;
            default:
                break;
        }

        boxWidth = 82.5f;
        movesLeft = 8;
        index = 0;

        toBeSearched = randomNumberGenerator.nextInt(buttonArray.length) == 0 ? randomNumberGenerator.nextInt(buttonArray.length) : randomNumberGenerator.nextInt(buttonArray.length);
        System.out.println(toBeSearched);

        IntStream.range(1, buttonArray.length + 1).forEach(e -> {
            buttonArray[e - 1] = new Button(String.valueOf(e));
            buttonArray[e - 1].setPrefWidth(boxWidth);
            buttonArray[e - 1].setPrefHeight(boxHeight);
            buttonArray[e - 1].setCursor(Cursor.HAND);

            function.Button.hovering(buttonArray[e - 1]);

            buttonArray[e - 1].setOnAction(j -> {
                updateMovesLeft();

                if (!pickedANumber(Integer.parseInt(buttonArray[e - 1].getText()), window)) {
                    buttonArray[e - 1].setId("button-clicked");
                }

                // LOSE
                if (movesLeft == 0) {
                    AlertBox alertBox = new AlertBox(window);

                    alertBox.display("You Lost", "You Lost", "Stupid", "Stupid");

                    restart();
                }
            });
        });
    }

    private void setup(float boxHeight, float boxHorizontal, float boxVertical, Button[] buttonArray) {
        this.boxHeight = boxHeight;

        this.boxHorizontal = boxHorizontal;
        this.boxVertical = boxVertical;

        this.buttonArray = buttonArray;
    }

    private Button[] buttonArray;

    private float boxHorizontal;
    private float boxVertical;
    private int movesLeft;
    private int index;

    private int toBeSearched;

    private float boxWidth;
    private float boxHeight;

    private Scene scene;
    private BorderPane borderPane;
    private BorderPane bottomBorderPane;
    private HBox buttonsHBox;
    private HBox bottomHBox;
    private HBox bottomLeftHBox;
    private HBox bottomRightHBox;
    private HBox topHbox;
    private HBox topCenterHBox;
    private VBox buttonsVBox;
    private Button startButton;
    private Button restartButton;
    private Button giveUpButton;
    private Button mainMenuButton;
    private Label titleLabel;
    private Label movesLeftLabel;

    public Scene scene(Stage window) {
        scene = new Scene(createBorderPane(window), 800, 600);
        scene.getStylesheets().add("Design.css");

        return scene;
    }

    private VBox createBoxes() {
        buttonsVBox = new VBox();
        buttonsVBox.setPadding(new Insets(5, 5, 0, 5));
        buttonsVBox.setSpacing(1);

        for (int i = 0; i < boxVertical; i++) {
            buttonsVBox.getChildren().add(createHBoxes());
        }

        return buttonsVBox;
    }

    private HBox createHBoxes() {
        buttonsHBox = new HBox();
        buttonsHBox.setPadding(new Insets(0.5, 5, 0, 5));
        buttonsHBox.setSpacing(1);

        for (int j = 0; j < boxHorizontal; j++, index++) {
            buttonsHBox.getChildren().add(buttonArray[index]);
        }

        return buttonsHBox;
    }

    private boolean pickedANumber(int pressedValue, Stage window) {
        if (binarySearch(pressedValue)) {
            AlertBox alertBox = new AlertBox(window);
            alertBox.display("You won", "But no cookie for you", "Basic" , "Basic");

            restart();

            return true;
        }

        return false;
    }

    private boolean binarySearch(int pressedValue) {
        if (pressedValue == toBeSearched) {
            restart();
            return true;
        }

        int numberOfColumns = buttonsHBox.getChildren().size();
        int numberOfRows = buttonsVBox.getChildren().size();

        int row = (pressedValue >= numberOfColumns ?
                pressedValue % numberOfColumns == 0 ? pressedValue / numberOfColumns : pressedValue / numberOfColumns + 1
                : 1);

        int column = pressedValue - ((row - 1) * numberOfColumns);

        int k = pressedValue - 1;
        if (pressedValue < toBeSearched) {

            for (int i = row; i >= 0 && k >= 0; i--) {

                for (int j = row == i ? column : numberOfColumns; j >= 0 && k >= 0; j--, k--) {
                    buttonArray[k].setDisable(true);
                }
            }
        }

        else {

            for (int i = row; i <= numberOfRows; i++) {

                for (int j = row == i ? column - 1 : 0; j <= numberOfColumns && k < buttonArray.length; j++, k++) {
                    buttonArray[k].setDisable(true);
                }
            }
        }

        return false;
    }

    private HBox createTopCenterHBox() {
        topCenterHBox = new HBox();
        topCenterHBox.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(topCenterHBox, Priority.ALWAYS);

        titleLabel = new Label("Guess The Number: ");
        titleLabel.setFont(Font.font(28));

        topCenterHBox.getChildren().add(titleLabel);

        return topCenterHBox;
    }

    private HBox createTopHBox() {
        topHbox = new HBox();
        topHbox.setPadding(new Insets(10));

        topHbox.getChildren().addAll(createTopCenterHBox());

        return topHbox;
    }

    private void updateMovesLeft() {
        movesLeft--;

        movesLeftLabel.setText("Moves Left: " + movesLeft);
    }

    private HBox createBottomRightHBox(Stage window) {
        bottomRightHBox = new HBox();
        bottomRightHBox.setSpacing(12);
        bottomRightHBox.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(bottomRightHBox, Priority.ALWAYS);

        mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefWidth(150);
        mainMenuButton.setPrefHeight(50);
        mainMenuButton.setCursor(Cursor.HAND);

        mainMenuButton.setOnAction(e -> {
            MainForm mainForm = new MainForm();

            window.setScene(mainForm.scene(window));
        });

        function.Button.hovering(mainMenuButton);

        movesLeftLabel = new Label();
        movesLeftLabel.setFont(Font.font(22));
        movesLeftLabel.setId("score-custom");
        movesLeftLabel.setText("Moves Left: " + movesLeft);

        bottomRightHBox.getChildren().addAll(movesLeftLabel, mainMenuButton);

        return bottomRightHBox;
    }

    private void restart() {
        toBeSearched = randomNumberGenerator.nextInt(buttonArray.length) == 0 ? randomNumberGenerator.nextInt(buttonArray.length) : randomNumberGenerator.nextInt(buttonArray.length);

        Arrays.stream(buttonArray).forEach( e -> {
            e.setId("button-default");
            e.setDisable(false);
        });

        movesLeft = 8;

        movesLeftLabel.setText("Moves Left: " + movesLeft);
        movesLeftLabel.setId("score-custom");
    }

    private HBox createBottomLeftHBox() {
        bottomLeftHBox = new HBox();
        bottomLeftHBox.setSpacing(10);
        bottomLeftHBox.setAlignment(Pos.TOP_LEFT);
        HBox.setHgrow(bottomLeftHBox, Priority.ALWAYS);

        startButton = new Button("Start");
        startButton.setPrefWidth(150);
        startButton.setPrefHeight(50);
        startButton.setCursor(Cursor.HAND);

        function.Button.hovering(startButton);

        giveUpButton = new Button("Give Up");
        giveUpButton.setPrefWidth(150);
        giveUpButton.setPrefHeight(50);
        giveUpButton.setCursor(Cursor.HAND);

        giveUpButton.setOnAction(e -> {
            buttonArray[toBeSearched - 1].setId("button-giveup");
        });

        function.Button.hovering(giveUpButton);

        restartButton = new Button("Restart");
        restartButton.setPrefWidth(150);
        restartButton.setPrefHeight(50);
        restartButton.setCursor(Cursor.HAND);

        restartButton.setOnAction(e -> {
            restart();
        });

        function.Button.hovering(restartButton);

        bottomLeftHBox.getChildren().addAll(giveUpButton, restartButton);

        return bottomLeftHBox;
    }

    private HBox createBottomHBox(Stage window) {
        bottomHBox = new HBox();
        bottomHBox.setPadding(new Insets(10, 10, 10, 10));

        bottomHBox.getChildren().addAll(createBottomLeftHBox(), createBottomRightHBox(window));

        return bottomHBox;
    }

    private BorderPane createBottomBorderPane(Stage window) {
        bottomBorderPane = new BorderPane();

        bottomBorderPane.setTop(createBottomHBox(window));

        return bottomBorderPane;
    }

    private BorderPane createBorderPane(Stage window) {
        borderPane = new BorderPane();

        borderPane.setBottom(createBottomBorderPane(window));
        borderPane.setTop(createTopHBox());
        borderPane.setCenter(createBoxes());

        return borderPane;
    }
}