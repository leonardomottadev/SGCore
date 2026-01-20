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
@Table(name = "projects_members")
public class ProjectMember extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    private ProjectMemberRole role;
}
