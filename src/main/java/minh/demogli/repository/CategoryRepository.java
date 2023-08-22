package minh.demogli.repository;

import jakarta.transaction.Transactional;
import minh.demogli.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT * FROM categories",nativeQuery = true)
    public List<Category> getAllCategories();

    @Query(value = "SELECT * FROM categories WHERE id = :id",nativeQuery = true)
    public Category getCategoryById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM categories WHERE id=:id", nativeQuery = true)
    public void deleteCategory(Long id);


}
