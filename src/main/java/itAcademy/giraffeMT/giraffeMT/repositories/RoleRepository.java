package itAcademy.giraffeMT.giraffeMT.repositories;

import itAcademy.giraffeMT.giraffeMT.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
