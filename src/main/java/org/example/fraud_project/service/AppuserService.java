package org.example.fraud_project.service;
import org.example.fraud_project.dao.entity.AppUser;
import org.example.fraud_project.dao.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppuserService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    // Constructor with @Autowired annotation
    @Autowired
    public AppuserService(final UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = this.utilisateurRepository.findByEmail(email);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        } else {
            return User.withUsername(appUser.getEmail())
                    .password(appUser.getPassword())
                    .roles(appUser.getRole()) // Assuming 'role' is a single role, otherwise adjust to your logic
                    .build();
        }
    }
}
