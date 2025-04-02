package se.demoproject.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ExecuteWorkOrderCommand {

    @TargetAggregateIdentifier
    private String id;

    protected ExecuteWorkOrderCommand() {
    }

    public ExecuteWorkOrderCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
