package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.Lening;
import be.ehb.eindproject.service.LeningService;
import be.ehb.eindproject.model.User;
import be.ehb.eindproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class LeningController {
    @Autowired
    private LeningService leningService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/leningen")
    public String mijnLeningen(Model model, Principal principal) {
        if (principal == null) return "redirect:/login";
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        List<Lening> alleLeningen = leningService.getLeningenByUserId(user.getId());
        // Filter alleen leningen met een geldige id
        List<Lening> leningen = new java.util.ArrayList<>();
        for (Lening l : alleLeningen) {
            if (l != null && l.getId() != null) {
                leningen.add(l);
            }
        }
        model.addAttribute("leningen", leningen);
        model.addAttribute("leningAantal", leningen.size());
        return "leningen";
    }

    @PostMapping("/leningen/verwijderen")
    public String verwijderLening(@RequestParam Long leningId, Principal principal) {
        if (principal == null) return "redirect:/login";
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        leningService.deleteLening(leningId, user.getId());
        return "redirect:/leningen";
    }
}
