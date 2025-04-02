package se.demoproject.api.events;

public class WorkOrderExecutedEvent {
    private String id;
    private String executionNotes;

    public WorkOrderExecutedEvent() {
    }

    public WorkOrderExecutedEvent(String id, String executionNotes) {
        this.id = id;
        this.executionNotes = executionNotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExecutionNotes() {
        return executionNotes;
    }

    public void setExecutionNotes(String executionNotes) {
        this.executionNotes = executionNotes;
    }
}
