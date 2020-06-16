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
public class Bicycle extends BaseItemModel {
    String color;
    String gender;
}
