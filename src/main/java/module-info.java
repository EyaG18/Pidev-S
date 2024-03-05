module com.example.pidev_v {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafx.web;
    requires org.json;
    requires java.mail;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires infobip.api.java.client;
   // requires jakarta.mail;
    requires okhttp3;
    requires com.jfoenix;
    opens com.example.pidev_v1.entities to javafx.base;

    opens com.example.pidev_v1 to javafx.fxml;
    exports com.example.pidev_v1;
}