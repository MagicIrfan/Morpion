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

/**
 * Contrôleur de la vue du menu principal.
 */
public class MainMenuController {

    /**
     * Bouton de lancement d'une partie contre un joueur humain.
     */
    @FXML
    public Button playButton;

    /**
     * Bouton de fermeture de l'application.
     */
    @FXML
    public Button quitButton;

    /**
     * Titre de la vue du menu principal.
     */
    @FXML
    public Label titleLabel;

    /**
     * Bouton de lancement d'une partie contre l'ordinateur.
     */
    @FXML
    public Button playBotButton;

    /**
     * Pane de la vue du menu principal.
     */
    @FXML
    public Pane mainMenuPane;

    /**
     * Pane de la vue des options.
     */
    @FXML
    public Pane optionsPane;

    /**
     * Lance une partie contre un joueur humain lorsque le bouton playButton est cliqué.
     * @param actionEvent l'événement de clic sur le bouton.
     * @throws IOException si une erreur se produit lors du chargement de la vue du jeu.
     */
    @FXML
    public void onPlayButton(ActionEvent actionEvent) throws IOException {
        this.play(actionEvent,false);
    }

    /**
     * Ferme l'application lorsque le bouton quitButton est cliqué.
     * @param actionEvent l'événement de clic sur le bouton.
     */
    @FXML
    public void onQuitButton(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        SceneChangerUtils.getInstance().shutdown();
    }

    /**
     * Lance une partie contre l'ordinateur lorsque le bouton playBotButton est cliqué.
     * @param actionEvent l'événement de clic sur le bouton.
     * @throws IOException si une erreur se produit lors du chargement de la vue du jeu.
     */
    @FXML
    public void onPlayBotButton(ActionEvent actionEvent) throws IOException {
        this.play(actionEvent,true);
    }

    /**
     * Charge la vue du jeu et initialise le contrôleur de la vue du jeu avec les informations de la partie à jouer.
     * @param actionEvent l'événement de clic sur le bouton de lancement de la partie.
     * @param isPlayingWithBot indique si la partie est jouée contre l'ordinateur ou contre un joueur humain.
     * @throws IOException si une erreur se produit lors du chargement de la vue du jeu.
     */
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
