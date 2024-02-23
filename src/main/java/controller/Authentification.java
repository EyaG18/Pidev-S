package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;
import utilties.DataSource;

public class Authentification {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboBoxRole;

    @FXML
    private TextField TFAddress;

    @FXML
    private TextField TFEmail;

    @FXML
    private TextField TFFullName;

    @FXML
    private TextField TFNum;

    @FXML
    private TextField TFPassword;
    @FXML
    void Authentificate(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ComboBoxRole.getScene().getWindow(); // Getting the current stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    void addAccount(ActionEvent event) {

        String email = TFEmail.getText();
        String fullName = TFFullName.getText();
        String password = TFPassword.getText();
        String address = TFAddress.getText();
        int numTel = Integer.parseInt(TFNum.getText());
        String role = ComboBoxRole.getValue();

        if (email.isEmpty() || fullName.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill in all fields");
            alert.show();
            return;
        }

        String[] parts = fullName.split(" ");
        String firstName = parts[0];
        String lastName = "";

        if (parts.length > 1) {
            for (int i = 1; i < parts.length; i++) {
                lastName += parts[i];
                if (i < parts.length - 1) {
                    lastName += " ";
                }
            }
        }


        UserService ps = new UserService();

        User user = new User(0, firstName,lastName, address, email, password, numTel, role);
        ps.add(user);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("User added successfully");
        alert.show();
    }

    @FXML
    void initialize() {
        assert ComboBoxRole != null : "fx:id=\"ComboBoxRole\" was not injected: check your FXML file 'authentification.fxml'.";
        assert TFAddress != null : "fx:id=\"TFAddress\" was not injected: check your FXML file 'authentification.fxml'.";
        assert TFEmail != null : "fx:id=\"TFEmail\" was not injected: check your FXML file 'authentification.fxml'.";
        assert TFFullName != null : "fx:id=\"TFFullName\" was not injected: check your FXML file 'authentification.fxml'.";
        assert TFNum != null : "fx:id=\"TFNum\" was not injected: check your FXML file 'authentification.fxml'.";
        assert TFPassword != null : "fx:id=\"TFPassword\" was not injected: check your FXML file 'authentification.fxml'.";

    }

}
