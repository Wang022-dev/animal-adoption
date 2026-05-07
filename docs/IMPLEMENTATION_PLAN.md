# 流浪动物一站式领养管理系统（方案2）实施计划

> **For Hermes:** Use subagent-driven-development skill to implement this plan task-by-task.

**Goal:** 在今晚内交付一个可在 IDEA 中直接打开运行的 Java + Spring Boot + MyBatis + MySQL 项目，重点解决三角色权限隔离、动物救助到领养审核回访闭环、以及答辩可展示的现代化界面。

**Architecture:** 基于现有 Spring Boot + MyBatis + Thymeleaf/AJAX 一体化工程快速重构，但不再沿用旧页面与旧业务命名。保留 Java 8、Maven、MySQL 的稳定组合，新增统一 RBAC、动物状态流转、审核与回访闭环，并使用服务端模板 + 现代化 CSS 快速完成差异化前后台界面。

**Tech Stack:** Java 8, Spring Boot 2.2.x, MyBatis, MySQL 8, Maven, Thymeleaf, HTML/CSS/JS, IDEA。

---

## 一、答辩主线

### 1. 旧项目问题
- 管理员、志愿者、用户登录后界面几乎一样
- 权限边界不清晰，老师一眼就能看出“角色只是名字不同”
- 业务只停留在信息展示和 CRUD，缺少救助→审核→领养→回访闭环
- 动物档案字段不完整，无法体现真实业务

### 2. 新项目目标
- 三角色登录后进入不同工作台
- 管理员拥有审核与配置权，志愿者负责救助/跟进，用户只能浏览与申请
- 每只动物有唯一档案与状态流转日志
- 从“发现流浪动物”到“领养后回访”形成闭环

---

## 二、角色与权限设计

### 管理员
- 登录进入：后台管理工作台
- 可见模块：工作台、动物档案、救助审核、领养审核、回访管理、用户管理、志愿者管理、资讯管理、统计分析
- 核心权限：审核志愿者提交的救助记录；审核用户领养申请；配置动物可领养状态；查看全部数据

### 志愿者
- 登录进入：志愿者工作台
- 可见模块：我的救助、动物护理、待回访任务、回访记录、个人资料
- 核心权限：登记发现的流浪动物、补充健康和安置信息、填写回访记录
- 禁止：审核领养申请、管理用户、查看系统级统计

### 普通用户
- 登录进入：用户中心
- 可见模块：首页、领养中心、动物详情、领养申请、我的申请、个人资料
- 核心权限：浏览待领养动物、提交申请、查看审核结果、查看自己的回访/领养状态
- 禁止：编辑动物档案、审核任何业务

---

## 三、闭环状态流转

### 动物状态
1. 已发现待救助
2. 已救助待审核
3. 救助中
4. 医疗处理中
5. 待开放领养
6. 开放领养中
7. 申请审核中
8. 审核通过待交接
9. 已领养待回访
10. 领养完成
11. 退养处理中
12. 重新开放领养

### 关键闭环流程
志愿者发现流浪动物 → 提交救助记录 → 管理员审核入库 → 志愿者补充医疗/安置信息 → 管理员开放领养 → 用户提交领养申请 → 管理员审核 → 志愿者线下回访 → 领养完成/退养重开

---

## 四、数据库最小落地范围

### 必做表
1. sys_user：统一账号表（管理员/志愿者/用户）
2. animal：动物主表
3. rescue_record：救助记录
4. animal_medical_record：医疗记录
5. adoption_application：领养申请
6. adoption_followup：回访记录
7. animal_status_log：状态日志
8. article：资讯/公告

### 关键字段补充
#### animal
- animal_no
- name
- category（猫/狗/其他）
- breed
- color
- age_desc
- gender
- weight
- sterilized
- vaccinated
- health_status
- personality
- found_location
- found_time
- rescue_time
- current_location
- rescue_desc
- cover_image
- adopt_requirements
- status
- created_by

#### adoption_application
- animal_id
- applicant_user_id
- apply_reason
- housing_info
- family_info
- pet_experience
- status
- audit_remark
- audit_by
- audit_time

#### rescue_record
- animal_id
- volunteer_user_id
- found_location
- found_time
- emergency_level
- initial_health_desc
- rescue_desc
- audit_status
- audit_by
- audit_time

---

## 五、页面设计原则

### 前台
- 温暖公益风
- 卡片化动物展示
- 标签清晰：品种、年龄、性别、状态
- 首页突出：可领养动物、救助流程、成功案例

### 后台/工作台
- 管理员：深色文字 + 浅色背景 + 数据卡片 + 待审核清单
- 志愿者：任务导向，突出“待处理救助”“待回访”
- 用户中心：强调申请进度时间线

### 三角色界面差异化实现方式
- 登录成功后按 role 跳转不同首页：
  - `/admin/dashboard`
  - `/volunteer/dashboard`
  - `/user/home`
- 左侧菜单由后端按角色渲染，不共用整套菜单
- 控制器接口也按角色校验，不仅仅是前端隐藏

---

## 六、今晚优先级（MVP）

### P0 必须完成
1. 三角色登录与不同首页
2. 动物档案列表/详情
3. 志愿者提交救助记录
4. 管理员审核救助记录
5. 用户提交领养申请
6. 管理员审核领养申请
7. 志愿者填写回访记录
8. 状态流转展示

### P1 可加分
1. 首页统计卡片
2. 公告资讯
3. 假数据初始化
4. 演示账号预置

### P2 可后置
1. 图片多文件上传增强
2. 协议导出
3. 消息提醒

---

## 七、实施步骤

### Step 1：建立新项目目录与命名
- 目录：`/root/liulang-animal-adoption-system`
- 目标：保留 Maven/IDEA 结构，剥离旧业务命名污染

### Step 2：重写数据库脚本
- 文件：`/root/liulang-animal-adoption-system/sql/liulang_animal_adoption.sql`
- 建立新库：`liulang_animal_adoption`
- 初始化三角色演示账号与若干动物/申请数据

### Step 3：调整应用配置
- 修改 `pom.xml`
- 修改 `src/main/resources/application.yml`
- 修改启动类名称与项目显示名称
- context-path 改为 `/adoption`

### Step 4：新增统一用户与角色模型
- 新建 sys_user 实体、dao、service、mapper
- 新建角色判断工具与会话工具
- 登录统一走一套表，不再像旧项目拆成 users/yonghu/ziyuanzhe 三张账号表

### Step 5：新增核心业务模型
- animal
- rescue_record
- animal_medical_record
- adoption_application
- adoption_followup
- animal_status_log

### Step 6：实现角色控制
- 登录后按角色跳转
- 页面访问拦截
- 菜单按角色渲染
- 接口按角色校验

### Step 7：实现前台门户
- 首页
- 领养中心
- 动物详情页
- 在线申请页
- 用户中心

### Step 8：实现管理员后台
- 工作台
- 动物管理
- 救助审核
- 领养审核
- 用户/志愿者管理

### Step 9：实现志愿者后台
- 工作台
- 我的救助
- 动物照护
- 回访管理

### Step 10：准备答辩材料
- README 运行说明
- 答辩讲解提纲
- 演示账号说明
- 闭环流程说明

---

## 八、演示账号建议
- 管理员：admin / 123456
- 志愿者：volunteer1 / 123456
- 用户：user1 / 123456

---

## 九、答辩时可以直接讲的亮点
1. 用统一账号表 + RBAC 彻底解决角色权限混乱
2. 登录后不同角色进入不同工作台，而不是“同一后台换个名字”
3. 用动物状态流转日志体现完整生命周期
4. 补充发现地点、发现时间、品种、年龄、性别、健康、安置位置等核心档案字段
5. 形成救助-审核-领养-回访闭环，业务完整度明显高于普通 CRUD 毕设
