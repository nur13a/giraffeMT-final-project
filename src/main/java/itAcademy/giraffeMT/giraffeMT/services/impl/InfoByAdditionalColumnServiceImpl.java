package itAcademy.giraffeMT.giraffeMT.services.impl;

import itAcademy.giraffeMT.giraffeMT.entities.AdditionalColumnName;
import itAcademy.giraffeMT.giraffeMT.repositories.InfoByAdditionalColumnRepo;
import itAcademy.giraffeMT.giraffeMT.services.InfoByAdditionalColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoByAdditionalColumnServiceImpl implements InfoByAdditionalColumnService {
    @Autowired
    private InfoByAdditionalColumnRepo infoByAdditionalColumnRepo;
    @Override
    public AdditionalColumnName create(String info) {
        AdditionalColumnName information= AdditionalColumnName.builder().columnName(info).build();
        return infoByAdditionalColumnRepo.save(information);
    }

    @Override
    public AdditionalColumnName getByColumnName(String name) {
        return infoByAdditionalColumnRepo.findByColumnName(name);
    }
}
