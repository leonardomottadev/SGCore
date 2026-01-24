package br.com.sgcore.sgcore_cloud.modules.work.dto;

import lombok.Data;

@Data
public class WorkItemRequestDTO {
    private String title;
    private String description;
    private String status;
    private String priority;
    private String assignedTo;
    private Long projectId;
}