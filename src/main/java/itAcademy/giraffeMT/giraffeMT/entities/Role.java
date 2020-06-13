package itAcademy.giraffeMT.giraffeMT.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "roleName")
    String roleName;

    @ManyToOne
    @Column(name = "user")
    User user;


}
