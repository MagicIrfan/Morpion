package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.visitor.SelectVisitor;
import javafx.application.Platform;

/**
 * Une stratégie de jeu implémentant un joueur bot qui choisit aléatoirement une case non sélectionnée.
 */
public class BotStrategy extends PlayableStrategy {

    private Thread botThread;

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
        botThread = new Thread(() -> {
            Platform.runLater(() -> {
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
                try {
                    Thread.sleep(1000); // attendre une seconde
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                caseToChoose.accept(new SelectVisitor());
            });
        });
        botThread.start();
    }
}
