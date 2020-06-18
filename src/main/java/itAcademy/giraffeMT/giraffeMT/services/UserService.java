package itAcademy.giraffeMT.giraffeMT.services;
import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.models.UserModel;

public interface UserService extends BaseService<User, UserModel> {
    User findByLogin(String login) throws NotFound;
    void deleteByLogin(String login) throws NotFound;
}
