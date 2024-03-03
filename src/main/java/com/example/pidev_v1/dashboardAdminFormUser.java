package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class dashboardAdminFormUser implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adresseField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField numField;

    @FXML
    private TextField prenomField;

    @FXML
    private ComboBox<String> roleCombo;

    @FXML
    private Text userText;
    private User currentUser;
    private User selectedUser;
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

    void goBack(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboardAdmin.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) userText.getScene().getWindow();
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();
            dashboardAdmin dashboardAdmin = fxmlLoader.getController();
            dashboardAdmin .setUser(currentUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void Annuler(ActionEvent event) {
        goBack();
    }

    @FXML
    void Confirmer(ActionEvent event) {
        UserService userService = new UserService();
        String nomUser = nomField.getText();
        String prenomUser = prenomField.getText();
        String adrUser = adresseField.getText();
        String emailUsr = emailField.getText();
        String image = imageFullPath.getText();
        int numtel = Integer.parseInt(numField.getText());

        if(nomUser.isEmpty() || prenomUser.isEmpty() || adrUser.isEmpty() || emailUsr.isEmpty())
        {
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
        if(!pattern.matcher(emailUsr).matches())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Email field isn't in the correct format");
            alert.show();
            return;
        }

        String role = roleCombo.getValue();

        if(selectedUser != null)
        {
            User user = new User(selectedUser.getId_user(), nomUser,prenomUser,adrUser,emailUsr,selectedUser.getPassword(),numtel,role,image);
            userService.update(user);
        }else{
            User user = new User(0,nomUser,prenomUser,adrUser,emailUsr,"password",numtel,role,image);
            userService.add(user);
        }
        goBack();

    }

    @FXML
    void OpenUtilisateur(ActionEvent event) {
        goBack();
    }

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
/*
    @FXML
    void initialize() {
        assert adresseField != null : "fx:id=\"adresseField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert nomField != null : "fx:id=\"nomField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert numField != null : "fx:id=\"numField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert prenomField != null : "fx:id=\"prenomField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert roleCombo != null : "fx:id=\"roleCombo\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert userText != null : "fx:id=\"userText\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";

    }*/

    public void setUser(User user) {
        currentUser = user;
        userText.setText(user.getNomuser() + " " + user.getPrenomuser());
    }

    public void setSelectedUser(User user) {
        selectedUser = user;

        nomField.setText(user.getNomuser());
        adresseField.setText(user.getAdrUser());
        emailField.setText(user.getEmailUsr());
        numField.setText(String.valueOf(user.getNumtel()));
        prenomField.setText(user.getPrenomuser());
        roleCombo.setValue(user.getRole());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert adresseField != null : "fx:id=\"adresseField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert nomField != null : "fx:id=\"nomField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert numField != null : "fx:id=\"numField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert prenomField != null : "fx:id=\"prenomField\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert roleCombo != null : "fx:id=\"roleCombo\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
        assert userText != null : "fx:id=\"userText\" was not injected: check your FXML file 'dashboardAdminFormUser.fxml'.";
    }
}