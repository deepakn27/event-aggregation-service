# Event Aggregation Service

A robust event processing and aggregation service built with Spring Boot, designed to handle and aggregate various types of events (Ad events, Cart events, Click-to-Basket events) in real-time.

## 🏗 Architecture

### Core Components

1. **Event Consumers**
   - Kafka-based event consumers for different event types
   - Supports Ad events, Cart events, and Click-to-Basket events
   - Implements Spring Cloud Stream for event processing

2. **Event Processors**
   - Type-specific processors for each event type
   - Implements the Chain of Responsibility pattern for event handling
   - Uses MapStruct for efficient object mapping

3. **Data Storage**
   - MongoDB for persistent storage of events and aggregations
   - Redis for real-time counters and caching
   - Implements repository pattern for data access

4. **Event Handlers**
   - Chain of Responsibility pattern for event processing
   - Separate handlers for different event types (Ad Clicked, Ad Viewed)
   - Extensible design for adding new event types

### Technology Stack

- **Framework**: Spring Boot
- **Event Processing**: Spring Cloud Stream, Kafka
- **Data Storage**: MongoDB, Redis
- **Object Mapping**: MapStruct
- **Monitoring**: Spring Boot Actuator, Micrometer
- **Build Tool**: Gradle

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Gradle 7.x or higher
- MongoDB 4.x or higher
- Redis 6.x or higher
- Kafka 2.x or higher

### Building the Project

```bash
./gradlew clean build
```

### Running the Service

```bash
./gradlew bootRun
```

## 📊 Event Flow

1. **Ad Event Processing**
   - Events are consumed from Kafka topics
   - Processed through the event processor chain
   - Aggregated in MongoDB and cached in Redis
   - Real-time counters updated for views and clicks

2. **Cart Event Processing**
   - Cart events are processed and stored
   - Used for conversion tracking and analytics

3. **Click-to-Basket Event Processing**
   - Tracks user journey from ad click to cart
   - Updates conversion metrics

## 🔍 Monitoring

The service includes comprehensive monitoring through:

- Spring Boot Actuator endpoints
- Prometheus metrics
- Health checks for MongoDB and Redis
- Detailed logging with SLF4J

### Available Endpoints

- `/actuator/health`: Service health status
- `/actuator/metrics`: Service metrics
- `/actuator/prometheus`: Prometheus metrics


### Adding New Event Types

1. Create new event model in the `model` module
2. Implement event processor
3. Add event handler if needed
4. Configure consumer in `EventConsumerConfig`

## 🧪 Testing

Run tests using:

```bash
./gradlew test
```


## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

- Spring Boot team
- Apache Kafka
- MongoDB
- Redis 