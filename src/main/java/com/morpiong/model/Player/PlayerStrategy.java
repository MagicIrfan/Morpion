package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.visitor.*;
import javafx.scene.input.MouseEvent;

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
     * Permet au joueur de choisir une case en cliquant dessus.
     * @param cases le tableau des cases du plateau de jeu.
     */
    @Override
    public void chooseCase(Case[][] cases) {
        for(Case[] rowCase: cases) {
            for (Case simpleCase : rowCase) {
                simpleCase.getPane().setOnMouseClicked((MouseEvent event) ->{
                    if(!simpleCase.selectionnedProperty().get()){
                        simpleCase.accept(new SelectCaseVisitor());
                    }
                });
            }
        }
    }
}
