package se.demoproject.api.queries;

import lombok.Value;

@Value
public class WorkOrderQueryResponse {

    String id;
    String instruction;
    String assignee;
    String executionNotes;
    String status;

}
