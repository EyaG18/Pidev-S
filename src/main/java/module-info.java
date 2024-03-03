module com.example.pidev_v1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires twilio;
    requires com.jfoenix;
    requires org.json;
    requires javafx.web;

    opens com.example.pidev_v1.entities to javafx.base;

    opens com.example.pidev_v1 to javafx.fxml;
    exports com.example.pidev_v1;
}