package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.Product;
import be.ehb.eindproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogusController {
    private final ProductRepository productRepository;

    @Autowired
    public CatalogusController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/catalogus")
    public String toonCatalogus(
            @RequestParam(required = false) String zoek,
            @RequestParam(required = false) String categorie,
            Model model) {
        List<Product> producten;
        if (zoek != null && !zoek.isEmpty() && categorie != null && !categorie.isEmpty()) {
            producten = productRepository.findByNaamAndCategorie(zoek, categorie);
        } else if (zoek != null && !zoek.isEmpty()) {
            producten = productRepository.findByNaamContainsIgnoreCase(zoek);
        } else if (categorie != null && !categorie.isEmpty()) {
            producten = productRepository.findByCategorie(categorie);
        } else {
            producten = productRepository.findAll();
        }
        model.addAttribute("producten", producten);
        model.addAttribute("zoek", zoek);
        model.addAttribute("categorie", categorie);
        return "catalogus";
    }
}
