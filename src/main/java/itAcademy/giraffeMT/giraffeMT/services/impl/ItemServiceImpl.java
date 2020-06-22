package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.entities.Category;
import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.ItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.immovables.FlatModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.TransportModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.Bicycle;
import itAcademy.giraffeMT.giraffeMT.dto.transport.MotocycleModel;
import itAcademy.giraffeMT.giraffeMT.repositories.ItemRepository;
import itAcademy.giraffeMT.giraffeMT.services.ItemService;
import itAcademy.giraffeMT.giraffeMT.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
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
        return itemRepository.findAll();
    }

    @Override
    public Item getById(Long id) throws Exception {
        Optional<Item> user = itemRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public Item create(ItemModel model) throws NotFound {
        return null;
    }

    // @Override
    public Item createWithPhoto(ItemModel model, MultipartFile multipartFile) throws NotFound, IOException {
        Category category = categoryService.getByName(model.getCategory());
        Subcategory subcategory = subcategoryService.getByName(model.getSubcategory());
        User user = userService.findByLogin(model.getUserLogin());
        Item item = Item.builder().memory(model.getMemory())
                .category(category)
                .cpu(model.getCpu())
                .ssd(model.getSsd())
                .landArea(model.getLandArea())
                .address(model.getAddress())
                .gender(model.getGender())
                .color(model.getColor())
                .description(model.getDescription())
                .square(model.getSquare())
                .price(model.getPrice())
                .user(user)
                .itemState(model.getItemState())
                .photoLink(addImage(multipartFile))
                .subcategory(subcategory)
                .volume(model.getVolume())
                .model(model.getModel())
                .currency(model.getCurrency())
                .driveUnit(model.getDriveUnit())
                .numberCores(model.getNumberCores())
                .bodyType(model.getBodyType())
                .millage(model.getMillage()).build();

        return itemRepository.save(item);
    }

    private String addImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String modifiedFileName = System.currentTimeMillis() + file.getOriginalFilename();
        Path path = Paths.get("C:\\Users\\Nuriza\\Desktop\\giraffeMtProject\\" + modifiedFileName);
        Files.write(path, bytes);
        System.err.println(path.getFileName());
        return modifiedFileName;
    }

    //  @Override
    public Item createe(BaseItemModel model, String category, String subcategory) throws Exception {
        Subcategory subcategory1 = subcategoryService.getByName(subcategory);
        Category category1 = categoryService.getByName(category);
        User user = userService.getById(model.getUserId());
        if (user != null && subcategory1 != null && category1 != null) {
            switch (subcategory1.getName()) {
                case "auto": {
                    TransportModel itemModel = (TransportModel) model;
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
        }
        return null;
    }


    private Item createAuto(TransportModel itemModel, User user) {
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

    @Override
    public List<Item> findAllByTransportModel(TransportModel transportModel) {
        List<Item> list = itemRepository.findAllByBodyTypeContainsAndColorContainsAndCurrencyContainsAndDriveUnitContainsAndIssueYearBeforeAndMillageBeforeAndVolumeEqualsAndModelIsLikeAndPrice(
                transportModel.getBodyType(), transportModel.getColor(), transportModel.getCurrency(), transportModel.getDriveUnit(), transportModel.getIssueYear(), transportModel.getMillage(), transportModel.getVolume(), transportModel.getModel(), transportModel.getPrice());
        return list;
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

    @Override
    public List<Item> findAllByDescriptionContains(String description) {
        return itemRepository.findAllByDescriptionContains(description);
    }
}
