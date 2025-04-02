package se.demoproject.api.events;

public class WorkOrderCreatedEvent {
    private final String id;
    private final String instruction;

    public WorkOrderCreatedEvent(String id, String instruction) {
        this.id = id;
        this.instruction = instruction;
    }

    public String getId() {
        return id;
    }

    public String getInstruction() {
        return instruction;
    }
}
