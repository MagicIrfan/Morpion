package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.visitor.CaseVisitor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BotStrategy extends PlayableStrategy {

    public BotStrategy(String urlImageShape){
        super(urlImageShape);
    }
    @Override
    public void chooseCase(Case[][] cases) {
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
        caseToChoose.accept(new CaseVisitor());
    }
}
