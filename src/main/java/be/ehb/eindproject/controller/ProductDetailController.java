package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.Product;
import be.ehb.eindproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductDetailController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return "redirect:/catalogus";
        }
        model.addAttribute("product", product);
        return "productdetail";
    }
}
