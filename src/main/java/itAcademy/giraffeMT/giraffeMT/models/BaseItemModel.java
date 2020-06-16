package itAcademy.giraffeMT.giraffeMT.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Builder

public  class BaseItemModel {
    BigDecimal price;
    String description;
    Long userId;
    String state;
}
