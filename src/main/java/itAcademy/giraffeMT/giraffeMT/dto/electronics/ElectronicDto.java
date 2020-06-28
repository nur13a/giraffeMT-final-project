package itAcademy.giraffeMT.giraffeMT.dto.electronics;

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

@NoArgsConstructor

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ElectronicDto extends BaseItemModel {
    String ssd;
    String memory;
    Integer numberCores;
    String cpu;
    String model;
    Color color;

    @Builder
    public ElectronicDto(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Status status, String ssd, String memory, Integer numberCores, String cpu, String model, Color color, List<AdditionalColumnDtoResponse> additionalList) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink, status,additionalList);
        this.ssd = ssd;
        this.memory = memory;
        this.numberCores = numberCores;
        this.cpu = cpu;
        this.model = model;
        this.color = color;
    }
}
