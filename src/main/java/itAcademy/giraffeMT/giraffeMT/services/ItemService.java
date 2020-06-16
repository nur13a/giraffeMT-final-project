package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.models.BaseItemModel;

public interface ItemService extends BaseService<Item, BaseItemModel> {
     Item createe(BaseItemModel model, String category, String subcategory) throws Exception ;

    }
