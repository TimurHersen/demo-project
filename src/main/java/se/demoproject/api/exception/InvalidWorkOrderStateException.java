package se.demoproject.api.exception;

public class InvalidWorkOrderStateException extends WorkOrderException {
    public InvalidWorkOrderStateException(String message, String id) {
        super(message);
    }
}
