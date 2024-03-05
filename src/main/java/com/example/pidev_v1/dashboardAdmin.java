package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class dashboardAdmin {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView profilePicture;

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
    private ComboBox<String> filterCombo;
    @FXML
    private TextField searchTextField;

    /*@FXML
    void ShowStats(ActionEvent event) {
        BarChart<String, Number> chart = createChart();

        // Create a new stage
        Stage stage = new Stage();
        stage.setTitle("Statistics");

        // Add the chart to the scene
        stage.setScene(new Scene(new Group(chart)));

        // Show the stage
        stage.show();
    }*/
    /*private BarChart<String, Number> createChart() {
        // Sample data for the chart
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("User Statistics");
        xAxis.setLabel("Category");
        yAxis.setLabel("Value");

        UserService userService = new UserService();
        // Add data series to the chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("User Types");
        series.getData().add(new XYChart.Data<>("Admins", userService.countUsersByRole("administrateur")));
        series.getData().add(new XYChart.Data<>("Clients", userService.countUsersByRole("Client")));
        series.getData().add(new XYChart.Data<>("Livreur", userService.countUsersByRole("Livreur")));
        series.getData().add(new XYChart.Data<>("Employee", userService.countUsersByRole("Employee")));

        // Add the series to the chart
        chart.getData().add(series);

        return chart;
    }*/
   /* @FXML
    void searchUsersTable(KeyEvent event) {
        nomCo.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        prenomCo.setCellValueFactory(new PropertyValueFactory<>("prenomuser"));
        adresseCo.setCellValueFactory(new PropertyValueFactory<>("AdrUser"));
        emailCo.setCellValueFactory(new PropertyValueFactory<>("EmailUsr"));
        numCo.setCellValueFactory(new PropertyValueFactory<>("Numtel"));
        roleCo.setCellValueFactory(new PropertyValueFactory<>("Role"));
        UserService userService = new UserService();
        ObservableList<User> userList = FXCollections.observableArrayList(userService.searchByFilter(filterCombo.getValue(),searchTextField.getText()));

        table.setItems(userList);

    }*/
//rBVXKw%1
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

    }

    void showUsers()
    {
        filterCombo.getItems().setAll("Nom", "Prenom", "Adresse","Email","Numero","Role");
        nomCo.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        prenomCo.setCellValueFactory(new PropertyValueFactory<>("prenomuser"));
        adresseCo.setCellValueFactory(new PropertyValueFactory<>("AdrUser"));
        emailCo.setCellValueFactory(new PropertyValueFactory<>("EmailUsr"));
        numCo.setCellValueFactory(new PropertyValueFactory<>("Numtel"));
        roleCo.setCellValueFactory(new PropertyValueFactory<>("Role"));
        UserService userService = new UserService();
        ObservableList<User> userList = FXCollections.observableArrayList(userService.afficher());
        System.out.println(userService.afficher());
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
        showUsers();

        if (!user.getImage().isEmpty()) {
            String imagePath = "/upload/" + user.getImage(); // Replace this with the path to your image in resources
            InputStream inputStream = getClass().getResourceAsStream(imagePath);
            if (inputStream != null) {
                Image image = new Image(inputStream);
                profilePicture.setImage(image);
            } else {
                // Handle the case when the resource stream is null
                System.err.println("Resource stream is null for image: " + imagePath);
                // Optionally, you can set a default image or display an error message
            }
        } else {
            // Handle the case when the user's image path is empty
            System.err.println("Image path is empty for user: " + currentUser.getNomuser());
            // Optionally, you can set a default image or display an error message
        }
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
