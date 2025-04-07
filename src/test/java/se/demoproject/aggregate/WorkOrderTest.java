package se.demoproject.aggregate;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.demoproject.api.events.WorkOrderAssignedEvent;
import se.demoproject.api.events.WorkOrderCreatedEvent;
import se.demoproject.api.events.WorkOrderExecutedEvent;
import se.demoproject.command.AssignWorkOrderCommand;
import se.demoproject.command.CreateWorkOrderCommand;
import se.demoproject.command.ExecuteWorkOrderCommand;

import java.util.UUID;

class WorkOrderTest {

    private FixtureConfiguration<WorkOrder> fixture;
    private String workOrderId;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(WorkOrder.class);
        workOrderId = UUID.randomUUID().toString();
    }

    @Test
    void shouldCreateWorkOrder() {
        String instruction = "Fix the broken door";

        fixture.givenNoPriorActivity()
                .when(new CreateWorkOrderCommand(workOrderId, instruction))
                .expectEvents(new WorkOrderCreatedEvent(workOrderId, instruction));
    }

    @Test
    void shouldAssignWorkOrder() {
        String instruction = "Fix the broken door";
        String assignee = "John Doe";

        fixture.given(new WorkOrderCreatedEvent(workOrderId, instruction))
                .when(new AssignWorkOrderCommand(workOrderId, assignee))
                .expectEvents(new WorkOrderAssignedEvent(workOrderId, assignee));
    }

    @Test
    void shouldExecuteAssignedWorkOrder() {
        String instruction = "Fix the broken door";
        String assignee = "John Doe";

        fixture.given(
                        new WorkOrderCreatedEvent(workOrderId, instruction),
                        new WorkOrderAssignedEvent(workOrderId, assignee)
                )
                .when(new ExecuteWorkOrderCommand(workOrderId))
                .expectEvents(new WorkOrderExecutedEvent(workOrderId));
    }

}