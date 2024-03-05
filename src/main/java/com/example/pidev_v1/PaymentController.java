package com.example.pidev_v1;
//package sun.util.locale;
import com.example.pidev_v1.API.Payment;
import com.example.pidev_v1.entities.Panier;
import com.example.pidev_v1.entities.User;
import com.example.pidev_v1.services.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.lang.String;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class PaymentController implements Initializable {
    @FXML
    private Spinner< Integer> MM;

    private SpinnerValueFactory MM1;

    private SpinnerValueFactory YY1;

    private SpinnerValueFactory CV1;
    /*public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
    }*/



    @FXML
    private Spinner<Integer> YY;

    @FXML
    private Button back_btn;

    @FXML
    private TextField client_name;

    @FXML
    private Spinner<Integer> cvc;

    @FXML
    private TextField email;

    @FXML
    private TextField num_card;

    @FXML
    private Button pay_btn;

    @FXML
    private Label total;

    private float total_pay;
    private Panier panier;
    private TextField spinnerTextField;
    private UserService userService=new UserService();
    private User client;


    // Replace with your actual Stripe secret key (never store it directly in code)
    //private final String STRIPE_SECRET_KEY = "sk_test_51Oq3VTC4DfcX81jOg4IgEi0DJ88o63817Nf6dAFOFyj6G249vlbaCGEipesnC5cEIaCzsT3PD2j0SGXrkmzGNWUn00AqxMnm2A";



    private boolean isEmpty(TextField field) {
        return field.getText().trim().isEmpty();
    }
    private String getSecretKey() {
        return System.getenv("sk_test_51Oq3VTC4DfcX81jOg4IgEi0DJ88o63817Nf6dAFOFyj6G249vlbaCGEipesnC5cEIaCzsT3PD2j0SGXrkmzGNWUn00AqxMnm2A");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String STRIPE_SECRET_KEY = "sk_test_51Oq3VTC4DfcX81jOg4IgEi0DJ88o63817Nf6dAFOFyj6G249vlbaCGEipesnC5cEIaCzsT3PD2j0SGXrkmzGNWUn00AqxMnm2A";

        try {
            // Create a PaymentIntent with other payment details
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(1000L) // Amount in cents (e.g., $10.00)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

// If the payment was successful, display a success message
            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
        } catch (StripeException e) {
// If there was an error processing the payment, display the error message
            System.out.println("Payment failed. Error: " + e.getMessage());
        }


        // Assuming you have a way to get the currently logged-in user ID (e.g., from session)
        int loggedInUserId = 0;
        if (client != null) {
            loggedInUserId = client.getId_user();
            // Rest of your code
        } else {
            // Handle the case where client is null
        }

        // Retrieve the user object using the UserService
        User user = userService.getUserByEmail(String.valueOf(loggedInUserId)); // Replace with appropriate method if ID is not email
        client = userService.getUserByEmail(String.valueOf(loggedInUserId));

        if (client != null) {
            client_name.setText(client.getNomuser() + " " + client.getPrenomuser());
            email.setText(client.getEmailUsr());
        } else {
            // Handle the case where user is not retrieved (e.g., display an error message or redirect to login)
            System.out.println("error");
       /* if (user != null) {
            client_name.setText(user.getNomuser() + " " + user.getPrenomuser());
            email.setText(user.getEmailUsr());*/
        }
        Spinner<Integer> MM = new Spinner<>();
        Spinner<Integer> YY = new Spinner<>();
        Spinner<Integer> cvc = new Spinner<>();

        SpinnerValueFactory<Integer> valueFactory_month = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, LocalDate.now().getMonthValue(), 1);
        SpinnerValueFactory<Integer> valueFactory_year = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999999, LocalDate.now().getYear(), 1);
        SpinnerValueFactory<Integer> valueFactory_cvc = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 1, 1);

        MM.setValueFactory(valueFactory_month);
        YY.setValueFactory(valueFactory_year);
        cvc.setValueFactory(valueFactory_cvc);

        spinnerTextField = cvc.getEditor();
        spinnerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                cvc.getValueFactory().setValue(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {
                // Handle invalid input (e.g., display an error message)
            }
        });
    }

    public void setData(Panier p, float total_price) {
        this.panier = p;
        this.total_pay = total_price;
        String total_txt = "Total : " + String.valueOf(total_pay) + " Dt.";
        total.setText(total_txt);
    }
    private boolean isValidEmail(String email) {
        // Implement your logic to validate email format using a regular expression
        String regex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean check_cvc(int cvc) {
        // Check if the CVC code is a 3- or 4-digit number
        if (String.valueOf(cvc).length() != 3 && String.valueOf(cvc).length() != 4) {
            return false;
        }

        // You can add further validation based on specific card types
        // (e.g., some card types have specific ranges for their CVC codes)

        // Assuming the CVC code is valid after the length check
        return true;
    }


    private boolean check_expDate(int year, int month) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Check if the entered year is in the future
        if (year < currentDate.getYear()) {
            return false;
        }

        // Check if the entered month is in the future (if the year is the same)
        if (year == currentDate.getYear() && month < currentDate.getMonthValue()) {
            return false;
        }

        // Assuming the expiration date is valid after these checks
        return true;
    }
    @FXML
    private void payment(ActionEvent event) throws StripeException {
        // Input validation
        if  (client_name.getText().isEmpty() ||
                isEmpty(email) ||
                !isValidEmail(email.getText()) ||
                isEmpty(num_card) ||
                !check_cvc(cvc.getValue()) ||
                !check_expDate((Integer) YY.getValue(), (int) MM.getValue()))  {
            return; // Handle validation errors, e.g., display appropriate messages
        }}}

        /*Payment processing using Stripe
        try {
            Stripe.apiKey = "sk_test_51Oq3VTC4DfcX81jOg4IgEi0DJ88o63817Nf6dAFOFyj6G249vlbaCGEipesnC5cEIaCzsT3PD2j0SGXrkmzGNWUn00AqxMnm2A";;

            // Set payment details (replace with actual values based on user input)
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) Math.round(total_pay * 100)) // Convert to cents
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            // Display payment success or failure message
            System.out.println(intent.getStatus().equals("succeeded") ?
                    "Payment successful. PaymentIntent ID: " + intent.getId() :  "Payment failed. Error:");


        }catch (StripeException e) {
        // Display a user-friendly error message based on the exception
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Payment Error");
        alert.setHeaderText("Payment could not be processed");
        alert.setContentText(e.getMessage());
        alert.showAndWait();

        // Optionally, log the exception for debugging or troubleshooting
        // e.printStackTrace();
    }}}*/