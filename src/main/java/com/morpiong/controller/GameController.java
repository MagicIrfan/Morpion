package com.morpiong.controller;

import com.morpiong.model.GameModel;
import com.morpiong.model.Plate;
import com.morpiong.model.Player.BotStrategy;
import com.morpiong.model.Player.PlayerStrategy;
import com.morpiong.utils.ImageUtils;
import com.morpiong.utils.SceneChangerUtils;
import com.morpiong.model.builder.AlertBuilder;
import com.morpiong.model.visitor.*;
import com.morpiong.model.Case;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameController{
    @FXML
    public Pane gamePane;
    @FXML
    public Pane platePane;
    @FXML
    public Label playerTurnLabel;
    @FXML
    public ImageView playerShape;
    private GameModel model;
    private boolean isPlayingWithBot;

    public void initialize(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/morpiong/plate-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Plate plate = loader.getController();
        platePane.getChildren().add(root);
        this.model = new GameModel(plate.getCases());
        this.model.setPlayerStrategy(new PlayerStrategy(ImageUtils.O));
        this.model.setOpponentStrategy(isPlayingWithBot ? new BotStrategy(ImageUtils.X) : new PlayerStrategy(ImageUtils.X));
        this.playerShape.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(this.model.getPlayer().getUrlShape()))));
        this.createBindings();
    }

    public void setPlayingWithBot(boolean isBot){
        this.isPlayingWithBot = isBot;
    }
    private void createBindings(){
        Case[][] cases = this.model.getCases();
        for(Case[] rowCase: cases){
            for(Case simpleCase : rowCase){
                simpleCase.selectionnedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        this.model.onTurnFinished(simpleCase);
                        this.model.onBeginTurn();
                        this.playerShape.setImage(new Image(Objects.requireNonNull(this.model.getActivePlayer().getUrlShape())));
                    }
                });
            }
        }
        // Afficher un message de fin de jeu si la partie est terminée
        this.model.gameFinishedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                this.model.onGameFinished(gamePane);
            }
        });
        this.playerTurnLabel.textProperty().bind(Bindings.concat("Au tour du joueur ", this.model.playerTurnProperty()));
        this.model.getPlayer().chooseCase(cases);
    }
}