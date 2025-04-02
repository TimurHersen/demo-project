package se.demoproject.api.events;

public class WorkOrderAssignedEvent {

    private String id;
    private String assignee;

    public WorkOrderAssignedEvent() {
    }

    public WorkOrderAssignedEvent(String id, String assignee) {
        this.id = id;
        this.assignee = assignee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

}
