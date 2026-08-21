package be.ehb.eindproject.controller;

import be.ehb.eindproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) {
        username = username.trim();
        email = email.trim().toLowerCase();
        model.addAttribute("username", username);
        model.addAttribute("email", email);

        if (!username.matches("[A-Za-z0-9_-]{3,30}")) {
            model.addAttribute("error", "Gebruikersnaam moet 3 tot 30 letters, cijfers, _ of - bevatten.");
            return "register";
        }
        if (password.length() < 8) {
            model.addAttribute("error", "Je wachtwoord moet minstens 8 tekens bevatten.");
            return "register";
        }
        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            model.addAttribute("error", "Vul een geldig e-mailadres in.");
            return "register";
        }
        if (userService.usernameExists(username)) {
            model.addAttribute("error", "Gebruikersnaam bestaat al");
            return "register";
        }
        if (userService.emailExists(email)) {
            model.addAttribute("error", "E-mailadres bestaat al");
            return "register";
        }
        userService.registerNewUser(username, password, email);
        return "redirect:/login?registerSuccess";
    }
}

