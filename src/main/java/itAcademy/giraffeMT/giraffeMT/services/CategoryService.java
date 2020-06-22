package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.Category;
import itAcademy.giraffeMT.giraffeMT.dto.CategoryModel;

public interface CategoryService extends BaseService<Category, CategoryModel> {
    Category getByName(String name);
}
