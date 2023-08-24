package minh.demogli.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
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
