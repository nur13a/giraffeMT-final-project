package itAcademy.giraffeMT.giraffeMT.dto.immovables;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.dto.AdditionalColumnDtoResponse;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.enums.BuildingType;
import itAcademy.giraffeMT.giraffeMT.enums.ItemState;
import itAcademy.giraffeMT.giraffeMT.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class FlatDto extends BaseItemModel {
    Double square;
    Integer floor;
    Integer roomNumber;
    Integer floorsNumber;
    String district;
    BuildingType buildingType;
    String address;

    @Builder

    public FlatDto(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Status status, Double square, Integer floor, Integer roomNumber, Integer floorsNumber, String district, BuildingType buildingType, String address, List<AdditionalColumnDtoResponse>additionalList) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink, status,additionalList);
        this.square = square;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.floorsNumber = floorsNumber;
        this.district = district;
        this.buildingType = buildingType;
        this.address = address;
    }
}
