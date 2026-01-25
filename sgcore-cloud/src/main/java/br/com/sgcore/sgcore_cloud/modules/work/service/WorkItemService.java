package br.com.sgcore.sgcore_cloud.modules.work.service;

import br.com.sgcore.sgcore_cloud.modules.core.interfaces.GenericCrudService;
import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItem;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemResponseDTO;
import br.com.sgcore.sgcore_cloud.modules.work.mapper.WorkItemMapper;
import br.com.sgcore.sgcore_cloud.modules.work.repository.WorkItemRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public WorkItemResponseDTO create(WorkItemRequestDTO dto) {
        WorkItem entity = WorkItemMapper.toEntity(dto);
        WorkItem saved = repository.save(entity);
        return WorkItemMapper.toDTO(saved);
    }

    @Override
    public List<WorkItemResponseDTO> createAll(List<WorkItemRequestDTO> dtoList) {
        List<WorkItem> entities = dtoList.stream()
                .map(WorkItemMapper::toEntity)
                .toList();
        List<WorkItem> saved = repository.saveAll(entities);
        return saved.stream().map(WorkItemMapper::toDTO).toList();
    }

    @Override
    public WorkItemResponseDTO findById(Long id) {
        Optional<WorkItem> workItemEntity = repository.findById(id);
        return workItemEntity.map(WorkItemMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Work Item not found"));
    }

    @Override
    public List<WorkItemResponseDTO> findAll() {
        List<WorkItem> workItems = repository.findAll();
        return workItems.stream().map(WorkItemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public WorkItemResponseDTO update(Long id, WorkItemRequestDTO dto) {
        WorkItem entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Work Item not found"));
        WorkItem saved = repository.save(WorkItemMapper.updateEntity(entity, dto));
        return WorkItemMapper.toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
