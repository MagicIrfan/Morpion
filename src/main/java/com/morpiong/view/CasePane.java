package com.morpiong.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class CasePane extends Pane {

    ImageView image;
    public CasePane(){
        this.image = new ImageView();
        this.image.setFitWidth(200);
        this.image.setFitHeight(200);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        this.getChildren().add(this.image);
    }

    public void setImage(InputStream stream){
        this.image.setImage(new Image(stream));
    }
}
