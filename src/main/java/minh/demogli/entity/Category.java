package minh.demogli.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code",nullable = false)
    private String code;
    @Column(name = "name",nullable = false)
    private String name;

    @Getter
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Product> products;


}
