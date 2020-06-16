package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.entities.Category;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.repositories.CategoryRepository;
import itAcademy.giraffeMT.giraffeMT.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> user = categoryRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public Category create(Category model) {
        return categoryRepository.save(Category.builder().name(model.getName()).build());
    }

    @Override
    public void delete(Long id) throws Exception {
        Category category = getById(id);
        if (category != null)
            categoryRepository.delete(category);
        throw new NotFound("Category not found");
    }

    @Override
    public Category update(Category entity) {
        return categoryRepository.save(entity);
    }
}
