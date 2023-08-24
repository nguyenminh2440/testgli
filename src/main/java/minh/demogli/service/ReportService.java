package minh.demogli.service;

import minh.demogli.entity.JasperDetail;
import minh.demogli.repository.JasperRepository;
import minh.demogli.repository.ProductRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Implementation for Jasper Report
@Service
public class ReportService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    JasperRepository jasperRepository;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        //generate path to file
        String path ="D:\\code";
        //Get content for report
        List<JasperDetail> jasperDetail = jasperRepository.getJasperList();
        //Grab jrxml template
        File file = ResourceUtils.getFile("classpath:products.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        //Connect datasource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jasperDetail);
        //Dummy parameter
        Map<String,Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        //Export to pdf
        if(reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint,path + "\\products.pdf");
        }

        //Inform result
        return "Report Generated in " + path;
    }
}
