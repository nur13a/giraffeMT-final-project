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
public class HouseModel extends BaseItemModel {
    Double square;
    Double landArea;
    Integer roomNumber;
    Integer floorNumber;
    String district;
}
