package br.com.sgcore.sgcore_cloud.modules.work.service;

import br.com.sgcore.sgcore_cloud.modules.core.interfaces.GenericCrudService;
import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItem;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemDTO;
import br.com.sgcore.sgcore_cloud.modules.work.mapper.WorkItemMapper;
import br.com.sgcore.sgcore_cloud.modules.work.repository.WorkItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkItemService implements GenericCrudService<WorkItem, WorkItemDTO> {

    private final WorkItemRepository repository;

    public WorkItemService(WorkItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public WorkItem insert(WorkItemDTO dto)
    {
        WorkItem entity = WorkItemMapper.toEntity(dto);
        return repository.save(entity);
    }

    @Override
    public List<WorkItemDTO> insert(List<WorkItemDTO> dtoList) {
        return List.of();
    }

    @Override
    public WorkItemDTO findById(Long id) {
        Optional<WorkItem> workItemEntity = repository.findById(id);
        return workItemEntity.map(WorkItemMapper::toDTO).orElse(null);
    }

    @Override
    public List<WorkItemDTO> findAll() {
        List<WorkItem> workItems = repository.findAll();
        return workItems.stream().map(WorkItemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void update(Long id, WorkItemDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }
}
