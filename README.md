# 医院管理系统 (Hospital Management System)

一个基于 Spring Boot + Vue 3 的全栈医院管理系统，支持医院、医生、患者、挂号等核心业务的管理，并提供统计仪表盘和 AI 智能助手。

## 技术栈

### 后端
| 技术 | 版本 | 用途 |
|---|---|---|
| Spring Boot | 2.7.10 | 应用框架 |
| Java | 8 | 开发语言 |
| MyBatis | 2.2.2 | ORM / 数据访问 |
| MySQL | 8.0.22 | 关系型数据库 |
| Druid | 1.2.18 | JDBC 连接池与 SQL 监控 |
| PageHelper | 1.4.5 | 分页插件（已集成） |
| Lombok | 1.18.24 | 简化代码 |
| Maven | — | 构建工具 |

### 前端
| 技术 | 版本 | 用途 |
|---|---|---|
| Vue | 3.4.0 | UI 框架（Composition API + `<script setup>`） |
| Vite | 5.0.0 | 构建工具与开发服务器 |
| Element Plus | 2.5.0 | UI 组件库 |
| ECharts | 5.5.0 | 图表可视化 |
| Axios | 1.6.0 | HTTP 客户端 |
| xlsx | 0.18.5 | Excel 导出 |

## 功能模块

### 1. 医院管理 (`/hos`)
- 医院的增删改查，支持按名称和等级模糊搜索
- 字段：名称、类型、等级、编码、地址、状态
- 支持批量删除

### 2. 医生管理 (`/doctor`)
- 医生的增删改查，支持按姓名、科室、职称模糊搜索
- 字段：姓名、职称、科室、编码、电话、状态、挂号计数
- 支持批量删除

### 3. 患者管理 (`/patient`)
- 患者的增删改查，支持按姓名、电话、身份证号模糊搜索
- 字段：姓名、电话、身份证号、性别、年龄、生日、地址、血型、过敏史
- 支持批量删除

### 4. 挂号管理 (`/registration`)
- 患者挂号到指定医生，自动填充医院和科室信息
- 挂号成功后自动递增医生的挂号计数
- 支持按患者 ID 或医生 ID 筛选，支持取消（删除）挂号

### 5. 统计仪表盘 (`/sta`)
- 医院总数、患者总数统计
- 患者年龄分布（0-19、20-29、30-39、40-49）
- 各医院患者数量统计
- 前端使用 ECharts 柱状图和饼图可视化展示

### 6. AI 智能助手
- 右下角悬浮聊天按钮，点击打开 AI 对话窗口
- 嵌入 Coze AI 聊天机器人，提供智能问答服务

## 项目结构

```
hospital-sys-sd-test/
├── pom.xml                              # Maven 构建配置
├── src/main/java/com/gjj/
│   ├── HospitalApplication.java         # Spring Boot 启动类
│   ├── config/
│   │   └── DruidDataSourceConfig.java   # Druid 数据源配置
│   ├── controller/                      # 控制层
│   │   ├── HospitalController.java      # 医院接口
│   │   ├── DoctorController.java        # 医生接口
│   │   ├── PatientController.java       # 患者接口
│   │   ├── RegistrationController.java  # 挂号接口
│   │   └── StaController.java           # 统计接口
│   ├── service/                         # 业务逻辑层
│   │   ├── HospitalService.java
│   │   ├── DoctorService.java
│   │   ├── PatientService.java
│   │   ├── RegistrationService.java
│   │   └── StaService.java
│   ├── mapper/                          # MyBatis 数据访问层
│   │   ├── HospitalMapper.java
│   │   ├── DoctorMapper.java
│   │   ├── PatientMapper.java
│   │   ├── RegistrationMapper.java
│   │   └── StaMapper.java
│   ├── pojo/                            # 实体类与 DTO
│   │   ├── Hospital.java
│   │   ├── Doctor.java
│   │   ├── Patient.java
│   │   ├── Registration.java
│   │   ├── padoId.java                  # 挂号请求 DTO
│   │   └── Result.java                  # 统一响应封装
│   └── exception/
│       └── GlobalExceptionHandler.java  # 全局异常处理
├── src/main/resources/
│   ├── application.yml                  # 应用配置
│   ├── sqlMapConfig.xml                 # MyBatis 配置
│   └── mapper/                          # MyBatis XML 映射文件
│       ├── HospitalMapper.xml
│       ├── DoctorMapper.xml
│       ├── PatientMapper.xml
│       ├── RegistrationMapper.xml
│       └── StaMapper.xml
└── webui/                               # 前端项目
    ├── package.json                     # 依赖与脚本
    ├── vite.config.js                   # Vite 配置（含 API 代理）
    └── src/
        ├── main.js                      # Vue 入口
        ├── App.vue                      # 根布局组件
        ├── api/                         # API 请求封装
        │   ├── hospital.js
        │   ├── doctor.js
        │   ├── patient.js
        │   └── registration.js
        ├── components/                  # 公共组件
        │   ├── Layout/                  # 布局组件（Header、Sidebar）
        │   ├── Dashboard/               # 图表组件（StatCard、BarChart、PieChart）
        │   └── Chat/                    # AI 聊天组件
        └── views/                       # 页面视图
            ├── Dashboard.vue            # 统计仪表盘
            ├── Hospital.vue             # 医院管理页
            ├── Doctor.vue               # 医生管理页
            └── Patient.vue              # 患者管理页
```

## 快速开始

### 环境要求
- JDK 8+
- Maven 3.6+
- Node.js 18+
- MySQL 8.0+

### 1. 数据库配置

修改 `src/main/resources/application.yml` 中的数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://your-host:3306/your-database
    username: your-username
    password: your-password
```

### 2. 启动后端

```bash
mvn spring-boot:run
```

后端将在 `http://localhost:8080` 启动。

### 3. 启动前端

```bash
cd webui
npm install
npm run dev
```

前端将在 `http://localhost:5173` 启动，API 请求会自动代理到后端。

### 4. 访问应用

打开浏览器访问 `http://localhost:5173`。

## API 响应规范

所有接口统一返回 `Result` 对象：

```json
{
  "code": "200",
  "message": "操作成功",
  "data": {}
}
```

- `code` = `"200"` — 操作成功
- `code` = `"400"` — 客户端错误（由 `GlobalExceptionHandler` 统一处理）
- 其他 2xx 状态码用于标识不同类型的成功操作

## 注意事项

- 当前版本未集成认证/授权机制，适合学习与演示用途
- 服务层采用具体类直接注入（未使用接口-实现分离模式）
- POJO 同时作为实体和 DTO 使用，`padoId` 为专用请求 DTO
- 分页插件已引入但尚未在列表查询中使用
