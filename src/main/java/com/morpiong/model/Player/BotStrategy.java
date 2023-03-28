package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.visitor.CaseVisitor;

public class BotStrategy implements PlayableStrategy {
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
        caseToChoose.accept(new CaseVisitor(caseToChoose.getPane()));
        //caseToChoose.getPane().fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, false, false, false, false, true, false, false, true, false, false, null));
    }
}
