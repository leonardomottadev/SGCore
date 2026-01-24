package br.com.sgcore.sgcore_cloud.modules.work.repository;

import br.com.sgcore.sgcore_cloud.modules.work.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
