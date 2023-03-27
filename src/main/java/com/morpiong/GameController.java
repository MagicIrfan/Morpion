package com.morpiong;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;

public class GameController {
    public Pane gamePane;
    private Plate plate;
    private GameModel model;

    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("plate-view.fxml"));
        Parent root = loader.load();
        this.plate = loader.getController();
        gamePane.getChildren().add(root);
        this.model = new GameModel(this.plate.getCases());
        this.createBindings();
    }

    private void createBindings(){
        Case[][] cases = this.model.getCases();
        for(Case[] rowCase: cases){
            for(Case simpleCase : rowCase){
                simpleCase.setPair(this.model.getNbMoves()%2==0);
                simpleCase.getPane().setOnMouseClicked((MouseEvent event) ->{
                    if(!simpleCase.selectionnedProperty().get()){
                        simpleCase.accept(new CaseVisitor(simpleCase.getPane()));
                    }
                    this.model.addTurn();
                });
                simpleCase.selectionnedProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue){
                        simpleCase.setPair(this.model.getNbMoves()%2==0);
                        simpleCase.getPane().setBackground(new Background(new BackgroundFill(
                                Color.valueOf(this.model.getNbMoves()%2==0 ? "#00FF00" : "#FF0000"),
                                null,
                                null
                        )));
                        this.model.checkFinishedGame();
                    }
                });
            }
        }
        // Afficher un message de fin de jeu si la partie est terminée
        this.model.gameFinishedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                String message = "";
                if(this.model.isWin()){
                    message = "Bravo, joueur " + (this.model.getNbMoves() % 2 == 0 ? "2" : "1") + " a gagné !";
                } else if(this.model.isDraw()) {
                    message = "Match nul.";
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fin de partie");
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }
}