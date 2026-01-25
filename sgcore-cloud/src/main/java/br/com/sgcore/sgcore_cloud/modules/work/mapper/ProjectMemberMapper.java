package br.com.sgcore.sgcore_cloud.modules.work.mapper;

import br.com.sgcore.sgcore_cloud.modules.work.domain.Project;
import br.com.sgcore.sgcore_cloud.modules.work.domain.ProjectMember;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectMemberRequestDTO;
import br.com.sgcore.sgcore_cloud.modules.work.dto.ProjectMemberResponseDTO;
import br.com.sgcore.sgcore_cloud.modules.work.enums.ProjectMemberRole;

public class ProjectMemberMapper {
    public static ProjectMember toEntity(ProjectMemberRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        ProjectMember entity = new ProjectMember();
        entity.setUserId(dto.getUserId());
        entity.setRole(ProjectMemberRole.valueOf(dto.getRole()));
        entity.setRole(ProjectMemberRole.fromString(dto.getRole()).orElse(ProjectMemberRole.NONE));
        if (dto.getProjectId() != null) {
            Project project = new Project();
            project.setId(dto.getProjectId());
            entity.setProject(project);
        }
        return entity;
    }

    public static ProjectMember updateEntity(ProjectMember entity, ProjectMemberRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        entity.setUserId(dto.getUserId());
        entity.setRole(ProjectMemberRole.valueOf(dto.getRole()));
        entity.setRole(ProjectMemberRole.fromString(dto.getRole()).orElse(ProjectMemberRole.NONE));
        if (dto.getProjectId() != null) {
            Project project = new Project();
            project.setId(dto.getProjectId());
            entity.setProject(project);
        }
        return entity;
    }

    public static ProjectMemberResponseDTO toDTO(ProjectMember entity) {
        if (entity == null) {
            return null;
        }
        ProjectMemberResponseDTO dto = new ProjectMemberResponseDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setRole(entity.getRole().name());
        dto.setProject(ProjectMapper.toDTO(entity.getProject()));
        return dto;
    }
}
