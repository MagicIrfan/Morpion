package com.morpiong.model.visitor;

import com.morpiong.model.Case;
import javafx.scene.layout.Pane;

public class CaseVisitor implements Visitor {
    public void visit(Case caseElement){
        caseElement.selectionnedProperty().set(true);
    }
}
