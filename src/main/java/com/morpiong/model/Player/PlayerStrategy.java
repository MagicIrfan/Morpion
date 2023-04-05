package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.Plate;
import com.morpiong.model.visitor.*;
import com.morpiong.view.CasePane;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Cette classe représente la stratégie d'un joueur humain dans le jeu.
 * Elle permet à l'utilisateur de sélectionner une case lors de son tour.
 */
public class PlayerStrategy extends PlayableStrategy {

    /**
     * Constructeur de la classe PlayerStrategy.
     * @param urlImageShape l'URL de l'image de la forme du joueur.
     */
    public PlayerStrategy(String urlImageShape){
        super(urlImageShape);
    }

    /**
     * Choisit une case non sélectionnée parmi toutes les cases fournies et la sélectionne.
     *
     * @param plate le plateau du jeu
     */
    @Override
    public void chooseCase(Plate plate) {
        Case[][] cases = plate.getCases();
        GridPane platePane = (GridPane) plate.getPlatePane();
        for(Case[] rowCase: cases) {
            for (Case simpleCase : rowCase) {
                int x = simpleCase.getXCoord();
                int y = simpleCase.getYCoord();
                Node node = platePane.getChildren().stream()
                        .filter(n -> GridPane.getRowIndex(n) == x && GridPane.getColumnIndex(n) == y)
                        .findFirst()
                        .orElse(null);
                if (node instanceof CasePane) {
                    CasePane casePane = (CasePane) node;
                    casePane.setOnMouseClicked(event -> {
                        if (!simpleCase.selectionnedProperty().get()) {
                            simpleCase.accept(new SelectCaseVisitor());
                        }
                    });
                }
            }
        }
    }
}
