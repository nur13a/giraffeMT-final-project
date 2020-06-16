package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import itAcademy.giraffeMT.giraffeMT.models.SubcategoryModel;

import java.util.List;

public interface SubcategoryService extends BaseService<Subcategory, SubcategoryModel> {
    Subcategory getByName(String name);
}
