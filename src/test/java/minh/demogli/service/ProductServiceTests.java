package minh.demogli.service;

import minh.demogli.entity.Category;
import minh.demogli.entity.Product;
import minh.demogli.payload.ProductDetailDto;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Test
    public void ProductServiceGetProductDetailTest() {

        ProductDetailDto dto = new ProductDetailDto();
        dto.setPCode("JWA");
        dto.setPName("JWA");
        dto.setCName("JWA");

        when(productRepository.getProductDetail(1L,1L)).thenReturn(dto);

        ProductDetailDto result = implementation.getProductDetail(1L,1L);

        Assertions.assertEquals("JWA", result.getPCode());


    }

    @Test
    public void ProductServiceGetNonExpiredProductTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse("26-09-2034");
        Category category = new Category();
        category.setCode("JWA");
        category.setName("JWA");
        Product product1 = new Product();
        product1.setCode("JWA");
        product1.setName("JWA");
        product1.setPrice(10000);
        product1.setExpire(date);
        product1.setCategory(category);
        date = formatter.parse("26-09-2026");
        Product product2 = new Product();
        product2.setCode("AWJ");
        product2.setName("AWJ");
        product2.setPrice(10000);
        product2.setExpire(date);
        product2.setCategory(category);
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);

        when(productRepository.getNonExpiredProducts()).thenReturn(list);
        List<ProductDto> result = implementation.getNonExpiredProducts();
        Assertions.assertEquals("JWA",result.get(0).getCode());
        Assertions.assertEquals("AWJ",result.get(1).getCode());

    }
}
