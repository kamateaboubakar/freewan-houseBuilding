package com.housebuilding.api.category;

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

import static com.housebuilding.api.Route.CATEGORY;
import static com.housebuilding.api.Route.ROOT;
import static com.housebuilding.api.Route.V1_URI;

@RestController
@RequestMapping(ROOT + V1_URI + CATEGORY)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("")
    public List<CategoryDto> findAllCategories() {
        return categoryMapper.toDtos(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable Long id) {
        return categoryMapper.toDto(categoryService.findById(id));
    }


    @PostMapping("")
    public CategoryDto addNewCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        if (categoryDto.categoryId() == null) {
            category.setParent(null);
        }
        return categoryMapper.toDto(categoryService.save(category));
    }

    @PutMapping("")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.findById(categoryDto.categoryId());
        category = categoryMapper.partialUpdate(categoryDto, category);
        return categoryMapper.toDto(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
