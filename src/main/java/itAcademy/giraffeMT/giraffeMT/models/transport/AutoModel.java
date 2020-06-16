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
public class AutoModel extends BaseItemModel {
    Double volume;
    String driveUnit;
    String bodyType;
    String issueYear;
    Integer millage;
    Long subcategory;
    String color;
    String model;
}
