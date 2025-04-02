package se.demoproject.query.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class WorkOrderEntity {

    @Id
    String id;
    String instruction;
    String assignee;
    String status;

}
