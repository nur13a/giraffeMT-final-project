package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.dto.AdditionalColumnDtoRequest;
import itAcademy.giraffeMT.giraffeMT.dto.ItemDynamicColumnDto;
import itAcademy.giraffeMT.giraffeMT.entities.AdditionalColumn;
import itAcademy.giraffeMT.giraffeMT.entities.Category;
import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.repositories.AdditionalColumnRepo;
import itAcademy.giraffeMT.giraffeMT.services.AdditionalColumnService;
import itAcademy.giraffeMT.giraffeMT.services.CategoryService;
import itAcademy.giraffeMT.giraffeMT.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalColumnServiceImpl implements AdditionalColumnService {
    @Autowired
    private AdditionalColumnRepo additionalColumnRepo;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubcategoryService subcategoryService;

    @Override
    public List<AdditionalColumn> getAll() {
        return additionalColumnRepo.findAll();
    }

    @Override
    public AdditionalColumn getById(Long id) throws Exception {
        Optional<AdditionalColumn> user = additionalColumnRepo.findById(id);
        return user.orElse(null);
    }

    @Override
    public AdditionalColumn create(AdditionalColumnDtoRequest model) throws NotFound {
        return null;
    }

    @Override
    public AdditionalColumnDtoRequest createByModel(AdditionalColumnDtoRequest model) throws NotFound {
        Category category=categoryService.getByName(model.getCategoryName());
        Subcategory subcategory=subcategoryService.getByName(model.getSubcategoryName());
        if(category !=null &&subcategory!=null){
            AdditionalColumn additionalColumn=AdditionalColumn.builder()
                    .columnName(model.getColumnName())
                    .category(category)
                    .subcategory(subcategory)
                    .inf0(model.getInfo()).build();
            additionalColumnRepo.save(additionalColumn);
            return AdditionalColumnDtoRequest.builder().categoryName(additionalColumn.getCategory().getName())
                    .columnName(additionalColumn.getColumnName())
                    .info(additionalColumn.getInf0())
                    .subcategoryName(additionalColumn.getSubcategory().getName()).build();
        }
        else throw new NotFound("Category or Subcategory doesn't exist");
    }

    @Override
    public void delete(Long id) throws Exception {
        AdditionalColumn column = getById(id);
        if (column != null)
            additionalColumnRepo.delete(column);
        throw new NotFound("Column not found");
    }

    @Override
    public AdditionalColumn update(AdditionalColumn entity) {
        return additionalColumnRepo.save(entity);
    }
}
