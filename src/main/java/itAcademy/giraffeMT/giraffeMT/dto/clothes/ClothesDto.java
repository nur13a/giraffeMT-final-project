package itAcademy.giraffeMT.giraffeMT.dto.clothes;

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
public class ClothesDto extends BaseItemModel {
    String size;
    Color color;
    Gender gender;

    @Builder

    public ClothesDto(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Status status, List<AdditionalColumnDtoResponse> columns, String size, Color color, Gender gender) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink, status, columns);
        this.size = size;
        this.color = color;
        this.gender = gender;
    }
}
