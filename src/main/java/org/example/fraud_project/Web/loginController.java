package org.example.fraud_project.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
    // Controller doesn't need changes for successful login redirection.
    @GetMapping("/login")
    public String showLoginForm(
            @RequestParam(value = "error", required = false) String error,
            Model model) {

        // Add error message if login failed
        if (error != null) {
            model.addAttribute("error", "Invalid email or password. Please try again.");
        }

        return "login"; // Return the login.html view
    }
}
