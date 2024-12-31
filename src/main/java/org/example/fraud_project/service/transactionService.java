package org.example.fraud_project.service;

import org.example.fraud_project.dao.entity.Transaction;
import org.example.fraud_project.dao.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class transactionService {
    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;

    // Constructor Injection
    @Autowired
    public transactionService(TransactionRepository transactionRepository, RestTemplate restTemplate) {
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplate;
    }

    public Transaction saveTransaction(Transaction transaction) {
        // Send transaction data to Flask API for fraud prediction
        String apiUrl = "http://127.0.0.1:5001/predict"; // Flask API URL
        String prediction = restTemplate.postForObject(apiUrl, transaction, String.class);
        transaction.setPredictionResult(prediction);

        // Save the transaction
        return transactionRepository.save(transaction);
    }
}


