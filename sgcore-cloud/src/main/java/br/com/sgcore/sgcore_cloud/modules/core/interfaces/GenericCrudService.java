package br.com.sgcore.sgcore_cloud.modules.core.interfaces;

import java.util.Collection;
import java.util.List;

public interface GenericCrudService<T, R> {

    T insert(R r);

    List<R> insert(List<R> r);

    R findById(Long id);

    Collection<R> findAll();

    void update(Long id, R r);

    void delete(Long id);
}
