package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.entities.Purchase;
import itAcademy.giraffeMT.giraffeMT.entities.Role;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.repositories.RoleRepository;
import itAcademy.giraffeMT.giraffeMT.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Long id) {
        Optional<Role> user = roleRepository.findById(id);
        return user.orElse(null);

    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) throws Exception {
        Role role = getById(id);
        if (role != null)
            roleRepository.delete(role);
        throw new NotFound("Role doesn't exist");
    }
}
