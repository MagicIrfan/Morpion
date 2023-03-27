package com.morpiong;
import com.morpiong.CaseVisitor;
import javafx.beans.property.*;
import javafx.scene.layout.Pane;

public class Case {

    private final Pane casePane;
    private final BooleanProperty selectionned;
    private boolean isPair;

    public Case(Pane pane){
        this.casePane = pane;
        this.selectionned = new SimpleBooleanProperty(false);
    }
    public BooleanProperty selectionnedProperty(){
        return this.selectionned;
    }
    public boolean isSelectionned(){
        return this.selectionned.get();
    }
    public Pane getPane(){
        return this.casePane;
    }

    public void accept(CaseVisitor visitor){
        visitor.visit(this);
    }

    public void setPair(boolean isPair){
        this.isPair = isPair;
    }

    public boolean isPair(){
        return this.isPair;
    }
}
