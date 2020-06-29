package itAcademy.giraffeMT.giraffeMT.repositories;

import itAcademy.giraffeMT.giraffeMT.entities.AdditionalColumnName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoByAdditionalColumnRepo extends JpaRepository<AdditionalColumnName,Long> {
    AdditionalColumnName findByColumnName(String columnName);
}
