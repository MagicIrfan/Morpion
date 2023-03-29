package com.morpiong.controller;

import com.morpiong.utils.SceneChangerUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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
    public Pane mainMenuPane;
    @FXML
    public Pane optionsPane;

    @FXML
    public void onPlayButton(ActionEvent actionEvent) throws IOException {
        this.play(actionEvent,false);
    }
    @FXML
    public void onQuitButton(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        SceneChangerUtils.getInstance().shutdown();
    }
    @FXML
    public void onPlayBotButton(ActionEvent actionEvent) throws IOException {
        this.play(actionEvent,true);
    }

    private void play(ActionEvent actionEvent, boolean isPlayingWithBot) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/morpiong/game-view.fxml"));
        Parent root = loader.load();
        GameController controller = loader.getController();
        controller.setPlayingWithBot(isPlayingWithBot);
        controller.initialize();
        Pane pane = (Pane) ((Node) actionEvent.getSource()).getParent();
        SceneChangerUtils.getInstance().changeScene(pane, root);
    }
}
