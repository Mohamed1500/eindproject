package be.ehb.eindproject.repository;

import be.ehb.eindproject.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryProductRepositoryTest {

    private final InMemoryProductRepository repository = new InMemoryProductRepository();

    @Test
    void findByCategorieReturnsOnlyProductsInCategory() {
        List<Product> products = repository.findByCategorie("Kabel");

        assertEquals(2, products.size());
        assertEquals("Kabel", products.get(0).getCategorie());
        assertEquals("Kabel", products.get(1).getCategorie());
    }

    @Test
    void findByNaamAndCategorieCombinesBothFilters() {
        List<Product> products = repository.findByNaamAndCategorie("licht", "Belichting");

        assertEquals(1, products.size());
        assertEquals("Lichtpaneel", products.get(0).getNaam());
    }
}