package be.ehb.eindproject.repository;

import be.ehb.eindproject.model.Lening;
import java.util.List;

public interface LeningRepository {
    void save(Lening lening);
    List<Lening> findByUserId(Long userId);
    int countByUserId(Long userId);
    List<Lening> findAll();
    void deleteById(Long leningId, Long userId);
}
