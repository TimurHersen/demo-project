package se.demoproject.api.request;

public class CreateWorkOrderRequest {

    private String instruction;

    public CreateWorkOrderRequest() {
    }

    public CreateWorkOrderRequest(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}