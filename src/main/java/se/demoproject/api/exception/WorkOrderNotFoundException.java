package se.demoproject.api.exception;

public class WorkOrderNotFoundException extends WorkOrderException {

    public WorkOrderNotFoundException(String id) {
        super("Work order not found with id: " + id);
    }

}
