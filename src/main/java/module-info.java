module com.example.pidev_v1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.pidev_v1 to javafx.fxml;
    exports com.example.pidev_v1;
}