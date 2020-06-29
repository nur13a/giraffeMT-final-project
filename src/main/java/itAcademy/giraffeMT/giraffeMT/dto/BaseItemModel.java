package itAcademy.giraffeMT.giraffeMT.dto;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.entities.AdditionalColumn;
import itAcademy.giraffeMT.giraffeMT.enums.ItemState;
import itAcademy.giraffeMT.giraffeMT.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Builder
@Data
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
    Status status;
    List<AdditionalColumnDtoResponse> columns;

   // @Builder
    public BaseItemModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Status status, List<AdditionalColumnDtoResponse> columns) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.userLogin = userLogin;
        this.itemState = itemState;
        this.currency = currency;
        this.category = category;
        this.subcategory = subcategory;
        this.photoLink = photoLink;
        this.status = status;
        this.columns = columns;
    }
}
