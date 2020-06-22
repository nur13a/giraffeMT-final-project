package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.Role;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;

import java.util.List;

public interface RoleService  {
    List<Role> getAll();

    Role getById(Long id) throws Exception;

    Role save(Role role) throws NotFound;

    void delete(Long id) throws Exception;
}
