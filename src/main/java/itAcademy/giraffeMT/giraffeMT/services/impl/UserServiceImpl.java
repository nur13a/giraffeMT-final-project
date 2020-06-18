package itAcademy.giraffeMT.giraffeMT.services.impl;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.service.interfaces.BankAccountService;
import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.models.UserModel;
import itAcademy.giraffeMT.giraffeMT.repositories.UserRepository;
import itAcademy.giraffeMT.giraffeMT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    //  @Autowired
    // private BankAccountService bankAccountService;
    private BankAccountService bankAccountService;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) throws NotFound {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User create(UserModel model) throws NotFound {
//        if (model.getRequisite() != null) {
            BankAccount bankAccount = bankAccountService.findBankAccountByAccountNumber(model.getRequisite());
            User user = User.builder().fullName(model.getFullName())
                    .login(model.getLogin())
                    .password(model.getPassword())
                    .bankAccount(bankAccount).build();
            return userRepository.save(user);
         // throw new NotFound("bank account not found");
    }

    @Override
    public User findByLogin(String login) throws NotFound {
        try {
            return userRepository.findByLogin(login);
        } catch (Exception e) {
            throw new NotFound("User not found");
        }
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public void deleteByLogin(String login) throws NotFound {
        User user = findByLogin(login);
        userRepository.delete(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
