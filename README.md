# admin-backend

A management system backend project based on Vue3+SpringBoot3, providing complete backend management functionality APIs.

## 🌟 Project Overview

This is a fully-featured enterprise-level management system backend, built with modern technology stack, providing core functionalities including user management, department management, employee management, article management, and data statistics.

**Frontend Project**: [admin-vue](https://github.com/zichuanxu/admin-vue)

## 🛠️ Technology Stack

- **Backend Framework**: SpringBoot 3
- **Database**: MySQL
- **ORM Framework**: MyBatis-Plus
- **Authentication**: JWT (JSON Web Token)
- **Build Tool**: Maven

## 🏗️ Project Structure

```
src/main/java/org/xu/admin/
├── annotation/          # Custom annotations (permission control)
├── common/             # Common classes and utilities
├── config/             # Configuration classes
├── controller/         # Controller layer
├── dto/                # Data Transfer Objects
├── entity/             # Entity classes
├── interceptor/        # Interceptors
├── mapper/             # Data access layer
├── service/            # Business logic layer
└── utils/              # Utility classes
```

## 🚀 Quick Start

### Requirements

- JDK 17+
- MySQL 8.0+
- Maven 3.6+

### Installation Steps

1. **Clone the project**

```bash
git clone https://github.com/zichuanxu/admin-backend.git
cd admin-backend
```

1. **Configure database**
   - Initialize MySQL database using `init.sql`
   - Modify database configuration in `application.yaml`

2. **Run the project using IDEA**

## 📋 System Features

### 🔐 User Authentication & Authorization

- **User Registration**: Support new user registration
- **User Login**: JWT token authentication
- **Permission Control**: Annotation-based permission management, distinguishing between regular users and admin privileges
- **Password Change**: Users can modify login passwords
- **User Logout**: Secure system logout

#### Interface Screenshots

**Login Page**
![Login Page](assets/login.png)

**Registration Page**
![Registration Page](assets/register.png)

**Change Password**
![Change Password](assets/password.png)

### 📊 Data Statistics Dashboard

- **Core Data Statistics**: Total users, total employees, total articles, total departments
- **Department Personnel Distribution**: Visual display of personnel distribution across departments
- **Real-time Data Updates**: Support for real-time data refresh

#### Interface Screenshots

**Statistics Dashboard**
![Statistics Dashboard](assets/statistics.png)

**Admin Backend Homepage**
![Admin Backend Homepage](assets/homepage.png)

### 👥 User Management

- **User List**: Paginated query of all user information
- **User Edit**: Modify user basic information
- **User Delete**: Support single and batch deletion
- **User Details**: View detailed user information
- **Personal Center**: Users can view and edit personal information

#### Interface Screenshots

**User Management**
![User Management](assets/user.png)

**User Edit**
![User Edit](assets/user-edit.png)

**Personal Center**
![Personal Center](assets/info.png)

**Information Edit**
![Information Edit](assets/info-edit.png)

### 🏢 Department Management

- **Department List**: View all department information
- **Department Add**: Create new departments
- **Department Edit**: Modify department information
- **Department Delete**: Support single and batch deletion
- **Department Details**: View detailed department information

#### Interface Screenshots

**Department Management**
![Department Management](assets/department.png)

**Add Department**
![Add Department](assets/department-new.png)

### 👨‍💼 Employee Management

- **Employee List**: Paginated query of employee information, including department associations
- **Employee Add**: Add new employees
- **Employee Edit**: Modify employee information
- **Employee Delete**: Support single and batch deletion
- **Data Import**: Support Excel batch import of employee information
- **Data Export**: Support exporting employee data to Excel files

#### Interface Screenshots

**Employee Management**
![Employee Management](assets/employee.png)

**Employee Edit**
![Employee Edit](assets/employee-edit.png)

### 📝 Article Management

- **Article List**: Paginated query of article content
- **Article Publish**: Create and publish new articles
- **Article Edit**: Modify article content
- **Article Delete**: Support single and batch deletion
- **Article Preview**: View article details

#### Interface Screenshots

**Article Management**
![Article Management](assets/article.png)

**New Article**
![New Article](assets/article-new.png)

**Article Preview**
![Article Preview](assets/article-preview.png)

### 🎯 Administrator Features

- **System Overview**: View system running status and data statistics
- **User Permission Management**: Manage user permissions and roles
- **Data Maintenance**: Batch data operations and maintenance

#### Interface Screenshots

**Administrator Interface**
![Administrator Interface](assets/admin.png)

## 📡 API Endpoints

### Authentication APIs

- `POST /user/login` - User login
- `POST /user/register` - User registration
- `POST /user/logout` - User logout
- `POST /user/password` - Change password

### User Management

- `GET /user/all` - Paginated user query
- `GET /user/{id}` - Get user details
- `PUT /user` - Update user
- `DELETE /user/{id}` - Delete user

### Department Management

- `GET /department/all` - Get all departments
- `GET /department/page` - Paginated department query
- `POST /department` - Add department
- `PUT /department` - Update department
- `DELETE /department/{id}` - Delete department

### Employee Management

- `GET /employee/page` - Paginated employee query
- `POST /employee/add` - Add employee
- `PUT /employee/update` - Update employee
- `POST /employee/import` - Import employee data
- `POST /employee/export` - Export employee data

### Article Management

- `GET /article/page` - Paginated article query
- `POST /article/add` - Add article
- `PUT /article/update` - Update article
- `DELETE /article/{id}` - Delete article

### Statistics Data

- `GET /statistics/dashboard` - Get dashboard data

## 🔐 Permission Description

The system uses JWT-based authentication mechanism and annotation-based permission control:

- `@Auth` - Requires user login
- `@Auth(mustAdmin = true)` - Requires administrator privileges

## 🤝 Contributing Guidelines

1. Fork this repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
