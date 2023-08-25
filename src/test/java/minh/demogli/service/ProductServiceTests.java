package minh.demogli.service;

import minh.demogli.entity.Category;
import minh.demogli.entity.Product;
import minh.demogli.payload.ProductDto;
import minh.demogli.repository.CategoryRepoTests;
import minh.demogli.repository.CategoryRepository;
import minh.demogli.repository.ProductRepository;
import minh.demogli.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl implementation;

    @Test
    public void ProductServiceCreateTest() {
        Category category = new Category();
        category.setId(1L);
        category.setCode("JWA");
        category.setName("JWA");
        Product product = new Product();
        product.setCode("JWA");
        product.setName("JWA");
        product.setPrice(10000);
        product.setExpire(new Date());
        product.setCategory(category);

        ProductDto productDto = new ProductDto();
        productDto.setCode("JWA");
        productDto.setName("JWA");
        productDto.setPrice(10000);
        productDto.setExpire(new Date());

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ProductDto savedDto = implementation.createProduct(productDto,category.getId());

        Assertions.assertEquals("JWA",savedDto.getCode());
    }

    @Test
    public void ProductServiceGetAllInCategoryTest() {
        List<Product> productList = Collections.singletonList(Mockito.mock(Product.class));
        when(productRepository.getAllProducts(1L)).thenReturn(productList);

        List<ProductDto> list =  implementation.getAllProductsInCategory(1L);

        Assertions.assertNotNull(list);

    }

    @Test
    public void ProductServiceGetAllTest() {
        List<Product> productList =  Collections.singletonList(Mockito.mock(Product.class));
        when(productRepository.getAllProducts()).thenReturn(productList);

        List<Product> list =  implementation.getAllProducts();
        Assertions.assertNotNull(list);

    }

    @Test
    public void ProductServiceGetTest() {
        Category category = new Category();
        category.setId(1L);
        category.setCode("JWA");
        category.setName("JWA");
        Product product = new Product();
        product.setCode("JWA");
        product.setName("JWA");
        product.setPrice(10000);
        product.setExpire(new Date());
        product.setCategory(category);

        when(productRepository.getProductById(1L,1L)).thenReturn(product);

        ProductDto result =  implementation.getProductById(1L,1L);
        Assertions.assertEquals("JWA",result.getCode());
    }

    @Test
    public void ProductServiceUpdateTest() {
        Category category = new Category();
        category.setId(1L);
        category.setCode("JWA");
        category.setName("JWA");
        Product product = new Product();
        product.setCode("JWA");
        product.setName("JWA");
        product.setPrice(10000);
        product.setExpire(new Date());
        product.setCategory(category);

        ProductDto productDto = new ProductDto();
        productDto.setCode("AWJ");
        productDto.setName("AWJ");
        productDto.setPrice(10000);
        productDto.setExpire(new Date());

        when(productRepository.getProductById(1L,1L)).thenReturn(product);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ProductDto result  = implementation.updateProduct(productDto,1L,1L);

        Assertions.assertEquals("AWJ",result.getCode());

    }

    @Test
    public void ProductServiceDeleteTest() {
        Assertions.assertAll(() -> implementation.deleteProduct(1L,1L));
    }
}
