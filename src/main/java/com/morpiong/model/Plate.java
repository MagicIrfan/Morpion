package com.morpiong.model;

import com.morpiong.model.Factory.CaseFactory;
import com.morpiong.view.CasePane;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 La classe Plate représente le plateau du jeu. Elle contient des cases et initialise le plateau.
 */
public class Plate {

    /**
     Le pane qui contient le plateau.
     */
    @FXML
    private Pane platePane;

    /**
     Le tableau de cases représentant le plateau de jeu.
     */
    private Case[][] cases;

    /**
     Initialise le plateau de jeu en créant un tableau de cases et en les ajoutant au pane.
     */
    public void initialize() {
        int MAX_SIZE = 3;
        this.cases = new Case[MAX_SIZE][MAX_SIZE];
        for (int index = 0; index < MAX_SIZE; index++) {
            for (int JIndex = 0; JIndex < MAX_SIZE; JIndex++) {
                this.cases[index][JIndex] = CaseFactory.create(index,JIndex);
                ((GridPane) platePane).add(new CasePane(), index, JIndex);
            }
        }
    }

    /**
     Renvoie le tableau de cases représentant le plateau de jeu.
     @return le tableau de cases représentant le plateau de jeu
     */
    public Case[][] getCases() {
        return this.cases;
    }

    /**
     Renvoie le plateau du jeu.
     @return le plateau du jeu
     */
    public Pane getPlatePane(){
        return this.platePane;
    }
}
