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
- Docker

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 17** or higher
- **Docker Desktop** for running containerized services
- **Postman** (optional) for testing the API

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/workorder-management.git
cd workorder-management
```

### 2. Start the Required Services

The application requires Axon Server and MySQL to be running. A Docker Compose file is provided for easy setup.

```bash
navigate to docker folder (cd docker)
docker-compose up -d
```

This will start:
- **Axon Server** on port 8024 (HTTP) and 8124 (gRPC)
- **MySQL** on port 3306

You can verify that Axon Server is running by visiting http://localhost:8024 in your browser.

### 3. Build and Run the Application

```bash
mvn clean install
Run the application
```

The application will start and connect to both Axon Server and MySQL. It will be accessible at http://localhost:8080.

## API Endpoints

The application exposes the following REST endpoints:

| HTTP Method | Endpoint | Description |
|-------------|----------|-------------|
| POST | `/workorders` | Create a new work order |
| PUT | `/workorders/{id}/assign` | Assign a work order to a person |
| PUT | `/workorders/{id}/execute` | Mark a work order as executed |
| GET | `/workorders/{id}` | Retrieve a work order by ID |


## Postman Collection

A Postman collection is included in the repository for easy testing of the API. To use it:

1. Import the `postman-collection.json` file into Postman
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
Identified issues why spring boot application was not connecting to axon server: problem seems be running spring boot version above 3.1.5. When downgraded from 3.4.4 -> 3.1.5 the connection was successfull.

Identified issues with Lombok annotation procession together with AggregateIdentifier, removed lombok third party dependency to set up classes with code instead.