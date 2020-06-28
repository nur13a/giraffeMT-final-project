package itAcademy.giraffeMT.giraffeMT.dto.transport;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.dto.AdditionalColumnDtoResponse;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.enums.Color;
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

public class AutoModel extends BaseItemModel {
    Double volume;
    String driveUnit;
    String bodyType;
    String issueYear;
    Integer millage;
    Color color;
    String model;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoModel autoModel = (AutoModel) o;
        return Objects.equals(volume, autoModel.volume) &&
                Objects.equals(driveUnit, autoModel.driveUnit) &&
                Objects.equals(bodyType, autoModel.bodyType) &&
                Objects.equals(issueYear, autoModel.issueYear) &&
                Objects.equals(millage, autoModel.millage) &&
                color == autoModel.color &&
                Objects.equals(model, autoModel.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, driveUnit, bodyType, issueYear, millage, color, model);
    }

    @Builder
    public AutoModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Status status, Double volume, String driveUnit, String bodyType, String issueYear, Integer millage, Color color, String model, List<AdditionalColumnDtoResponse> additionalList) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink,status,additionalList);
        this.volume = volume;
        this.driveUnit = driveUnit;
        this.bodyType = bodyType;
        this.issueYear = issueYear;
        this.millage = millage;
        this.color = color;
        this.model = model;
    }
}
