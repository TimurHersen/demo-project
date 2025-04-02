package se.demoproject.api.events;

public class WorkOrderExecutedEvent {
    private String id;

    public WorkOrderExecutedEvent() {
    }

    public WorkOrderExecutedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
