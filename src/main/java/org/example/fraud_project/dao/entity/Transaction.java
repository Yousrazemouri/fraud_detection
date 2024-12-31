package org.example.fraud_project.dao.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@NoArgsConstructor // Generates a no-arguments constructor
@AllArgsConstructor
@Table(name = "Transaction")// Generates an all-arguments constructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double time;
    private double v1;
    private double v2;
    private double v3;
    private double v4;
    private double v5;
    private double v6;
    private double v7;
    private double v8;
    private double v9;
    private double v10;
    private double v11;
    private double v12;
    private double v13;
    private double v14;
    private double v15;
    private double v16;
    private double v17;
    private double v18;
    private double v19;
    private double v20;
    private double v21;
    private double v22;
    private double v23;
    private double v24;
    private double v25;
    private double v26;
    private double v27;
    private double v28;
    private double amount;

    private String predictionResult; // Result from Flask API

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private AppUser utilisateur;
}