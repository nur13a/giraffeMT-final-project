package itAcademy.giraffeMT.giraffeMT.dto.electronics;

import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ComputerModel extends BaseItemModel {
    String ssd;
    String memory;
    Integer numberCores;
    String cpu;
    Long subcategory;
}
