package br.com.sgcore.sgcore_cloud.modules.work.service;

import br.com.sgcore.sgcore_cloud.modules.core.interfaces.GenericCrudService;
import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItem;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkItemService implements GenericCrudService<WorkItem, WorkItemDTO> {

    @Override
    public WorkItem insert(WorkItemDTO dto) {
        return null;
    }

    @Override
    public List<WorkItemDTO> insert(List<WorkItemDTO> dtoList) {
        return List.of();
    }

    @Override
    public WorkItemDTO findById(Long id) {
        return null;
    }

    @Override
    public List<WorkItemDTO> findAll() {
        return null;
    }

    @Override
    public void update(Long id, WorkItemDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }
}
