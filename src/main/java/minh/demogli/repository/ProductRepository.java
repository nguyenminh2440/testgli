package minh.demogli.repository;

import jakarta.transaction.Transactional;
import minh.demogli.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * FROM products", nativeQuery = true)
    public List<Product> getAllProducts();

    @Query(value = "SELECT * FROM products WHERE id=:id",nativeQuery = true)
    public Product getProductById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM products WHERE id=:id",nativeQuery = true)
    public void deleteProduct(Long id);
}
