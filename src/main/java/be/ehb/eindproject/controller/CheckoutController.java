
package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.Product;
import be.ehb.eindproject.repository.ProductRepository;
import be.ehb.eindproject.model.Lening;
import be.ehb.eindproject.service.LeningService;
import be.ehb.eindproject.model.User;
import be.ehb.eindproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import be.ehb.eindproject.model.WinkelmandItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LeningService leningService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        List<WinkelmandItem> winkelmand = (List<WinkelmandItem>) session.getAttribute("winkelmand");
        if (winkelmand == null) {
            winkelmand = new ArrayList<>();
        }
        model.addAttribute("winkelmand", winkelmand);
        return "checkout";
    }

    @PostMapping("/checkout/bevestigen")
        public String bevestigCheckout(
            @RequestParam("afhaaldatum") String afhaaldatum,
            @RequestParam("einddatum") String einddatum,
            @RequestParam(value = "opmerkingen", required = false) String opmerkingen,
            HttpSession session, Model model, java.security.Principal principal) {
        java.time.LocalDate gekozenDatum = java.time.LocalDate.parse(afhaaldatum);
        java.time.LocalDate eindDatum = java.time.LocalDate.parse(einddatum);
        java.time.LocalDate vandaag = java.time.LocalDate.now();
        if (gekozenDatum.isBefore(vandaag)) {
            List<WinkelmandItem> winkelmand = (List<WinkelmandItem>) session.getAttribute("winkelmand");
            if (winkelmand == null) {
                winkelmand = new ArrayList<>();
            }
            model.addAttribute("winkelmand", winkelmand);
            model.addAttribute("error", "De gekozen afhaaldatum is al gepasseerd. Kies een geldige datum.");
            return "checkout";
        }
        if (eindDatum.isBefore(gekozenDatum)) {
            List<WinkelmandItem> winkelmand = (List<WinkelmandItem>) session.getAttribute("winkelmand");
            if (winkelmand == null) {
                winkelmand = new ArrayList<>();
            }
            model.addAttribute("winkelmand", winkelmand);
            model.addAttribute("error", "De einddatum mag niet vóór de afhaaldatum liggen.");
            return "checkout";
        }

        List<WinkelmandItem> winkelmand = (List<WinkelmandItem>) session.getAttribute("winkelmand");

        if (winkelmand == null || winkelmand.isEmpty()) {
            model.addAttribute("winkelmand", new ArrayList<>());
            model.addAttribute("error", "Je winkelmandje is leeg.");
            return "checkout";
        }

        for (WinkelmandItem item : winkelmand) {
            Product product = productRepository.findById(item.getProductId());
            if (product == null || product.getVoorraad() < item.getAantal()) {
                model.addAttribute("winkelmand", winkelmand);
                model.addAttribute("error", "Er zijn niet genoeg " + item.getNaam() + " meer beschikbaar. Controleer je winkelmandje en probeer opnieuw.");
                return "checkout";
            }
        }

        // Verlaag voorraad en voeg leningen toe nadat de volledige bestelling is gecontroleerd.
        if (winkelmand != null && principal != null) {
            User user = userRepository.findByUsername(principal.getName()).orElse(null);
            for (WinkelmandItem item : winkelmand) {
                Product p = productRepository.findById(item.getProductId());
                if (p != null && p.getVoorraad() >= item.getAantal()) {
                    p.setVoorraad(p.getVoorraad() - item.getAantal());
                    if (user != null) {
                        Lening lening = new Lening(null, user.getId(), p.getId(), p.getNaam(), item.getAantal(), afhaaldatum, einddatum, opmerkingen);
                        leningService.saveLening(lening);
                    }
                }
            }
        }
        session.removeAttribute("winkelmand");
        model.addAttribute("bevestiging", "Je reservatie is succesvol ontvangen!");
        model.addAttribute("afhaaldatum", afhaaldatum);
        model.addAttribute("einddatum", einddatum);
        model.addAttribute("opmerkingen", opmerkingen);
        return "bevestiging";
    }
}
