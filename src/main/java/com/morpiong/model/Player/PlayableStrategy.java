package com.morpiong.model.Player;

import com.morpiong.model.Case;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class PlayableStrategy {
    protected final StringProperty urlShapeImage;

    protected PlayableStrategy(String urlShapeImage) {
        this.urlShapeImage = new SimpleStringProperty(urlShapeImage);
    }
    abstract public void chooseCase(Case[][] cases);
    public StringProperty urlShapeImageProperty(){
        return this.urlShapeImage;
    }
    public String getUrlShape(){
        return this.urlShapeImage.get();
    }
}
