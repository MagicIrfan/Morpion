package com.morpiong;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Plate {

    @FXML
    GridPane platePane;
    private final int MAX_SIZE = 3;
    private Case[][] cases;
    public void initialize(){
        this.cases = new Case[MAX_SIZE][MAX_SIZE];
        for(int index=0; index < MAX_SIZE; index++){
            for(int JIndex=0; JIndex < MAX_SIZE; JIndex++){
                this.cases[index][JIndex] = new Case(new CasePane());
                Case simpleCase = this.cases[index][JIndex];
                platePane.add(simpleCase.getPane(),index,JIndex);
            }
        }
    }

    public Case[][] getCases(){
        return this.cases;
    }
}
