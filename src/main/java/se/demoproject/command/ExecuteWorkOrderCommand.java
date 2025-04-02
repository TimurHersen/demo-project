package se.demoproject.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ExecuteWorkOrderCommand {

    @TargetAggregateIdentifier
    String id;
    String executionNotes;
}
