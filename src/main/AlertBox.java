package main;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertBox {
    public AlertBox(Stage window) {
        mainWindowHeight = window.getHeight();
        mainWindowWidth = window.getWidth();
        mainWindowXCoor = window.getX();
        mainWindowYCoor = window.getY();

        answer = new SimpleBooleanProperty(this, "answer", false);
    }

    private final double mainWindowXCoor;
    private final double mainWindowYCoor;
    private final double mainWindowHeight;
    private final double mainWindowWidth;

    private Stage window;
    private Label messageLabel;
    private Button yesButton;
    private Button noButton;
    private VBox vBox;
    private HBox hBox;
    private Scene scene;

    private final BooleanProperty answer;

    public BooleanProperty display(String title, String message) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        messageLabel = new Label(message);
        messageLabel.setStyle("-fx-text-fill: #000000");

        vBox.getChildren().addAll(messageLabel, createHBox());

        scene = new Scene(vBox, 250, 100);
        scene.getStylesheets().add("Design.css");

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                answer.set(false);
                window.close();
            }
        });

        window.setScene(scene);
        window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        window.setX((mainWindowXCoor + mainWindowWidth) / 2);
        window.setY((mainWindowYCoor + mainWindowHeight) / 2);
        window.showAndWait();

        return answer;
    }

    private HBox createHBox() {
        hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        yesButton = new Button("Yes");
        yesButton.setPrefWidth(75);

        yesButton.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                answer.set(true);
                window.close();
            }
        });

        yesButton.setOnAction(e -> {
            answer.set(true);
            window.close();
        });

        noButton = new Button("No");
        noButton.setPrefWidth(75);

        noButton.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                answer.set(false);
                window.close();
            }
        });

        noButton.setOnAction(e -> {
            answer.set(false);
            window.close();
        });

        hBox.getChildren().addAll(yesButton, noButton);
        return hBox;
    }
}
