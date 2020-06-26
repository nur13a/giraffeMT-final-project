package itAcademy.giraffeMT.giraffeMT.dto.transport;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.enums.Color;
import itAcademy.giraffeMT.giraffeMT.enums.Gender;
import itAcademy.giraffeMT.giraffeMT.enums.ItemState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BicycleModel extends BaseItemModel {
    Color color;
    Gender gender;

    @Builder
    public BicycleModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Color color, Gender gender) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink);
        this.color = color;
        this.gender = gender;
    }
}