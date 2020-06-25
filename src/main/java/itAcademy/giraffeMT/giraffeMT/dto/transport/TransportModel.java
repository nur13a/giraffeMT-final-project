package itAcademy.giraffeMT.giraffeMT.dto.transport;

import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.enums.Color;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TransportModel extends BaseItemModel {
    Double volume;
    String driveUnit;
    String bodyType;
    String issueYear;
    Integer millage;
    String color;
    String model;
}
