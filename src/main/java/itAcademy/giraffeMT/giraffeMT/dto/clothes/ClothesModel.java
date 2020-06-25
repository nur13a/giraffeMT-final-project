package itAcademy.giraffeMT.giraffeMT.dto.clothes;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.enums.Color;
import itAcademy.giraffeMT.giraffeMT.enums.ItemState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClothesModel extends BaseItemModel {
    String size;
    Color color;

    @Builder
    public ClothesModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String size, Color color) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory);
        this.size = size;
        this.color = color;
    }
}
