package itAcademy.giraffeMT.giraffeMT.dto.transport;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.enums.Color;
import itAcademy.giraffeMT.giraffeMT.enums.ItemState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MotocycleModel extends BaseItemModel {
    Double volume;
    Color color;
    String model;
@Builder
    public MotocycleModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Double volume, Color color, String model) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink);
        this.volume = volume;
        this.color = color;
        this.model = model;
    }
}