package be.ehb.eindproject.service;

import be.ehb.eindproject.model.Lening;
import be.ehb.eindproject.repository.LeningRepository;
import be.ehb.eindproject.repository.ProductRepository;
import be.ehb.eindproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeningService {
    @Autowired
    private LeningRepository leningRepository;

    @Autowired
    private ProductRepository productRepository;

    public void saveLening(Lening lening) {
        leningRepository.save(lening);
    }

    public List<Lening> getLeningenByUserId(Long userId) {
        return leningRepository.findByUserId(userId);
    }

    public int countLeningenByUserId(Long userId) {
        return leningRepository.countByUserId(userId);
    }

    public void deleteLening(Long leningId, Long userId) {
        // Zoek de lening die verwijderd wordt
        Lening lening = null;
        for (Lening l : leningRepository.findByUserId(userId)) {
            if (l.getId().equals(leningId)) {
                lening = l;
                break;
            }
        }
        if (lening != null) {
            // Verhoog de voorraad van het product
            Product product = productRepository.findById(lening.getProductId());
            if (product != null) {
                product.setVoorraad(product.getVoorraad() + lening.getAantal());
            }
        }
        leningRepository.deleteById(leningId, userId);
    }
}
