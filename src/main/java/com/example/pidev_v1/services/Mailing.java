package com.example.pidev_v1.services;
import javafx.scene.control.Alert;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class Mailing {
    public static void send(String recipient, String messageToSend, String subject) {
        System.out.println("Sending email");
        // Sender's email ID needs to be mentioned
        String from = "azizowski10@gmail.com"; // Update with your email address
        final String username = "azizowski10@gmail.com"; // Update with your email address
        final String password = "fwab eevi jpij yhzc"; // Update with your password
        // Assuming you are sending email through smtp.gmail.com
        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465"); // Use the SSL port for Gmail
        props.put("mail.smtp.ssl.enable", "true");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(messageToSend);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Forgot Password");
            alert.setHeaderText(null);
            alert.setContentText("Sent message successfully....");
            alert.showAndWait();
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
