package itAcademy.giraffeMT.giraffeMT.dto;

import com.company.banksystem.enums.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;
import itAcademy.giraffeMT.giraffeMT.enums.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemModel {
    BigDecimal price;
    String description;
    ItemState itemState;
    String category;
    Currency currency;
    String subcategory;
    String userLogin;
    Double volume;
    String model;
    String driveUnit;
    String bodyType;
    String issueYear;
    Integer millage;
    String size;
    Color color;
    String ssd;
    String memory;
    Integer numberCores;
    String cpu;
    String address;
    Double square;
    Integer floor;
    Integer roomNumber;
    String district;
    Double landArea;
    Gender gender;
    Status status;
    Integer floorsNumber;
    BuildingType buildingType;
    List<AdditionalColumnDtoResponse>additionalColumn;
}


