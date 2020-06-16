package itAcademy.giraffeMT.giraffeMT.models.immovables;

import itAcademy.giraffeMT.giraffeMT.models.BaseItemModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class FlatModel extends BaseItemModel {
    Double square;
    Integer floors;
    Integer roomNumber;
    Integer floorNumber;
    String district;

    Long subcategory;
}
