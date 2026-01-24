package br.com.sgcore.sgcore_cloud.modules.work.mapper;

import br.com.sgcore.sgcore_cloud.modules.work.domain.Project;
import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItem;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.enums.WorkItemPriority;
import br.com.sgcore.sgcore_cloud.modules.work.enums.WorkItemStatus;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemResponseDTO;

public class WorkItemMapper {
    public static WorkItem toEntity(WorkItemResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        WorkItem entity = new WorkItem();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setAssignedTo(dto.getAssignedTo());
        entity.setDescription(dto.getDescription());
        entity.setStatus(WorkItemStatus.fromString(dto.getStatus()).orElse(WorkItemStatus.UNKNOWN));
        entity.setPriority(WorkItemPriority.fromString(dto.getPriority()).orElse(WorkItemPriority.MEDIUM));
        entity.setProject(ProjectMapper.toEntity(dto.getProject()));
        return entity;
    }

    public static WorkItem toEntity(WorkItemRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        WorkItem entity = new WorkItem();
        entity.setTitle(dto.getTitle());
        entity.setAssignedTo(dto.getAssignedTo());
        entity.setDescription(dto.getDescription());
        entity.setStatus(WorkItemStatus.fromString(dto.getStatus()).orElse(WorkItemStatus.UNKNOWN));
        entity.setPriority(WorkItemPriority.fromString(dto.getPriority()).orElse(WorkItemPriority.MEDIUM));
        if(dto.getProjectId() != null) {
            Project project = new Project();
            project.setId(dto.getProjectId());
            entity.setProject(project);
        }
        return entity;
    }

    public static WorkItemResponseDTO toDTO(WorkItem entity) {
        if (entity == null) {
            return null;
        }
        WorkItemResponseDTO dto = new WorkItemResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus().name());
        dto.setPriority(entity.getPriority().name());
        dto.setAssignedTo(entity.getAssignedTo());
        dto.setProject(ProjectMapper.toDTO(entity.getProject()));
        return dto;
    }
}
