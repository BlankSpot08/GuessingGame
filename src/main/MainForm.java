package main;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import start.DifficultyForm;

public class MainForm extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Scene scene;
    private BorderPane mainBorderPane;
    private BorderPane bottomBorderPane;
    private HBox difficultiesHBox;
    private HBox rightBotHBox;
    private Button startButton;
    private Button quitButton;
    private VBox vBox;

    @Override
    public void start(Stage window) {
        window.setScene(scene(window));
        window.setTitle("Binary Search Project");
        window.setResizable(false);

        window.setOnCloseRequest(e -> {
            e.consume();

            closeProgram(window);
        });

        window.show();
    }

    private void closeProgram(Stage window) {
        AlertBox alertBox = new AlertBox(window.getX(), window.getY(), window.getHeight(), window.getWidth());

        BooleanProperty answer = alertBox.display("Exit Program", "Are you sure?");

        if (answer.getValue()) {
            window.close();
        }
    }

    public Scene scene(Stage window) {
        mainBorderPane = new BorderPane();

        mainBorderPane.setCenter(createVBox(window));

        scene = new Scene(mainBorderPane, 800, 600);
        scene.getStylesheets().add("Choo.css");

        return scene;
    }

    private VBox createVBox(Stage window) {
        vBox = new VBox();

        startButton = new Button("Start");
        startButton.setPrefWidth(200);
        startButton.setPrefHeight(50);
        startButton.setCursor(Cursor.HAND);

        startButton.setOnAction(e -> {
            DifficultyForm difficultyForm = new DifficultyForm();

            window.setScene(difficultyForm.scene(window));
        });

        quitButton = new Button("Quit");
        quitButton.setPrefWidth(200);
        quitButton.setPrefHeight(50);
        quitButton.setCursor(Cursor.HAND);

        quitButton.setOnAction(e -> {
            closeProgram(window);
        });

        vBox.getChildren().addAll(startButton, quitButton);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0, 0, 0, 0));

        return vBox;
    }
}
