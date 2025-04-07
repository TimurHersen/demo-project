package se.demoproject.query.handler;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import se.demoproject.api.events.WorkOrderAssignedEvent;
import se.demoproject.api.events.WorkOrderCreatedEvent;
import se.demoproject.api.events.WorkOrderExecutedEvent;
import se.demoproject.api.exception.InvalidWorkOrderStateException;
import se.demoproject.api.exception.WorkOrderNotFoundException;
import se.demoproject.api.queries.FindWorkOrderQuery;
import se.demoproject.api.queries.WorkOrderQueryResponse;
import se.demoproject.query.entity.WorkOrderEntity;
import se.demoproject.query.repository.WorkOrderRepository;

@Component
@RequiredArgsConstructor
public class WorkOrderQueryHandler {

    private final WorkOrderRepository workOrderRepository;

    @EventHandler
    public void on(WorkOrderCreatedEvent event) {
        WorkOrderEntity workOrderEntity = new WorkOrderEntity();
        workOrderEntity.setId(event.getId());
        workOrderEntity.setInstruction(event.getInstruction());
        workOrderEntity.setStatus("CREATED");
        workOrderRepository.save(workOrderEntity);
    }

    @EventHandler
    public void on(WorkOrderAssignedEvent event) {
        WorkOrderEntity workOrderEntity = workOrderRepository.findById(event.getId())
                .orElseThrow(() -> new WorkOrderNotFoundException(event.getId()));

        if (workOrderEntity != null) {
            workOrderEntity.setAssignee(event.getAssignee());
            workOrderEntity.setStatus("ASSIGNED");
            workOrderRepository.save(workOrderEntity);
        }
    }

    @EventHandler
    public void on(WorkOrderExecutedEvent event) {
        WorkOrderEntity workOrderEntity = workOrderRepository.findById(event.getId())
                .orElseThrow(() -> new WorkOrderNotFoundException(event.getId()));

        if(workOrderEntity.getAssignee() == null || workOrderEntity.getAssignee().isEmpty()) {
            throw new InvalidWorkOrderStateException("Work order must be assigned before execution", event.getId());
        }

        workOrderEntity.setStatus("EXECUTED");
        workOrderRepository.save(workOrderEntity);
    }

    @QueryHandler
    public WorkOrderQueryResponse handle(FindWorkOrderQuery query) {
        return workOrderRepository.findById(query.getId())
                .map(workOrderEntity -> new WorkOrderQueryResponse(
                        workOrderEntity.getId(),
                        workOrderEntity.getInstruction(),
                        workOrderEntity.getStatus(),
                        workOrderEntity.getAssignee()
                )).orElseThrow(() -> new RuntimeException("Could not find work order"));
    }

}
