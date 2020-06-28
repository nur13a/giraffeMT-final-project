package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.dto.clothes.ClothesDto;
import itAcademy.giraffeMT.giraffeMT.dto.electronics.ElectronicDto;
import itAcademy.giraffeMT.giraffeMT.dto.immovables.ImmovablesDto;
import itAcademy.giraffeMT.giraffeMT.dto.transport.AutoModel;
import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.ItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.TransportModel;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService extends BaseService<Item, ItemModel> {
     BaseItemModel createWithPhoto(ItemModel itemModel, MultipartFile multipartFile) throws Exception;

    List<Item> findAllByDescriptionContains(String description);
    List<TransportModel>searchTransport(TransportModel transportModel);
    List<ElectronicDto>searchElectronics(ElectronicDto electronicDto);
    List<ClothesDto>searchClothes(ClothesDto clothesDto);
    List<ImmovablesDto>searchImmovables(ImmovablesDto immovablesDto);
    public List<BaseItemModel>findByCategory(String category);
    }
