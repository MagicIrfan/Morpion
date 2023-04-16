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
public class NaiveBotStrategy extends BotStrategy {

    /**
     * Crée une nouvelle instance de {@code NaiveBotStrategy} avec le symbole spécifié.
     * @param symbol le symbole spécifié
     */
    public NaiveBotStrategy(Symbol symbol){
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
        botThread = new Thread(() -> {
            initialiseBotThread(plate);
            Case caseToChoose = getRandomMove(cases);
            try {
                Thread.sleep(1000); // wait for one second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Platform.runLater(() -> {
                caseToChoose.accept(new SelectCaseVisitor());
            });
        });
        botThread.start();
    }
}
