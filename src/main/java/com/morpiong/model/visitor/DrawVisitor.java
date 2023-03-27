package com.morpiong.model.visitor;

import com.morpiong.model.Case;
import com.morpiong.view.CasePane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class DrawVisitor implements Visitor {
    public Pane pane;
    public DrawVisitor(Pane pane){
        this.pane = pane;
    }
    public void visit(Case caseElement){
        ((CasePane) caseElement.getPane()).setImage(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(!caseElement.isPair() ? "com/morpiong/images/O.png" : "com/morpiong/images/X.png")));
    }
}
