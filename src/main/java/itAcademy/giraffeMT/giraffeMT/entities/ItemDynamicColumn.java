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
@Table(name = "itemDynamicColumn")
public class ItemDynamicColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "column_name")
    String columnName;

    @Column(name = "info")
    String inf0;

    @Column(name = "category")
    String category;
}
