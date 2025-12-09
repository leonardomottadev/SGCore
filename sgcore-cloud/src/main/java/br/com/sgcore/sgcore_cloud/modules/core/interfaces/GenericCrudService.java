package br.com.sgcore.sgcore_cloud.modules.core.interfaces;

import java.util.List;

public interface GenericCrudService<T, R> {

    R insert(T t);

    List<R> insert(List<T> t);

    R findById(Long id);

    void update(T t);

    void delete(Long id);
}
