package br.com.sgcore.sgcore_cloud.modules.work.mapper;

import br.com.sgcore.sgcore_cloud.modules.work.domain.Project;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectResponseDTO;

public class ProjectMapper {
    public static Project toEntity(ProjectResponseDTO dto) {
        if(dto == null) {
            return null;
        }
        Project entity = new Project();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static Project toEntity(ProjectRequestDTO dto) {
        if(dto == null) {
            return null;
        }
        Project entity = new Project();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static Project updateEntity(Project entity, ProjectRequestDTO dto) {
        if(dto == null) {
            return entity;
        }
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static ProjectResponseDTO toDTO(Project entity) {
        if(entity == null) {
            return null;
        }
        ProjectResponseDTO dto = new ProjectResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
