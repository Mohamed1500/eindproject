package be.ehb.eindproject.controller;

import be.ehb.eindproject.repository.LeningRepository;
import be.ehb.eindproject.repository.ProductRepository;
import be.ehb.eindproject.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final LeningRepository leningRepository;

    public AdminController(UserRepository userRepository, ProductRepository productRepository, LeningRepository leningRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.leningRepository = leningRepository;
    }

    @GetMapping("/admin")
    public String dashboard(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("producten", productRepository.findAll());
        model.addAttribute("leningen", leningRepository.findAll());
        return "admin";
    }
}