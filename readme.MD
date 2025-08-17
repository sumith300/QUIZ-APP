# 🎯 Full-Stack Quiz Application with Microservices Architecture

A complete **Quiz Management System** featuring a modern **Next.js frontend** and **Spring Boot microservices backend** with user-specific statistics, privacy-focused design, and comprehensive quiz management capabilities.

## 🌟 Key Features

- 🔐 **JWT Authentication** with user registration/login
- 📊 **User-Specific Statistics** - personalized performance tracking
- 🎨 **Modern UI/UX** - responsive design with dark/light themes
- 🏗️ **Microservices Architecture** - scalable and maintainable
- 🔒 **Privacy Protection** - users see only their own data
- 📱 **Admin Dashboard** - comprehensive quiz and question management
- 📈 **Real-time Analytics** - live performance statistics

---

## 🧰 Technology Stack

### Frontend
- **Next.js 14** - React framework with TypeScript
- **Tailwind CSS** - Modern utility-first styling
- **React Components** - Modular and reusable UI components
- **localStorage** - Client-side session management

### Backend
- **Java 17** - Modern Java features
- **Spring Boot** - Microservices framework
- **Spring Cloud Netflix Eureka** - Service discovery
- **Spring Cloud Gateway** - API routing and load balancing
- **Spring Security + JWT** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **PostgreSQL** - Production database

### DevOps & Tools
- **Maven** - Dependency management
- **Postman** - API testing collections
- **Service Management Scripts** - Automated startup/shutdown

---

## 🏗️ System Architecture

### Frontend Application
| Component | Description | Port |
|-----------|-------------|------|
| **Next.js Frontend** | Modern React-based UI with TypeScript | `3000` |

### Microservices Backend
| Service Name | Description | Port | Key Features |
|--------------|-------------|------|--------------|
| **Service Registry** | Eureka server for service discovery | `8761` | Health monitoring, Dynamic registration |
| **API Gateway** | Central entry point and request routing | `8765` | Load balancing, Security filtering |
| **Auth Service** | JWT authentication and user management | `8081` | User registration, Login, Token validation |
| **Question Service** | Question CRUD operations and management | `8080` | Categories, Difficulty levels, Question pool |
| **Quiz Service** | Quiz creation, submission, and statistics | `8090` | **User-specific stats**, Quiz history, Results |

### Database Layer
- **PostgreSQL** - Production-ready relational database
- **User Data Isolation** - Privacy-focused data architecture
- **JPA/Hibernate** - Modern ORM with relationship mapping

---

## 🚀 Quick Start Guide

### 1. Clone the Repository
```bash
git clone https://github.com/sumith300/QUIZ-backend.git
cd QUIZ-APP
```

### 2. Database Setup
Create PostgreSQL databases with default credentials (`postgres/root`):
```sql
CREATE DATABASE authdb;
CREATE DATABASE questiondb;
CREATE DATABASE quizdb;
```

### 3. Start Backend Services (Automated)
```bash
# Option 1: Use the simple service manager
chmod +x simple-service-manager.sh
./simple-service-manager.sh

# Option 2: Use individual startup scripts
chmod +x start-services.sh
./start-services.sh

# Option 3: Manual startup (in order)
cd service-registry && ./mvnw spring-boot:run
cd ../auth-service && ./mvnw spring-boot:run
cd ../question-service && ./mvnw spring-boot:run
cd ../quiz-service && ./mvnw spring-boot:run
cd ../api-gateway && ./mvnw spring-boot:run
```

### 4. Start Frontend Application
```bash
cd quiz-frontend
npm install
npm run dev
```

### 5. Access the Application
- **Frontend Dashboard**: http://localhost:3000
- **Eureka Registry**: http://localhost:8761
- **API Gateway**: http://localhost:8765

### 6. Default Test Users
- **Admin**: `admin/admin123`
- **User**: `user1/admin123`
- **Demo User**: Any username (for testing)

---

## 🎯 Application Features

### 🎨 Frontend Features
- **Dashboard**: Personalized user dashboard with statistics
- **Quiz Interface**: Modern, intuitive quiz-taking experience
- **Admin Panel**: Complete quiz and question management
- **User Authentication**: Secure login/registration with form validation
- **Responsive Design**: Mobile-friendly interface with dark/light themes
- **Real-time Updates**: Live statistics and performance tracking

### 🔒 Privacy & Security Features
- **User-Specific Data**: Each user sees only their own statistics and quiz history
- **JWT Authentication**: Secure token-based authentication system
- **Data Isolation**: Database queries filtered by user ID for privacy protection
- **Secure API**: All endpoints protected with proper authorization

### 📊 Statistics & Analytics
- **Personal Performance**: Individual quiz scores, averages, and best scores
- **Quiz History**: Complete history of attempted quizzes with timestamps
- **Progress Tracking**: Visual progress indicators and performance levels
- **Real-time Updates**: Statistics update immediately after quiz completion

---

## 🔌 API Endpoints

### 🔐 Authentication (Port 8081)
```bash
POST /auth-service/auth/register    # User registration
POST /auth-service/auth/login       # User login  
POST /auth-service/auth/validate    # Token validation
GET  /auth-service/auth/health      # Health check
```

### 📝 Questions (Port 8080)
```bash
GET    /question-service/question/allQuestions       # Get all questions
POST   /question-service/question/add                # Add new question
GET    /question-service/question/{id}               # Get question by ID
PUT    /question-service/question/update/{id}        # Update question
DELETE /question-service/question/delete/{id}        # Delete question
GET    /question-service/question/category/{category} # Get by category
```

### 🎯 Quizzes (Port 8090) - User-Specific Endpoints
```bash
GET  /quiz-service/quiz/get/{id}                     # Get quiz by ID
POST /quiz-service/quiz/create                       # Create new quiz
POST /quiz-service/quiz/submit/{id}/user/{userId}    # Submit quiz (USER-SPECIFIC)
GET  /quiz-service/quiz/stats/{userId}               # Get user statistics (USER-SPECIFIC)  
GET  /quiz-service/quiz/history/{userId}             # Get user quiz history (USER-SPECIFIC)
GET  /quiz-service/quiz/allQuizzes                   # Get available quizzes
```

### 🌐 API Gateway (Port 8765)
All requests are routed through the API Gateway with load balancing and security filtering.

---

## 🏥 Health Monitoring

### Service Health Endpoints
```bash
http://localhost:8761/actuator/health  # Service Registry
http://localhost:8765/actuator/health  # API Gateway
http://localhost:8081/actuator/health  # Auth Service
http://localhost:8080/actuator/health  # Question Service  
http://localhost:8090/actuator/health  # Quiz Service
http://localhost:3000/api/health       # Frontend Health
```

### Eureka Dashboard
Monitor all registered services at: http://localhost:8761

---

## 📂 Project Structure

```
QUIZ-APP/
├── 🎨 quiz-frontend/              # Next.js Frontend Application
│   ├── app/                       # Next.js 14 App Router
│   │   ├── dashboard/            # User dashboard with personalized stats
│   │   ├── quizzes/              # Quiz-taking interface
│   │   ├── login/                # Authentication pages
│   │   └── admin/                # Admin panel for management
│   ├── components/               # Reusable React components
│   │   ├── layout/               # Layout components
│   │   └── ui/                   # UI components (Cards, Buttons, etc.)
│   ├── services/                 # API service layer
│   ├── types/                    # TypeScript type definitions
│   └── styles/                   # Tailwind CSS styles
│
├── 🏗️ Backend Microservices/
│   ├── service-registry/         # Eureka Server (8761)
│   ├── api-gateway/              # Spring Cloud Gateway (8765)
│   ├── auth-service/             # Authentication & JWT (8081)
│   ├── question-service/         # Question Management (8080)
│   └── quiz-service/             # Quiz & User Statistics (8090)
│
├── 📊 postman/                   # API Testing Collections
├── 📝 logs/                      # Application logs
├── 🔧 *.sh                      # Service management scripts
└── 📚 Documentation files
```

---

## 🔄 Data Flow Architecture

### 1. User Authentication Flow
```
Frontend → API Gateway → Auth Service → PostgreSQL
    ↓          ↓            ↓            ↓
1. Login   2. Route to   3. Validate   4. Check User DB
   Form       Auth API     Credentials    & Generate JWT
```

### 2. Quiz Taking Flow
```
Frontend → API Gateway → Quiz Service → Question Service → Database  
    ↓          ↓            ↓              ↓                ↓
1. Take    2. Route to   3. Get Quiz    4. Fetch         5. Return
   Quiz       Quiz API     Questions      Questions        Quiz Data
```

### 3. User-Specific Statistics Flow
```
Frontend → API Gateway → Quiz Service → Database
    ↓          ↓            ↓            ↓
1. Load    2. Route to   3. Query       4. Filter by userId
   Dashboard  Stats API    User Stats     (Privacy Protection)
```

---

## 🎯 Key Implementation Highlights

### 🔒 Privacy-First Design
- **User Data Isolation**: All statistics and quiz history are filtered by `userId`
- **No Cross-User Access**: Users cannot see other users' performance data
- **Secure Data Flow**: JWT tokens ensure user identity throughout the request chain

### 🏗️ Microservices Benefits
- **Independent Scaling**: Each service can scale based on demand
- **Technology Diversity**: Services can use different technologies as needed  
- **Fault Isolation**: Failure in one service doesn't affect others
- **Easy Maintenance**: Clear separation of concerns and responsibilities

### 🎨 Modern Frontend Architecture
- **Component-Based**: Reusable React components with TypeScript
- **Responsive Design**: Mobile-first approach with Tailwind CSS
- **Real-time Updates**: Immediate reflection of user actions and data changes
- **User Experience**: Intuitive navigation and modern UI patterns

---

## 🚀 Deployment & Production

### Environment Configuration
```bash
# Development
NODE_ENV=development
DATABASE_URL=localhost:5432

# Production  
NODE_ENV=production
DATABASE_URL=your-production-db-url
JWT_SECRET=your-production-secret
```

### Docker Deployment (Future Enhancement)
```yaml
# docker-compose.yml structure for containerization
services:
  - Frontend (Next.js)
  - API Gateway  
  - Auth Service
  - Question Service
  - Quiz Service
  - PostgreSQL Database
  - Service Registry
```

---

## 🧪 Testing

### API Testing with Postman
- Import collections from `postman/` directory
- **Auth.postman_collection.json** - Authentication endpoints
- **Quiz.postman_collection.json** - Quiz and question endpoints

### Frontend Testing
```bash
cd quiz-frontend
npm run test          # Run unit tests
npm run test:e2e      # Run end-to-end tests
npm run lint          # Code linting
npm run type-check    # TypeScript validation
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors

- **Sumith Meena** - *Initial work* - [sumith300](https://github.com/sumith300)

---

## 🙏 Acknowledgments

- Spring Boot community for excellent microservices framework
- Next.js team for the modern React framework
- Tailwind CSS for the utility-first styling approach
- Eureka for service discovery capabilities

---

## 📞 Support

For support, email sumithmeena300@gmail.com or create an issue in this repository.

---

**⭐ Star this repository if you find it helpful!**