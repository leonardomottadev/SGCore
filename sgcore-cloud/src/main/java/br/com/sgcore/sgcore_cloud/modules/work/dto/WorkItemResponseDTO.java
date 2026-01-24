package br.com.sgcore.sgcore_cloud.modules.work.dto;

import lombok.Data;

@Data
public class WorkItemResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String assignedTo;
    private ProjectResponseDTO project;
}
