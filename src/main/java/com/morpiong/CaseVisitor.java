package com.morpiong;

import javafx.scene.layout.Pane;

public class CaseVisitor {
    public Pane pane;
    public CaseVisitor(Pane pane){
        this.pane = pane;
    }
    public void visit(Case caseElement){
        caseElement.selectionnedProperty().set(true);
    }
}
