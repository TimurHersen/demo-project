package se.demoproject.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.demoproject.api.events.WorkOrderAssignedEvent;
import se.demoproject.api.events.WorkOrderCreatedEvent;
import se.demoproject.api.events.WorkOrderExecutedEvent;
import se.demoproject.api.exception.WorkOrderNotFoundException;
import se.demoproject.api.queries.FindWorkOrderQuery;
import se.demoproject.api.queries.WorkOrderQueryResponse;
import se.demoproject.query.entity.WorkOrderEntity;
import se.demoproject.query.handler.WorkOrderQueryHandler;
import se.demoproject.query.repository.WorkOrderRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkOrderQueryHandlerTest {

    @Mock
    private WorkOrderRepository workOrderRepository;

    @InjectMocks
    private WorkOrderQueryHandler queryHandler;

    private String workOrderId;
    private WorkOrderEntity workOrderEntity;

    @BeforeEach
    void setUp() {
        workOrderId = UUID.randomUUID().toString();
        workOrderEntity = new WorkOrderEntity();
        workOrderEntity.setId(workOrderId);
        workOrderEntity.setInstruction("Fix the broken door");
        workOrderEntity.setStatus("CREATED");
    }

    @Test
    void shouldHandleWorkOrderCreatedEvent() {
        // Arrange
        WorkOrderCreatedEvent event = new WorkOrderCreatedEvent(workOrderId, "Fix the broken door");

        // Act
        queryHandler.on(event);

        // Assert
        verify(workOrderRepository).save(any(WorkOrderEntity.class));
    }

    @Test
    void shouldHandleWorkOrderAssignedEvent() {
        // Arrange
        WorkOrderAssignedEvent event = new WorkOrderAssignedEvent(workOrderId, "John Doe");
        when(workOrderRepository.findById(workOrderId)).thenReturn(Optional.of(workOrderEntity));

        // Act
        queryHandler.on(event);

        // Assert
        verify(workOrderRepository).findById(workOrderId);
        verify(workOrderRepository).save(any(WorkOrderEntity.class));
        assertEquals("John Doe", workOrderEntity.getAssignee());
        assertEquals("ASSIGNED", workOrderEntity.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenAssigningNonExistentWorkOrder() {
        // Arrange
        WorkOrderAssignedEvent event = new WorkOrderAssignedEvent(workOrderId, "John Doe");
        when(workOrderRepository.findById(workOrderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(WorkOrderNotFoundException.class, () -> queryHandler.on(event));
        verify(workOrderRepository).findById(workOrderId);
        verify(workOrderRepository, never()).save(any(WorkOrderEntity.class));
    }

    @Test
    void shouldHandleWorkOrderExecutedEvent() {
        // Arrange
        WorkOrderExecutedEvent event = new WorkOrderExecutedEvent(workOrderId);
        workOrderEntity.setAssignee("John Doe");
        workOrderEntity.setStatus("ASSIGNED");
        when(workOrderRepository.findById(workOrderId)).thenReturn(Optional.of(workOrderEntity));

        // Act
        queryHandler.on(event);

        // Assert
        verify(workOrderRepository).findById(workOrderId);
        verify(workOrderRepository).save(workOrderEntity);
        assertEquals("EXECUTED", workOrderEntity.getStatus());
    }

    @Test
    void shouldHandleFindWorkOrderQuery() {
        // Arrange
        FindWorkOrderQuery query = new FindWorkOrderQuery(workOrderId);
        workOrderEntity.setAssignee("John Doe");
        workOrderEntity.setStatus("ASSIGNED");
        when(workOrderRepository.findById(workOrderId)).thenReturn(Optional.of(workOrderEntity));

        // Act
        WorkOrderQueryResponse response = queryHandler.handle(query);

        // Assert
        assertNotNull(response);
        assertEquals(workOrderId, response.getId());
        assertEquals("Fix the broken door", response.getInstruction());
        assertEquals("John Doe", response.getAssignee());
        assertEquals("ASSIGNED", response.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenFindingNonExistentWorkOrder() {
        // Arrange
        FindWorkOrderQuery query = new FindWorkOrderQuery(workOrderId);
        when(workOrderRepository.findById(workOrderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(WorkOrderNotFoundException.class, () -> queryHandler.handle(query));
    }
}