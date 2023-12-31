package minh.demogli.service.impl;

import minh.demogli.entity.Category;
import minh.demogli.entity.JasperDetail;
import minh.demogli.entity.Product;
import minh.demogli.payload.JasperDetailDto;
import minh.demogli.payload.ProductDetailDto;
import minh.demogli.payload.ProductDto;
import minh.demogli.repository.CategoryRepository;
import minh.demogli.repository.JasperRepository;
import minh.demogli.repository.ProductRepository;
import minh.demogli.service.ProductService;
import minh.demogli.utils.TestMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    JasperRepository jasperRepository;


    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,JasperRepository jasperRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.jasperRepository = jasperRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, Long categoryId) {
        Product product = mapToEntity(productDto);
        Category category = categoryRepository.getCategoryById(categoryId);
        product.setCategory(category);
        Product saved = productRepository.save(product);
        return maptoDto(saved);
    }

    @Override
    public List<ProductDto> getAllProductsInCategory(Long categoryId) {
        return productRepository.getAllProducts(categoryId).stream().map(this::maptoDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id, Long categoryId) {
        Product product = productRepository.getProductById(categoryId,id);
        return maptoDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id, Long categoryId) {
        Product product = productRepository.getProductById(categoryId,id);
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        Product saved = productRepository.save(product);
        return maptoDto(saved);
    }

    @Override
    public ProductDetailDto getProductDetail(Long categoryId, Long id) {
        return productRepository.getProductDetail(categoryId,id);
    }

    @Override
    public List<ProductDto> getNonExpiredProducts() {
        return productRepository.getNonExpiredProducts().stream().map(this::maptoDto).collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    //Get the List for Jasper report
    @Override
    public List<JasperDetailDto> getJasperList() {
        return jasperRepository.getJasperList().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id, Long categoryId) {
        productRepository.deleteProduct(categoryId,id);
    }
    private ProductDto maptoDto(Product product) {
        return TestMapper.INSTANCE.convert(product);
    }

    private Product mapToEntity(ProductDto productDto) {
        return TestMapper.INSTANCE.convert(productDto);
    }

    private JasperDetail mapToEntity(JasperDetailDto dto) {
        return TestMapper.INSTANCE.convert(dto);
    }

    private JasperDetailDto mapToDto(JasperDetail detail) {
        return TestMapper.INSTANCE.convert(detail);
    }
}
