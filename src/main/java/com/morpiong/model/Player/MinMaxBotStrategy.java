package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.visitor.SelectCaseVisitor;
import javafx.application.Platform;
import javafx.util.Pair;

public class MinMaxBotStrategy extends PlayableStrategy {

    private Thread botThread;
    private final int maxDepth;

    public MinMaxBotStrategy(String urlImageShape, int maxDepth) {
        super(urlImageShape);
        this.maxDepth = maxDepth;
    }

    @Override
    public void chooseCase(Case[][] cases) {
        botThread = new Thread(() -> {
            Platform.runLater(() -> {
                for (Case[] rowCase : cases) {
                    for (Case simpleCase : rowCase) {
                        simpleCase.getPane().setOnMouseClicked(null);
                    }
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Case caseToChoose = null;

            try {
                caseToChoose = minimax(0, true, cases).getKey();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Case finalCaseToChoose = caseToChoose;
            Platform.runLater(() -> finalCaseToChoose.accept(new SelectCaseVisitor()));
        });

        botThread.start();
    }

    private Pair<Case, Integer> minimax(int depth, boolean isMaximizingPlayer, Case[][] cases){
        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;
            Case bestCase = null;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!cases[i][j].isSelectionned()) {
                        cases[i][j].accept(new SelectCaseVisitor());
                        int score = minimax(depth + 1, false, cases).getValue();
                        if (score > bestScore) {
                            bestScore = score;
                            bestCase = cases[i][j];
                        }
                    }
                }
            }
            return new Pair<>(bestCase, bestScore);
        } else {
            int bestScore = Integer.MAX_VALUE;
            Case bestCase = null;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!cases[i][j].isSelectionned()) {
                        cases[i][j].accept(new SelectCaseVisitor());

                        int score = minimax(depth + 1, true, cases).getValue();

                        if (score < bestScore) {
                            bestScore = score;
                            bestCase = cases[i][j];
                        }
                    }
                }
            }
            return new Pair<>(bestCase, bestScore);
        }
    }
}