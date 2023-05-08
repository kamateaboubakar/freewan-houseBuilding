package com.housebuilding.api.unit;

import com.housebuilding.api.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultUnitService implements UnitService {
    private final UnitRepository unitRepository;

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    @Override
    public Unit findById(Long id) {
        return unitRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Unit with id: '%d' not found".formatted(id)));
    }

    @Override
    public Unit save(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public void deleteById(Long id) {
        delete(findById(id));
    }

    @Override
    public void delete(Unit unit) {
        unitRepository.delete(unit);
    }

    /**
     *
     */
    @Override
    public void init() {
        if (unitRepository.count() != 0) return;
        unitRepository.saveAll(List.of(new Unit("TON", "Tonne"), new Unit("KG", "Kilogramme")));
    }
}