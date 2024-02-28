package com.example.pidev_v1.API;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.conversations.v1.AddressConfiguration;
import com.twilio.type.PhoneNumber;
public class TwilioSMS {

    // Vos identifiants Twilio
    public static final String ACCOUNT_SID = "AC61d3e474c6dcaf986f91d93279f39cc3";
    public static final String AUTH_TOKEN = "fe73b6aba085bb54d6e074f23ff06bb0";

    public static void sendSMS(int number,int code) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Recipient phone number
        String toPhoneNumber = "+216"+number;

        // Sender phone number (Twilio number)
        String fromPhoneNumber = "+17753838901";

        // SMS message content
        String message = "Bonsoir Ayouta la Swiftie c'est l'annéee: " + code;
        // Send the SMS message
        Message twilioMessage;
        twilioMessage = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                message).create();

        // Print the message SID (identifier) and status
        System.out.println("Message SID: " + twilioMessage.getSid());
        System.out.println("Message status: " + twilioMessage.getStatus());
    }

    public static void main(String[] args) {
        // Exemple d'utilisation
        //sendSMS("+14155552671", "Test d'envoi de SMS avec Twilio depuis Java !");
        sendSMS(23067230,
                2324);


    }



}
