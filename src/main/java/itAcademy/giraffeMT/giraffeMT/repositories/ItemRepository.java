package itAcademy.giraffeMT.giraffeMT.repositories;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.entities.Category;
import itAcademy.giraffeMT.giraffeMT.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item>findAllByBodyTypeContainsAndColorContainsAndCurrencyContainsAndDriveUnitContainsAndIssueYearBeforeAndMillageBeforeAndVolumeEqualsAndModelIsLikeAndPrice
            (String bodyType, String color, Currency currency, String driveUnit, String issueYear, Integer millage, Double volume, String model, BigDecimal price);

    List<Item> findAllByDescriptionContains(String description);
}
