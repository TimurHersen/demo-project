package se.demoproject.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkOrderCommand {

    @TargetAggregateIdentifier
    String id = UUID.randomUUID().toString();
    String instruction;

}
