package br.com.sgcore.sgcore_cloud.modules.work.service;

import br.com.sgcore.sgcore_cloud.modules.core.interfaces.GenericCrudService;
import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItem;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemResponseDTO;
import br.com.sgcore.sgcore_cloud.modules.work.mapper.WorkItemMapper;
import br.com.sgcore.sgcore_cloud.modules.work.repository.WorkItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkItemService implements GenericCrudService<WorkItem, WorkItemRequestDTO, WorkItemResponseDTO> {

    private final WorkItemRepository repository;

    public WorkItemService(WorkItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public WorkItem create(WorkItemRequestDTO dto) {
        WorkItem entity = WorkItemMapper.toEntity(dto);
        return repository.save(entity);
    }

    @Override
    public List<WorkItem> createAll(List<WorkItemRequestDTO> dtoList) {
        List<WorkItem> entities = dtoList.stream()
                .map(WorkItemMapper::toEntity)
                .toList();
        return repository.saveAll(entities);
    }

    @Override
    public WorkItemResponseDTO findById(Long id) {
        Optional<WorkItem> workItemEntity = repository.findById(id);
        return workItemEntity.map(WorkItemMapper::toDTO).orElse(null);
    }

    @Override
    public List<WorkItemResponseDTO> findAll() {
        List<WorkItem> workItems = repository.findAll();
        return workItems.stream().map(WorkItemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void update(Long id, WorkItemRequestDTO dto) {
        Optional<WorkItem> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return;
        }
        repository.save(WorkItemMapper.toEntity(dto));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
