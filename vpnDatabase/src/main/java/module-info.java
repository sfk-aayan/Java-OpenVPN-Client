module com.example.vpndatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.sql;
    requires java.mail;

    opens com.example.vpndatabase to javafx.fxml;
    exports com.example.vpndatabase;
}