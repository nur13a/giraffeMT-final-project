package itAcademy.giraffeMT.giraffeMT.entities;

import itAcademy.giraffeMT.giraffeMT.enums.PaymentType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne
    @JoinColumn(name = "userFrom", referencedColumnName = "id")
    User userFrom;
    @OneToOne
    @JoinColumn(name = "item", referencedColumnName = "id")
    Item item;
    @ManyToOne
    @JoinColumn(name = "userTo", referencedColumnName = "id")
    User userTo;
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentType")
    PaymentType paymentType;
}
