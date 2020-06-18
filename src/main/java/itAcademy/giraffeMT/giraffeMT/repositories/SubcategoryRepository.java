package itAcademy.giraffeMT.giraffeMT.repositories;
import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Subcategory findByName(String name);
}
