package com.morpiong.model;

import com.morpiong.model.Case;
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
        return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            boolean allPair = true;
            boolean allUnpair = true;
            boolean allSelected = true;

            for (int j = 0; j < 3; j++) {
                if (!this.cases[i][j].isSelectionned()) {
                    allSelected = false;
                }
                if (this.cases[i][j].isPair()) {
                    allUnpair = false;
                } else {
                    allPair = false;
                }
            }

            if ((allPair || allUnpair) && allSelected) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            boolean allPair = true;
            boolean allUnpair = true;
            boolean allSelected = true;

            for (int j = 0; j < 3; j++) {
                if (!this.cases[j][i].isSelectionned()) {
                    allSelected = false;
                }
                if (this.cases[j][i].isPair()) {
                    allUnpair = false;
                } else {
                    allPair = false;
                }
            }

            if ((allPair || allUnpair) && allSelected) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        boolean allPair = true;
        boolean allUnpair = true;
        boolean allSelected = true;

        for (int i = 0; i < 3; i++) {
            if (!this.cases[i][i].isSelectionned()) {
                allSelected = false;
            }
            if (this.cases[i][i].isPair()) {
                allUnpair = false;
            } else {
                allPair = false;
            }
        }

        if ((allPair || allUnpair) && allSelected) {
            return true;
        }

        allPair = true;
        allUnpair = true;
        allSelected = true;

        for (int i = 0; i < 3; i++) {
            if (!this.cases[i][2 - i].isSelectionned()) {
                allSelected = false;
            }
            if (this.cases[i][2 - i].isPair()) {
                allUnpair = false;
            } else {
                allPair = false;
            }
        }

        return (allPair || allUnpair) && allSelected;
    }

    public boolean isDraw(){
        boolean allSelectionned = true;
        for(Case[] rowCase: this.cases) {
            for (Case simpleCase : rowCase) {
                if(!simpleCase.isSelectionned()){
                    allSelectionned = false;
                    break;
                }
            }
            if(!allSelectionned)
                break;
        }
        return allSelectionned && !isWin();
    }

    public void checkFinishedGame(){
        this.gameFinished.set(isDraw() || isWin());
    }
}