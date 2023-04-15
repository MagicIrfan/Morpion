package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.Plate;
import com.morpiong.model.visitor.SelectCaseVisitor;
import com.morpiong.model.Symbol;
import com.morpiong.view.CasePane;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * Une stratégie de jeu implémentant un joueur bot qui choisit aléatoirement une case non sélectionnée.
 */
public class BotStrategy extends PlayableStrategy {

    private Thread botThread;

    /**
     * Crée une nouvelle instance de {@code BotStrategy} avec l'image de forme spécifiée.
     *
     */
    public BotStrategy(Symbol symbol){
        super(symbol);
    }

    /**
     * Choisit une case aléatoire non sélectionnée parmi toutes les cases fournies et la sélectionne.
     *
     * @param plate le plateau du jeu
     */
    @Override
    public void chooseCase(Plate plate) {
        Case[][] cases = plate.getCases();
        GridPane platePane = (GridPane) plate.getPlatePane();
        // wait for one second
        botThread = new Thread(() -> {
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
            Case caseToChoose = null;
            while (caseToChoose == null) {
                int randIndex = (int) (Math.random() * 3);
                int randJIndex = (int) (Math.random() * 3);
                if (!cases[randIndex][randJIndex].isSelectionned()) {
                    caseToChoose = cases[randIndex][randJIndex];
                }
            }
            try {
                Thread.sleep(1000); // wait for one second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Case finalCaseToChoose = caseToChoose;
            Platform.runLater(() -> {
                finalCaseToChoose.accept(new SelectCaseVisitor());
            });
        });
        botThread.start();
    }
}
