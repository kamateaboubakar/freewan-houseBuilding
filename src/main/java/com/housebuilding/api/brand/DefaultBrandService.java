package com.housebuilding.api.brand;

import com.housebuilding.api.common.BaseServiceImp;
import org.springframework.stereotype.Service;

@Service
public class DefaultBrandService extends BaseServiceImp<Brand, Long, BrandRepository> implements BrandService{
    public DefaultBrandService(BrandRepository repository) {
        super(repository);
    }
}
