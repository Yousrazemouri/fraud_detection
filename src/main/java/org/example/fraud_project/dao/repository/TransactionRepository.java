package org.example.fraud_project.dao.repository;

import org.example.fraud_project.dao.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Custom query methods (if needed) can be added here
    // For example, to find transactions by user ID (if needed)
    // List<Transaction> findByUtilisateurId(Long utilisateurId);
}
