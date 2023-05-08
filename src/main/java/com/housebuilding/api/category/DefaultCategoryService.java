package com.housebuilding.api.category;

import com.housebuilding.api.common.BaseServiceImp;
import org.springframework.stereotype.Service;

@Service
public class DefaultCategoryService extends BaseServiceImp<Category, Long, CategoryRepository> implements CategoryService {
    public DefaultCategoryService(CategoryRepository repository) {
        super(repository);
    }
}
