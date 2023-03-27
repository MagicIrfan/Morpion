package com.morpiong.model.visitor;

import com.morpiong.model.Case;

public interface Visitor {
    void visit(Case caseElement);
}
