package se.demoproject.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class CreateWorkOrderCommand {

    @TargetAggregateIdentifier
    private String id;
    private String instruction;

    public CreateWorkOrderCommand() {
    }

    public CreateWorkOrderCommand(String id, String instruction) {
        this.id = id;
        this.instruction = instruction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

}
