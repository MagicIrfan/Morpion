package com.morpiong.model.visitor;

import com.morpiong.model.Case;
import com.morpiong.view.CasePane;

import java.util.Objects;

public class DrawVisitor implements Visitor {
    private final String imageUrl;
    public DrawVisitor(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public void visit(Case caseElement){
        caseElement.urlShapeImageProperty().set(imageUrl);
        ((CasePane) caseElement.getPane()).setImage(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(caseElement.getUrlShape())));
    }
}
