package be.ehb.eindproject.repository;

import be.ehb.eindproject.model.Lening;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryLeningRepository implements LeningRepository {
    private final List<Lening> leningen = new ArrayList<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public void save(Lening lening) {
        if (lening.getId() == null) {
            lening.setId(idGen.getAndIncrement());
        }
        leningen.add(lening);
    }

    @Override
    public List<Lening> findByUserId(Long userId) {
        List<Lening> result = new ArrayList<>();
        for (Lening l : leningen) {
            if (l.getUserId().equals(userId)) {
                result.add(l);
            }
        }
        return result;
    }

    @Override
    public int countByUserId(Long userId) {
        int count = 0;
        for (Lening l : leningen) {
            if (l.getUserId().equals(userId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Lening> findAll() {
        return new ArrayList<>(leningen);
    }

    @Override
    public void deleteById(Long leningId, Long userId) {
        leningen.removeIf(l -> l.getId().equals(leningId) && l.getUserId().equals(userId));
    }
}
