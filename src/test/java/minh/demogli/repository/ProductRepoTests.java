package minh.demogli.repository;

import minh.demogli.entity.Category;
import minh.demogli.entity.Product;
import minh.demogli.payload.ProductDetailDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepoTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void ProductRepoGetTest() {
        Category category = new Category();
        category.setCode("JWA");
        category.setName("JWA");
        Product product = new Product();
        product.setCode("JWA");
        product.setName("JWA");
        product.setPrice(10000);
        product.setExpire(new Date());
        product.setCategory(category);

        Category savedCat = categoryRepository.save(category);

        Product saved =  productRepository.save(product);
        Product test = productRepository.getProductById(savedCat.getId(), savedCat.getId());

        Assertions.assertEquals("JWA",test.getCode());
        Assertions.assertEquals("JWA",test.getName());
    }

    @Test
    public void ProductRepoGetAllTest() {
        Category category = new Category();
        category.setCode("JWA");
        category.setName("JWA");
        Product product1 = new Product();
        product1.setCode("JWA");
        product1.setName("JWA");
        product1.setPrice(10000);
        product1.setExpire(new Date());
        product1.setCategory(category);
        Product product2 = new Product();
        product2.setCode("AWJ");
        product2.setName("AWJ");
        product2.setPrice(10000);
        product2.setExpire(new Date());
        product2.setCategory(category);

        Category savedCat = categoryRepository.save(category);
        Product saved1 =  productRepository.save(product1);
        Product saved2 =  productRepository.save(product2);

        List<Product> list= productRepository.getAllProducts(savedCat.getId());

        Assertions.assertEquals("JWA",list.get(0).getCode());
        Assertions.assertEquals("AWJ",list.get(1).getCode());

    }

    @Test
    public void ProductRepoUpdateTest() {
        Category category = new Category();
        category.setCode("JWA");
        category.setName("JWA");
        Product product = new Product();
        product.setCode("JWA");
        product.setName("JWA");
        product.setPrice(10000);
        product.setExpire(new Date());
        product.setCategory(category);

        Category savedCat = categoryRepository.save(category);

        Product saved =  productRepository.save(product);

        Product update = productRepository.getProductById(savedCat.getId(), saved.getId());

        update.setCode("AWJ");
        update.setName("AWJ");
        productRepository.save(update);

        Assertions.assertEquals("AWJ",productRepository.getProductById(savedCat.getId(), saved.getId()).getCode());
        Assertions.assertEquals("AWJ",productRepository.getProductById(savedCat.getId(), saved.getId()).getName());

    }

    @Test
    public void ProductRepoDeleteTest() {
        Category category = new Category();
        category.setCode("JWA");
        category.setName("JWA");
        Product product = new Product();
        product.setCode("JWA");
        product.setName("JWA");
        product.setPrice(10000);
        product.setExpire(new Date());
        product.setCategory(category);

        Category savedCat = categoryRepository.save(category);

        Product saved =  productRepository.save(product);

        productRepository.deleteProduct(saved.getId(), saved.getId());

        Assertions.assertNull(productRepository.getProductById(savedCat.getId(), saved.getId()));
    }

    @Test
    public void ProductRepoGetNonExpiredProducts() throws ParseException {
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
        date = formatter.parse("26-09-2022");
        Product product2 = new Product();
        product2.setCode("AWJ");
        product2.setName("AWJ");
        product2.setPrice(10000);
        product2.setExpire(date);
        product2.setCategory(category);

        Category savedCat = categoryRepository.save(category);
        Product saved1 =  productRepository.save(product1);
        Product saved2 =  productRepository.save(product2);

        List<Product> list= productRepository.getNonExpiredProducts();

        Assertions.assertEquals("JWA",list.get(0).getCode());
        Assertions.assertEquals(1,list.size());
    }
    @Test
    public void ProductRepoGetProductDetailTest() {
        Category category = new Category();
        category.setCode("JWA");
        category.setName("JWA");
        Product product = new Product();
        product.setCode("JWA");
        product.setName("JWA");
        product.setPrice(10000);
        product.setExpire(new Date());
        product.setCategory(category);

        Category savedCat = categoryRepository.save(category);
        Product savedProd = productRepository.save(product);

        ProductDetailDto result = productRepository.getProductDetail(savedCat.getId(), savedProd.getId());

        Assertions.assertEquals("JWA",result.getPCode());
        Assertions.assertEquals("JWA",result.getCName());
    }
}
