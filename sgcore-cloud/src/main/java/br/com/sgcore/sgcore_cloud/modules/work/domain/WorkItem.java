package br.com.sgcore.sgcore_cloud.modules.work.domain;

import br.com.sgcore.sgcore_cloud.modules.core.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "work-item")
public class WorkItem extends BaseEntity {
    private String title;
    private String description;
    private String status;
}
