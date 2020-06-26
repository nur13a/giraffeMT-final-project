package itAcademy.giraffeMT.giraffeMT.dto.immovables;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.enums.ItemState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class FlatModel extends BaseItemModel {
    Double square;
    Integer floors;
    Integer roomNumber;
    Integer floorNumber;
    String district;

    @Builder

    public FlatModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Double square, Integer floors, Integer roomNumber, Integer floorNumber, String district) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink);
        this.square = square;
        this.floors = floors;
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.district = district;
    }
}
