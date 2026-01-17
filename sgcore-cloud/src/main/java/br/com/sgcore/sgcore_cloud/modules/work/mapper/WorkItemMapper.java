package br.com.sgcore.sgcore_cloud.modules.work.mapper;

import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItem;
import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItemPriority;
import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItemStatus;
import br.com.sgcore.sgcore_cloud.modules.work.dto.WorkItemDTO;

public class WorkItemMapper {
    public static WorkItem toEntity(WorkItemDTO dto) {
        if (dto == null) {
            return null;
        }
        WorkItem entity = new WorkItem();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(WorkItemStatus.fromString(dto.getStatus()).orElse(WorkItemStatus.UNKNOWN));
        entity.setPriority(WorkItemPriority.fromString(dto.getPriority()).orElse(WorkItemPriority.MEDIUM));
        entity.setAssignedTo(dto.getAssignedTo());
        return entity;
    }

    public static WorkItemDTO toDTO(WorkItem entity) {
        if (entity == null) {
            return null;
        }
        WorkItemDTO dto = new WorkItemDTO();
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus().name());
        dto.setPriority(entity.getPriority().name());
        dto.setAssignedTo(entity.getAssignedTo());
        return dto;
    }
}
