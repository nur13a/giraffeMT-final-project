package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.Purchase;
import itAcademy.giraffeMT.giraffeMT.dto.PurchaseDto;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;

public interface PurchaseService extends BaseService<Purchase, PurchaseDto> {
    public PurchaseDto createByModel(PurchaseDto model) throws NotFound;
}
