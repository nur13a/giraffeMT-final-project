package itAcademy.giraffeMT.giraffeMT.repositories;

import com.company.banksystem.enums.Currency;
import itAcademy.giraffeMT.giraffeMT.dto.ItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.AutoModel;
import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.enums.BuildingType;
import itAcademy.giraffeMT.giraffeMT.enums.Color;
import itAcademy.giraffeMT.giraffeMT.enums.Gender;
import itAcademy.giraffeMT.giraffeMT.enums.ItemState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    //    @Query(value = "select  new itAcademy.giraffeMT.giraffeMT.dto.transport.AutoModel(  i.id,i.price  ,i.description ,ug.login,i.itemState ,i.currency ,c.name  ,s.name ,i.photoLink ,i.status ,i.volume,i.driveUnit,i.bodyType,i.issueYear,i.millage,i.color,i.model)  from Item i left join i.user as ug" +
//            "left join i.category as  c   \n" +
//            "left join i.subcategory as s" +
//            " where  i.bodyType like :body_type or\n" +
//            " i.color like :color or\n" +
//            "i.currency like :currency or\n" +
//            "i.description like :desc or\n" +
//            "i.driveUnit like :drive_unit or \n" +
//            "i.issueYear like :is_year or\n" +
//            "i.millage = :millage or\n" +
//            "i.model like :model or\n" +
//            "i.price = :price or\n" +
//            "i.volume = :volume or \n" +
//            "i.itemState like :state")
    @Query(value = "from Item i " +
            " where  i.bodyType like :body_type or\n" +
            " i.color like :color or\n" +
            "i.currency like :currency or\n" +
            "i.description like :desc or\n" +
            "i.driveUnit like :drive_unit or \n" +
            "i.issueYear like :is_year or\n" +
            "i.millage = :millage or\n" +
            "i.model like :model or\n" +
            "i.price = :price or\n" +
            "i.volume = :volume or \n" +
            "i.itemState like :state or i.gender like :gender")
    List<Item> getTransports(@Param("body_type") String bodyType, @Param("color") Color color, @Param("currency") Currency currency,
                             @Param("desc") String desc, @Param("drive_unit") String driveUnit, @Param("is_year") String issueYear,
                             @Param("millage") Integer millage, @Param("model") String model, @Param("price") BigDecimal price,
                             @Param("volume") Double volume, @Param("state") ItemState state, @Param("gender") Gender gender);


    @Query(value = "from Item i where i.size like :sizze or i.color like :color or gender like :gender or i.price =:price or i.itemState like :itemState")
    List<Item> getClothes(@Param("sizze") String size, @Param("color") Color color, @Param("gender") Gender gender, @Param("price") BigDecimal price, @Param("itemState") ItemState itemState);

    @Query(value = "from Item i where i.ssd like :ssd or i.model like :model or i.cpu like :cpu or i.memory like :memory or i.color like :color or i.itemState like :itemState or i.numberCores = :numberCores or i.price = :price ")
    List<Item> getElectronics(@Param("ssd") String ssd, @Param("model") String model, @Param("cpu") String cpu, @Param("memory") String memory, @Param("color") Color color, @Param("itemState") ItemState itemState, @Param("numberCores") Integer numberCores, @Param("price") BigDecimal price);

    @Query(value = "from Item i where i.address like :address or i.buildingType like :buildingType or i.district like :district or i.square like :square or i.roomNumber = :roomNumber or i.landArea like :landArea or i.floorsNumber = :floorsNumber or i.currency like :currency or i.price = :price or i.floor = :floor ")
    List<Item> getImmovables(@Param("address") String address, @Param("buildingType") BuildingType buildingType, @Param("district") String district, @Param("square") Double square, @Param("roomNumber") Integer roomNumber, @Param("landArea") Double landArea, @Param("floorsNumber") Integer floorsNumber, @Param("currency") Currency currency, @Param("price") BigDecimal price, @Param("floor") Integer floor);

    List<Item> findAllByDescriptionContains(String description);

    List<Item> findAllByCategory_Name(String categoryName);
}
