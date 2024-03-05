package com.example.pidev_v1.API;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;

public class Payment {
    public static void main(String[] args) {
// Set your secret key here
        Stripe.apiKey = "sk_test_51Oq3VTC4DfcX81jOg4IgEi0DJ88o63817Nf6dAFOFyj6G249vlbaCGEipesnC5cEIaCzsT3PD2j0SGXrkmzGNWUn00AqxMnm2A";

        try {
// Retrieve your account information
            Account account = Account.retrieve();
            System.out.println("Account ID: " + account.getId());
// Print other account information as needed
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
