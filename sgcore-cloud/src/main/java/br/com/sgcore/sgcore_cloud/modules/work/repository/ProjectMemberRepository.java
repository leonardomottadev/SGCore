package br.com.sgcore.sgcore_cloud.modules.work.repository;

import br.com.sgcore.sgcore_cloud.modules.work.domain.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
}
