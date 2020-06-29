package itAcademy.giraffeMT.giraffeMT.repositories;

import itAcademy.giraffeMT.giraffeMT.entities.AdditionalColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalColumnRepo extends JpaRepository<AdditionalColumn,Long> {
    AdditionalColumn findByColumnName(String columnName);
}
