package com.morpiong.model.visitor;

import com.morpiong.model.Case;
import javafx.scene.layout.Pane;

public class CaseVisitor implements Visitor {
    public Pane pane;
    public CaseVisitor(Pane pane){
        this.pane = pane;
    }
    public void visit(Case caseElement){
        caseElement.selectionnedProperty().set(true);
    }
}
