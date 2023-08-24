package minh.demogli.repository;

import jakarta.transaction.Transactional;
import minh.demogli.entity.JasperDetail;
import minh.demogli.entity.Product;
import minh.demogli.payload.ProductDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * FROM products WHERE category_id=:id", nativeQuery = true)
    public List<Product> getAllProducts(Long id);

    @Query(value = "SELECT * FROM products WHERE category_id=:categoryId AND id=:id",nativeQuery = true)
    public Product getProductById(Long categoryId,Long id);

    @Query(name = "getProductDetailDto", nativeQuery = true)
    public ProductDetailDto getProductDetail(@Param("categoryId") Long categoryId,@Param("id") Long id);

    @Query(value = "SELECT * FROM products WHERE expire > CURRENT_DATE",nativeQuery = true)
    public List<Product> getNonExpiredProducts();

    @Query(value = "SELECT * FROM products",nativeQuery = true)
    public List<Product> getAllProducts();





    @Modifying
    @Transactional
    @Query(value = "DELETE FROM products WHERE category_id=:categoryId AND id=:id",nativeQuery = true)
    public void deleteProduct(Long categoryId, Long id);
}
