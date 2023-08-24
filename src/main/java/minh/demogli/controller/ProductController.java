package minh.demogli.controller;

import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import minh.demogli.entity.Product;
import minh.demogli.payload.ProductDetailDto;
import minh.demogli.payload.ProductDto;
import minh.demogli.service.ProductService;
import minh.demogli.service.ReportService;
import minh.demogli.utils.ExcelGenerator;
import minh.demogli.utils.PdfGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ProductController {

    ProductService productService;
    @Autowired
    ReportService reportService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{categoryId}/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable(name = "categoryId")Long categoryId,@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.updateProduct(productDto,id,categoryId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "categoryId") Long categoryId,@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id,categoryId);
        return new ResponseEntity<>("Product deleted.",HttpStatus.OK);
    }

    //Get Detail Of One Product(with Category name)
    @GetMapping("/{categoryId}/products/{id}/details")
    public ResponseEntity<ProductDetailDto> getProductDetail(@PathVariable(name = "categoryId") Long categoryId,@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.getProductDetail(categoryId,id),HttpStatus.OK);
    }


    //Get Non-expired Products
    @GetMapping("/not_expire")
    public ResponseEntity<List<ProductDto>> getNonExpiredProducts() {
        return new ResponseEntity<>(productService.getNonExpiredProducts(),HttpStatus.OK);
    }

    //Excel export controller
    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        //Initialize timestamp
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        //Setup header
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=product" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        //Get content
        List <Product> productList = productService.getAllProducts();

        //Generate Excel
        ExcelGenerator generator = new ExcelGenerator(productList);
        generator.generateExcelFile(response);
    }

    //PDF Export controller(no Jasper)
    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException
    {
        //Setup timestamp
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        //Setup header
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=product" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        //Get content
        List < Product > productList = productService.getAllProducts();
        //Generate file
        PdfGenerator generator = new PdfGenerator();
        generator.generate(productList, response);
    }

    //Jasper report generate controller
    @GetMapping("/jasper")
    public String generateJasperReport() throws JRException, FileNotFoundException {
        return reportService.exportReport("pdf");

    }


    //Get Jasper info with json(for testing)
    /*@GetMapping("/jasper/json")
    public List<JasperDetailDto> getJasperJson() {
        return productService.getJasperList();
    }*/


}
