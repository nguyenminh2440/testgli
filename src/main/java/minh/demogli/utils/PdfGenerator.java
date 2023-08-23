package minh.demogli.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import minh.demogli.entity.Product;

import java.io.IOException;
import java.util.List;

public class PdfGenerator {
    public void generate(List<Product> productList, HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        Paragraph paragraph1 = new Paragraph("Product List", fontTiltle);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph1);
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new int[] {3,3,3,3,3,3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Code", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Category", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Expiry Date", font));
        table.addCell(cell);

        for (Product product: productList) {

            table.addCell(String.valueOf(product.getId()));

            table.addCell(product.getCode());

            table.addCell(product.getName());

            table.addCell(product.getCategory().getName());
            table.addCell(String.valueOf(product.getPrice()));
            table.addCell(String.valueOf(product.getExpire()));
        }
        document.add(table);

        document.close();
    }
}
