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
    public Item createBase(BaseItemModel model) throws NotFound {
        Subcategory subcategory1 = subcategoryService.getByName(model.getSubcategory());
        Category category1 = categoryService.getByName(model.getCategory());
        User user = userService.findByLogin(model.getUserLogin());
        if (user != null && subcategory1 != null && category1 != null) {
            switch (subcategory1.getName()) {
                case "auto": {
                    createAuto(model, user, category1, subcategory1);
                }
                case "bicycle": {
                    BicycleModel bicycle = (BicycleModel) model;
                    createBicycle(bicycle, user, category1, subcategory1);
                }
                case "motocycle": {
                    model = new MotocycleModel();
                    createMotocycle((MotocycleModel) model, user);
                }
                case "flat": {
                    FlatModel flatModel = (FlatModel) model;
                    createFlat(flatModel, user);
                }
            }
        }
        else if(user!=null&&category1==null&&subcategory1==null){
            Item item=Item.builder().user(user)
                    .price(model.getPrice())
                    .description(model.getDescription())
                    .itemState(model.getItemState())
                    .currency(model.getCurrency()).build();
            itemRepository.save(item);

            //return new BaseItemModel(item.getId(),item.getPrice(),item.getDescription(),item.getUser().getLogin(),item.getItemState(),item.getCurrency());
        }
        return null;
    }

    private Item createBicycle(BicycleModel bicycle, User user, Category category, Subcategory subcategory) {
        Item item = Item.builder().price(bicycle.getPrice())
                .color(bicycle.getColor())
                .itemState(bicycle.getItemState())
                .user(user)
                .description(bicycle.getDescription())
                // .currency(bicycle.getCurrency())
                .category(category)
                .subcategory(subcategory)
                .gender(bicycle.getGender())
                .description(bicycle.getDescription())
                .build();
        return itemRepository.save(item);
    }

    private Item createMotocycle(MotocycleModel motocycleModel, User user) {
        Item item = Item.builder().description(motocycleModel.getDescription())

                .user(user)
                .price(motocycleModel.getPrice())
                .model(motocycleModel.getModel())
                .color(motocycleModel.getColor())
                .volume(motocycleModel.getVolume())
                .build();
        return itemRepository.save(item);
    }

    @Override
    public Item createWithPhoto(ItemModel model, MultipartFile multipartFile) throws NotFound, IOException {
        Category category = categoryService.getByName(model.getCategory());
        Subcategory subcategory = subcategoryService.getByName(model.getSubcategory());
        User user = userService.findByLogin(model.getUserLogin());
        Item item = Item.builder().memory(model.getMemory())
                .category(category)
                .cpu(model.getCpu())
                .ssd(model.getSsd())
                .address(model.getAddress())
                .gender(model.getGender())
                .color(model.getColor())
                .description(model.getDescription())
                .square(model.getSquare())
                .price(model.getPrice())
                .user(user)
                .subcategory(subcategory)
                .itemState(model.getItemState())
                .photoLink(addImage(multipartFile))
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
        return new Item();
    }


    private Item createAuto(BaseItemModel itemModel, User user, Category category, Subcategory subcategory) {
      //  itemModel = new AutoModel();
        Item item = Item.builder().
                category(category)
                .subcategory(subcategory)
                .currency(itemModel.getCurrency())
                .itemState(itemModel.getItemState())
                .bodyType(((AutoModel) itemModel).getBodyType())
                .description(itemModel.getDescription())
                .driveUnit(((AutoModel) itemModel).getDriveUnit())
                .issueYear(((AutoModel) itemModel).getIssueYear())
                .millage(((AutoModel) itemModel).getMillage())
                .model(((AutoModel) itemModel).getModel())
                .price(itemModel.getPrice())
                .user(user)
                .build();
        return itemRepository.save(item);
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
