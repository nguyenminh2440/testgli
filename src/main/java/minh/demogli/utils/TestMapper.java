package minh.demogli.utils;

import minh.demogli.entity.Category;
import minh.demogli.entity.JasperDetail;
import minh.demogli.entity.Product;
import minh.demogli.payload.CategoryDto;
import minh.demogli.payload.JasperDetailDto;
import minh.demogli.payload.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
//MapStruct config
@Mapper(componentModel = "spring")
public interface TestMapper {
    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);
    CategoryDto convert(Category category);
    Category convert(CategoryDto teamDto);

    Product convert(ProductDto productDto);

    ProductDto convert(Product player);

    JasperDetail convert(JasperDetailDto detailDto);

    JasperDetailDto convert(JasperDetail detail);
}