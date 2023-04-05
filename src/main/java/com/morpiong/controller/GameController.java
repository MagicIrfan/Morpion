package com.morpiong.controller;

import com.morpiong.model.GameModel;
import com.morpiong.model.Plate;
import com.morpiong.model.Player.BotStrategy;
import com.morpiong.model.Player.PlayerStrategy;
import com.morpiong.utils.ImageUtils;
import com.morpiong.model.Case;
import com.morpiong.view.CasePane;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Objects;

/**
 * Contrôleur de la vue principale du jeu de morpion.
 */
public class GameController {
    @FXML
    public Pane gamePane;
    @FXML
    public Label playerTurnLabel;
    @FXML
    public ImageView playerShape;
    private GameModel model;
    private boolean playerIsBot;
    private boolean opponentIsBot;
    private Plate plate;

    /**
     * Initialise la vue principale du jeu.
     */
    public void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/morpiong/plate-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.plate = loader.getController();
        root.setLayoutY(159);
        gamePane.getChildren().add(root);
        this.model = new GameModel(plate);
        this.model.setPlayerStrategy(playerIsBot ? new BotStrategy(ImageUtils.O) : new PlayerStrategy(ImageUtils.O));
        this.model.setOpponentStrategy(opponentIsBot ? new BotStrategy(ImageUtils.X) : new PlayerStrategy(ImageUtils.X));
        this.playerShape.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(this.model.getPlayer().getUrlShape()))));
        this.createBindings();
    }

    /**
     * Définit si le joueur est un bot.
     *
     * @param isBot vrai si le joueur est un bot, faux sinon.
     */
    public void setPlayerIsBot(boolean isBot) {
        this.playerIsBot = isBot;
    }

    /**
     * Définit si l'adversaire est un bot.
     *
     * @param isBot vrai si l'adversaire est un bot, faux sinon.
     */
    public void setOpponentIsBot(boolean isBot) {
        this.opponentIsBot = isBot;
    }

    /**
     * Crée les bindings pour les cases du plateau de jeu.
     */
    private void createBindings() {
        Case[][] cases = plate.getCases();
        for (Case[] rowCase : cases) {
            for (Case simpleCase : rowCase) {
                simpleCase.selectionnedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        int x = simpleCase.getXCoord();
                        int y = simpleCase.getYCoord();
                        Node node = (this.plate.getPlatePane()).getChildren().stream()
                                .filter(n -> GridPane.getRowIndex(n) == x && GridPane.getColumnIndex(n) == y)
                                .findFirst()
                                .orElse(null);
                        this.model.onTurnFinished(simpleCase,(Pane) node);
                        if(!this.model.gameFinishedProperty().get()){
                            this.model.onBeginTurn();
                            this.playerShape.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(this.model.getActivePlayer().getUrlShape()))));
                        }
                    }
                });
            }
        }
        // Afficher un message de fin de jeu si la partie est terminée
        this.model.gameFinishedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.model.onGameFinished(gamePane);
            }
        });
        this.playerTurnLabel.textProperty().bind(Bindings.concat("Au tour du joueur ", this.model.playerTurnProperty()));
        this.model.getPlayer().chooseCase(plate);
    }
}