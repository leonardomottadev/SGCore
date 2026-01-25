package br.com.sgcore.sgcore_cloud.modules.work.dto;

import lombok.Data;

@Data
public class ProjectMemberResponseDTO {
    private Long id;
    private ProjectResponseDTO project;
    private String userId;
    private String role;
}
