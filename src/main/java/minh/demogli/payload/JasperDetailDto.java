package minh.demogli.payload;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import minh.demogli.entity.JasperDetail;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JasperDetailDto {
    private Long id;
    private String name;
    private String code;
    private String category;
    private double price;
    private Date expire;
}
