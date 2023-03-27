package com.morpiong.model.states;

import com.morpiong.model.Case;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PlayingState {

    public void chooseCase(Case simpleCase, EventHandler<MouseEvent> event){
        simpleCase.getPane().setOnMouseClicked(event);
    }
}
