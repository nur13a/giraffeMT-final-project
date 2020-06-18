package itAcademy.giraffeMT.giraffeMT.entities;

import com.company.banksystem.entity.BankAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "userGiraffe")
@EntityScan("com.company.banksystem.entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "fullName")
    String fullName;

    @Column(name = "login", nullable = false, unique = true)
    String login;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "isActive")
    Integer isActive;
    @ManyToOne//(targetEntity =com.company.banksystem.entity.BankAccount.class)
    @JoinColumn(name = "bankAccount")
    BankAccount bankAccount;
}
