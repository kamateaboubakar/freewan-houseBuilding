package com.housebuilding.api.material;

import com.housebuilding.api.Route;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(Route.MATERIAL)
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialMapper materialMapper;

    @GetMapping("")
    public List<MaterialDto> findAllMaterials() {
        return materialMapper.toDtos(materialService.findAll());
    }

    @GetMapping("/{id}")
    public MaterialDto findMaterialById(@PathVariable Long id) {
        return materialMapper.toDto(materialService.findById(id));
    }


    @PostMapping("")
    public MaterialDto addNewMaterial(@RequestBody MaterialDto materialDto) {
        Material material = materialMapper.toEntity(materialDto);
        return materialMapper.toDto(materialService.save(material));
    }

    @PutMapping("")
    public MaterialDto updateMaterial(@RequestBody MaterialDto materialDto) {
        Material material = materialService.findById(materialDto.materialId());
        material = materialMapper.partialUpdate(materialDto, material);
        return materialMapper.toDto(materialService.save(material));
    }

    @DeleteMapping("/{id}")
    public void deleteMaterialById(@PathVariable Long id) {
        materialService.deleteById(id);
    }
}
