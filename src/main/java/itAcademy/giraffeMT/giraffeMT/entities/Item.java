package itAcademy.giraffeMT.giraffeMT.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "description", length = 8000)
    String description;

    @Builder.Default
    @Column(name = "createdDate")
    @CreatedDate
    Date createdDate = new Date();

    @Column(name = "itemState")
    String itemState;

    @ManyToOne
    @Column(name = "category_id")
    Category category;

    @ManyToOne
    @Column(name = "user_id")
    User user;

    @Column(name = "volume")
    Double volume;

    @Column(name = "driveUnit")
    String driveUnit;

    @Column(name = "bodyType")
    String bodyType;

    @Column(name = "issueYear")
    Date issueYear;

    @Column(name = "millage")
    Integer millage;

    @Column(name = "size")
    String size;

    @Column(name="color")
    String color;

    @Column(name="ssd")
    String ssd;

    @Column(name="memory")
    String memory;

    @Column(name="numberCores")
    Integer numberCores;

    @Column(name="cpu")
    String cpu;

    @Column(name="address")
    String address;

    @Column(name="square")
    Double square;

    @Column(name="floors")
    Integer floors;

    @Column(name="roomNumber")
    Integer roomNumber;

}
