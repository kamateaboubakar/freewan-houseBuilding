package com.housebuilding.api.unit;

import com.housebuilding.api.Route;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Route.UNIT_URI)
public class UnitController {
    private final UnitService unitService;
    private final UnitMapper unitMapper;

    @GetMapping("")
    public List<UnitDto> getAll() {
        return unitMapper.toDtos(unitService.findAll());
    }

    @GetMapping("/{unitId}")
    public UnitDto getByUnitId(@PathVariable Long unitId) {
        return unitMapper.toDto(unitService.findById(unitId));
    }

    @PostMapping("")
    public UnitDto addNewUnit(@RequestBody UnitDto unitDto) {
        Unit unit = unitMapper.toEntity(unitDto);
        return unitMapper.toDto(unitService.save(unit));
    }

    @PutMapping("")
    public UnitDto updateUnit(@RequestBody UnitDto unitDto) {
        Unit unit = unitService.findById(unitDto.unitId());
        unit = unitMapper.partialUpdate(unitDto, unit);
        return unitMapper.toDto(unitService.save(unit));
    }

    @DeleteMapping("/{id}")
    public void deleteUnitById(@PathVariable Long id) {
        unitService.deleteById(id);
    }
}
