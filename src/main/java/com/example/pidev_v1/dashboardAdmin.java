package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardAdmin implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TableColumn<?, ?> adresseCo;

    @FXML
    private TableColumn<?, ?> emailCo;

    @FXML
    private TableColumn<?, ?> nomCo;

    @FXML
    private TableColumn<?, ?> numCo;

    @FXML
    private TableColumn<?, ?> prenomCo;

    @FXML
    private TableColumn<?, ?> roleCo;

    @FXML
    private TableView<User> table;
    @FXML
    private Text userText;
    private User currentUser;
    private User selectedUser;

    @FXML
    private Button supprimerButton;
    @FXML
    private Button modifierButton;

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

    @FXML
    void initialize() {
        assert userText != null : "fx:id=\"userText\" was not injected: check your FXML file 'dashboardAdmin.fxml'.";

        nomCo.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        prenomCo.setCellValueFactory(new PropertyValueFactory<>("prenomuser"));
        adresseCo.setCellValueFactory(new PropertyValueFactory<>("AdrUser"));
        emailCo.setCellValueFactory(new PropertyValueFactory<>("EmailUsr"));
        numCo.setCellValueFactory(new PropertyValueFactory<>("Numtel"));
        roleCo.setCellValueFactory(new PropertyValueFactory<>("Role"));
        showUsers();
    }

    void showUsers()
    {
        UserService userService = new UserService();
        ObservableList<User> userList = FXCollections.observableArrayList(userService.afficher());

        table.setItems(userList);
        table.setOnMouseClicked((event)->{
            handleTableClicked();
        });

        modifierButton.setDisable(true);
        supprimerButton.setDisable(true);
    }

    private void handleTableClicked() {
        selectedUser = table.getSelectionModel().getSelectedItem();
        modifierButton.setDisable(false);
        supprimerButton.setDisable(false);
    }

    public void setUser(User user) {
        currentUser = user;
        userText.setText(currentUser.getNomuser()+" "+currentUser.getPrenomuser());
    }

    @FXML
    void SupprimerUser(ActionEvent event) {
        if(selectedUser != null)
        {
            UserService userService = new UserService();
            userService.remove(selectedUser);
            showUsers();
        }
    }

    @FXML
    void addUser(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboardAdminFormUser.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) userText.getScene().getWindow();
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();
            dashboardAdminFormUser dashboardAdminFormUser = fxmlLoader.getController();
            dashboardAdminFormUser.setUser(currentUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void modifierUser(ActionEvent event) {
        if(selectedUser != null)
        {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboardAdminFormUser.fxml"));
                AnchorPane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) userText.getScene().getWindow();
                stage.setTitle("Dashboard");
                stage.setScene(scene);
                stage.show();
                dashboardAdminFormUser dashboardAdminFormUser = fxmlLoader.getController();
                dashboardAdminFormUser.setUser(currentUser);
                dashboardAdminFormUser.setSelectedUser(selectedUser);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert userText != null : "fx:id=\"userText\" was not injected: check your FXML file 'dashboardAdmin.fxml'.";

        nomCo.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        prenomCo.setCellValueFactory(new PropertyValueFactory<>("prenomuser"));
        adresseCo.setCellValueFactory(new PropertyValueFactory<>("AdrUser"));
        emailCo.setCellValueFactory(new PropertyValueFactory<>("EmailUsr"));
        numCo.setCellValueFactory(new PropertyValueFactory<>("Numtel"));
        roleCo.setCellValueFactory(new PropertyValueFactory<>("Role"));
        showUsers();
    }*/
}
