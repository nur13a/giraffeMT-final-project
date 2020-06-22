package itAcademy.giraffeMT.giraffeMT.dto;

import itAcademy.giraffeMT.giraffeMT.entities.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SubcategoryModel {
    String name;
    Category category;
}

