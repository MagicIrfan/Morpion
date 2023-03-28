package com.morpiong.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public Button playButton;
    @FXML
    public Button quitButton;
    @FXML
    public Label titleLabel;
    @FXML
    public Button playBotButton;
    @FXML
    public void onPlayButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/morpiong/game-view.fxml"));
        Parent root = loader.load();
        GameController controller = new GameController(); // Créez une nouvelle instance du contrôleur en passant true comme paramètre
        controller.setIsBot(false);
        loader.setControllerFactory(c -> controller);
        Scene mainMenuScene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }
    @FXML
    public void onQuitButton(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void onPlayBotButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/morpiong/game-view.fxml"));
        Parent root = loader.load();
        GameController controller = new GameController(); // Créez une nouvelle instance du contrôleur en passant true comme paramètre
        controller.setIsBot(true);
        loader.setControllerFactory(c -> controller);
        Scene mainMenuScene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }
}
