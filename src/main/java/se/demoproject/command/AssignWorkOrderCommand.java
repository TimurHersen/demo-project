package se.demoproject.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AssignWorkOrderCommand {

    @TargetAggregateIdentifier
    private String id;
    private String assignedTo;

    // No-argument constructor
    public AssignWorkOrderCommand() {
    }

    // All-argument constructor
    public AssignWorkOrderCommand(String id, String assignedTo) {
        this.id = id;
        this.assignedTo = assignedTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
