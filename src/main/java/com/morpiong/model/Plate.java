package com.morpiong.model;

import com.morpiong.model.Factory.CaseFactory;
import com.morpiong.view.CasePane;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Plate {

    @FXML
    Pane platePane;
    private Case[][] cases;
    public void initialize(){
        int MAX_SIZE = 3;
        this.cases = new Case[MAX_SIZE][MAX_SIZE];
        for(int index = 0; index < MAX_SIZE; index++){
            for(int JIndex = 0; JIndex < MAX_SIZE; JIndex++){
                this.cases[index][JIndex] = CaseFactory.create();
                Case simpleCase = this.cases[index][JIndex];
                ((GridPane) platePane).add(simpleCase.getPane(),index,JIndex);
            }
        }
    }
    public Case[][] getCases(){
        return this.cases;
    }
}
