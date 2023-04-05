package com.morpiong.model;

import com.morpiong.model.Player.PlayableStrategy;
import com.morpiong.model.builder.AlertBuilder;
import com.morpiong.model.visitor.DrawCaseVisitor;
import com.morpiong.utils.SceneChangerUtils;
import com.morpiong.view.CasePane;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * GameModel représente le modèle du jeu Tic-Tac-Toe. Il contient une grille de cases,
 * deux stratégies jouables, un tour de jeu, un nombre de mouvements et un statut pour savoir
 * si la partie est terminée ou non.
 */
public class GameModel {
    private final Plate plate;
    private final IntegerProperty nbMoves;
    private final BooleanProperty gameFinished;
    private final IntegerProperty playerTurn;
    private PlayableStrategy player;
    private PlayableStrategy opponent;

    /**
     * Constructeur pour la classe GameModel.
     * @param plate le plateau du jeu
     */
    public GameModel(Plate plate){
        this.plate = plate;
        this.playerTurn = new SimpleIntegerProperty(1);
        this.nbMoves = new SimpleIntegerProperty(0);
        this.gameFinished = new SimpleBooleanProperty(false);
    }

    /**
     * Getter pour la propriété playerTurn.
     * @return la propriété playerTurn
     */
    public IntegerProperty playerTurnProperty(){
        return this.playerTurn;
    }

    /**
     * Setter pour la propriété playerTurn.
     * @param turn le tour à définir
     */
    public void setPlayerTurn(int turn){
        this.playerTurn.set(turn);
    }

    /**
     * Getter pour la propriété nbMoves.
     * @return le nombre de mouvements
     */
    public IntegerProperty nbMovesProperty(){
        return this.nbMoves;
    }

    /**
     * Getter pour le nombre de mouvements.
     * @return le nombre de mouvements
     */
    public int getNbMoves(){
        return this.nbMoves.get();
    }

    /**
     * Méthode pour ajouter un tour de jeu.
     */
    public void addTurn() {
        this.nbMoves.set(getNbMoves()+1);
    }

    /**
     * Getter pour la propriété gameFinished.
     * @return la propriété gameFinished
     */
    public BooleanProperty gameFinishedProperty(){
        return this.gameFinished;
    }

    /**
     * Vérifie si un joueur a gagné la partie.
     * @return true si un joueur a gagné, false sinon
     */
    public boolean isWin() {
        return !getWinningCases().isEmpty();
    }

    /**
     * Méthode privée qui retourne les cases gagnantes, s'il y en a.
     * @return les cases gagnantes, une liste vide sinon
     */
    private List<Case> getWinningCases(){
        List<Case> rowCases = this.checkRowsForWin();
        List<Case> columnCases = this.checkColumnsForWin();
        List<Case> diagonalCases = this.checkDiagonalsForWin();
        if(!rowCases.isEmpty())
            return rowCases;
        if(!columnCases.isEmpty())
            return columnCases;
        if(!diagonalCases.isEmpty())
            return diagonalCases;
        return Collections.emptyList();
    }

    /**
     * Méthode privée qui vérifie si un joueur a gagné en parcourant chaque ligne.
     * @return les cases gagnantes, une liste vide sinon
     */
    private List<Case> checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            boolean allPair = true;
            boolean allUnpair = true;
            boolean allSelected = true;
            List<Case> winningCells = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                if (!plate.getCases()[i][j].isSelectionned()) {
                    allSelected = false;
                }
                if (plate.getCases()[i][j].isPair()) {
                    allUnpair = false;
                } else {
                    allPair = false;
                }
                if(allPair || allUnpair){
                    winningCells.add(plate.getCases()[i][j]);
                }
            }

            if ((allPair || allUnpair) && allSelected) {
                return winningCells;
            }
        }
        return Collections.emptyList();
    }

    /**
     * Méthode privée qui vérifie si un joueur a gagné en parcourant chaque colonne.
     * @return les cases gagnantes, une liste vide sinon
     */
    private List<Case> checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            boolean allPair = true;
            boolean allUnpair = true;
            boolean allSelected = true;
            List<Case> winningCells = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                if (!plate.getCases()[j][i].isSelectionned()) {
                    allSelected = false;
                }
                if (plate.getCases()[j][i].isPair()) {
                    allUnpair = false;
                } else {
                    allPair = false;
                }
                if (allPair || allUnpair) {
                    winningCells.add(plate.getCases()[j][i]);
                }
            }

            if (winningCells.size() == 3 && allSelected) {
                return winningCells;
            }
        }
        return Collections.emptyList();
    }

    /**
     * Méthode privée qui vérifie si un joueur a gagné en parcourant chaque diagonale.
     * @return les cases gagnantes, une liste vide sinon
     */
    private List<Case> checkDiagonalsForWin() {
        boolean allPair = true;
        boolean allUnpair = true;
        boolean allSelected = true;

        List<Case> diagonal1 = new ArrayList<>();
        List<Case> diagonal2 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (!plate.getCases()[i][i].isSelectionned()) {
                allSelected = false;
            }
            if (plate.getCases()[i][i].isPair()) {
                allUnpair = false;
            } else {
                allPair = false;
            }
            diagonal1.add(plate.getCases()[i][i]);
        }

        if (allSelected && (allPair || allUnpair)) {
            return diagonal1;
        }

        allPair = true;
        allUnpair = true;
        allSelected = true;

        for (int i = 0; i < 3; i++) {
            if (!plate.getCases()[i][2 - i].isSelectionned()) {
                allSelected = false;
            }
            if (plate.getCases()[i][2 - i].isPair()) {
                allUnpair = false;
            } else {
                allPair = false;
            }
            diagonal2.add(plate.getCases()[i][2 - i]);
        }

        if (allSelected && (allPair || allUnpair)) {
            return diagonal2;
        }

        return Collections.emptyList();
    }

    /**
     * Méthode privée qui vérifie si le jeu se finit sur une égalité.
     * @return un booléen permettant de savoir si le jeu se finit sur une égalité
     */
    public boolean isDraw(){
        for(Case[] rowCase: plate.getCases()) {
            for (Case simpleCase : rowCase) {
                if(!simpleCase.isSelectionned()){
                    return false;
                }
            }
        }
        return !isWin();
    }

    /**
     * Méthode publique qui éxécute du code lorsque le jeu est terminée
     * @param gamePane le pane principal du jeu
     */
    public void onGameFinished(Pane gamePane){
        String message = "";
        if(this.isWin()){
            this.fillWinningCases();
            message = "Bravo, joueur " + this.playerTurnProperty().get() + " a gagné !";
        } else if(this.isDraw()) {
            message = "Match nul.";
        }
        Alert alert = new AlertBuilder()
                .setTitle("Fin de partie")
                .setContentText(message)
                .setType(Alert.AlertType.INFORMATION)
                .setOnCloseRequest(e -> {
                    try {
                        SceneChangerUtils.getInstance().changeScene(gamePane, FXMLLoader.load(Objects.requireNonNull(SceneChangerUtils.class.getResource("/com/morpiong/mainmenu-view.fxml"))));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .build();
        alert.showAndWait();
    }

    /**
     * Méthode privée qui permet de remplir les cases gagnantes d'un fond orange
     */
    private void fillWinningCases(){
        for(Case singleCase : this.getWinningCases()){
            int x = singleCase.getXCoord();
            int y = singleCase.getYCoord();
            Node node = this.plate.getPlatePane().getChildren().stream()
                    .filter(n -> GridPane.getRowIndex(n) == x && GridPane.getColumnIndex(n) == y)
                    .findFirst()
                    .orElse(null);
            ((CasePane)node).setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
        }
    }

    /**
     * Méthode publique qui execute du code lorsqu'un tour de jeu se termine
     * @param simpleCase la case sélectionné
     * @param casePane la représentation graphique de la case sélectionné
     */
    public void onTurnFinished(Case simpleCase,Pane casePane){
        // Définir la paire pour le joueur en cours
        simpleCase.setPair(this.getNbMoves() % 2 == 0);
        // Afficher le coup sur le plateau
        simpleCase.accept(new DrawCaseVisitor(this.getActivePlayer().getUrlShape(),casePane));
        // Vérifier si la partie est terminée
        this.checkFinishedGame();
    }

    /**
     * Méthode publique qui execute du code lorsqu'un tour de jeu commence
     */
    public void onBeginTurn(){
        this.addTurn();
        // Changer le tour de joueur
        this.setPlayerTurn(this.getNbMoves() % 2 == 0 ? 1 : 2);
        // Si l'option "bot" est activée, faire jouer le bot
        this.getActivePlayer().chooseCase(plate);
    }

    /**
     * Définit la stratégie du joueur.
     *
     * @param playableStrategy La stratégie du joueur.
     */
    public void setPlayerStrategy(PlayableStrategy playableStrategy){
        this.player = playableStrategy;
    }

    /**
     * Définit la stratégie de l'adversaire.
     *
     * @param playableStrategy La stratégie de l'adversaire.
     */
    public void setOpponentStrategy(PlayableStrategy playableStrategy){
        this.opponent = playableStrategy;
    }

    /**
     * Obtient la stratégie du joueur.
     *
     * @return La stratégie du joueur.
     */
    public PlayableStrategy getPlayer(){
        return this.player;
    }

    /**
     * Obtient la stratégie de l'adversaire.
     *
     * @return La stratégie de l'adversaire.
     */
    public PlayableStrategy getOpponent(){
        return this.opponent;
    }

    /**
     * Vérifie si la partie est terminée.
     * La partie est terminée si elle est nulle ou si l'un des joueurs a gagné.
     */
    public void checkFinishedGame(){
        this.gameFinished.set(isDraw() || isWin());
    }

    /**
     * Obtient le joueur actif.
     *
     * @return Le joueur actif.
     */
    public PlayableStrategy getActivePlayer(){
        return this.playerTurn.get() == 1 ? player : opponent;
    }
}