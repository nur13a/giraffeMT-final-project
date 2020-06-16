package itAcademy.giraffeMT.giraffeMT.services;

import com.fasterxml.jackson.databind.ser.Serializers;
import itAcademy.giraffeMT.giraffeMT.entities.Category;

public interface CategoryService extends BaseService<Category,Category> {
    Category getByName(String name);
}
