package com.morpiong.model.builder;

import javafx.scene.control.Dialog;

public interface DialogBuilder<T> {
    Dialog<T> build();
}
