package itAcademy.giraffeMT.giraffeMT.services;

import itAcademy.giraffeMT.giraffeMT.entities.AdditionalColumnName;

public interface InfoByAdditionalColumnService {
    AdditionalColumnName create(String info);
    AdditionalColumnName getByColumnName(String name);

}
