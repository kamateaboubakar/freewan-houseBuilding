package com.housebuilding.api.brand;

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

import static com.housebuilding.api.Route.BRAND;
import static com.housebuilding.api.Route.ROOT;
import static com.housebuilding.api.Route.V1_URI;

@RestController
@RequestMapping(ROOT + V1_URI +BRAND)
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @GetMapping("")
    public List<BrandDto> findAllBrands() {
        return brandMapper.toDtos(brandService.findAll());
    }

    @GetMapping("/{id}")
    public Brand findBrandById(@PathVariable Long id) {
        return brandService.findById(id);
    }


    @PostMapping("")
    public Brand addNewBrand(@RequestBody BrandDto brandDto) {
        return brandService.save(brandMapper.toEntity(brandDto));
    }

    @PutMapping("")
    public Brand updateBrand(@RequestBody BrandDto brandDto) {
        Brand brand = brandService.findById(brandDto.brandId());
        return brandService.save(brandMapper.partialUpdate(brandDto, brand));
    }

    @DeleteMapping("/{id}")
    public void deleteBrandById(@PathVariable Long id) {
        brandService.deleteById(id);
    }
}
