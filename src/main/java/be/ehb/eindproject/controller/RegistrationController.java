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
        if (userService.usernameExists(username)) {
            model.addAttribute("error", "Gebruikersnaam bestaat al");
            return "register";
        }
        userService.registerNewUser(username, password, email);
        return "redirect:/login?registerSuccess";
    }
}

