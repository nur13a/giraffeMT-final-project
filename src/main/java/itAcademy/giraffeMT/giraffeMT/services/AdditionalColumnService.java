package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.dto.AdditionalColumnDtoRequest;
import itAcademy.giraffeMT.giraffeMT.dto.ItemDynamicColumnDto;
import itAcademy.giraffeMT.giraffeMT.entities.AdditionalColumn;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;

import java.util.List;

public interface AdditionalColumnService  extends BaseService<AdditionalColumn, AdditionalColumnDtoRequest>{
    public AdditionalColumnDtoRequest createByModel(AdditionalColumnDtoRequest model) throws NotFound;
}
