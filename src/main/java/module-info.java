module com.morpiong.morpiong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.morpiong to javafx.fxml;
    exports com.morpiong;
    exports com.morpiong.model;
    opens com.morpiong.model to javafx.fxml;
    exports com.morpiong.view;
    opens com.morpiong.view to javafx.fxml;
    exports com.morpiong.controller;
    opens com.morpiong.controller to javafx.fxml;
    exports com.morpiong.utils;
    opens com.morpiong.utils to javafx.fxml;
    exports com.morpiong.model.visitor;
    opens com.morpiong.model.visitor to javafx.fxml;
    exports com.morpiong.model.Player;
    opens com.morpiong.model.Player to javafx.fxml;
}