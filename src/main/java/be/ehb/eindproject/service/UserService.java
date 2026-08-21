package be.ehb.eindproject.service;

import be.ehb.eindproject.model.User;
import be.ehb.eindproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public void registerNewUser(String username, String password, String email) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, email);
        userRepository.save(user);
    }
}
