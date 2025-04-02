package se.demoproject.api.events;

import lombok.Value;

@Value
public class WorkOrderCreatedEvent {
    String orderId;
    String instruction;
}
