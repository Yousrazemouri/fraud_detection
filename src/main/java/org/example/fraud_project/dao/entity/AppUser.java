package org.example.fraud_project.dao.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "appuser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use IDENTITY for auto-increment
    private Long id_user;

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Column(unique = true, nullable = false)
    private String phone;

    private String address;
    private String email;

    private String role;  // Change to lowercase for consistency

    private Date createdAt;

    // Optional: Constructor for initialization
    public AppUser(String firstName, String lastName, String username, String password, String phone, String address, String email, String role, Date createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }
}
