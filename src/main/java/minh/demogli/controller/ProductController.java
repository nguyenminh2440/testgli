package minh.demogli.controller;

import jakarta.servlet.http.HttpServletResponse;
import minh.demogli.entity.Product;
import minh.demogli.payload.ProductDetailDto;
import minh.demogli.payload.ProductDto;
import minh.demogli.service.ProductService;
import minh.demogli.utils.ExcelGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,@PathVariable(name = "categoryId") Long categoryId) {
        return new ResponseEntity<>(productService.createProduct(productDto,categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable(name = "categoryId") Long categoryId) {
        return new ResponseEntity<>(productService.getAllProductsInCategory(categoryId),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "categoryId") Long categoryId, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.getProductById(id, categoryId),HttpStatus.OK);
    }

    @PutMapping("/{categoryId}/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable(name = "categoryId")Long categoryId,@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.updateProduct(productDto,id,categoryId),HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "categoryId") Long categoryId,@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id,categoryId);
        return new ResponseEntity<>("Product deleted.",HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products/{id}/details")
    public ResponseEntity<ProductDetailDto> getProductDetail(@PathVariable(name = "categoryId") Long categoryId,@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.getProductDetail(categoryId,id),HttpStatus.OK);
    }

    @GetMapping("/not_expire")
    public ResponseEntity<List<ProductDto>> getNonExpiredProducts() {
        return new ResponseEntity<>(productService.getNonExpiredProducts(),HttpStatus.OK);
    }

    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=product" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Product> productList = productService.getAllProducts();
        ExcelGenerator generator = new ExcelGenerator(productList);
        generator.generateExcelFile(response);
    }


}
