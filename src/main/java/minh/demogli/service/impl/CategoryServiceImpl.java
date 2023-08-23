package minh.demogli.service.impl;

import minh.demogli.entity.Category;
import minh.demogli.entity.Product;
import minh.demogli.payload.CategoryDto;
import minh.demogli.payload.ProductDto;
import minh.demogli.repository.CategoryRepository;
import minh.demogli.repository.ProductRepository;
import minh.demogli.service.CategoryService;
import minh.demogli.utils.TestMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapToEntity(categoryDto);
        Category saved = categoryRepository.save(category);
        return mapToDto(saved);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.getAllCategories();
        return categories.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.getCategoryById(id);
        return mapToDto(category);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category category = categoryRepository.getCategoryById(id);
        category.setCode(categoryDto.getCode());
        category.setName(categoryDto.getName());
        Category saved = categoryRepository.save(category);
        return mapToDto(saved);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteCategory(id);

    }


    private CategoryDto mapToDto(Category category) {
        return TestMapper.INSTANCE.convert(category);
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        return TestMapper.INSTANCE.convert(categoryDto);
    }

}
