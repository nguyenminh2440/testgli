package minh.demogli.service;

import minh.demogli.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);

    public List<CategoryDto> getAllCategories();
    public CategoryDto getCategoryById(Long id);

    public CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    public void deleteCategory(Long id);

}
