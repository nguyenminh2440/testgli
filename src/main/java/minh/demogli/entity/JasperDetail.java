package minh.demogli.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Entity
@AllArgsConstructor@NoArgsConstructor
@Getter
@Setter
//Entity for items in Jasper report list
public class JasperDetail {
    @Id
    private Long id;
    private String name;
    private String code;
    private String category;
    private double price;
    private Date expire;
}
