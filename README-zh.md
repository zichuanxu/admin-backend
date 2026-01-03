# admin-backend

基于Vue3+SpringBoot3的管理系统后端项目，提供完整的后台管理功能接口。

## 🌟 项目简介

这是一个功能完善的企业级管理系统后端，采用现代化的技术栈，提供了用户管理、部门管理、员工管理、文章管理、数据统计等核心功能。

**前端项目地址**: [admin-vue](https://github.com/zichuanxu/admin-vue)

## 🛠️ 技术栈

- **后端框架**: SpringBoot 3
- **数据库**: MySQL
- **ORM框架**: MyBatis-Plus
- **身份认证**: JWT (JSON Web Token)
- **构建工具**: Maven


## 🏗️ 项目结构

```
src/main/java/org/xu/admin/
├── annotation/          # 自定义注解（权限控制）
├── common/             # 通用类和工具
├── config/             # 配置类
├── controller/         # 控制器层
├── dto/                # 数据传输对象
├── entity/             # 实体类
├── interceptor/        # 拦截器
├── mapper/             # 数据访问层
├── service/            # 业务逻辑层
└── utils/              # 工具类
```

## 🚀 快速开始

### 环境要求
- JDK 17+
- MySQL 8.0+
- Maven 3.6+

### 安装步骤

1. **克隆项目**
```bash
git clone https://github.com/zichuanxu/admin-backend.git
cd admin-backend
```

2. **配置数据库**
    - 使用`init.sql`初始化MySQL数据库
    - 修改`application.yaml`中的数据库配置

3. **使用IDEA运行项目**



## 📋 系统功能

### 🔐 用户认证与授权
- **用户注册**: 支持新用户注册
- **用户登录**: JWT令牌认证
- **权限控制**: 基于注解的权限管理，区分普通用户和管理员权限
- **密码修改**: 用户可修改登录密码
- **用户登出**: 安全退出系统

#### 界面展示
**登录页面**
![登录页面](assets/login.png)

**注册页面**
![注册页面](assets/register.png)

**修改密码**
![修改密码](assets/password.png)

### 📊 数据统计仪表板
- **核心数据统计**: 用户总数、员工总数、文章总数、部门总数
- **部门人员分布**: 可视化展示各部门人员数量分布
- **实时数据更新**: 支持数据实时刷新

#### 界面展示
**统计仪表板**
![统计仪表板](assets/statistics.png)

**管理后台主页**
![管理后台主页](assets/homepage.png)

### 👥 用户管理
- **用户列表**: 分页查询所有用户信息
- **用户编辑**: 修改用户基本信息
- **用户删除**: 支持单个删除和批量删除
- **用户详情**: 查看用户详细信息
- **个人中心**: 用户查看和编辑个人信息

#### 界面展示
**用户管理**
![用户管理](assets/user.png)

**用户编辑**
![用户编辑](assets/user-edit.png)

**个人中心**
![个人中心](assets/info.png)

**信息编辑**
![信息编辑](assets/info-edit.png)

### 🏢 部门管理
- **部门列表**: 查看所有部门信息
- **部门新增**: 创建新的部门
- **部门编辑**: 修改部门信息
- **部门删除**: 支持单个删除和批量删除
- **部门详情**: 查看部门详细信息

#### 界面展示
**部门管理**
![部门管理](assets/department.png)

**新增部门**
![新增部门](assets/department-new.png)

### 👨‍💼 员工管理
- **员工列表**: 分页查询员工信息，包含部门关联
- **员工新增**: 添加新员工
- **员工编辑**: 修改员工信息
- **员工删除**: 支持单个删除和批量删除
- **数据导入**: 支持Excel批量导入员工信息
- **数据导出**: 支持导出员工数据为Excel文件

#### 界面展示
**员工管理**
![员工管理](assets/employee.png)

**员工编辑**
![员工编辑](assets/employee-edit.png)

### 📝 文章管理
- **文章列表**: 分页查询文章内容
- **文章发布**: 创建和发布新文章
- **文章编辑**: 修改文章内容
- **文章删除**: 支持单个删除和批量删除
- **文章预览**: 查看文章详情

#### 界面展示
**文章管理**
![文章管理](assets/article.png)

**新建文章**
![新建文章](assets/article-new.png)

**文章预览**
![文章预览](assets/article-preview.png)

### 🎯 管理员功能
- **系统概览**: 查看系统运行状态和数据统计
- **用户权限管理**: 管理用户权限和角色
- **数据维护**: 批量数据操作和维护

#### 界面展示
**管理员界面**
![管理员界面](assets/admin.png)

## 📡 API接口

### 认证接口
- `POST /user/login` - 用户登录
- `POST /user/register` - 用户注册
- `POST /user/logout` - 用户登出
- `POST /user/password` - 修改密码

### 用户管理
- `GET /user/all` - 分页查询用户
- `GET /user/{id}` - 获取用户详情
- `PUT /user` - 更新用户
- `DELETE /user/{id}` - 删除用户

### 部门管理
- `GET /department/all` - 获取所有部门
- `GET /department/page` - 分页查询部门
- `POST /department` - 新增部门
- `PUT /department` - 更新部门
- `DELETE /department/{id}` - 删除部门

### 员工管理
- `GET /employee/page` - 分页查询员工
- `POST /employee/add` - 新增员工
- `PUT /employee/update` - 更新员工
- `POST /employee/import` - 导入员工数据
- `POST /employee/export` - 导出员工数据

### 文章管理
- `GET /article/page` - 分页查询文章
- `POST /article/add` - 新增文章
- `PUT /article/update` - 更新文章
- `DELETE /article/{id}` - 删除文章

### 统计数据
- `GET /statistics/dashboard` - 获取仪表板数据

## 🔐 权限说明

系统采用基于JWT的认证机制和基于注解的权限控制：

- `@Auth` - 需要用户登录
- `@Auth(mustAdmin = true)` - 需要管理员权限

## 🤝 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情