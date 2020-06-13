package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.exceptions.UserNotFound;
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

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) throws UserNotFound {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(UserNotFound::new);
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void delete(Long id) throws UserNotFound {
        User user = getById(id);
        if (user != null)
            userRepository.delete(user);
        throw new UserNotFound();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);

    }
}
