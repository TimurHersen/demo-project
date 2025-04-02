package se.demoproject.api.controller;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import se.demoproject.api.queries.FindWorkOrderQuery;
import se.demoproject.api.queries.WorkOrderQueryResponse;
import se.demoproject.command.AssignWorkOrderCommand;
import se.demoproject.command.CreateWorkOrderCommand;
import se.demoproject.command.ExecuteWorkOrderCommand;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workorders")
public class WorkOrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping()
    public CompletableFuture<String> createWorkOrder(@RequestBody CreateWorkOrderCommand command) {
        return commandGateway.send(new CreateWorkOrderCommand(
                UUID.randomUUID().toString(),
                command.getInstruction()));
    }

    @PutMapping({"{id}/assign"})
    public CompletableFuture<Void> assignWorkOrder(@PathVariable String id, @RequestBody AssignWorkOrderCommand command) {
        commandGateway.send(new AssignWorkOrderCommand(id, command.getAssignedTo()));
        return null;
    }

    @PutMapping("{id}/execute")
    public CompletableFuture<Void> executeWorkOrder(@PathVariable String id, @RequestBody ExecuteWorkOrderCommand command) {
        return commandGateway.send(new ExecuteWorkOrderCommand(
                id,
                command.getExecutionNotes()
        ));
    }

    @GetMapping("{id}/retrieve")
    public CompletableFuture<WorkOrderQueryResponse> getWorkOrder(@PathVariable String id) {
        return queryGateway.query(
                new FindWorkOrderQuery(id),
                ResponseTypes.instanceOf(WorkOrderQueryResponse.class)
        );
    }

}
