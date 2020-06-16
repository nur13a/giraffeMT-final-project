package itAcademy.giraffeMT.giraffeMT.models.electronics;

import itAcademy.giraffeMT.giraffeMT.models.BaseItemModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TelephoneModel extends BaseItemModel {
    String model;
    String color;
    Double volume;
}
