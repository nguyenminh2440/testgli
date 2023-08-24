package minh.demogli.entity;

import jakarta.persistence.*;
import minh.demogli.payload.ProductDetailDto;

import java.util.Date;
//Query for product detail
@Entity
@NamedNativeQuery(name = "getProductDetailDto",query = "SELECT products.name as pName,\n" +
        " products.code as pCode,\n" +
        " categories.name as cName,\n" +
        " price, expire as expDate\n" +
        " FROM products join categories ON products.category_id = categories.id WHERE category_id=:categoryId AND products.id=:id",
resultSetMapping = "productDetailDto")
@SqlResultSetMapping(name = "productDetailDto",
        classes = @ConstructorResult(
                targetClass = ProductDetailDto.class,
                columns = {
                        @ColumnResult(name = "pName",type = String.class),
                        @ColumnResult(name = "pCode", type = String.class),
                        @ColumnResult(name = "cName",type = String.class),
                        @ColumnResult(name = "price",type = double.class),
                        @ColumnResult(name = "expDate",type= Date.class)
                }
        )
)
public class ProductDetailQuery {

    @Id
    Long id;
}
