package minh.demogli.repository;

import minh.demogli.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryRepoTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void CategoryRepoGetTest() {
        Category category =new Category();
        category.setCode("KAJ");
        category.setName("KAJ");

        Category save = categoryRepository.save(category);
        Category test =  categoryRepository.getCategoryById(save.getId());

        Assertions.assertEquals("KAJ",test.getName());
        Assertions.assertEquals("KAJ",test.getCode());
    }

    @Test
    public void CategoryRepoGetAllTest() {
        Category category1 = new Category();
        category1.setCode("JWA");
        category1.setName("JWA");
        Category category2 = new Category();
        category2.setCode("OPI");
        category2.setName("OPI");

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        List<Category> testList = categoryRepository.getAllCategories();

        Assertions.assertEquals("JWA",testList.get(0).getCode());
        Assertions.assertEquals("OPI",testList.get(1).getCode());

    }

    @Test
    public void CategoryRepoUpdateTest() {
        Category category1 = new Category();
        category1.setCode("JWA");
        category1.setName("JWA");

        Category saved = categoryRepository.save(category1);

        Category update = categoryRepository.getCategoryById(saved.getId());

        update.setCode("AWJ");
        update.setName("AWJ");

        categoryRepository.save(update);

        Assertions.assertEquals("AWJ",categoryRepository.getCategoryById(saved.getId()).getCode());
        Assertions.assertEquals("AWJ",categoryRepository.getCategoryById(saved.getId()).getName());

    }

    @Test
    public void CategoryRepoDeleteTest() {
        Category category1 = new Category();
        category1.setCode("JWA");
        category1.setName("JWA");

        Category saved = categoryRepository.save(category1);

        categoryRepository.deleteCategory(saved.getId());

        Assertions.assertNull(categoryRepository.getCategoryById(saved.getId()));
    }

}
