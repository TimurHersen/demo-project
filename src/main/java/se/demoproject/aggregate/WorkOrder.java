package se.demoproject.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import se.demoproject.api.events.WorkOrderAssignedEvent;
import se.demoproject.api.events.WorkOrderCreatedEvent;
import se.demoproject.api.events.WorkOrderExecutedEvent;
import se.demoproject.command.AssignWorkOrderCommand;
import se.demoproject.command.CreateWorkOrderCommand;
import se.demoproject.command.ExecuteWorkOrderCommand;

@Aggregate
public class WorkOrder {

    @AggregateIdentifier
    private String orderId;

    // No-argument constructor
    protected WorkOrder() {
    }

    @CommandHandler
    public WorkOrder(CreateWorkOrderCommand command) {
        AggregateLifecycle.apply(new WorkOrderCreatedEvent(
                command.getId(),
                command.getInstruction()));
    }

    @CommandHandler
    public void handle(AssignWorkOrderCommand command) {
        AggregateLifecycle.apply(new WorkOrderAssignedEvent(
                command.getId(),
                command.getAssignedTo()));
    }

    @CommandHandler
    public void handle(ExecuteWorkOrderCommand command) {
        AggregateLifecycle.apply(new WorkOrderExecutedEvent(
                command.getId(),
                command.getExecutionNotes()));
    }

    @EventSourcingHandler
    public void on(WorkOrderCreatedEvent event) {
        this.orderId = event.getOrderId();
    }

}
