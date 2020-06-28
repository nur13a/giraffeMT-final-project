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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseItemModel)) return false;
        BaseItemModel that = (BaseItemModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description) &&
                Objects.equals(userLogin, that.userLogin) &&
                itemState == that.itemState &&
                currency == that.currency &&
                Objects.equals(category, that.category) &&
                Objects.equals(subcategory, that.subcategory) &&
                Objects.equals(photoLink, that.photoLink) &&
                status == that.status &&
                Objects.equals(columns, that.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink, status, columns);
    }

    @Builder
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
