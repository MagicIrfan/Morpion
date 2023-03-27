package com.morpiong;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameModel {

    private final Case[][] cases;
    private final IntegerProperty nbMoves;
    private final BooleanProperty gameFinished;

    public GameModel(Case[][] cases){
        this.cases = cases;
        this.nbMoves = new SimpleIntegerProperty(0);
        this.gameFinished = new SimpleBooleanProperty(false);
    }

    public IntegerProperty nbMovesProperty(){
        return this.nbMoves;
    }

    public int getNbMoves(){
        return this.nbMoves.get();
    }
    public void addTurn() {
        this.nbMoves.set(getNbMoves()+1);
    }

    public BooleanProperty gameFinishedProperty(){
        return this.gameFinished;
    }

    public Case[][] getCases(){
        return this.cases;
    }

    public boolean isWin() {
        // Vérifier les lignes
        for (int i = 0; i < 3; i++) {
            if (((!this.cases[i][0].isPair() && !this.cases[i][1].isPair() && !this.cases[i][2].isPair())
            || (this.cases[i][0].isPair() && this.cases[i][1].isPair() && this.cases[i][2].isPair()))
                    && this.cases[i][0].isSelectionned() && this.cases[i][1].isSelectionned()
                    && this.cases[i][2].isSelectionned()) {
                return true;
            }
        }

        // Vérifier les colonnes
        for (int i = 0; i < 3; i++) {
            if (((this.cases[0][i].isPair() && this.cases[1][i].isPair() && this.cases[2][i].isPair())
                || (!this.cases[0][i].isPair() && !this.cases[1][i].isPair() && !this.cases[2][i].isPair()))
                    && this.cases[0][i].isSelectionned() && this.cases[1][i].isSelectionned()
                    && this.cases[2][i].isSelectionned()) {
                return true;
            }
        }

        // Vérifier les diagonales
        if (((this.cases[0][0].isPair() && this.cases[1][1].isPair() && this.cases[2][2].isPair())
         || (!this.cases[0][0].isPair() && !this.cases[1][1].isPair() && !this.cases[2][2].isPair()))
                && this.cases[0][0].isSelectionned() && this.cases[1][1].isSelectionned()
                && this.cases[2][2].isSelectionned()) {
            return true;
        }

        if (((!this.cases[0][2].isPair() && !this.cases[1][1].isPair() && !this.cases[2][0].isPair())
        || (this.cases[0][2].isPair() && this.cases[1][1].isPair() && this.cases[2][0].isPair()))
                && this.cases[0][2].isSelectionned() && this.cases[1][1].isSelectionned()
                && this.cases[2][0].isSelectionned()) {
            return true;
        }

        return false;
    }

    public boolean isDraw(){
        boolean allSelectionned = true;
        for(Case[] rowCase: this.cases) {
            for (Case simpleCase : rowCase) {
                if(!simpleCase.isSelectionned()){
                    allSelectionned = false;
                }
            }
        }
        return allSelectionned && !isWin();
    }

    public void checkFinishedGame(){
        this.gameFinished.set(isDraw() || isWin());
    }
}