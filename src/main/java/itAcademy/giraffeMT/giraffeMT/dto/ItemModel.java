package itAcademy.giraffeMT.giraffeMT.dto;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.entities.Category;
import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemModel {
    BigDecimal price;
    String description;
        String itemState;
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
    String color;
    String ssd;
    String memory;
    Integer numberCores;
    String cpu;
    String address;
    Double square;
    Integer floors;
    Integer roomNumber;
    String district;
    Double landArea;
    String gender;
    Status status;
}


