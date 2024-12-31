package org.example.fraud_project.Web;



import jakarta.validation.Valid;

import org.example.fraud_project.Dto.RegisterDto;
import org.example.fraud_project.dao.entity.AppUser;
import org.example.fraud_project.dao.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AuthControler {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Show registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("registerDto", registerDto);
        model.addAttribute("success", false);
        return "register";  // Your registration HTML page
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String register(
            Model model,
            @Valid @ModelAttribute RegisterDto registerDto,
            BindingResult result) {

        // Check if passwords match
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(new FieldError("registerDto", "confirmPassword", "Passwords do not match"));
        }

        // Check if email is already in use
        AppUser existingUser = utilisateurRepository.findByEmail(registerDto.getEmail());
        if (existingUser != null) {
            result.addError(new FieldError("registerDto", "email", "Email already in use"));
        }

        // If there are validation errors, return to the registration form
        if (result.hasErrors()) {
            return "register";
        }

        // Save the user
        try {
            AppUser newUser = new AppUser();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPhone(registerDto.getPhone());
            newUser.setAddress(registerDto.getAddress());
            newUser.setRole("USER");
            newUser.setCreatedAt(new Date());

            // Encrypt the password
            String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
            newUser.setPassword(encodedPassword);

            utilisateurRepository.save(newUser);

            // Add success message and reset form
            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
        } catch (Exception ex) {
            // Log the exception to the console or a logging system
            System.err.println("Error occurred during registration: " + ex.getMessage());

            // Add a general error message for the user
            model.addAttribute("error", "An unexpected error occurred. Please try again later.");
            return "register"; // Return to the registration page
        }

        return "register";
    }
}

