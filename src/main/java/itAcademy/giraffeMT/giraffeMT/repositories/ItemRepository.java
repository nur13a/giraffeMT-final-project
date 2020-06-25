package itAcademy.giraffeMT.giraffeMT.repositories;

import itAcademy.giraffeMT.giraffeMT.dto.ItemModel;
import itAcademy.giraffeMT.giraffeMT.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select  * from item i\n" +
            "where i.body_type like :body_type or\n" +
            " i.color like :color or\n" +
            "i.currency like :currency or\n" +
            "i.description like :desc or\n" +
            "i.drive_unit like :drive_unit or \n" +
            "i.issue_year like :is_year or\n" +
            "i.millage = :millage or\n" +
            "i.model like :model or\n" +
            "i.price = :price or\n" +
            "i.volume = :volume or \n" +
            "i.item_state like :state",nativeQuery = true)
    List<ItemModel> getTransports(@Param("body_type") String bodyType, @Param("color") String color, @Param("currency") String currency,
                             @Param("desc") String desc, @Param("drive_unit") String driveUnit, @Param("is_year") String issueYear,
                             @Param("millage") Integer millage, @Param("model") String model, @Param("price") BigDecimal price,
                             @Param("volume") Double volume, @Param("state") String state);

    List<Item> findAllByDescriptionContains(String description);
//
//    @Query("select  i.body_type  ,i.color ,i.currency ,i.description ,i.drive_unit ,i.issue_year  ,i.millage ,i.model ,i.price ,i.volume,i.item_state from item i\n" +
//            "where i.color like :color")
//    List<TransportModel> getByColorTransport(@Param("color") String color);

    List<Item> findAllByCategory_Name(String categoryName);
}
