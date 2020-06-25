package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.ItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.TransportModel;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService extends BaseService<Item, ItemModel> {
     Item createe(BaseItemModel model, String category, String subcategory) throws Exception ;
     Item createWithPhoto(ItemModel itemModel, MultipartFile multipartFile) throws Exception;

    List<Item> findAllByDescriptionContains(String description);
    List<ItemModel>searchTransport(ItemModel transportModel);
   // List<TransportModel>getByColorTransport(String color );

    public Item createBase(BaseItemModel model) throws NotFound;

    public List<BaseItemModel>findByCategory(String category);
    }
