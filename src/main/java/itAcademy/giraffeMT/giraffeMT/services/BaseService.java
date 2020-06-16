package itAcademy.giraffeMT.giraffeMT.services;

import java.util.List;

public interface BaseService<T,J> {
    List<T> getAll();

    T getById(Long id) throws Exception;

    T create(J model);

    void delete(Long id) throws Exception;

    T update(T entity);
}
