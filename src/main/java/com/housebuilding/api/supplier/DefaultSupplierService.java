package com.housebuilding.api.supplier;

import com.housebuilding.api.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultSupplierService implements SupplierService {

    private final SupplierRepository supplierRepository;

    /**
     * @return Get list of all supplier
     */
    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    /**
     * @param id supplier id.
     * @return a supplier
     */
    @Override
    public Supplier findById(UUID id) {
        return supplierRepository.findById(id).orElseThrow(() -> new DataNotFoundException(
                "Supplier with id '%s' not found.".formatted(id.toString())));
    }

    /**
     * @param supplier the supplier
     * @return supplier id
     */
    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    /**
     * @param id supplier id
     */
    @Override
    public void deleteById(UUID id) {
        delete(findById(id));
    }

    /**
     * @param supplier the supplier
     */
    @Override
    public void delete(Supplier supplier) {
        supplierRepository.delete(supplier);
    }
}
