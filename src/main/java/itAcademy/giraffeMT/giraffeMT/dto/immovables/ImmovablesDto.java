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
public class ImmovablesDto extends BaseItemModel {
    Double square;
    Double landArea;
    Integer roomNumber;
    Integer floorsNumber;
    String district;
    Integer floor;
    BuildingType buildingType;
    String address;

    @Builder

    public ImmovablesDto(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Status status, Double square, Double landArea, Integer roomNumber, Integer floorsNumber, String district, Integer floor, BuildingType buildingType, String address, List<AdditionalColumnDtoResponse> additionalList) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink, status,additionalList);
        this.square = square;
        this.landArea = landArea;
        this.roomNumber = roomNumber;
        this.floorsNumber = floorsNumber;
        this.district = district;
        this.floor = floor;
        this.buildingType = buildingType;
        this.address = address;
    }
}
