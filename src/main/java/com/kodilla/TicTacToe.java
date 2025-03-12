package com.kodilla;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private final Image imageback = new Image("file:src/main/resources/images/background.png");
    private Image crossImg = new Image("file:src/main/resources/images/cross.png");
    private Image noughtImg = new Image("file:src/main/resources/images/nought.png");
    private FlowPane cards = new FlowPane(Orientation.HORIZONTAL);

    public static void main(String[] args) {
        launch(args);

//        GameLogic gameLogic = new GameLogic();
//        gameLogic.run();
    }

    private Stage primaryStage;
    private Scene menuScene, gameScene;
    private int boardSize = 3;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Kółko i krzyżyk - Menu");

        // Tworzymy układ menu
        VBox menuLayout = new VBox(15);
        menuLayout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Wybierz rozmiar planszy:");
        ComboBox<Integer> boardSizeCombo = new ComboBox<>();
        // Dodajemy wartości od 3 do 10
        for (int i = 3; i <= 10; i++) {
            boardSizeCombo.getItems().add(i);
        }
        boardSizeCombo.setValue(3);  // wartość domyślna

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            boardSize = boardSizeCombo.getValue();
            createGameScene();
            primaryStage.setScene(gameScene);
        });

        menuLayout.getChildren().addAll(titleLabel, boardSizeCombo, startButton);
        menuScene = new Scene(menuLayout, 300, 200);

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    private void createGameScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        // Dynamiczne generowanie przycisków na planszy
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                // Przykładowa logika – przy kliknięciu ustawiamy "X"
                button.setOnAction(e -> button.setText("X"));
                grid.add(button, col, row);
            }
        }

        // Ustalanie rozmiaru sceny na podstawie liczby pól
        int sceneSize = boardSize * 110; // 100 na przycisk + 10 marginesu
        gameScene = new Scene(grid, sceneSize, sceneSize);
        gameScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
    }

}
