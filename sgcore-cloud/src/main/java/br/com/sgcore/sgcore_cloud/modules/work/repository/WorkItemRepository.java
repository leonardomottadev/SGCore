package br.com.sgcore.sgcore_cloud.modules.work.repository;

import br.com.sgcore.sgcore_cloud.modules.work.domain.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {

}
