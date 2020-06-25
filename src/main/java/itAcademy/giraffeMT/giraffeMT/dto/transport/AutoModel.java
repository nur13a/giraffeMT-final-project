package itAcademy.giraffeMT.giraffeMT.dto.transport;

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

public class AutoModel extends BaseItemModel {
    Double volume;
    String driveUnit;
    String bodyType;
    String issueYear;
    Integer millage;
    Color color;
    String model;

    @Builder
    public AutoModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, Double volume, String driveUnit, String bodyType, String issueYear, Integer millage, Color color, String model) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory);
        this.volume = volume;
        this.driveUnit = driveUnit;
        this.bodyType = bodyType;
        this.issueYear = issueYear;
        this.millage = millage;
        this.color = color;
        this.model = model;
    }
}
