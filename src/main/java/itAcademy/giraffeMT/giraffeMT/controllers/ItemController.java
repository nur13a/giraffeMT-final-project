package itAcademy.giraffeMT.giraffeMT.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import itAcademy.giraffeMT.giraffeMT.dto.clothes.ClothesDto;
import itAcademy.giraffeMT.giraffeMT.dto.electronics.ElectronicDto;
import itAcademy.giraffeMT.giraffeMT.dto.immovables.ImmovablesDto;
import itAcademy.giraffeMT.giraffeMT.dto.transport.AutoModel;
import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.dto.BaseItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.ItemModel;
import itAcademy.giraffeMT.giraffeMT.dto.transport.TransportModel;
import itAcademy.giraffeMT.giraffeMT.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity getAll() {
        List<Item> items = itemService.getAll();
        try {
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) throws Exception {
        Item item = itemService.getById(id);
        try {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity create(@RequestParam("item") String itemModel, @RequestParam("file") MultipartFile multipartFile) {
        try {
            ItemModel object = new ObjectMapper().readValue(itemModel, ItemModel.class);
            BaseItemModel item = itemService.createWithPhoto(object, multipartFile);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            itemService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Item itemEntity) {
        Item item = itemService.update(itemEntity);
        try {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{description}")
    public ResponseEntity searchByDescription(@PathVariable("description") String description) {
        try {
            List<Item> items = itemService.findAllByDescriptionContains(description);
            return new ResponseEntity<>(items, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchByCategory/{category}")
    public ResponseEntity searchByCategory(@PathVariable("category") String category) {
        try {
            List<BaseItemModel> items = itemService.findByCategory(category);
            return new ResponseEntity<>(items, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/search/filter/transport")
    public ResponseEntity getTransports(@RequestBody TransportModel transportModel) {
        try {
            List<TransportModel> list = itemService.searchTransport(transportModel);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/search/filter/clothes")
    public ResponseEntity getClothes(@RequestBody ClothesDto clothesDto) {
        try {
            List<ClothesDto> list = itemService.searchClothes(clothesDto);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/search/filter/transport")
    public ResponseEntity getElectronics(@RequestBody ElectronicDto electronicDto) {
        try {
            List<ElectronicDto> list = itemService.searchElectronics(electronicDto);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/search/filter/immovables")
    public ResponseEntity getImmovables(@RequestBody ImmovablesDto immovablesDto) {
        try {
            List<ImmovablesDto> list = itemService.searchImmovables(immovablesDto);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
