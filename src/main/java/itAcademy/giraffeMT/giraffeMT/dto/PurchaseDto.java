package itAcademy.giraffeMT.giraffeMT.dto;

import itAcademy.giraffeMT.giraffeMT.enums.PaymentType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PurchaseDto {
    String userFrom;
    Long itemId;
    String userTo;
    PaymentType paymentType;
}
