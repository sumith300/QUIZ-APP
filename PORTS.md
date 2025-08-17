# 🔌 Port Configuration

This document outlines the port assignments for all microservices in the Quiz Application.

## 📋 Port Assignments

| Service | Port | Description | Health Check |
|---------|------|-------------|--------------|
| **Service Registry** | `8761` | Eureka Discovery Server | `http://localhost:8761/actuator/health` |
| **API Gateway** | `8765` | Spring Cloud Gateway | `http://localhost:8765/actuator/health` |
| **Auth Service** | `8081` | JWT Authentication | `http://localhost:8081/actuator/health` |
| **Question Service** | `8080` | Question Management | `http://localhost:8080/actuator/health` |
| **Quiz Service** | `8090` | Quiz Management | `http://localhost:8090/actuator/health` |

## 🌐 Service URLs

### Direct Access (Development)
- **Service Registry**: `http://localhost:8761`
- **API Gateway**: `http://localhost:8765`
- **Auth Service**: `http://localhost:8081`
- **Question Service**: `http://localhost:8080`
- **Quiz Service**: `http://localhost:8090`

### Through API Gateway (Production)
- **Auth Service**: `http://localhost:8765/auth-service/`
- **Question Service**: `http://localhost:8765/question-service/`
- **Quiz Service**: `http://localhost:8765/quiz-service/`

## 🔍 Health Check Endpoints

All services expose health check endpoints via Spring Boot Actuator:

```bash
# Service Registry
curl http://localhost:8761/actuator/health

# API Gateway
curl http://localhost:8765/actuator/health

# Auth Service
curl http://localhost:8081/actuator/health

# Question Service
curl http://localhost:8080/actuator/health

# Quiz Service
curl http://localhost:8090/actuator/health
```

## 📊 Eureka Dashboard

Access the Eureka dashboard to see all registered services:
- **URL**: `http://localhost:8761`
- **Shows**: All registered microservices and their status

## 🔧 Port Configuration Files

### Service Registry
```properties
server.port=8761
```

### API Gateway
```properties
server.port=8765
```

### Auth Service
```properties
server.port=8081
```

### Question Service
```properties
server.port=8080
```

### Quiz Service
```properties
server.port=8090
```

## 🚨 Port Conflicts

If you encounter port conflicts:

1. **Check if ports are in use**:
   ```bash
   lsof -i :8080
   lsof -i :8081
   lsof -i :8090
   lsof -i :8761
   lsof -i :8765
   ```

2. **Kill processes using the ports**:
   ```bash
   kill -9 <PID>
   ```

3. **Alternative port assignments** (if needed):
   - Auth Service: `8082`
   - Question Service: `8083`
   - Quiz Service: `8091`

## 🔄 Service Discovery

All services register with Eureka at:
```
http://localhost:8761/eureka/
```

The API Gateway routes requests to services using service names:
- `auth-service`
- `question-service`
- `quiz-service`

## 📝 Notes

- **Port 8080**: Standard Spring Boot default port
- **Port 8761**: Standard Eureka server port
- **Port 8765**: API Gateway port (close to Eureka)
- **Port 8081**: Auth service (next to default)
- **Port 8090**: Quiz service (higher range)

All ports are explicitly configured to avoid conflicts and ensure consistent deployment. 