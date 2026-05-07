# Pet Haven 流浪动物领养系统交付说明

## 1. 最新源码
- 项目目录：`/root/liulang-animal-adoption-system`
- 交付压缩包（待本轮全链路验证后生成）：`/root/pet-haven-delivery.zip`

## 2. 数据库 SQL
- SQL 文件：`/root/liulang-animal-adoption-system/sql/liulang_animal_adoption.sql`
- 数据库名：`liulang_animal_adoption`
- 默认连接配置见：`src/main/resources/application.yml`

## 3. 启动方式（IDEA）
1. 安装 JDK 8
2. 安装 MySQL 5.7+/8.0，创建数据库 `liulang_animal_adoption`
3. 执行 SQL：`sql/liulang_animal_adoption.sql`
4. 修改 `src/main/resources/application.yml` 中数据库账号密码
5. IDEA 以 Maven 项目导入 `/root/liulang-animal-adoption-system`
6. 等待 Maven 依赖下载完成
7. 运行启动类：`com.AdoptionSystemApplication`
8. 本地访问：
   - 直连：`http://localhost:8081/adoption/login`
   - 如配 Nginx 代理：`http://localhost/login`

## 4. 当前已完成
- 登录页/注册页品牌化改造
- 管理员/用户/志愿者三端主页面改造
- 注册入口 404/401 问题排查修复
- 删除登录页不需要的“演示账号已从页面移除...”提示
- Nginx 80 端口代理到 Spring Boot 8081
- Spring Boot 服务已可启动
- SQL 文件已定位

## 5. 当前仍在继续处理
- 统一所有模板中的路径写法，彻底兼容 `/adoption` context-path 与 80 端口代理双入口
- 全链路跑通：注册 -> 登录 -> 用户申请 -> 管理员审核 -> 志愿者回访
- 最终生成源码+SQL+说明的完整 ZIP 交付包
