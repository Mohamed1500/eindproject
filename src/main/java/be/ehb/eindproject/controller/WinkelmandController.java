    // ...existing code...
package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.Product;
import be.ehb.eindproject.model.WinkelmandItem;
import be.ehb.eindproject.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WinkelmandController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/winkelmand/toevoegen")
    public String voegToeAanWinkelmand(@RequestParam Long productId, HttpSession session, Model model) {
        List<WinkelmandItem> winkelmand = (List<WinkelmandItem>) session.getAttribute("winkelmand");
        if (winkelmand == null) {
            winkelmand = new ArrayList<>();
        }
        boolean gevonden = false;
        for (WinkelmandItem item : winkelmand) {
            if (item.getProductId().equals(productId)) {
                // Controleer voorraad
                Product p = productRepository.findById(productId);
                if (p != null && p.getVoorraad() > 0) {
                    item.setAantal(item.getAantal() + 1);
                    // Verlaag voorraad
                    if (productRepository instanceof be.ehb.eindproject.repository.InMemoryProductRepository repo) {
                        repo.verlaagVoorraad(productId, 1);
                    }
                }
                gevonden = true;
                break;
            }
        }
        if (!gevonden) {
            Product p = productRepository.findById(productId);
            if (p != null && p.getVoorraad() > 0) {
                winkelmand.add(new WinkelmandItem(p.getId(), p.getNaam(), 1));
                if (productRepository instanceof be.ehb.eindproject.repository.InMemoryProductRepository repo) {
                    repo.verlaagVoorraad(productId, 1);
                }
            }
        }
        session.setAttribute("winkelmand", winkelmand);
        session.setAttribute("melding", "Product toegevoegd aan winkelmandje!");
        return "redirect:/catalogus";
    }

    @GetMapping("/winkelmand")
    public String toonWinkelmand(Model model, HttpSession session) {
        List<WinkelmandItem> winkelmand = (List<WinkelmandItem>) session.getAttribute("winkelmand");
        if (winkelmand == null) {
            winkelmand = new ArrayList<>();
        }
        model.addAttribute("winkelmand", winkelmand);
        return "winkelmand";
    }
    @PostMapping("/winkelmand/verwijderen")
    public String verwijderUitWinkelmand(@RequestParam Long productId, HttpSession session) {
        List<WinkelmandItem> winkelmand = (List<WinkelmandItem>) session.getAttribute("winkelmand");
        if (winkelmand == null) {
            winkelmand = new ArrayList<>();
        }
        for (WinkelmandItem item : winkelmand) {
            if (item.getProductId().equals(productId)) {
                // Verhoog voorraad in repository
                Product p = productRepository.findById(productId);
                if (p != null && productRepository instanceof be.ehb.eindproject.repository.InMemoryProductRepository repo) {
                    repo.verlaagVoorraad(productId, -1); // verhoog voorraad met 1
                }
                if (item.getAantal() > 1) {
                    item.setAantal(item.getAantal() - 1);
                } else {
                    winkelmand.remove(item);
                }
                break;
            }
        }
        session.setAttribute("winkelmand", winkelmand);
        return "redirect:/winkelmand";
    }
    @PostMapping("/winkelmand/verwijderAlles")
    public String verwijderAllesUitWinkelmand(@RequestParam Long productId, HttpSession session) {
        List<WinkelmandItem> winkelmand = (List<WinkelmandItem>) session.getAttribute("winkelmand");
        if (winkelmand == null) {
            winkelmand = new ArrayList<>();
        }
        WinkelmandItem teVerwijderen = null;
        for (WinkelmandItem item : winkelmand) {
            if (item.getProductId().equals(productId)) {
                // Verhoog voorraad met het aantal dat verwijderd wordt
                Product p = productRepository.findById(productId);
                if (p != null && productRepository instanceof be.ehb.eindproject.repository.InMemoryProductRepository repo) {
                    repo.verlaagVoorraad(productId, -item.getAantal());
                }
                teVerwijderen = item;
                break;
            }
        }
        if (teVerwijderen != null) {
            winkelmand.remove(teVerwijderen);
        }
        session.setAttribute("winkelmand", winkelmand);
        return "redirect:/winkelmand";
    }
}
