package org.example.fraud_project.dao.repository;


import jakarta.transaction.Transactional;

import org.example.fraud_project.dao.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByEmail(String email);
    // Check if a user exists by email

}