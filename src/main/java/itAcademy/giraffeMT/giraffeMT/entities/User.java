package itAcademy.giraffeMT.giraffeMT.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "userGiraffe")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "fullName")
    String fullName;

    @Column(name = "login")
    String login;
    @JsonIgnore
    @Column(name = "password")
    String password;

    @Column(name = "isActive")
    Integer isActive;
}
