package br.com.sgcore.sgcore_cloud.modules.work.domain;

import br.com.sgcore.sgcore_cloud.modules.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "work_items")
public class WorkItem extends BaseEntity {
    private String title;
    private String description;
    private String assignedTo;

    @Enumerated(EnumType.STRING)
    private WorkItemStatus status;

    @Enumerated(EnumType.STRING)
    private WorkItemPriority priority;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
