package com.housebuilding.api.supplier;

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
import java.util.UUID;

import static com.housebuilding.api.Route.ROOT;
import static com.housebuilding.api.Route.SUPPLIER;
import static com.housebuilding.api.Route.V1_URI;

@RestController
@RequestMapping(ROOT + V1_URI + SUPPLIER)
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @GetMapping("")
    public List<SupplierDto> findAllSuppliers() {
        return supplierMapper.toDtos(supplierService.findAll());
    }

    @GetMapping("/{id}")
    public SupplierDto findSupplierById(@PathVariable UUID id) {
        return supplierMapper.toDto(supplierService.findById(id));
    }

    @PostMapping("")
    public SupplierDto addNewSupplier(@RequestBody SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);
        return supplierMapper.toDto(supplierService.save(supplier));
    }

    @PutMapping("")
    public SupplierDto updateSupplier(@RequestBody SupplierDto supplierDto) {
        Supplier supplier = supplierService.findById(supplierDto.supplierId());
        supplier = supplierMapper.partialUpdate(supplierDto, supplier);
        return supplierMapper.toDto(supplierService.save(supplier));
    }

    @DeleteMapping("/{id}")
    public void deleteSupplierById(@PathVariable UUID id) {
        supplierService.deleteById(id);
    }
}
