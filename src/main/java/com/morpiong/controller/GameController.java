package com.morpiong.controller;

import com.morpiong.model.GameModel;
import com.morpiong.model.Plate;
import com.morpiong.model.visitor.CaseVisitor;
import com.morpiong.model.visitor.DrawVisitor;
import com.morpiong.utils.SceneChangerUtils;
import com.morpiong.model.AlertBuilder;
import com.morpiong.model.Case;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    public Pane gamePane;
    private GameModel model;

    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/morpiong/plate-view.fxml"));
        Parent root = loader.load();
        Plate plate = loader.getController();
        gamePane.getChildren().add(root);
        this.model = new GameModel(plate.getCases());
        this.createBindings();
    }

    private void createBindings(){
        Case[][] cases = this.model.getCases();
        for(Case[] rowCase: cases){
            for(Case simpleCase : rowCase){
                simpleCase.getPane().setOnMouseClicked((MouseEvent event) ->{
                    if(!simpleCase.selectionnedProperty().get()){
                        simpleCase.accept(new CaseVisitor(simpleCase.getPane()));
                    }
                    this.model.addTurn();
                });
                simpleCase.selectionnedProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue){
                        simpleCase.setPair(this.model.getNbMoves()%2==0);
                        simpleCase.accept(new DrawVisitor(simpleCase.getPane()));
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
                Alert alert = new AlertBuilder()
                        .setTitle("Fin de partie")
                        .setContentText(message)
                        .setType(Alert.AlertType.INFORMATION)
                        .setOnCloseRequest(e -> {
                            Stage stage = (Stage) ((Alert) e.getSource()).getDialogPane().getScene().getWindow();
                            stage.close();
                            try {
                                SceneChangerUtils.changeScene(gamePane,"/com/morpiong/mainmenu-view.fxml");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .build();
                alert.showAndWait();
            }
        });
    }
}