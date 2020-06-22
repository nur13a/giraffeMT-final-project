package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.dto.SubcategoryModel;
import itAcademy.giraffeMT.giraffeMT.repositories.SubcategoryRepository;
import itAcademy.giraffeMT.giraffeMT.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public Subcategory getByName(String name) {
        return subcategoryRepository.findByName(name);
    }

    @Override
    public List<Subcategory> getAll() {
        return subcategoryRepository.findAll();
    }

    @Override
    public Subcategory getById(Long id) {
        Optional<Subcategory> subcategory = subcategoryRepository.findById(id);
        return subcategory.orElse(null);
    }

    @Override
    public Subcategory create(SubcategoryModel model) {
        return subcategoryRepository.save(Subcategory.builder().name(model.getName())
                .category(model.getCategory()).build());

    }

    @Override
    public void delete(Long id) throws Exception {
        Subcategory subcategory = getById(id);
        if (subcategory != null)
            subcategoryRepository.delete(subcategory);
        throw new NotFound("Subcategory not found");
    }

    @Override
    public Subcategory update(Subcategory entity) {
        return subcategoryRepository.save(entity);
    }
}
