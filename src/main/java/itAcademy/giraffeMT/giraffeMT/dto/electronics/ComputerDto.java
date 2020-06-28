package itAcademy.giraffeMT.giraffeMT.dto.electronics;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.dto.AdditionalColumnDtoResponse;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
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

public class ComputerDto extends BaseItemModel {
    String ssd;
    String memory;
    Integer numberCores;
    String cpu;
    String model;
    @Builder
    public ComputerDto(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String photoLink, Status status, String ssd, String memory, Integer numberCores, String cpu,String model, List<AdditionalColumnDtoResponse> additionalList) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory, photoLink,status,additionalList);
        this.ssd = ssd;
        this.memory = memory;
        this.numberCores = numberCores;
        this.cpu = cpu;
        this.model=model;
    }


}
