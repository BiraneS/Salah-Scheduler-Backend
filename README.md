# Salah-Scheduler-Backend
Islamic Prayer & Schedule Management Backend API - Spring Boot REST API helping Muslims organize daily activities around prayer times with real-time calculations and JWT authentication

## Features
- Real-time prayer time calculations based on user location
- Prayer completion tracking with history and statistics
- Daily activity scheduling around prayer times
- Qada (makeup prayer) management for missed prayers
- User profile management with Islamic preferences
- JWT authentication for secure user sessions

## API Endpoints

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login with JWT token
- `GET /api/auth/profile/{userId}` - Get user profile
- `PUT /api/auth/profile/{userId}` - Update user profile

### Prayer Management
- `GET /api/prayers/daily` - Get today's prayer times
- `GET /api/prayers/date/{date}` - Get prayer times for specific date
- `POST /api/prayers/log/{id}` - Log completed or missed prayer
- `GET /api/prayers/history/{userId}` - Get prayer completion history
- `GET /api/prayers/type/{prayerType}` - Get prayers by type
- `GET /api/prayers/qada/{userId}/{prayerStatus}` - Get makeup prayers

### Activity Management
- `POST /api/activities/{id}` - Create new activity
- `GET /api/activities/user/{userId}` - Get user's activities
- `PUT /api/activities/{id}` - Update activity
- `DELETE /api/activities/{id}` - Delete activity

### Schedule Management
- `POST /api/schedule/{id}` - Create new schedule
- `GET /api/schedule/daily/{date}` - Get daily schedule
- `PUT /api/schedule/{id}` - Update schedule
- `DELETE /api/schedule/{id}` - Delete schedule

## Setup Instructions

### Prerequisites
- Java 17 or higher
- MySQL 8.0
- Maven
- Git

### Installation
1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/salah-scheduler-backend.git
   cd salah-scheduler-backend
