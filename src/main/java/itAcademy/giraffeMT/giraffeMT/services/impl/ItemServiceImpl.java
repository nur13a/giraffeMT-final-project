package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.dto.clothes.ClothesDto;
import itAcademy.giraffeMT.giraffeMT.dto.electronics.ComputerDto;
import itAcademy.giraffeMT.giraffeMT.dto.electronics.ElectronicDto;
import itAcademy.giraffeMT.giraffeMT.dto.electronics.PhoneDto;
import itAcademy.giraffeMT.giraffeMT.dto.immovables.HouseDto;
import itAcademy.giraffeMT.giraffeMT.dto.immovables.ImmovablesDto;
import itAcademy.giraffeMT.giraffeMT.dto.transport.AutoModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.BicycleModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.MotocycleModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.TransportModel;
import itAcademy.giraffeMT.giraffeMT.entities.*;
import itAcademy.giraffeMT.giraffeMT.enums.Status;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.ItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.immovables.FlatDto;
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
    public BaseItemModel createWithPhoto(ItemModel model, MultipartFile multipartFile) throws NotFound, IOException {
        Category category = categoryService.getByName(model.getCategory());
        Subcategory subcategory = subcategoryService.getByName(model.getSubcategory());
        User user = userService.findByLogin(model.getUserLogin());
        if (user != null && category != null || subcategory != null) {
            switch (category.getName()) {
                case "transport": {
                    switch (subcategory.getName()) {
                        case "auto":
                            return createAuto(model, user, category, subcategory, multipartFile);
                        case "motocycle":
                            return createMotocycle(model, user, category, subcategory, multipartFile);
                        case "bicycle":
                            return createBicycle(model, user, category, subcategory, multipartFile);
                    }
                }
                case "immovables": {
                    switch (subcategory.getName()) {
                        case "flat":
                            return createFlat(model, user, category, subcategory, multipartFile);
                        case "house":
                            return createHouse(model, user, category, subcategory, multipartFile);
                    }
                }
                case "electronics": {
                    switch (subcategory.getName()) {
                        case "phone":
                            return createPhone(model, user, category, subcategory, multipartFile);
                        case "computer":
                            return createComputer(model, user, category, subcategory, multipartFile);
                    }
                }
                case "clothes": {
                    return createClothes(model, user, category, subcategory, multipartFile);
                }
                default:
                    return createByDefault(model, user, category, subcategory, multipartFile);
            }

        } else if (user != null) {
            Item item =Item.builder().user(user)
                    .price(model.getPrice())
                    .description(model.getDescription())
                    .itemState(model.getItemState())
                    .currency(model.getCurrency())
                    .status(Status.ACTIVE)
                    .photoLink(addImage(multipartFile))
                    .additionalColumn(model.getAdditionalColumn())
                    .build();
            itemRepository.save(item);
            return createByDefault(model, user, null, null, multipartFile);
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


    private AutoModel createAuto(ItemModel it, User user, Category category, Subcategory subcategory, MultipartFile photo) throws IOException {

        Item item = Item.builder().
                category(category)
                .subcategory(subcategory)
                .bodyType(it.getBodyType())
                .model(it.getModel())
                .color(it.getColor())
                .description(it.getDescription())
                .driveUnit(it.getDriveUnit())
                .currency(it.getCurrency())
                .issueYear(it.getIssueYear())
                .millage(it.getMillage())
                .price(it.getPrice())
                .volume(it.getVolume())
                .itemState(it.getItemState())
                .user(user)
                .photoLink(addImage(photo))
                .status(Status.ACTIVE)
                .additionalColumn(it.getAdditionalColumn())
                .build();
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
                .status(item.getStatus())
                .additionalList(item.getAdditionalColumn())
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
                .status(Status.ACTIVE)
                .additionalColumn(it.getAdditionalColumn())
                .build();
        itemRepository.save(item);
        BicycleModel model = BicycleModel.builder()
                .description(item.getDescription())
                .userLogin(item.getUser().getLogin())
                .price(item.getPrice())
                .color(item.getColor())
                .gender(item.getGender())
                .currency(item.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(item.getSubcategory().getName())
                .category(item.getCategory().getName())
                .itemState(item.getItemState())
                .status(item.getStatus())
                .additionalList(item.getAdditionalColumn())
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
                .status(Status.ACTIVE)
                .additionalColumn(it.getAdditionalColumn())
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
                .status(item.getStatus())
                .additionalList(item.getAdditionalColumn())
                .id(item.getId()).build();
        return model;
    }

    private FlatDto createFlat(ItemModel it, User user, Category category, Subcategory subcategory, MultipartFile photo) throws IOException {
        Item item = Item.builder()
                .description(it.getDescription())
                .user(user)
                .price(it.getPrice())
                .currency(it.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(subcategory)
                .category(category)
                .itemState(it.getItemState())
                .district(it.getDistrict())
                .floor(it.getFloor())
                .roomNumber(it.getRoomNumber())
                .square(it.getSquare())
                .address(it.getAddress())
                .floorsNumber(it.getFloorsNumber())
                .buildingType(it.getBuildingType())
                .status(Status.ACTIVE)
                .additionalColumn(it.getAdditionalColumn())
                .build();
        itemRepository.save(item);
        FlatDto model = FlatDto.builder()
                .description(item.getDescription())
                .userLogin(item.getUser().getLogin())
                .price(item.getPrice())
                .currency(item.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(item.getSubcategory().getName())
                .category(item.getCategory().getName())
                .itemState(item.getItemState())
                .id(item.getId())
                .status(item.getStatus())
                .floor(item.getFloor())
                .roomNumber(item.getRoomNumber())
                .district(item.getDistrict())
                .floorsNumber(item.getFloorsNumber())
                .square(item.getSquare())
                .buildingType(item.getBuildingType())
                .additionalList(item.getAdditionalColumn())
                .build();
        return model;
    }

    private HouseDto createHouse(ItemModel it, User user, Category category, Subcategory subcategory, MultipartFile photo) throws IOException {
        Item item = Item.builder()
                .description(it.getDescription())
                .user(user)
                .price(it.getPrice())
                .currency(it.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(subcategory)
                .category(category)
                .itemState(it.getItemState())
                .district(it.getDistrict())
                .roomNumber(it.getRoomNumber())
                .square(it.getSquare())
                .address(it.getAddress())
                .floorsNumber(it.getFloorsNumber())
                .buildingType(it.getBuildingType())
                .address(it.getAddress())
                .landArea(it.getLandArea())
                .status(Status.ACTIVE)
                .buildingType(it.getBuildingType())
                .additionalColumn(it.getAdditionalColumn())
                .build();
        itemRepository.save(item);
        HouseDto model = HouseDto.builder()
                .description(item.getDescription())
                .userLogin(item.getUser().getLogin())
                .price(item.getPrice())
                .currency(item.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(item.getSubcategory().getName())
                .category(item.getCategory().getName())
                .itemState(item.getItemState())
                .id(item.getId())
                .status(item.getStatus())
                .roomNumber(item.getRoomNumber())
                .district(item.getDistrict())
                .floorsNumber(item.getFloorsNumber())
                .landArea(item.getLandArea())
                .square(item.getSquare())
                .additionalList(item.getAdditionalColumn())
                .buildingType(item.getBuildingType()).build();
        return model;
    }

    private PhoneDto createPhone(ItemModel it, User user, Category category, Subcategory subcategory, MultipartFile photo) throws IOException {
        Item item = Item.builder()
                .description(it.getDescription())
                .user(user)
                .price(it.getPrice())
                .currency(it.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(subcategory)
                .category(category)
                .itemState(it.getItemState())
                .status(Status.ACTIVE)
                .color(it.getColor())
                .memory(it.getMemory())
                .model(it.getModel())
                .additionalColumn(it.getAdditionalColumn())
                .build();
        itemRepository.save(item);
        PhoneDto model = PhoneDto.builder()
                .description(item.getDescription())
                .userLogin(item.getUser().getLogin())
                .price(item.getPrice())
                .currency(item.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(item.getSubcategory().getName())
                .category(item.getCategory().getName())
                .itemState(item.getItemState())
                .id(item.getId())
                .status(item.getStatus())
                .color(item.getColor())
                .memory(item.getMemory())
                .model(item.getModel())
                .additionalList(item.getAdditionalColumn())
                .build();
        return model;
    }

    private ComputerDto createComputer(ItemModel it, User user, Category category, Subcategory subcategory, MultipartFile photo) throws IOException {
        Item item = Item.builder()
                .description(it.getDescription())
                .user(user)
                .price(it.getPrice())
                .currency(it.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(subcategory)
                .category(category)
                .itemState(it.getItemState())
                .status(Status.ACTIVE)
                .cpu(it.getCpu())
                .numberCores(it.getNumberCores())
                .ssd(it.getSsd())
                .memory(it.getMemory())
                .model(it.getModel())
                .additionalColumn(it.getAdditionalColumn())
                .build();
        itemRepository.save(item);
        ComputerDto model = ComputerDto.builder()
                .description(item.getDescription())
                .userLogin(item.getUser().getLogin())
                .price(item.getPrice())
                .currency(item.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(item.getSubcategory().getName())
                .category(item.getCategory().getName())
                .itemState(item.getItemState())
                .id(item.getId())
                .status(item.getStatus())
                .memory(item.getMemory())
                .model(item.getModel())
                .cpu(item.getCpu())
                .numberCores(item.getNumberCores())
                .ssd(item.getSsd())
                .additionalList(item.getAdditionalColumn())
                .build();
        return model;
    }

    private ClothesDto createClothes(ItemModel it, User user, Category category, Subcategory subcategory, MultipartFile photo) throws IOException {
        Item item = Item.builder()
                .description(it.getDescription())
                .user(user)
                .price(it.getPrice())
                .currency(it.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(subcategory)
                .category(category)
                .itemState(it.getItemState())
                .status(Status.ACTIVE)
                .color(it.getColor())
                .gender(it.getGender())
                .size(it.getSize())
                .additionalColumn(it.getAdditionalColumn())
                .build();
        itemRepository.save(item);
        ClothesDto model = ClothesDto.builder()
                .description(item.getDescription())
                .userLogin(item.getUser().getLogin())
                .price(item.getPrice())
                .currency(item.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(item.getSubcategory().getName())
                .category(item.getCategory().getName())
                .itemState(item.getItemState())
                .id(item.getId())
                .status(item.getStatus())
                .color(item.getColor())
                .gender(item.getGender())
                .size(item.getSize())
                .additionalList(item.getAdditionalColumn())
                .build();
        return model;
    }

    private BaseItemModel createByDefault(ItemModel it, User user, Category category, Subcategory subcategory, MultipartFile photo) throws IOException {
        Item item = Item.builder()
                .description(it.getDescription())
                .user(user)
                .price(it.getPrice())
                .currency(it.getCurrency())
                .photoLink(addImage(photo))
                .subcategory(subcategory)
                .category(category)
                .itemState(it.getItemState())
                .status(Status.ACTIVE)
                .additionalColumn(it.getAdditionalColumn())
                .build();
        itemRepository.save(item);
        BaseItemModel model = new BaseItemModel();
        model.setDescription(item.getDescription());
        model.setUserLogin(item.getUser().getLogin());
        model.setPrice(item.getPrice());
        model.setCurrency(item.getCurrency());
        model.setPhotoLink(addImage(photo));
        model.setSubcategory(item.getSubcategory().getName());
        model.setCategory(item.getCategory().getName());
        model.setItemState(item.getItemState());
        model.setId(item.getId());
        model.setStatus(item.getStatus());
        model.setColumns(item.getAdditionalColumn());
        return model;
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
    public List<TransportModel> searchTransport(TransportModel transportModel) {
        List<Item> list = itemRepository.getTransports(
                transportModel.getBodyType(), transportModel.getColor(), transportModel.getCurrency(),
                transportModel.getDescription(), transportModel.getDriveUnit(), transportModel.getIssueYear(), transportModel.getMillage(),
                transportModel.getModel(), transportModel.getPrice(), transportModel.getVolume(), transportModel.getItemState(), transportModel.getGender());
        List<TransportModel> models = new ArrayList<>();
        for (Item i : list
        ) {
            models.add(TransportModel.builder().id(i.getId())
                    .price(i.getPrice())
                    .millage(i.getMillage())
                    .volume(i.getVolume())
                    .bodyType(i.getBodyType())
                    .itemState(i.getItemState())
                    .color(i.getColor())
                    .driveUnit(i.getDriveUnit())
                    .issueYear(i.getIssueYear())
                    .model(i.getModel())
                    .category(i.getCategory().getName())
                    .currency(i.getCurrency())
                    .description(i.getDescription())
                    .gender(i.getGender())
                    .photoLink(i.getPhotoLink())
                    .status(i.getStatus())
                    .subcategory(i.getSubcategory().getName())
                    .userLogin(i.getUser().getLogin()).build());
        }
        return models;

    }

    @Override
    public List<ElectronicDto> searchElectronics(ElectronicDto electronicDto) {
        List<Item> list = itemRepository.getElectronics(electronicDto.getSsd(), electronicDto.getModel(), electronicDto.getCpu(), electronicDto.getMemory(), electronicDto.getColor(), electronicDto.getItemState(), electronicDto.getNumberCores(), electronicDto.getPrice());
        List<ElectronicDto> models = new ArrayList<>();
        for (Item i : list
        ) {
            models.add(ElectronicDto.builder()
                    .id(i.getId())
                    .price(i.getPrice())
                    .itemState(i.getItemState())
                    .color(i.getColor())
                    .model(i.getModel())
                    .category(i.getCategory().getName())
                    .currency(i.getCurrency())
                    .description(i.getDescription())
                    .photoLink(i.getPhotoLink())
                    .status(i.getStatus())
                    .subcategory(i.getSubcategory().getName())
                    .userLogin(i.getUser().getLogin())
                    .cpu(i.getCpu())
                    .memory(i.getMemory())
                    .numberCores(i.getNumberCores())
                    .ssd(i.getSsd()).build());
        }
        return models;
    }

    @Override
    public List<ClothesDto> searchClothes(ClothesDto clothesDto) {
        List<Item> list = itemRepository.getClothes(clothesDto.getSize(), clothesDto.getColor(), clothesDto.getGender(), clothesDto.getPrice(), clothesDto.getItemState());
        List<ClothesDto> models = new ArrayList<>();
        for (Item i : list) {
            models.add(ClothesDto.builder()
                    .id(i.getId())
                    .price(i.getPrice())
                    .itemState(i.getItemState())
                    .color(i.getColor())
                    .category(i.getCategory().getName())
                    .currency(i.getCurrency())
                    .description(i.getDescription())
                    .photoLink(i.getPhotoLink())
                    .status(i.getStatus())
                    .subcategory(i.getSubcategory().getName())
                    .userLogin(i.getUser().getLogin())
                    .size(i.getSize())
                    .gender(i.getGender())
                    .build());
        }
        return models;
    }

    @Override
    public List<ImmovablesDto> searchImmovables(ImmovablesDto immovablesDto) {
        List<Item> list = itemRepository.getImmovables(immovablesDto.getAddress(), immovablesDto.getBuildingType(), immovablesDto.getDistrict(), immovablesDto.getSquare(), immovablesDto.getRoomNumber(),
                immovablesDto.getLandArea(), immovablesDto.getFloorsNumber(), immovablesDto.getCurrency(), immovablesDto.getPrice(), immovablesDto.getFloor());
        List<ImmovablesDto> models = new ArrayList<>();
        for (Item i : list) {
            models.add(ImmovablesDto.builder()
                    .id(i.getId())
                    .price(i.getPrice())
                    .itemState(i.getItemState())
                    .category(i.getCategory().getName())
                    .currency(i.getCurrency())
                    .description(i.getDescription())
                    .photoLink(i.getPhotoLink())
                    .status(i.getStatus())
                    .subcategory(i.getSubcategory().getName())
                    .userLogin(i.getUser().getLogin())
                    .address(i.getAddress())
                    .buildingType(i.getBuildingType())
                    .district(i.getDistrict())
                    .floor(i.getFloor())
                    .floorsNumber(i.getFloorsNumber())
                    .landArea(i.getLandArea())
                    .roomNumber(i.getRoomNumber())
                    .square(i.getSquare())
                    .build());
        }
        return models;
    }

    @Override
    public List<BaseItemModel> findByCategory(String category) {
        List<BaseItemModel> categoryItems = new ArrayList<>();
        List<Item> itemList = itemRepository.findAllByCategory_Name(category);
        Category category1 = categoryService.getByName(category);
        for (Item item : itemList
        ) {
            if (category1 != null) {
                if (category1.getName().equals("transport")) {
                    categoryItems.add(AutoModel.builder()
                            .id(item.getId())
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
                            .status(item.getStatus())
                            .photoLink(item.getPhotoLink())
                            .build());
                } else if (category1.getName().equals("immovables")) {
                    categoryItems.add(FlatDto.builder().price(item.getPrice())
                            .currency(item.getCurrency())
                            .description(item.getDescription())
                            .itemState(item.getItemState())
                            .userLogin(item.getUser().getLogin())
                            .category(category).subcategory(item.getSubcategory().getName())
                            .square(item.getSquare())
                            .district(item.getDistrict())
                            .floorsNumber(item.getFloorsNumber())
                            .roomNumber(item.getRoomNumber())
                            .floor(item.getFloor())
                            .id(item.getId()).build());
                } else if (category1.getName().equals("electronics")) {
                    categoryItems.add(ComputerDto.builder().price(item.getPrice())
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
                } else if (category1.getName().equals("clothes")) {
                    categoryItems.add(ClothesDto.builder().price(item.getPrice())
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

        return categoryItems;
    }
}
