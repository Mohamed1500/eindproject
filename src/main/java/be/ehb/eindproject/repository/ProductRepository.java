package be.ehb.eindproject.repository;

import be.ehb.eindproject.model.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findByNaamContainsIgnoreCase(String naam);
    List<Product> findByCategorie(String categorie);
    List<Product> findByNaamAndCategorie(String naam, String categorie);
    Product findById(Long id);
}
