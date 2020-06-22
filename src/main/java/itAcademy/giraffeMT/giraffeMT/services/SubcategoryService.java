package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import itAcademy.giraffeMT.giraffeMT.dto.SubcategoryModel;

public interface SubcategoryService extends BaseService<Subcategory, SubcategoryModel> {
    Subcategory getByName(String name);
}
