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
import main.MainForm;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StartForm {
    private final Scanner inputReader = new Scanner(System.in);
    private final DecimalFormat decimalFormat = new DecimalFormat("0.0");

    public StartForm(String difficulty) {

        easy = false;
        normal = false;
        hard = false;

        toBeSearched = new Random().nextInt(100);

        switch (difficulty) {
            case "Easy":
                easy = true;
                boxHeight = 40;
                boxWidth = 82.5f;
                boxHorizontal = 10;
                boxVertical = 10;
                movesLeft = 10;

                buttonArray = new Button[100];
//                intArray = IntStream.range(0, 101).toArray();

//            button.setPrefWidth(boxWidth);
//            button.setPrefHeight(boxHeight);
//            button.setCursor(Cursor.HAND);

                IntStream.range(1, 101).forEach(e -> {
                    buttonArray[e - 1] = new Button(String.valueOf(e));
                    buttonArray[e - 1].setPrefWidth(boxWidth);
                    buttonArray[e - 1].setPrefHeight(boxHeight);
                    buttonArray[e - 1].setCursor(Cursor.HAND);
                    buttonArray[e - 1].setStyle("-fx-background-color: #123456; -fx-text-fill: #FFFFFF");

                    buttonArray[e - 1].setOnAction(j -> {
                        pickedANumber(Integer.parseInt(buttonArray[e - 1].getText()));
                    });
                });

//                System.out.println(Arrays.toString(buttonArray));
            case "Normal":
                normal = true;
                boxes = 200;
            case "Hard":
                hard = true;
                boxes = 300;
        }

        i = 0;

        System.out.println("TO BE SEARCHED: " + toBeSearched);
    }

    private Button[] buttonArray;

    private boolean easy;
    private boolean normal;
    private boolean hard;
    private int boxes;
    private int boxHorizontal;
    private int boxVertical;
    private int i;
    private int movesLeft;

    private final int toBeSearched;

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
    private HBox topRightHBox;
    private VBox buttonsVBox;
    private Button startButton;
    private Button restartButton;
    private Button giveUpButton;
    private Button mainMenuButton;
    private StackPane questionMarkStackPane;;
    private Label titleLabel;

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
//            System.out.println(buttonsVBox.getChildren().get(i) + " " + i);
        }

        return buttonsVBox;
    }

    private HBox createHBoxes() {
        buttonsHBox = new HBox();
        buttonsHBox.setPadding(new Insets(0.5, 5, 0, 5));
        buttonsHBox.setSpacing(1);

        for (int j = 0; j < boxHorizontal; j++, i++) {
            buttonsHBox.getChildren().add(buttonArray[i]);
//            System.out.println(buttonsHBox.getChildren().get(j) + " " + j);
        }

        return buttonsHBox;
    }

    private void pickedANumber(int pressedValue) {
        if (binarySearch(pressedValue)) {
            System.out.println("Correct Answer");
        }

        else {

        }
    }

    private boolean binarySearch(int pressedValue) {
        if (pressedValue == toBeSearched) {
            return true;
        }

        System.out.println("VALUE: " + pressedValue);

        int row = (pressedValue >= 10
                ? pressedValue % 10 == 0 ? pressedValue / 10 : pressedValue / 10 + 1
                : 1);
        int column = (int) (((pressedValue / 10f) - (row - 1)) * 10);

        System.out.println("ROW: " + row);
        System.out.println("COLUMN : " + column);

        int k = pressedValue - 1;
        System.out.println("K : " + k);
        if (pressedValue < toBeSearched) {
            System.out.println("AGAIN");
            for (int i = row; i >= 0 && k >= 0; i--) {
                for (int j = row == i ? column : 10; j >= 0 && k >= 0; j--, k--) {
                    buttonArray[k].setDisable(true);
                    System.out.println(buttonArray[k] + " " + k);
                }
                System.out.println("\nNEXT LINE");
            }
        }

        else {
            System.out.println("SIZE: " + buttonsHBox.getChildren().size());
            for (int i = row; i <= buttonsVBox.getChildren().size(); i++) {
                for (int j = row == i ? column - 1 : 0; j <= buttonsHBox.getChildren().size() && k < buttonArray.length; j++, k++) {
                    buttonArray[k].setDisable(true);
                }
            }
        }

        return false;
    }

    private HBox createTopRightHBox() {
        topRightHBox = new HBox();

        return topRightHBox;
    }

    private HBox createTopCenterHBox() {
        topCenterHBox = new HBox();
        topCenterHBox.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(topCenterHBox, Priority.ALWAYS);

        titleLabel = new Label("Guess The Number: ");
        titleLabel.setFont(Font.font("Arial", FontWeight.LIGHT, 28));
        titleLabel.setStyle("-fx-text-fill: #43D8C9");

        topCenterHBox.getChildren().add(titleLabel);

        return topCenterHBox;
    }

    private HBox createTopHBox() {
        topHbox = new HBox();
        topHbox.setPadding(new Insets(10));

        topHbox.getChildren().addAll(createTopCenterHBox(), createTopRightHBox());

        return topHbox;
    }

    private HBox createBottomRightHBox(Stage window) {
        bottomRightHBox = new HBox();
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

        bottomRightHBox.getChildren().add(mainMenuButton);

        return bottomRightHBox;
    }

    private void restart() {

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

        giveUpButton = new Button("Give Up");
        giveUpButton.setPrefWidth(150);
        giveUpButton.setPrefHeight(50);
        giveUpButton.setCursor(Cursor.HAND);

        restartButton = new Button("Restart");
        restartButton.setPrefWidth(150);
        restartButton.setPrefHeight(50);
        restartButton.setCursor(Cursor.HAND);

        restartButton.setOnAction(e -> {
            restart();
        });

        bottomLeftHBox.getChildren().addAll(startButton, giveUpButton, restartButton);

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
        bottomBorderPane.setStyle("-fx-background-color: #654321");

        bottomBorderPane.setTop(createBottomHBox(window));

        return bottomBorderPane;
    }

    private BorderPane createBorderPane(Stage window) {
        borderPane = new BorderPane();

        borderPane.setTop(createTopHBox());
        borderPane.setCenter(createBoxes());
        borderPane.setBottom(createBottomBorderPane(window));

//        System.out.print("DISABLE WHICH: ");
//        int which = inputReader.nextInt();
//
//        int row = (which >= 10 ? which % 10 == 0 ? which / 10 : which / 10 + 1 : 1) - 1;
//        System.out.println("ROW: " + row);
//
////        float column = Float.parseFloat(decimalFormat.format()));
//        int column = (int) (((which / 10f) - row) * 10);
//
//        System.out.println("COLUMN: " + column);
//
//        int alternateRow = Integer.parseInt(String.valueOf(String.valueOf(which).charAt(0)));

//        System.out.println("ALTERNATE ROW: " + alternateRow);
//
//        int alternateColumn = String.valueOf(which).length() == 2 ?
//                Integer.parseInt(String.valueOf(String.valueOf(which).charAt(1))) : 0;
//
//        System.out.println("ALTERNATE COLUMN: " + alternateColumn);

        return borderPane;
    }
}
