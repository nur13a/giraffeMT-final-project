package itAcademy.giraffeMT.giraffeMT.dto.immovables;

import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
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
