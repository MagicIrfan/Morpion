package com.morpiong.controller;

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

/**
 * Contrôleur de la vue du menu principal.
 */
public class MainMenuController {

    /**
     * Bouton pour lancer une partie contre un autre joueur.
     */
    @FXML
    public Button playButton;

    /**
     * Bouton pour quitter l'application.
     */
    @FXML
    public Button quitButton;

    /**
     * Label pour afficher le titre du jeu.
     */
    @FXML
    public Label titleLabel;

    /**
     * Bouton pour lancer une partie contre un bot.
     */
    @FXML
    public Button playBotButton;

    /**
     * Pane du menu principal.
     */
    @FXML
    public Pane mainMenuPane;

    /**
     * Pane des options.
     */
    @FXML
    public Pane optionsPane;

    /**
     * Lance une partie contre un autre joueur.
     * @param actionEvent Événement déclenché par l'utilisateur.
     * @throws IOException Si une erreur survient lors du chargement de la vue du jeu.
     */
    @FXML
    public void onPlayButton(ActionEvent actionEvent) throws IOException {
        this.play(actionEvent,false);
    }

    /**
     * Ferme l'application.
     * @param actionEvent Événement déclenché par l'utilisateur.
     */
    @FXML
    public void onQuitButton(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Lance une partie contre un bot.
     * @param actionEvent Événement déclenché par l'utilisateur.
     * @throws IOException Si une erreur survient lors du chargement de la vue du jeu.
     */
    @FXML
    public void onPlayBotButton(ActionEvent actionEvent) throws IOException {
        this.play(actionEvent,true);
    }

    /**
     * Charge la vue du jeu et initialise le contrôleur de la vue.
     * @param actionEvent Événement déclenché par l'utilisateur.
     * @param isPlayingWithBot Indique si l'utilisateur joue contre un bot.
     * @throws IOException Si une erreur survient lors du chargement de la vue du jeu.
     */
    private void play(ActionEvent actionEvent, boolean isPlayingWithBot) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/morpiong/game-view.fxml"));
        Parent root = loader.load();
        GameController controller = loader.getController();
        controller.setPlayingWithBot(isPlayingWithBot);
        controller.initialize();
        Scene mainMenuScene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }
}
