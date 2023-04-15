package com.morpiong.controller;

import com.morpiong.model.Difficult;
import com.morpiong.utils.SceneChangerUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Contrôleur pour la vue de choix de difficulté.
 */
public class ChooseDifficultController {

    @FXML
    private Button easyButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button returnButton;

    private Difficult difficult;

    private boolean isPlayerBot;

    private boolean isOpponentBot;

    /**
     Appelé lorsque le bouton "Facile" est cliqué.
     Définit la difficulté du jeu à "Facile" et lance la partie.
     @param actionEvent l'événement de clic sur le bouton "Facile"
     @throws IOException si une erreur se produit lors du chargement de la vue de jeu
     */
    @FXML
    public void onEasyButton(ActionEvent actionEvent) throws IOException {
        this.difficult = Difficult.EASY;
        this.play(actionEvent);
    }

    /**
     Appelé lorsque le bouton "Difficile" est cliqué.
     Définit la difficulté du jeu à "Difficile" et lance la partie.
     @param actionEvent l'événement de clic sur le bouton "Difficile"
     @throws IOException si une erreur se produit lors du chargement de la vue de jeu
     */
    @FXML
    public void onHardButton(ActionEvent actionEvent) throws IOException {
        this.difficult = Difficult.HARD;
        this.play(actionEvent);
    }

    /**
     Appelé lorsque le bouton "Retour" est cliqué.
     Charge la vue du menu principal.
     @param actionEvent l'événement de clic sur le bouton "Retour"
     @throws IOException si une erreur se produit lors du chargement de la vue du menu principal
     */
    @FXML
    public void onReturnButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/morpiong/mainmenu-view.fxml"));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        Pane pane = (Pane) ((Node) actionEvent.getSource()).getParent();
        SceneChangerUtils.getInstance().changeScene(pane, root);
    }

    /**
     Retourne la difficulté choisie pour le jeu.
     @return la difficulté du jeu
     */
    public Difficult getDifficult(){
        return difficult;
    }

    /**
     Définit si le joueur est un bot ou non.
     @param isPlayerBot true si le joueur est un bot, false sinon
     */
    public void setPlayerBot(boolean isPlayerBot){
        this.isPlayerBot = isPlayerBot;
    }

    /**
     Définit si l'adversaire est un bot ou non.
     @param isOpponentBot true si l'adversaire est un bot, false sinon
     */
    public void setOpponentBot(boolean isOpponentBot){
        this.isOpponentBot = isOpponentBot;
    }

    /**
     * Charge la vue du jeu et initialise le contrôleur de la vue du jeu avec les informations de la partie à jouer.
     * @param actionEvent l'événement de clic sur le bouton de lancement de la partie.
     * @throws IOException si une erreur se produit lors du chargement de la vue du jeu.
     */
    private void play(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/morpiong/game-view.fxml"));
        Parent root = loader.load();
        GameController controller = loader.getController();
        controller.setPlayerIsBot(isPlayerBot);
        controller.setOpponentIsBot(isOpponentBot);
        controller.setDifficult(difficult);
        controller.initialize();
        Pane pane = (Pane) ((Node) actionEvent.getSource()).getParent();
        SceneChangerUtils.getInstance().changeScene(pane, root);
    }

}
