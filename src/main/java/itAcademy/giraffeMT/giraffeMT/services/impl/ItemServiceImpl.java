package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.dto.clothes.ClothesModel;
import itAcademy.giraffeMT.giraffeMT.dto.electronics.ComputerModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.AutoModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.BicycleModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.MotocycleModel;
import itAcademy.giraffeMT.giraffeMT.entities.Category;
import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.entities.Subcategory;
import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.ItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.immovables.FlatModel;
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
import java.util.ArrayList;
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

    @Override
    public Item createe(BaseItemModel model, String category, String subcategory) throws Exception {
        return null;
    }

    @Override
    public BaseItemModel createWithPhoto(ItemModel model, MultipartFile multipartFile) throws NotFound, IOException {
        Category category = categoryService.getByName(model.getCategory());
        Subcategory subcategory = subcategoryService.getByName(model.getSubcategory());
        User user = userService.findByLogin(model.getUserLogin());
        if (user != null && subcategory != null && category != null) {
            Item item = Item.builder()
                    .description(model.getDescription())
                    .user(user)
                    .price(model.getPrice())
                    .currency(model.getCurrency())
                    .photoLink(addImage(multipartFile))
                    .subcategory(subcategory)
                    .category(category)
                    .itemState(model.getItemState())
                    .build();
            switch (category.getName()) {
                case "Transport": {
                    switch (subcategory.getName()) {
                        case "auto": {
                            return createAuto(model, item);
                        }
                        case "motocycle": {
                            return createMotocycle(model, user, category, subcategory, multipartFile);
                        }
                        case "bicycle": {
                            // return createBicycle();
                        }
                    }
                }
            }
        } else if (user != null && category == null && subcategory == null) {
            Item item = Item.builder().user(user)
                    .price(model.getPrice())
                    .description(model.getDescription())
                    .itemState(model.getItemState())
                    .currency(model.getCurrency()).build();
            itemRepository.save(item);
            //return itemRepository.save(item);
        }
        return null;
    }


    private String addImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String modifiedFileName = System.currentTimeMillis() + file.getOriginalFilename();
        Path path = Paths.get("C:\\Users\\Nuriza\\Desktop\\giraffeMtProject\\" + modifiedFileName);
        Files.write(path, bytes);
        System.err.println(path.getFileName());
        return modifiedFileName;
    }


    private AutoModel createAuto(ItemModel it, Item item) throws IOException {
        item.setBodyType(it.getBodyType());
        item.setModel(it.getModel());
        item.setColor(it.getColor());
        item.setDriveUnit(it.getDriveUnit());
        item.setIssueYear(it.getIssueYear());
        item.setMillage(it.getMillage());
        item.setVolume(it.getVolume());
        itemRepository.save(item);
        AutoModel autoModel = AutoModel.builder().
                id(item.getId()).bodyType(item.getBodyType())
                .model(item.getModel())
                .color(item.getColor())
                .description(item.getDescription())
                .driveUnit(item.getDriveUnit())
                .currency(item.getCurrency())
                .issueYear(item.getIssueYear())
                .millage(item.getMillage())
                .price(item.getPrice())
                .volume(item.getVolume())
                .itemState(item.getItemState())
                .category(item.getCategory().getName())
                .userLogin(item.getUser().getLogin())
                .subcategory(item.getSubcategory().getName())
                .photoLink(item.getPhotoLink())
                .build();
        return autoModel;
    }

    private BicycleModel createBicycle(ItemModel it, User user, Category category, Subcategory subcategory, MultipartFile photo) throws IOException {
        Item item = Item.builder()
                .description(it.getDescription())
                .user(user)
                .price(it.getPrice())
                .gender(it.getGender())
                .color(it.getColor())
                .currency(it.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(subcategory)
                .category(category)
                .itemState(it.getItemState())
                .build();
        itemRepository.save(item);
        BicycleModel model = BicycleModel.builder()
                .description(item.getDescription())
                .userLogin(item.getUser().getLogin())
                .price(item.getPrice())
                .color(item.getColor())
                .currency(item.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(item.getSubcategory().getName())
                .category(item.getCategory().getName())
                .itemState(item.getItemState())
                .id(item.getId()).build();
        return model;
    }

    private MotocycleModel createMotocycle(ItemModel it, User user, Category category, Subcategory subcategory, MultipartFile photo) throws IOException {
        Item item = Item.builder()
                .description(it.getDescription())
                .user(user)
                .price(it.getPrice())
                .model(it.getModel())
                .color(it.getColor())
                .volume(it.getVolume())
                .currency(it.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(subcategory)
                .category(category)
                .itemState(it.getItemState())
                .build();
        itemRepository.save(item);
        MotocycleModel model = MotocycleModel.builder()
                .description(item.getDescription())
                .userLogin(item.getUser().getLogin())
                .price(item.getPrice())
                .model(item.getModel())
                .color(item.getColor())
                .volume(item.getVolume())
                .currency(item.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(item.getSubcategory().getName())
                .category(item.getCategory().getName())
                .itemState(item.getItemState())
                .id(item.getId()).build();
        return model;
    }

    @Override
    public List<ItemModel> searchTransport(ItemModel transportModel) {
       /* List<ItemModel> list = itemRepository.getTransports(
                transportModel.getBodyType(), transportModel.getColor(), transportModel.getCurrency(),
                transportModel.getDescription(), transportModel.getDriveUnit(), transportModel.getIssueYear(), transportModel.getMillage(),
                transportModel.getModel(), transportModel.getPrice(), transportModel.getVolume(), transportModel.getItemState());
        return list;*/
        return null;
    }

    @Override
    public Item createBase(BaseItemModel model) throws NotFound {
        return null;
    }


    private Item createFlat(FlatModel flat, User user) {
        Item item = Item.builder().description(flat.getDescription())
                .square(flat.getSquare())
                .floors(flat.getFloors())
                .roomNumber(flat.getRoomNumber())
                .district(flat.getDistrict())
                .price(flat.getPrice())
                .user(user)
                .itemState(flat.getItemState())
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

    @Override
    public List<BaseItemModel> findByCategory(String category) {
        List<BaseItemModel> categoryItems = new ArrayList<>();
        List<Item> itemList = itemRepository.findAllByCategory_Name(category);
        Category category1 = categoryService.getByName(category);
        for (Item item : itemList
        ) {
            if (category1 != null) {
                switch (category1.getName()) {
                    case "transport": {
                        categoryItems.add(AutoModel.builder()
                                .bodyType(item.getBodyType())
                                .price(item.getPrice())
                                .currency(item.getCurrency())
                                .description(item.getDescription())
                                .itemState(item.getItemState())
                                .userLogin(item.getUser().getLogin())
                                .category(category)
                                .color(item.getColor())
                                .driveUnit(item.getDriveUnit())
                                .issueYear(item.getIssueYear())
                                .millage(item.getMillage())
                                .model(item.getModel())
                                .volume(item.getVolume())
                                .subcategory(item.getSubcategory().getName())
                                .build());
                    }

                    case "immovables": {
                        categoryItems.add(FlatModel.builder().price(item.getPrice())
                                .currency(item.getCurrency())
                                .description(item.getDescription())
                                .itemState(item.getItemState())
                                .userLogin(item.getUser().getLogin())
                                .category(category).subcategory(item.getSubcategory().getName())
                                .square(item.getSquare())
                                .district(item.getDistrict())
                                .floorNumber(item.getFloors())
                                .roomNumber(item.getRoomNumber())
                                .floors(item.getFloors())
                                .id(item.getId()).build());
                    }
                    case "electronics": {
                        categoryItems.add(ComputerModel.builder().price(item.getPrice())
                                .currency(item.getCurrency())
                                .description(item.getDescription())
                                .itemState(item.getItemState())
                                .userLogin(item.getUser().getLogin())
                                .category(category)
                                .subcategory(item.getSubcategory().getName())
                                .cpu(item.getCpu())
                                .memory(item.getMemory())
                                .numberCores(item.getNumberCores())
                                .ssd(item.getSsd())
                                .id(item.getId()).build());
                    }
                    case "clothes": {
                        categoryItems.add(ClothesModel.builder().price(item.getPrice())
                                .currency(item.getCurrency())
                                .description(item.getDescription())
                                .itemState(item.getItemState())
                                .userLogin(item.getUser().getLogin())
                                .category(category)
                                .subcategory(item.getSubcategory().getName())
                                .size(item.getSize())
                                .color(item.getColor()).build());
                    }
                }
            }
        }
        return categoryItems;
    }
}
