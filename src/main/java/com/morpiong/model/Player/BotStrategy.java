package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.Plate;
import com.morpiong.model.Symbol;
import com.morpiong.view.CasePane;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * BotStrategy est une classe abstraite qui étend PlayableStrategy et qui représente la stratégie d'un bot.
 * Cette classe est destinée à être héritée et implémentée par des classes qui définissent des stratégies spécifiques pour des bots.
 */
public abstract class BotStrategy extends PlayableStrategy {

    /**
     Thread exécuté par le bot.
     */
    protected Thread botThread;

    /**
     Constructeur de la classe BotStrategy.
     @param symbol le symbole joué par le bot
     */
    protected BotStrategy(Symbol symbol) {
        super(symbol);
    }

    /**
     Initialise le thread du bot en désactivant les cases de la grille de jeu.
     @param plate la grille de jeu
     */
    protected void initialiseBotThread(Plate plate){
        Case[][] cases = plate.getCases();
        // Désactiver toutes les autres cases
        GridPane platePane = (GridPane) plate.getPlatePane();
        Platform.runLater(() -> {
            for (Case[] rowCase : cases) {
                for (Case simpleCase : rowCase) {
                    int x = simpleCase.getXCoord();
                    int y = simpleCase.getYCoord();
                    Node node = platePane.getChildren().stream()
                            .filter(n -> GridPane.getRowIndex(n) == x && GridPane.getColumnIndex(n) == y)
                            .findFirst()
                            .orElse(null);
                    if (node instanceof CasePane) {
                        CasePane casePane = (CasePane) node;
                        casePane.setOnMouseClicked(null);
                    }
                }
            }
        });
    }

    /**
     * Renvoie une case aléatoire parmi celles disponibles dans le plateau de jeu.
     *
     * @param cases les cases du plateau de jeu
     * @return une case aléatoire disponible
     */
    protected Case getRandomMove(Case[][] cases){
        Case caseToChoose = null;
        while (caseToChoose == null) {
            int randIndex = (int) (Math.random() * 3);
            int randJIndex = (int) (Math.random() * 3);
            if (cases[randIndex][randJIndex].isEmpty()) {
                caseToChoose = cases[randIndex][randJIndex];
            }
        }
        return caseToChoose;
    }
}
