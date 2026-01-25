package br.com.sgcore.sgcore_cloud.modules.work.dto;

import lombok.Data;

@Data
public class ProjectMemberRequestDTO {
    private Long projectId;
    private String userId;
    private String role;
}
