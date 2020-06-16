package itAcademy.giraffeMT.giraffeMT.repositories;

import itAcademy.giraffeMT.giraffeMT.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
