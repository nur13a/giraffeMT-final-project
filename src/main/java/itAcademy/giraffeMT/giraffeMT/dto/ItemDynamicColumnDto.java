package itAcademy.giraffeMT.giraffeMT.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemDynamicColumnDto {
    Long id;
    String info;
    String columnName;
}
