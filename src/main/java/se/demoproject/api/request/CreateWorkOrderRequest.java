package se.demoproject.api.request;

public class CreateWorkOrderRequest {

    private String instructions;

    public CreateWorkOrderRequest() {
    }

    public CreateWorkOrderRequest(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

}
