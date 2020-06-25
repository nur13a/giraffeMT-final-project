package itAcademy.giraffeMT.giraffeMT.dto.electronics;

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

public class ComputerModel extends BaseItemModel {
    String ssd;
    String memory;
    Integer numberCores;
    String cpu;

    @Builder
    public ComputerModel(Long id, BigDecimal price, String description, String userLogin, ItemState itemState, Currency currency, String category, String subcategory, String ssd, String memory, Integer numberCores, String cpu) {
        super(id, price, description, userLogin, itemState, currency, category, subcategory);
        this.ssd = ssd;
        this.memory = memory;
        this.numberCores = numberCores;
        this.cpu = cpu;
    }
}
