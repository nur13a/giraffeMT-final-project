package itAcademy.giraffeMT.giraffeMT.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserModel {
    String fullName;
    String login;
    String password;
}
