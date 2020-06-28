package itAcademy.giraffeMT.giraffeMT.controllers;

import itAcademy.giraffeMT.giraffeMT.dto.AdditionalColumnDtoRequest;
import itAcademy.giraffeMT.giraffeMT.entities.AdditionalColumn;
import itAcademy.giraffeMT.giraffeMT.services.AdditionalColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/additionalColumn")
public class AdditionalColumnController {
    @Autowired
    private AdditionalColumnService additionalColumnService;
    @PostMapping
    public ResponseEntity create(@RequestBody AdditionalColumnDtoRequest additionalColumn) {
        try {
            AdditionalColumnDtoRequest columns = additionalColumnService.createByModel(additionalColumn);
            return new ResponseEntity<>(columns, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity getAll() {
        List<AdditionalColumn> categories = additionalColumnService.getAll();
        try {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) throws Exception {
        AdditionalColumn additionalColumn = additionalColumnService.getById(id);
        try {
            return new ResponseEntity<>(additionalColumn, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            additionalColumnService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody AdditionalColumn additionalColumn) {
        AdditionalColumn columns = additionalColumnService.update(additionalColumn);
        try {
            return new ResponseEntity<>(columns, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
