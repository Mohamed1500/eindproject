package be.ehb.eindproject.repository;

import be.ehb.eindproject.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> producten = new ArrayList<>();

    public InMemoryProductRepository() {
        producten.add(new Product(1L, "Lichtpaneel", "Belichting", "Krachtig LED-lichtpaneel.", 5, "lichtpaneel.jpg"));
        producten.add(new Product(2L, "Podiumelement", "Podium", "Stevig podiumelement van 2x1m.", 3, "podiumelement.jpg"));
        producten.add(new Product(3L, "XLR Kabel", "Kabel", "XLR audiokabel van 5 meter.", 10, "xlr_kabel.jpg"));
        producten.add(new Product(4L, "DMX Controller", "Controlepanelen", "DMX controller voor lichtsturing.", 2, "dmx_controller.jpg"));
        producten.add(new Product(5L, "Statief", "Accessoire", "Verstelbaar statief voor lampen.", 7, "statief.jpg"));
    }

    @Override
    public List<Product> findAll() {
        return producten;
    }

    @Override
    public Product findById(Long id) {
        for (Product p : producten) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Product> findByNaamContainsIgnoreCase(String naam) {
        String zoek = naam.toLowerCase();
        List<Product> result = new ArrayList<>();
        for (Product p : producten) {
            if (p.getNaam().toLowerCase().contains(zoek)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public List<Product> findByCategorie(String categorie) {
        List<Product> result = new ArrayList<>();
        for (Product p : producten) {
            if (p.getCategorie().equalsIgnoreCase(categorie)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public List<Product> findByNaamAndCategorie(String naam, String categorie) {
        String zoek = naam.toLowerCase();
        List<Product> result = new ArrayList<>();
        for (Product p : producten) {
            if (p.getNaam().toLowerCase().contains(zoek) && p.getCategorie().equalsIgnoreCase(categorie)) {
                result.add(p);
            }
        }
        return result;
    }
}
