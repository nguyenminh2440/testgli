package minh.demogli.repository;

import minh.demogli.entity.JasperDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//Repo for getting Jasper report content
public interface JasperRepository extends JpaRepository<JasperDetail,Long> {
    @Query(value ="SELECT products.id as id,\n" +
            "             products.name as name,\n" +
            "            products.code as code,\n" +
            "            categories.name as category,\n" +
            "price, expire FROM products join categories ON products.category_id = categories.id",nativeQuery = true)
    public List<JasperDetail> getJasperList();
}
