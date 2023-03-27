module com.morpiong.morpiong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.morpiong to javafx.fxml;
    exports com.morpiong;
}