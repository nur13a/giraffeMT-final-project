package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.exceptions.UserNotFound;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(Long id) throws UserNotFound;

    User create(User user);

    void delete(Long id) throws UserNotFound;

    User update(User user);
}
