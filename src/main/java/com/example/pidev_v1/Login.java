package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TFEmail;

    @FXML
    private TextField TFPassword;

    @FXML
    void CreateAccount(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authentification.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) TFEmail.getScene().getWindow();
            stage.setTitle("Authentification");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logIn(ActionEvent event) {
        String email = TFEmail.getText().trim();
        String password = TFPassword.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        UserService userService = new UserService();
        User user = userService.getUserByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            showAlert("Error", "Invalid email or password.");
            return;
        }
        FXMLLoader fxmlLoader;

        if(Objects.equals(user.getRole(), "administrateur"))
        {
            try {
                fxmlLoader = new FXMLLoader(getClass().getResource("dashboardAdmin.fxml"));
                AnchorPane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) TFEmail.getScene().getWindow();
                stage.setTitle("Dashboard");
                stage.setScene(scene);
                dashboardAdmin controller = fxmlLoader.getController();
                controller.setUser(user);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                fxmlLoader = new FXMLLoader(getClass().getResource("dashboardUser.fxml"));
                AnchorPane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) TFEmail.getScene().getWindow();
                stage.setTitle("Dashboard");
                stage.setScene(scene);
                dashboardUser controller = fxmlLoader.getController();
                controller.setUser(user);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
  /*  @FXML
    void initialize() {
        assert TFEmail != null : "fx:id=\"TFEmail\" was not injected: check your FXML file 'Login.fxml'.";
        assert TFPassword != null : "fx:id=\"TFPassword\" was not injected: check your FXML file 'Login.fxml'.";

    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert TFEmail != null : "fx:id=\"TFEmail\" was not injected: check your FXML file 'Login.fxml'.";
        assert TFPassword != null : "fx:id=\"TFPassword\" was not injected: check your FXML file 'Login.fxml'.";
    }
}
