package br.com.sgcore.sgcore_cloud.modules.core.interfaces;

import java.util.Collection;
import java.util.List;

public interface GenericCrudService<E, Req, Res> {

    E create(Req req);

    List<E> createAll(List<Req> req);

    Res findById(Long id);

    Collection<Res> findAll();

    void update(Long id, Req req);

    void delete(Long id);
}
