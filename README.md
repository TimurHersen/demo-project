# WorkOrder Management System

A simple work order management system built with Spring Boot and Axon Framework, implementing CQRS (Command Query Responsibility Segregation) and Event Sourcing patterns.

## Overview

This application demonstrates how to build an event-sourced system using Axon Framework with the following capabilities:
- Creating, assigning, and executing work orders
- Event sourcing to track all changes to work orders
- CQRS pattern separating the command and query responsibilities
- REST API for interacting with the system

## Technology Stack

- Java 17
- Spring Boot
- Axon Framework
- MySQL (for the query database)
- Axon Server (for event sourcing)
- Docker & Docker Compose

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 17** or higher
- **Maven** for building the project
- **Docker** for running containerized services
- **Postman** (optional) for testing the API

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/workorder-management.git
cd workorder-management
```

### 2. Start the Required Services

The application requires Axon Server and MySQL to be running. A Docker Compose file is provided for easy setup.

Navigate to the `docker` directory and run:
```bash
docker-compose up -d
```

This will start:
- **Axon Server** on port 8024 (HTTP) and 8124 (gRPC)
- **MySQL** on port 3306

You can verify that Axon Server is running by visiting http://localhost:8024 in your browser.

### 3. Build and Run the Application

```bash
mvn clean install
```
Run the application in your IDE

The application will start and connect to both Axon Server and MySQL. It will be accessible at http://localhost:8080.

## API Endpoints

The application exposes the following REST endpoints:

| HTTP Method | Endpoint | Description |
|-------------|----------|-------------|
| POST | `/workorders` | Create a new work order |
| PUT | `/workorders/{id}/assign` | Assign a work order to a person |
| PUT | `/workorders/{id}/execute` | Mark a work order as executed |
| GET | `/workorders/{id}` | Retrieve a work order by ID |

### API Examples

#### Create a Work Order
```bash
curl -X POST http://localhost:8080/workorders \
  -H "Content-Type: application/json" \
  -d '{"instruction": "Replace the broken door handle in room 101"}'
```

#### Assign a Work Order
```bash
curl -X PUT http://localhost:8080/workorders/{id}/assign \
  -H "Content-Type: application/json" \
  -d '{"assignee": "John Smith"}'
```

#### Execute a Work Order
```bash
curl -X PUT http://localhost:8080/workorders/{id}/execute \
  -H "Content-Type: application/json" \
  -d '{}'
```

#### Get a Work Order
```bash
curl -X GET http://localhost:8080/workorders/{id}
```
## API Documentation with Swagger

The application includes Swagger UI for interactive API documentation and testing.

### Accessing Swagger UI

Once the application is running, you can access the Swagger UI at:
- http://localhost:8080/swagger-ui.html

This provides:
- Interactive documentation for all endpoints
- The ability to test API endpoints directly from the browser
- Request and response schemas
- Detailed descriptions of parameters and responses

### OpenAPI Specification

The raw OpenAPI specification is available at:
- http://localhost:8080/api-docs

This can be used with various tools to generate client code or imported into API testing tools.

## Postman Collection

A Postman collection is included in the repository for easy testing of the API. To use it:

1. Import the `WorkOrder_API.postman_collection.json` file into Postman
2. Execute the requests in sequence (Create → Assign → Execute → Get)
3. The collection automatically stores the work order ID from the creation response

## Architecture

The application follows the CQRS and Event Sourcing patterns:

### Command Side
- `WorkOrder` aggregate handles commands and emits events
- Commands: CreateWorkOrderCommand, AssignWorkOrderCommand, ExecuteWorkOrderCommand
- Events: WorkOrderCreatedEvent, WorkOrderAssignedEvent, WorkOrderExecutedEvent

### Query Side
- Event handlers update the read model in MySQL
- Query handlers respond to queries using the read model
- Queries: FindWorkOrderQuery

### Event Sourcing
- All events are stored in Axon Server
- The aggregate state can be reconstructed from the event stream

## Troubleshooting

### Connection Issues with Axon Server
If you encounter connection issues with Axon Server, verify that:
- The Docker container is running: `docker ps | grep axon-server`
- The ports are properly exposed: `netstat -an | grep 8124`

### Database Connection Issues
For MySQL connection problems:
- Check if the container is running: `docker ps | grep mysql`
- Verify the connection details in application.properties
- Try connecting directly: `mysql -h localhost -P 3306 -u axonuser -p`
