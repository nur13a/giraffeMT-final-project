package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.entities.Category;
import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.models.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.models.immovables.FlatModel;
import itAcademy.giraffeMT.giraffeMT.models.transport.AutoModel;
import itAcademy.giraffeMT.giraffeMT.models.transport.Bicycle;
import itAcademy.giraffeMT.giraffeMT.models.transport.MotocycleModel;
import itAcademy.giraffeMT.giraffeMT.repositories.ItemRepository;
import itAcademy.giraffeMT.giraffeMT.services.ItemService;
import itAcademy.giraffeMT.giraffeMT.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public Item create(BaseItemModel model) {
        return null;
    }

    @Autowired
    private UserService userService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private SubcategoryServiceImpl subcategoryService;

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item getById(Long id) throws Exception {
        Optional<Item> user = itemRepository.findById(id);
        return user.orElse(null);
    }

  //  @Override
    public Item createe(BaseItemModel model, String category, String subcategory) throws Exception {
        Subcategory subcategory1 = subcategoryService.getByName(subcategory);
        Category category1 = categoryService.getByName(category);
        User user = userService.getById(model.getUserId());
        if (user != null && subcategory1 != null && category1 != null) {
            switch (subcategory1.getName()) {
                case "auto": {
                    AutoModel itemModel = (AutoModel) model;
                    createAuto(itemModel, user);
                }
                case "bicycle": {
                    Bicycle bicycle = (Bicycle) model;
                    createBicycle(bicycle, user);
                }
                case "motocycle": {
                    MotocycleModel motocycle = (MotocycleModel) model;
                    createMotocycle(motocycle, user);
                }
                case "flat": {
                    FlatModel flatModel = (FlatModel) model;
                    createFlat(flatModel, user);
                }
            }
        }return null;
    }


    private Item createAuto(AutoModel itemModel, User user) {
        Item item = Item.builder().bodyType(itemModel.getBodyType())
                .color(itemModel.getColor())
                .description(itemModel.getDescription())
                .driveUnit(itemModel.getDriveUnit())
                .issueYear(itemModel.getIssueYear())
                .millage(itemModel.getMillage())
                .model(itemModel.getModel())
                .price(itemModel.getPrice())
                .user(user)
                .build();
        return itemRepository.save(item);
    }

    private Item createBicycle(Bicycle bicycle, User user) {
        Item item = Item.builder().price(bicycle.getPrice())
                .color(bicycle.getColor())
                .itemState(bicycle.getState())
                .user(user)
                .gender(bicycle.getGender())
                .description(bicycle.getDescription())
                .build();
        return itemRepository.save(item);
    }

    private Item createMotocycle(MotocycleModel motocycleModel, User user) {
        Item item = Item.builder().description(motocycleModel.getDescription())
                .itemState(motocycleModel.getState())
                .user(user)
                .price(motocycleModel.getPrice())
                .model(motocycleModel.getModel())
                .color(motocycleModel.getColor())
                .volume(motocycleModel.getVolume())
                .build();
        return itemRepository.save(item);
    }

    private Item createFlat(FlatModel flat, User user) {
        Item item = Item.builder().description(flat.getDescription())
                .square(flat.getSquare())
                .floors(flat.getFloors())
                .roomNumber(flat.getRoomNumber())
                .district(flat.getDistrict())
                .price(flat.getPrice())
                .user(user)
                .itemState(flat.getState())
                .build();
        return itemRepository.save(item);
    }

    @Override
    public void delete(Long id) throws Exception {
        Item item = getById(id);
        if (item != null)
            itemRepository.delete(item);
        throw new NotFound("Item not found");
    }

    @Override
    public Item update(Item entity) {
        return itemRepository.save(entity);
    }
}
