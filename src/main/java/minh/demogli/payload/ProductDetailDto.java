package minh.demogli.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"pName", "pCode", "cName", "price", "expDate"})
public class ProductDetailDto {
    @JsonProperty("pName")
    private String pName;
    @JsonProperty("pCode")
    private String pCode;
    @JsonProperty("cName")
    private String cName;
    @JsonProperty("price")
    private double price;
    @JsonProperty("expDate")
    private Date expDate;

}
