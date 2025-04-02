package se.demoproject.api.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderExecutedEvent {
    private String id;
    private String executionNotes;
}
