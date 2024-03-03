package com.example.pidev_v1;


import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Authentification {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Label imageFullPath;


    @FXML
    void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            imageFullPath.setText(file.getName());
        }
        String fileName = imageFullPath.getText();
        if (fileName != null && !fileName.isEmpty()) {
            try {
                // Get the resource URL for the uploads directory
                URL resourceUrl = getClass().getClassLoader().getResource("uploads");
                if (resourceUrl == null) {
                    // If the directory doesn't exist, create it
                    File uploadsDirectory = new File("src/main/resources/upload");
                    if (!uploadsDirectory.exists()) {
                        uploadsDirectory.mkdirs();
                    }
                    resourceUrl = uploadsDirectory.toURI().toURL();
                }

                // Copy the uploaded file to the uploads directory
                Files.copy(new File(file.getPath()).toPath(), Paths.get(resourceUrl.toURI()).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace(); // Handle exception properly based on your application's requirements
            }
        }
    }

    @FXML
    void Authentificate(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) TFAddress.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addAccount(ActionEvent event) {

        String email = TFEmail.getText();
        String fullName = TFFullName.getText();
        String password = TFPassword.getText();
        String address = TFAddress.getText();
        String userImage = imageFullPath.getText();
        int numtel = parseInt(TFNum.getText());

        if (email.isEmpty() || fullName.isEmpty() || password.isEmpty() || address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill in all fields");
            alert.show();
            return;
        }

        if(numtel<10000000 || numtel>99999999)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Number field should contain 8 numbers");
            alert.show();
            return;
        }

        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(email).matches())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Email field isn't in the correct format");
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

        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern patternPassword = Pattern.compile(passwordPattern);
        Matcher matcher = patternPassword.matcher(password);

        if (!matcher.matches()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least:\n- One uppercase letter\n- One lowercase letter\n- One digit\n- One special character\n- Minimum length of 8 characters");
            alert.showAndWait();
            return;
        }

        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern emailPatternCompiled = Pattern.compile(emailPattern);
        Matcher emailMatcher = emailPatternCompiled.matcher(email);

        if (!emailMatcher.matches()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid email address\n");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email address Please retry!");
            alert.showAndWait();
            return;
        }

        UserService ps = new UserService();

        String hashedPassword = hashPassword(password);

        User user = new User(0, firstName,lastName, address, email, hashedPassword, numtel, "Client",userImage);
        ps.add(user);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("User added successfully");
        alert.show();
    }

    @FXML
    void initialize() {
        assert TFAddress != null : "fx:id=\"TFAddress\" was not injected: check your FXML file 'authentification.fxml'.";
        assert TFEmail != null : "fx:id=\"TFEmail\" was not injected: check your FXML file 'authentification.fxml'.";
        assert TFFullName != null : "fx:id=\"TFFullName\" was not injected: check your FXML file 'authentification.fxml'.";
        assert TFNum != null : "fx:id=\"TFNum\" was not injected: check your FXML file 'authentification.fxml'.";
        assert TFPassword != null : "fx:id=\"TFPassword\" was not injected: check your FXML file 'authentification.fxml'.";

    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        }  catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }




}