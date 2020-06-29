package itAcademy.giraffeMT.giraffeMT.dto.transport;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.dto.AdditionalColumnDtoResponse;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.enums.Color;
import itAcademy.giraffeMT.giraffeMT.enums.Gender;
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

public class TransportModel extends BaseItemModel {
    Double volume;
    String driveUnit;
    String bodyType;
    String issueYear;
    Integer millage;
    Color color;
    String model;
    Gender gender;

    @Builder
    public TransportModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Status status, List<AdditionalColumnDtoResponse> columns, Double volume, String driveUnit, String bodyType, String issueYear, Integer millage, Color color, String model, Gender gender) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink, status, columns);
        this.volume = volume;
        this.driveUnit = driveUnit;
        this.bodyType = bodyType;
        this.issueYear = issueYear;
        this.millage = millage;
        this.color = color;
        this.model = model;
        this.gender = gender;
    }
}
