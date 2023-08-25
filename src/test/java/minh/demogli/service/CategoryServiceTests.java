package minh.demogli.service;

import minh.demogli.entity.Category;
import minh.demogli.payload.CategoryDto;
import minh.demogli.repository.CategoryRepository;
import minh.demogli.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

    @Mock
    CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl implementation;

    @Test
    public void CategoryServiceCreateTest() {
        Category category =  new Category();
        category.setCode("AWJ");
        category.setName("AWJ");

        CategoryDto dto = new CategoryDto();
        dto.setCode("AWJ");
        dto.setName("AWJ");

        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        CategoryDto savedDto = implementation.createCategory(dto);

        Assertions.assertEquals("AWJ",savedDto.getCode());

    }

    @Test
    public void CategoryServiceGetAllTest() {
        List<Category> categoryList = Collections.singletonList(Mockito.mock(Category.class));

        when(categoryRepository.getAllCategories()).thenReturn(categoryList);

        List<CategoryDto> list = implementation.getAllCategories();

        Assertions.assertNotNull(list);
    }

    @Test
    public void CategoryServiceGetTest() {
        Category category =  new Category();
        category.setCode("AWJ");
        category.setName("AWJ");

        when(categoryRepository.getCategoryById(1L)).thenReturn(category);

        CategoryDto result = implementation.getCategoryById(1L);

        Assertions.assertEquals("AWJ",result.getCode());
    }

    @Test
    public void CategoryServiceUpdateTest() {
        Category category =  new Category();
        category.setCode("AWJ");
        category.setName("AWJ");

        CategoryDto dto = new CategoryDto();
        dto.setCode("JWA");
        dto.setName("JWA");

        when(categoryRepository.getCategoryById(1L)).thenReturn(category);
        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        CategoryDto result =implementation.updateCategory(dto,1L);

        Assertions.assertEquals("JWA",result.getCode());
    }

    @Test
    public void CategoryServiceDeleteTest() {
        Assertions.assertAll(() -> implementation.deleteCategory(1L));
    }

}
