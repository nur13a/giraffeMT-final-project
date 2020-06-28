package itAcademy.giraffeMT.giraffeMT.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "additionalColumn")
public class AdditionalColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "columnName",unique = true,nullable = false)
    String columnName;

    @Column(name = "info")
    String inf0;
    @Column(name = "category")
    Category category;
    @Column(name = "subcategory")
    Subcategory subcategory;

}
