# 流浪动物一站式领养管理系统

一个面向今晚答辩快速交付的 Java + Spring Boot + MyBatis + MySQL 项目，重点解决旧版本中：
- 三角色登录后界面相同
- 管理员、志愿者、用户权限边界混乱
- 只有展示，没有“救助 → 领养 → 审核 → 回访”闭环

## 技术栈
- Java 8
- Spring Boot 2.2.2
- MyBatis / MyBatis-Plus
- MySQL 8
- Maven
- Thymeleaf + HTML/CSS

## 项目目录
- `src/main/java/com`：后端代码
- `src/main/resources/templates`：页面模板
- `src/main/resources/static/assets`：样式资源
- `sql/liulang_animal_adoption.sql`：建库建表与演示数据
- `docs/IMPLEMENTATION_PLAN.md`：实施与答辩主线说明

## 运行步骤（IDEA 可直接打开）
1. 用 IDEA 打开目录：`/root/liulang-animal-adoption-system`
2. 确保本地 MySQL 可用，并存在 root/root 账号
3. 执行数据库脚本：
   ```sql
   source /root/liulang-animal-adoption-system/sql/liulang_animal_adoption.sql;
   ```
4. 运行启动类：
   - `com.AdoptionSystemApplication`
5. 打开浏览器访问：
   - `http://127.0.0.1:8081/adoption/login`

## 演示账号
- 管理员：`admin / 123456`
- 志愿者：`volunteer1 / 123456`
- 用户：`user1 / 123456`

## 三角色演示重点
### 管理员
- 登录进入管理员工作台
- 可审核志愿者提交的救助记录
- 可开放动物领养
- 可审核用户领养申请

### 志愿者
- 登录进入志愿者工作台
- 可新增流浪动物救助登记
- 只能看自己的救助任务
- 无审核权限

### 用户
- 登录进入领养中心
- 只能看公开动物与自己的申请
- 可提交领养申请
- 无后台管理权限

## 闭环主线
志愿者发现流浪动物 → 提交救助记录 → 管理员审核 → 动物进入救助/可领养状态 → 用户提交领养申请 → 管理员审核通过 → 志愿者回访 → 领养闭环完成

## 答辩亮点
1. 用统一账号表 `sys_user` 解决多角色账号分裂问题
2. 登录后跳转不同工作台，真正做到“不同角色不同界面”
3. 动物档案补充发现地点、发现时间、品种、年龄、性别、健康状态等关键字段
4. 增加 `animal_status_log`，让老师看到完整流程轨迹
5. 整体审美比旧课程设计模板更现代、更清爽

## 当前已完成
- 新项目目录创建
- 新数据库脚本编写并导入
- 新应用配置完成
- 核心实体 / DAO / Service 搭建
- 三角色登录与页面跳转
- 管理员/志愿者/用户三套基础页面
- 救助登记、救助审核、开放领养、领养申请、领养审核流程基础版

## 后续还能继续补的内容
- 回访录入页面单独完善
- 动物详情增加状态时间线
- 首页增加更多统计图表
- 增加图片上传与公告管理
