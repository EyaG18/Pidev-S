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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TFEmail;

    @FXML
    private TextField TFPassword;
    private static final String OAUTH_CALLBACK_URL = "http://localhost/callback";
    private String userId = "";
    private String userName = "";
    private String userEmail = "";
    private String userProfilePictureUrl = "";


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
        UserService userService = new UserService();
        User user = userService.getUserByEmail(email);

        /*if (user == null || !verifyPassword(password,user.getPassword())) {
            showAlert("Error", "Invalid email or password.");
            return;
        }*/
        loadUserDashboard(user);
    }

    private void loadUserDashboard(User user) {
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
                loadUserDashboard(user);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (Objects.equals(user.getRole(), "Client" ))
        {
            try {
                System.out.println("c'est un client qui se connecte");
                fxmlLoader = new FXMLLoader(getClass().getResource("FeedProduitsCoteClients.fxml"));
                BorderPane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) TFEmail.getScene().getWindow();
                stage.setTitle("Arya : Votre Style en Clic : Home Page");
                stage.setScene(scene);
                FeedProduitsCoteClientsController controller = fxmlLoader.getController();
                controller.setUser(user);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (Objects.equals(user.getRole(), "Employee" ))
        {
            try {
                //fxmlLoader = new FXMLLoader(getClass().getResource("dashboardEmployee.fxml"));
                fxmlLoader = new FXMLLoader(getClass().getResource("WelcomeBack.fxml"));
                BorderPane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) TFEmail.getScene().getWindow();
                stage.setTitle("Arya : Votre Style en Clic : Bienvenue Cher(e) Employé(e)");
                stage.setScene(scene);
                welcomeControllers controller = fxmlLoader.getController();
                controller.setUser(user);
                loadUserDashboard(user);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
       /* else {
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
        }*/
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


    boolean verifyPassword(String enteredPassword, String storedHashedPassword) {
        String hashedEnteredPassword = hashPassword(enteredPassword);

        return hashedEnteredPassword.equals(storedHashedPassword);
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle exception appropriately
            return null; // Or throw an exception
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert TFEmail != null : "fx:id=\"TFEmail\" was not injected: check your FXML file 'Login.fxml'.";
        assert TFPassword != null : "fx:id=\"TFPassword\" was not injected: check your FXML file 'Login.fxml'.";
    }


    private void loadLoginPage(String url) {
        // Create a new WebView
        WebView newWebView = new WebView();
        WebEngine newWebEngine = newWebView.getEngine();

        // Load the login page in the new WebView
        newWebEngine.load(url);
        newWebEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            if (newValue.startsWith(OAUTH_CALLBACK_URL)) {
                System.out.println("OAUTH Call back");
                // Extract code from URL
                String[] parts = newValue.split("code=");
                if (parts.length > 1) {
                    String code = parts[1];
                    // Handle OAuth callback
                    handleOAuthCallback(code);
                }
            }
        });
        // Show the new WebView in a new stage
        showWebView(newWebView);
    }

    private Stage showWebView(WebView newWebView) {
        Stage stage = new Stage();
        stage.setScene(new Scene(newWebView));
        stage.show();
        return stage;
    }

    public void LoginWithGmail(MouseEvent event) {
        String authUrl = "https://accounts.google.com/o/oauth2/auth?" +
                "client_id=YOUR_CLIENT_ID" +
                "&redirect_uri=" + OAUTH_CALLBACK_URL +
                "&response_type=code" +
                "&scope=email%20profile";
        loadLoginPage(authUrl);
    }

    public void LoginWithFacebook(MouseEvent event) {

        String authUrl = "https://www.facebook.com/v12.0/dialog/oauth?" +
                "client_id=1080980596556235" +
                "&redirect_uri=http://localhost/callback" +
                "&scope=email"; // Adjust scope as needed

        loadLoginPage(authUrl);
    }

    public void handleOAuthCallback(String code) {
        String appId = "1080980596556235";
        String appSecret = "00514a42f31eb9e369157b571d16d451";
        String redirectUri = "http://localhost/callback";

        try {
            String tokenEndpoint = "https://graph.facebook.com/v12.0/oauth/access_token";
            String params = String.format("client_id=%s&redirect_uri=%s&client_secret=%s&code=%s",
                    URLEncoder.encode(appId, "UTF-8"),
                    URLEncoder.encode(redirectUri, "UTF-8"),
                    URLEncoder.encode(appSecret, "UTF-8"),
                    URLEncoder.encode(code, "UTF-8"));

            URL url = new URL(tokenEndpoint + "?" + params);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response to extract the access token
                JSONObject jsonResponse = new JSONObject(response.toString());
                String accessToken = jsonResponse.getString("access_token");

                // Now make a request to get user information using the access token
                String userInfoEndpoint = "https://graph.facebook.com/me?fields=id,name,email,picture&access_token=" + accessToken;
                URL userInfoUrl = new URL(userInfoEndpoint);
                HttpURLConnection userInfoCon = (HttpURLConnection) userInfoUrl.openConnection();
                userInfoCon.setRequestMethod("GET");

                int userInfoResponseCode = userInfoCon.getResponseCode();
                if (userInfoResponseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader userInfoIn = new BufferedReader(new InputStreamReader(userInfoCon.getInputStream()));
                    StringBuffer userInfoResponse = new StringBuffer();
                    while ((inputLine = userInfoIn.readLine()) != null) {
                        userInfoResponse.append(inputLine);
                    }
                    userInfoIn.close();

                    // Parse the user information response
                    JSONObject userInfoJson = new JSONObject(userInfoResponse.toString());
                    userId = userInfoJson.getString("id");
                    userName = userInfoJson.getString("name");
                    userEmail = userInfoJson.getString("email");

                    // Check if the user has a profile picture
                    if (userInfoJson.has("picture")) {
                        userProfilePictureUrl = userInfoJson.getJSONObject("picture").getJSONObject("data").getString("url");
                        System.out.println("User Profile Picture URL: " + userProfilePictureUrl);
                    } else {
                        System.out.println("User has no profile picture available.");
                    }

                    System.out.println("User ID: " + userId);
                    System.out.println("User Name: " + userName);
                    System.out.println("User Email: " + userEmail);
                    Stage webViewStage = showWebView(new WebView()); // Create a dummy WebView to retrieve its stage
                    webViewStage.close();

                    handleDatabaseCheck();
                } else {
                    System.out.println("Error in fetching user information. Response code: " + userInfoResponseCode);
                }
            } else {
                System.out.println("Error in exchanging code for access token. Response code: " + responseCode);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    private void handleDatabaseCheck() {
        if(!Objects.equals(userEmail, ""))
        {
            UserService userService = new UserService();
            User facebookUser = userService.getUserByEmail(userEmail);
            if (facebookUser != null){
                loadUserDashboard(facebookUser);
            }else{
                String[] parts = userName.split(" ");
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
                User newUser = new User(firstName,lastName,"",userEmail,"",0,"Client","");
                userService.add(newUser);
                loadUserDashboard(newUser);
            }
        }
    }
}