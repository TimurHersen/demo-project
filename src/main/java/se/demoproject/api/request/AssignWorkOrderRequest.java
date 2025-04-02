package se.demoproject.api.request;

public class AssignWorkOrderRequest {

    private String assignee;

    public AssignWorkOrderRequest() {
    }

    public AssignWorkOrderRequest(String assignee) {
        this.assignee = assignee;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

}
