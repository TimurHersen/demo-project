package se.demoproject.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class AssignWorkOrderCommand {

    @TargetAggregateIdentifier
    String id;
    String assignedTo;
}
