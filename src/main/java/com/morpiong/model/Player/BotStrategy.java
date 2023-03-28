package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.visitor.SelectVisitor;

/**
 * Une stratégie de jeu implémentant un joueur bot qui choisit aléatoirement une case non sélectionnée.
 */
public class BotStrategy extends PlayableStrategy {

    /**
     * Crée une nouvelle instance de {@code BotStrategy} avec l'image de forme spécifiée.
     *
     * @param urlImageShape l'URL de l'image de forme à utiliser
     */
    public BotStrategy(String urlImageShape){
        super(urlImageShape);
    }

    /**
     * Choisit une case aléatoire non sélectionnée parmi toutes les cases fournies et la sélectionne.
     *
     * @param cases la matrice des cases
     */
    @Override
    public void chooseCase(Case[][] cases) {
        Case caseToChoose = null;
        while(caseToChoose == null) {
            int randIndex = (int) (Math.random() * 3);
            int randJIndex = (int) (Math.random() * 3);
            if (!cases[randIndex][randJIndex].isSelectionned()) {
                caseToChoose = cases[randIndex][randJIndex];
            }
        }
        for(Case[] rowCase: cases) {
            for (Case simpleCase : rowCase) {
                simpleCase.getPane().setOnMouseClicked(null);
            }
        }
        caseToChoose.accept(new SelectVisitor());
    }
}
