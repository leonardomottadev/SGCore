package br.com.sgcore.sgcore_cloud.modules.work.dto;

import lombok.Data;

@Data
public class WorkItemDTO {
    private String title;
    private String description;
    private String status;
}
