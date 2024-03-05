package com.example.pidev_v1;

import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.Mailing;
import com.example.pidev_v1.services.UserService;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
    private WebView captchaWebView;
    private WebEngine captchaWebEngine;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CAPTCHA_LENGTH = 6; // Length of the CAPTCHA string

    private static Properties props = new Properties();

    static {
        loadEnv();
    }

    private static void loadEnv() {
        try (InputStream input = new FileInputStream(".env")) {
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
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
    void ResetPassword(ActionEvent event) {
        String email = TFEmail.getText().trim();

        if(email.isEmpty())
        {
            showAlert("Error", "Please fill in the email field.");
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

        String recipient = email; // Replace with the recipient's email address
        String titleMail = "Password Reseted"; // Your message content
        String newpassword = generatePassword();

        UserService userService = new UserService();
        User userForgotPassword = userService.getUserByEmail(email);
        if(userForgotPassword == null)
        {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid email address\n");
            alert.setHeaderText(null);
            alert.setContentText("Email address doesn't exist in database!");
            alert.showAndWait();
            return;
        }
        userForgotPassword.setPassword(hashPassword(newpassword));
        userService.update(userForgotPassword);
        String content = "Your new password for this email is the following: "+newpassword;
        Mailing.send(recipient,content ,titleMail);

    }
    public String generatePassword() {
        String password_pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@$!%*?&";

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        while (!password.toString().matches(password_pattern)) {
            password.setLength(0); // Clear the StringBuilder
            for (int i = 0; i < 8; i++) {
                int randomIndex = random.nextInt(characters.length());
                char randomChar = characters.charAt(randomIndex);
                password.append(randomChar);
            }
        }
        return password.toString();
    }


    public static String getEnv(String key) {
        return props.getProperty(key);
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


        boolean captchaVerified = verifyCaptcha();
        if (captchaVerified) {
            showAlert("CAPTCHA Verification Failed", "The entered CAPTCHA Is Correct");
        } else {
            showAlert("CAPTCHA Verification Failed", "The entered CAPTCHA does not match. Try again.");
            return;
        }

        UserService userService = new UserService();
        User user = userService.getUserByEmail(email);

        if (user == null || !verifyPassword(password,user.getPassword())) {
            showAlert("Error", "Invalid email or password.");
            return;
        }
        loadUserDashboard(user);
    }

    private void loadUserDashboard(User user) {
        String recipient = user.getEmailUsr(); // Replace with the recipient's email address

        String titleMail = "Notification Alert"; // Your message content

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String content = "Someone just logged in your account at " + formattedDateTime;

        Mailing.send(recipient, content, titleMail);

        FXMLLoader fxmlLoader;

        if(Objects.equals(user.getRole(), "administrateur"))
        {
            try {
                fxmlLoader = new FXMLLoader(getClass().getResource("WelcomeBack.fxml"));
                BorderPane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) TFEmail.getScene().getWindow();
                stage.setTitle("Dashboard");
                stage.setScene(scene);
                welcomeControllers controller = fxmlLoader.getController();
                controller.setUser(user);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (Objects.equals(user.getRole(), "Client" ))
        {
            try {
            fxmlLoader = new FXMLLoader(getClass().getResource("welcomeFront.fxml"));
            BorderPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) TFEmail.getScene().getWindow();
            stage.setTitle("Arya : Votre Style en Clic : Home Page");
            stage.setScene(scene);
            welcomeFront controller = fxmlLoader.getController();
            controller.setUser(user);
            stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (Objects.equals(user.getRole(), "Employee" ))
        {
            try {
                fxmlLoader = new FXMLLoader(getClass().getResource("dashboardEmployee.fxml"));
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

    public static String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            captcha.append(CHARACTERS.charAt(index));
        }
        return captcha.toString();
    }

    public static boolean verifyCaptcha() {
        String generatedCaptcha = generateCaptcha();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("CAPTCHA Verification");
        dialog.setHeaderText("Please enter the following CAPTCHA to continue:");
        dialog.setContentText("CAPTCHA: " + generatedCaptcha);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String userInput = result.get();
            return userInput.equals(generatedCaptcha);
        } else {
            return false;
        }
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
                "client_id=" + getEnv("GMAIL_CLIENT_ID") +
                "&redirect_uri=http://localhost/callback" +
                "&response_type=code" +
                "&scope=email%20profile";
        loadLoginPage(authUrl);
    }

    public void LoginWithFacebook(MouseEvent event) {

        String authUrl = "https://www.facebook.com/v12.0/dialog/oauth?" +
                "client_id=" + getEnv("FACEBOOK_CLIENT_ID") +
                "&redirect_uri=http://localhost/callback" +
                "&scope=email"; // Adjust scope as needed

        loadLoginPage(authUrl);
    }

    public void handleOAuthCallbackGmail(String code) {
        String clientId = getEnv("GMAIL_CLIENT_ID");
        String clientSecret = getEnv("GMAIL_CLIENT_SECRET");
        String redirectUri = "http://localhost/callback";

        try {
            // Exchange authorization code for access token
            String tokenEndpoint = "https://oauth2.googleapis.com/token";
            String params = String.format("code=%s&client_id=%s&client_secret=%s&redirect_uri=%s&grant_type=authorization_code",
                    URLEncoder.encode(code, "UTF-8"),
                    URLEncoder.encode(clientId, "UTF-8"),
                    URLEncoder.encode(clientSecret, "UTF-8"),
                    URLEncoder.encode(redirectUri, "UTF-8"));

            URL url = new URL(tokenEndpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            // Send POST request with code to exchange for access token
            con.getOutputStream().write(params.getBytes());

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response to extract the access token
                JSONObject jsonResponse = new JSONObject(response.toString());
                String accessToken = jsonResponse.getString("access_token");

                // Now make a request to get user information using the access token
                String userInfoEndpoint = "https://www.googleapis.com/oauth2/v3/userinfo?access_token=" + accessToken;
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
                    userId = userInfoJson.getString("sub"); // Unique Google ID
                    userEmail = userInfoJson.getString("email");
                    userName = userInfoJson.getString("name");
                    userProfilePictureUrl = userInfoJson.getString("picture");

                    // Now you have the user information, you can use it as needed

                    System.out.println("User ID: " + userId);
                    System.out.println("User Name: " + userName);
                    System.out.println("User Email: " + userEmail);
                    System.out.println("User Profile Picture URL: " + userProfilePictureUrl);

                    // Close the WebView stage
                    Stage webViewStage = showWebView(new WebView());
                    webViewStage.close();

                    // Handle database check or any other action you need
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
