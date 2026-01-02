package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.User;
import be.ehb.eindproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfielController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profiel")
    public String profiel(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        String email = userRepository.findByUsername(username)
                .map(User::getEmail)
                .orElse("");
        model.addAttribute("email", email);
        return "profiel";
    }
}
