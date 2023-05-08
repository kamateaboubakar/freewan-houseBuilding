package com.housebuilding.api.material;

import com.housebuilding.api.common.BaseServiceImp;
import org.springframework.stereotype.Service;

@Service
public class DefaultMaterialService extends BaseServiceImp<Material, Long, MaterialRepository> implements MaterialService{
    public DefaultMaterialService(MaterialRepository repository) {
        super(repository);
    }
}
