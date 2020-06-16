package itAcademy.giraffeMT.giraffeMT.repositories;
import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Subcategory findByName(String name);
}
