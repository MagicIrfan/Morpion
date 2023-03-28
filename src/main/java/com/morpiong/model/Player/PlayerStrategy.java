package com.morpiong.model.Player;

import com.morpiong.model.Case;
import com.morpiong.model.visitor.CaseVisitor;
import javafx.scene.input.MouseEvent;

public class PlayerStrategy implements PlayableStrategy {
    @Override
    public void chooseCase(Case[][] cases) {
        for(Case[] rowCase: cases) {
            for (Case simpleCase : rowCase) {
                simpleCase.getPane().setOnMouseClicked((MouseEvent event) ->{
                    if(!simpleCase.selectionnedProperty().get()){
                        simpleCase.accept(new CaseVisitor(simpleCase.getPane()));
                    }
                });
            }
        }
    }
}
