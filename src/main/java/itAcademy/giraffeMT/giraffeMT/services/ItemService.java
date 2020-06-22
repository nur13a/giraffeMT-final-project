package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.ItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.TransportModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService extends BaseService<Item, ItemModel> {
     Item createe(BaseItemModel model, String category, String subcategory) throws Exception ;
     Item createWithPhoto(ItemModel itemModel, MultipartFile multipartFile) throws Exception;

    List<Item> findAllByDescriptionContains(String description);
    List<Item>findAllByTransportModel(TransportModel transportModel);

    }
