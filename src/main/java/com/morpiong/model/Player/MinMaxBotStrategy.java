package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.Factory.CaseFactory;
import com.morpiong.model.GameModel;
import com.morpiong.model.Plate;
import com.morpiong.model.visitor.SelectCaseVisitor;
import com.morpiong.model.Symbol;
import com.morpiong.view.CasePane;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.*;

/**
 * Une stratégie de jeu implémentant un joueur bot qui choisit une case non sélectionnée grâce à l'algorithme MinMax.
 */
public class MinMaxBotStrategy extends BotStrategy {

    private final GameModel game;

    /**
     * Crée une nouvelle instance de {@code MinMaxBotStrategy} avec l'image de forme spécifiée et le modèle de jeu.
     *
     * @param symbol le symbole
     * @param game le modèle de jeu
     */
    public MinMaxBotStrategy(Symbol symbol, GameModel game){
        super(symbol);
        this.game = game;
    }

    /**
     * Choisit une case non sélectionnée parmi toutes les cases fournies grâce à l'algorithme MinMax et la sélectionne.
     *
     * @param plate le plateau du jeu
     */
    @Override
    public void chooseCase(Plate plate) {
        Case[][] cases = plate.getCases();

        botThread = new Thread(() -> {
            initialiseBotThread(plate);
            // Calculate the best move using the MinMax algorithm
            int[] bestMove = minimax(5, true, this, cases);
            int row = bestMove[0];
            int col = bestMove[1];

            try {
                Thread.sleep(1000); // wait for one second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Select the chosen case
            Case caseToChoose = cases[row][col];
            Platform.runLater(() -> {
                caseToChoose.accept(new SelectCaseVisitor());
            });

        });
        botThread.start();
    }

    /**
     * Calcule le meilleur mouvement possible pour le joueur actuel en utilisant l'algorithme MinMax avec une profondeur maximale donnée.
     *
     * @param depth             la profondeur maximale de la recherche
     * @param isMaximizingPlayer  indique si le joueur actuel est en train de maximiser ou de minimiser son score
     * @param activePlayer      le joueur actuel
     * @param cases             les cases du plateau de jeu
     * @return un tableau d'entiers contenant la ligne et la colonne du meilleur mouvement
     */
    private int[] minimax(int depth, boolean isMaximizingPlayer, PlayableStrategy activePlayer, Case[][] cases) {
        // Check if the game is over or the maximum depth has been reached
        if (game.gameFinishedProperty().get() || depth == 0) {
            return new int[]{0, 0, getScore(cases, activePlayer)};
        }

        // Calculate the best move recursively for each possible move
        int[] bestMove = null;
        for (int[] move : getPossibleMoves(cases)) {
            int row = move[0];
            int col = move[1];

            Case[][] newCases = cloneCases(cases);
            newCases[row][col].setSymbol(activePlayer.getSymbol());

            PlayableStrategy nextPlayer = getNextPlayer(activePlayer);
            int[] score = minimax(depth - 1, !isMaximizingPlayer, nextPlayer, newCases);

            // Update the best move if necessary
            if (bestMove == null ||
                    (isMaximizingPlayer && score[2] > bestMove[2]) ||
                    (!isMaximizingPlayer && score[2] < bestMove[2])) {
                bestMove = new int[]{row, col, score[2]};
            }
        }

        return bestMove;
    }

    private PlayableStrategy getNextPlayer(PlayableStrategy player) {
        return player == this ? game.getOpponent() : game.getPlayer();
    }

    /**
     * Récupère tous les mouvements possibles pour les cases non sélectionnées sur le plateau.
     *
     * @param cases les cases du plateau de jeu
     * @return une liste d'entiers représentant les coordonnées des mouvements possibles
     */
    private List<int[]> getPossibleMoves(Case[][] cases) {
        List<int[]> possibleMoves = new ArrayList<>();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                if (!cases[i][j].isSelectionned()) {
                    possibleMoves.add(new int[]{i, j});
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Calcule le score d'un joueur en fonction de l'état du plateau de jeu et du symbole du joueur.
     *
     * @param cases tableau à deux dimensions représentant l'état du plateau de jeu.
     * @param player le joueur pour lequel le score est calculé.
     * @return le score du joueur.
     */
    private int getScore(Case[][] cases, PlayableStrategy player) {
        int score = 0;

        // Check for horizontal lines
        for (int row = 0; row < 3; row++) {
            boolean isLineComplete = true;
            for (int col = 0; col < 3; col++) {
                if (cases[row][col].getSymbol() != player.getSymbol()) {
                    isLineComplete = false;
                    break;
                }
            }
            if (isLineComplete) {
                score += 1;
            }
        }

        // Check for vertical lines
        for (int col = 0; col < 3; col++) {
            boolean isLineComplete = true;
            for (int row = 0; row < 3; row++) {
                if (cases[row][col].getSymbol() != player.getSymbol()) {
                    isLineComplete = false;
                    break;
                }
            }
            if (isLineComplete) {
                score += 1;
            }
        }

        // Check for diagonal lines
        if (cases[0][0].getSymbol() == player.getSymbol() && cases[1][1].getSymbol() == player.getSymbol() && cases[2][2].getSymbol() == player.getSymbol()) {
            score += 1;
        }
        if (cases[2][0].getSymbol() == player.getSymbol() && cases[1][1].getSymbol() == player.getSymbol() && cases[0][2].getSymbol() == player.getSymbol()) {
            score += 1;
        }

        return score;
    }

    private Case[][] cloneCases(Case[][] cases){
        int MAX_SIZE = 3;
        Case[][] clonedCases = new Case[MAX_SIZE][MAX_SIZE];
        for (int index = 0; index < MAX_SIZE; index++) {
            System.arraycopy(cases[index], 0, clonedCases[index], 0, MAX_SIZE);
        }
        return clonedCases;
    }
}

