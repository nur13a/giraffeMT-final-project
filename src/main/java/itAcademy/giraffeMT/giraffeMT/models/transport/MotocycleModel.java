package itAcademy.giraffeMT.giraffeMT.models.transport;

import itAcademy.giraffeMT.giraffeMT.models.BaseItemModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MotocycleModel extends BaseItemModel {
    Double volume;
    String color;
    String model;
}
