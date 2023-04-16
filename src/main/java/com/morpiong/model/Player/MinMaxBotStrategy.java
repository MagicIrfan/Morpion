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
            int[] bestMove = minimax(35, true, this, cases,Integer.MIN_VALUE,Integer.MAX_VALUE);
            int row = bestMove[0];
            int col = bestMove[1];
            System.out.println(bestMove[0] + " " + bestMove[1]);

            try {
                Thread.sleep(1000); // wait for one second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Select the chosen case
            Case caseToChoose = cases[row][col];
            Platform.runLater(() -> caseToChoose.accept(new SelectCaseVisitor()));

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
     * @param alpha             la valeur alpha pour l'élagage alpha-bêta
     * @param beta              la valeur beta pour l'élagage alpha-bêta
     * @return un tableau d'entiers contenant la ligne et la colonne du meilleur mouvement
     */
    private int[] minimax(int depth, boolean isMaximizingPlayer, PlayableStrategy activePlayer, Case[][] cases, int alpha, int beta) {
        // Check if the game is over or the maximum depth has been reached
        if (game.gameFinishedProperty().get() || depth == 0) {
            return new int[]{0, 0, evaluateMove(0, 0, activePlayer, cases)};
        }

        int[] bestMove = null;
        int score;
        for (int[] move : getPossibleMoves(cases)) {
            Case[][] newCases = cloneCases(cases);
            int row = move[0];
            int col = move[1];
            newCases[row][col].setSymbol(activePlayer.getSymbol());

            PlayableStrategy nextPlayer = getNextPlayer(activePlayer);
            score = evaluateMove(row, col, activePlayer, newCases);

            if (isMaximizingPlayer) {
                int[] maxMove = new int[]{row, col, score};
                if (bestMove == null || maxMove[2] > bestMove[2]) {
                    bestMove = maxMove;
                }
                alpha = Math.max(alpha, bestMove[2]);
                if (beta <= alpha) {
                    break;
                }
            } else {
                int[] minMove = new int[]{row, col, score};
                if (bestMove == null || minMove[2] < bestMove[2]) {
                    bestMove = minMove;
                }
                beta = Math.min(beta, bestMove[2]);
                if (beta <= alpha) {
                    break;
                }
            }

            // Call minimax recursively to evaluate the next level of moves
            int[] nextMove = minimax(depth - 1, !isMaximizingPlayer, nextPlayer, newCases, alpha, beta);

            // Update the best move and score if necessary
            if (isMaximizingPlayer && nextMove[2] > bestMove[2] || !isMaximizingPlayer && nextMove[2] < bestMove[2]) {
                bestMove = new int[]{row, col, nextMove[2]};
            }
        }

        // Return the best move
        return bestMove;
    }

    /**
     * Évalue un coup en fonction de son potentiel de victoire pour le joueur actif et du potentiel de victoire
     * de l'adversaire. Le coup est évalué en fonction du nombre de lignes ouvertes qu'il crée, avec un poids supplémentaire
     * pour les diagonales. Si le coup est gagnant, renvoie Integer.MAX_VALUE. Si le coup est perdant, renvoie Integer.MIN_VALUE.
     *
     * @param row          la ligne du coup
     * @param col          la colonne du coup
     * @param activePlayer le joueur qui effectue le coup
     * @param cases        la grille de jeu
     * @return la valeur d'évaluation du coup
     */
    private int evaluateMove(int row, int col, PlayableStrategy activePlayer, Case[][] cases) {
        // Check if the move leads to a win
        if (isWinningMove(row, col, activePlayer, cases)) {
            return Integer.MAX_VALUE;
        }

        // Check if the move leads to a loss
        PlayableStrategy otherPlayer = getNextPlayer(activePlayer);
        if (isWinningMove(row, col, otherPlayer, cases)) {
            return Integer.MIN_VALUE;
        }

        // Evaluate the move based on the number of open lines it creates
        int openLines = 0;
        int diagonalWeight = 1;
        for (int i = 0; i < 3; i++) {
            if (cases[row][i].isEmpty()) {
                openLines++;
                if ((row == i && row == col) || (row + col == 2 && i == 2)) {
                    diagonalWeight++;
                }
            }
            if (cases[i][col].isEmpty()) {
                openLines++;
                if ((row == i && row == col) || (row + col == 2 && i == 0)) {
                    diagonalWeight++;
                }
            }
        }
        if (row == col && cases[0][0].isEmpty() && cases[1][1].isEmpty() && cases[2][2].isEmpty()) {
            openLines += 2 * diagonalWeight;
        }
        if (row + col == 2 && cases[0][2].isEmpty() && cases[1][1].isEmpty() && cases[2][0].isEmpty()) {
            openLines += 2 * diagonalWeight;
        }
        return openLines;
    }

    /**
     * Vérifie si un coup est gagnant pour le joueur donné en vérifiant les lignes, les colonnes et les diagonales de la
     * grille de jeu. Si le coup est gagnant, renvoie true, sinon renvoie false.
     *
     * @param row    la ligne du coup
     * @param col    la colonne du coup
     * @param player le joueur qui effectue le coup
     * @param cases  la grille de jeu
     * @return true si le coup est gagnant, false sinon
     */
    private boolean isWinningMove(int row, int col, PlayableStrategy player, Case[][] cases) {
        int count = 0;
        int size = cases.length;
        Symbol symbol = player.getSymbol();

        // Vérifie la ligne
        count = countMatchingSymbols(cases[row], symbol);
        if (count >= size) {
            return true;
        }

        // Vérifie la colonne
        Case[] column = new Case[size];
        for (int i = 0; i < size; i++) {
            column[i] = cases[i][col];
        }
        count = countMatchingSymbols(column, symbol);
        if (count >= size) {
            return true;
        }

        // Vérifie la diagonale principale si le coup est dessus
        if (row == col) {
            Case[] diagonal = new Case[size];
            for (int i = 0; i < size; i++) {
                diagonal[i] = cases[i][i];
            }
            count = countMatchingSymbols(diagonal, symbol);
            if (count >= size) {
                return true;
            }
        }

        // Vérifie la diagonale secondaire si le coup est dessus
        if (row == size - 1 - col) {
            Case[] diagonal = new Case[size];
            for (int i = 0; i < size; i++) {
                diagonal[i] = cases[i][size - 1 - i];
            }
            count = countMatchingSymbols(diagonal, symbol);
            if (count >= size) {
                return true;
            }
        }

        return false;
    }

    /**

     Compte le nombre de cases contenant un symbole donné dans un tableau de cases.
     @param cases tableau de cases à évaluer
     @param symbol symbole à rechercher
     @return le nombre de cases contenant le symbole
     */
    private int countMatchingSymbols(Case[] cases, Symbol symbol) {
        int count = 0;
        for (Case aCase : cases) {
            if (aCase.getSymbol() == symbol) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**

     Renvoie le joueur suivant dans la partie, en fonction du joueur actuel.
     @param player le joueur actuel
     @return le joueur suivant
     */
    private PlayableStrategy getNextPlayer(PlayableStrategy player) {
        return player == this ? game.getOpponent() : this;
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
     Clone un tableau de cases en effectuant une copie profonde.
     @param cases le tableau de cases à cloner
     @return une copie profonde du tableau de cases
     */
    private Case[][] cloneCases(Case[][] cases){
        int MAX_SIZE = 3;
        Case[][] clonedCases = new Case[MAX_SIZE][MAX_SIZE];
        for (int index = 0; index < MAX_SIZE; index++) {
            System.arraycopy(cases[index], 0, clonedCases[index], 0, MAX_SIZE);
        }
        return clonedCases;
    }
}

