package itAcademy.giraffeMT.giraffeMT.dto;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.enums.ItemState;
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
    Long id;
    BigDecimal price;
    String description;
    String userLogin;
    ItemState itemState;
    Currency currency;
    String category;
    String subcategory;

    public BaseItemModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.userLogin = userLogin;
        this.itemState = itemState;
        this.currency = currency;
    }
}
