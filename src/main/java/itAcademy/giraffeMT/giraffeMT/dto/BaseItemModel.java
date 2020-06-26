package itAcademy.giraffeMT.giraffeMT.dto;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.enums.ItemState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@NoArgsConstructor

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
    String photoLink;

    public BaseItemModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.userLogin = userLogin;
        this.itemState = itemState;
        this.currency = currency;
        this.category = category;
        this.subcategory = subcategory;
        this.photoLink = photoLink;
    }
}
