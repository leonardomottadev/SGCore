package br.com.sgcore.sgcore_cloud.modules.core.interfaces;

import java.util.Collection;
import java.util.List;

public interface GenericCrudService<E, Req, Res> {

    Res create(Req req);

    List<Res> createAll(List<Req> req);

    Res findById(Long id);

    Collection<Res> findAll();

    Res update(Long id, Req req);

    void delete(Long id);
}
