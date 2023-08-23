package minh.demogli.service;

import minh.demogli.entity.Product;
import minh.demogli.payload.ProductDetailDto;
import minh.demogli.payload.ProductDto;

import java.util.List;

public interface ProductService {
    public ProductDto createProduct(ProductDto productDto, Long categoryId);

    public List<ProductDto> getAllProductsInCategory(Long categoryId);

    public ProductDto getProductById(Long id, Long categoryId);

    public ProductDto updateProduct(ProductDto productDto,Long id, Long categoryId);

    public ProductDetailDto getProductDetail(Long categoryId,Long id);

    public List<ProductDto> getNonExpiredProducts();

    public List<Product> getAllProducts();

    public void deleteProduct(Long id, Long categoryId);
}
