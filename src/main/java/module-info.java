module com.example.pidev_v {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafx.web;
    requires org.json;
    requires java.mail;
    requires twilio;
    requires com.jfoenix;
    requires java.desktop;
    requires stripe.java;
    opens com.example.pidev_v1.entities to javafx.base;

    opens com.example.pidev_v1 to javafx.fxml;
    exports com.example.pidev_v1;
}