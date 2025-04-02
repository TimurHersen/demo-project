package se.demoproject.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import se.demoproject.api.queries.FindWorkOrderQuery;
import se.demoproject.api.queries.WorkOrderQueryResponse;
import se.demoproject.api.request.AssignWorkOrderRequest;
import se.demoproject.api.request.CreateWorkOrderRequest;
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

    @Operation(summary = "Create a new work order", description = "Creates a work order with the specified instruction")
    @PostMapping()
    public CompletableFuture<String> createWorkOrder(@RequestBody CreateWorkOrderRequest request) {
        return commandGateway.send(new CreateWorkOrderCommand(
                UUID.randomUUID().toString(),
                request.getInstructions()));
    }

    @Operation(summary = "Assign a work order", description = "Assigns a work order to a specific person")
    @PutMapping({"{id}/assign"})
    public CompletableFuture<Void> assignWorkOrder(@PathVariable String id, @RequestBody AssignWorkOrderRequest request) {
        return commandGateway.send(new AssignWorkOrderCommand(id, request.getAssignee()));
    }

    @Operation(summary = "Execute a work order", description = "Marks a work order as executed")
    @PutMapping("{id}/execute")
    public CompletableFuture<Void> executeWorkOrder(@PathVariable String id) {
        return commandGateway.send(new ExecuteWorkOrderCommand(
                id
        ));
    }

    @Operation(summary = "Get work order details", description = "Retrieves the details of a specific work order")
    @GetMapping("{id}/retrieve")
    public CompletableFuture<WorkOrderQueryResponse> getWorkOrder(@PathVariable String id) {
        return queryGateway.query(
                new FindWorkOrderQuery(id),
                ResponseTypes.instanceOf(WorkOrderQueryResponse.class)
        );
    }

}
