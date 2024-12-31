package org.example.fraud_project.service;

import org.example.fraud_project.dao.entity.AppUser;
import org.example.fraud_project.dao.repository.UtilisateurRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final UtilisateurRepository utilisateurRepository; // Correct repository name

    // Constructor Injection
    @Autowired
    public EmailService(JavaMailSender emailSender, UtilisateurRepository utilisateurRepository) {
        this.emailSender = emailSender;
        this.utilisateurRepository = utilisateurRepository; // Correctly assign the repository
    }

    public void sendFraudNotification(String userEmail) {
        // Fetch the user from the database
        AppUser user = utilisateurRepository.findByEmail(userEmail); // Correct reference to repository

        if (user != null) {
            // Create email message
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Fraud Alert");
            message.setText("Your recent transaction has been flagged as fraudulent.");

            // Send the email
            emailSender.send(message);
        } else {
            // Handle case where the user is not found (optional)
            System.out.println("User with email " + userEmail + " not found.");
        }
    }
}
