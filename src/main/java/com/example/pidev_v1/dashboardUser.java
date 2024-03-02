package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardUser implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text userText;
    private User currentUser;

    @FXML
    void logOut(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authentification.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) userText.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setUser(User user) {
        currentUser = user;
        userText.setText(currentUser.getNomuser()+" "+currentUser.getPrenomuser());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert userText != null : "fx:id=\"userText\" was not injected: check your FXML file 'dashboardAdmin.fxml'.";
    }
}
