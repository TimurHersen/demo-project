package se.demoproject.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.demoproject.query.entity.WorkOrderEntity;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrderEntity, String> {
}
