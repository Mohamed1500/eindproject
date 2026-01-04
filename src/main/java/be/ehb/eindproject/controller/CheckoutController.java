
package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.Product;
import be.ehb.eindproject.repository.ProductRepository;
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
            @RequestParam(value = "opmerkingen", required = false) String opmerkingen,
            HttpSession session, Model model) {
        java.time.LocalDate gekozenDatum = java.time.LocalDate.parse(afhaaldatum);
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

        // Verlaag voorraad pas bij geslaagde checkout
        List<WinkelmandItem> winkelmand = (List<WinkelmandItem>) session.getAttribute("winkelmand");
        if (winkelmand != null) {
            for (WinkelmandItem item : winkelmand) {
                Product p = productRepository.findById(item.getProductId());
                if (p != null && p.getVoorraad() >= item.getAantal()) {
                    p.setVoorraad(p.getVoorraad() - item.getAantal());
                }
            }
        }
        session.removeAttribute("winkelmand");
        model.addAttribute("bevestiging", "Je reservatie is succesvol ontvangen!");
        model.addAttribute("afhaaldatum", afhaaldatum);
        model.addAttribute("opmerkingen", opmerkingen);
        return "bevestiging";
    }
}
