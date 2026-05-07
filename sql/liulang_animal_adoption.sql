DROP DATABASE IF EXISTS liulang_animal_adoption;
CREATE DATABASE liulang_animal_adoption DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE liulang_animal_adoption;

CREATE TABLE sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '登录账号',
    password VARCHAR(100) NOT NULL COMMENT '登录密码',
    role_code VARCHAR(20) NOT NULL COMMENT 'ADMIN/VOLUNTEER/USER',
    real_name VARCHAR(50) NOT NULL COMMENT '真实姓名',
    gender VARCHAR(10) DEFAULT NULL COMMENT '性别',
    phone VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    email VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    avatar VARCHAR(255) DEFAULT NULL COMMENT '头像',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '1启用 0禁用',
    bio VARCHAR(255) DEFAULT NULL COMMENT '备注简介',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT='统一账号表';

CREATE TABLE animal (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    animal_no VARCHAR(50) NOT NULL UNIQUE COMMENT '动物编号',
    name VARCHAR(50) NOT NULL COMMENT '动物昵称',
    category VARCHAR(20) NOT NULL COMMENT '猫/狗/其他',
    breed VARCHAR(50) DEFAULT NULL COMMENT '品种',
    color VARCHAR(50) DEFAULT NULL COMMENT '毛色',
    age_desc VARCHAR(50) DEFAULT NULL COMMENT '年龄描述',
    gender VARCHAR(10) DEFAULT NULL COMMENT '性别',
    weight DECIMAL(5,2) DEFAULT NULL COMMENT '体重kg',
    sterilized TINYINT NOT NULL DEFAULT 0 COMMENT '是否绝育',
    vaccinated TINYINT NOT NULL DEFAULT 0 COMMENT '是否免疫',
    health_status VARCHAR(50) DEFAULT NULL COMMENT '健康状态',
    personality VARCHAR(255) DEFAULT NULL COMMENT '性格描述',
    found_location VARCHAR(255) NOT NULL COMMENT '发现地点',
    found_time DATETIME NOT NULL COMMENT '发现时间',
    rescue_time DATETIME DEFAULT NULL COMMENT '救助时间',
    current_location VARCHAR(255) DEFAULT NULL COMMENT '当前安置地点',
    emergency_level VARCHAR(20) DEFAULT NULL COMMENT '紧急程度',
    rescue_desc TEXT COMMENT '救助描述',
    cover_image VARCHAR(255) DEFAULT NULL COMMENT '封面图',
    adopt_requirements VARCHAR(255) DEFAULT NULL COMMENT '领养要求',
    status VARCHAR(30) NOT NULL COMMENT '状态',
    created_by BIGINT DEFAULT NULL COMMENT '创建人',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT='动物主表';

CREATE TABLE rescue_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    animal_id BIGINT NOT NULL COMMENT '动物ID',
    volunteer_user_id BIGINT NOT NULL COMMENT '志愿者ID',
    found_location VARCHAR(255) NOT NULL COMMENT '发现地点',
    found_time DATETIME NOT NULL COMMENT '发现时间',
    rescue_time DATETIME DEFAULT NULL COMMENT '救助时间',
    emergency_level VARCHAR(20) DEFAULT NULL COMMENT '紧急程度',
    initial_health_desc VARCHAR(255) DEFAULT NULL COMMENT '初步健康情况',
    rescue_desc TEXT COMMENT '救助说明',
    audit_status VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT 'PENDING/APPROVED/REJECTED',
    audit_by BIGINT DEFAULT NULL COMMENT '审核人',
    audit_remark VARCHAR(255) DEFAULT NULL COMMENT '审核意见',
    audit_time DATETIME DEFAULT NULL COMMENT '审核时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT='救助记录';

CREATE TABLE animal_medical_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    animal_id BIGINT NOT NULL COMMENT '动物ID',
    record_type VARCHAR(30) NOT NULL COMMENT 'VACCINE/DEWORM/STERILIZE/TREATMENT/CHECKUP',
    record_date DATETIME NOT NULL COMMENT '记录时间',
    hospital_name VARCHAR(100) DEFAULT NULL COMMENT '医院',
    doctor_name VARCHAR(50) DEFAULT NULL COMMENT '医生',
    detail_desc VARCHAR(255) DEFAULT NULL COMMENT '详情',
    cost DECIMAL(10,2) DEFAULT NULL COMMENT '费用',
    operator_user_id BIGINT DEFAULT NULL COMMENT '操作人',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) COMMENT='动物医疗记录';

CREATE TABLE adoption_application (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    animal_id BIGINT NOT NULL COMMENT '动物ID',
    applicant_user_id BIGINT NOT NULL COMMENT '申请人ID',
    apply_reason TEXT COMMENT '申请理由',
    housing_info VARCHAR(255) DEFAULT NULL COMMENT '居住情况',
    family_info VARCHAR(255) DEFAULT NULL COMMENT '家庭情况',
    pet_experience VARCHAR(255) DEFAULT NULL COMMENT '养宠经验',
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT 'PENDING/APPROVED/REJECTED/SUPPLEMENT',
    audit_remark VARCHAR(255) DEFAULT NULL COMMENT '审核意见',
    audit_by BIGINT DEFAULT NULL COMMENT '审核管理员ID',
    audit_time DATETIME DEFAULT NULL COMMENT '审核时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT='领养申请';

CREATE TABLE adoption_followup (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    animal_id BIGINT NOT NULL COMMENT '动物ID',
    adopter_user_id BIGINT NOT NULL COMMENT '领养人ID',
    volunteer_user_id BIGINT NOT NULL COMMENT '回访志愿者ID',
    followup_time DATETIME NOT NULL COMMENT '回访时间',
    followup_result VARCHAR(20) NOT NULL COMMENT 'NORMAL/ABNORMAL/RETURNED',
    followup_content TEXT COMMENT '回访内容',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) COMMENT='领养回访';

CREATE TABLE animal_status_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    animal_id BIGINT NOT NULL COMMENT '动物ID',
    from_status VARCHAR(30) DEFAULT NULL COMMENT '原状态',
    to_status VARCHAR(30) NOT NULL COMMENT '新状态',
    remark VARCHAR(255) DEFAULT NULL COMMENT '备注',
    operator_user_id BIGINT DEFAULT NULL COMMENT '操作人',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) COMMENT='动物状态流转日志';

CREATE TABLE article (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    title VARCHAR(100) NOT NULL COMMENT '标题',
    category VARCHAR(30) NOT NULL COMMENT 'NEWS/STORY/NOTICE',
    cover_image VARCHAR(255) DEFAULT NULL COMMENT '封面图',
    summary VARCHAR(255) DEFAULT NULL COMMENT '摘要',
    content TEXT COMMENT '内容',
    publish_status TINYINT NOT NULL DEFAULT 1 COMMENT '1发布 0草稿',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT='资讯文章';

INSERT INTO sys_user (username, password, role_code, real_name, gender, phone, email, status, bio) VALUES
('admin', '123456', 'ADMIN', '系统管理员', '女', '13800000001', 'admin@example.com', 1, '负责审核与运营管理'),
('volunteer1', '123456', 'VOLUNTEER', '李志愿', '男', '13800000002', 'volunteer1@example.com', 1, '负责线下救助与回访'),
('user1', '123456', 'USER', '张领养', '女', '13800000003', 'user1@example.com', 1, '普通领养用户'),
('user2', '123456', 'USER', '王申请', '男', '13800000004', 'user2@example.com', 1, '用于演示补资料与驳回流程');

INSERT INTO animal (animal_no, name, category, breed, color, age_desc, gender, weight, sterilized, vaccinated, health_status, personality, found_location, found_time, rescue_time, current_location, emergency_level, rescue_desc, cover_image, adopt_requirements, status, created_by) VALUES
('AN202605001', '小橘', '猫', '中华田园猫', '橘白', '1岁左右', '母', 3.20, 1, 1, '健康稳定', '亲人、活泼', '大学城东门草坪', '2026-05-05 09:30:00', '2026-05-05 10:10:00', '校园救助站A区', '中', '发现时轻微营养不良，已完成基础检查', '/static/assets/animal-cat-1.jpg', '需接受封窗、有耐心家庭优先', 'ADOPTION_COMPLETED', 2),
('AN202605002', '阿黄', '狗', '中华田园犬', '黄白', '2岁左右', '公', 12.50, 0, 1, '观察中', '温顺、胆小', '老城区菜市场后巷', '2026-05-04 18:20:00', '2026-05-04 19:05:00', '合作宠物医院', '高', '右后腿有外伤，正在治疗恢复', '/static/assets/animal-dog-1.jpg', '需有养犬经验，接受回访', 'UNDER_RESCUE', 2),
('AN202605003', '雪球', '猫', '英短混血', '白色', '8个月', '公', 2.80, 1, 1, '健康稳定', '黏人、安静', '图书馆后侧停车场', '2026-05-08 20:15:00', '2026-05-08 21:00:00', '校园救助站B区', '低', '已完成驱虫和基础检查，适合尽快领养', '/static/assets/animal-cat-2.jpg', '封窗家庭优先，可接受新手领养', 'APPLICATION_REVIEW', 2),
('AN202605004', '可乐', '狗', '柯基串', '黑黄', '1岁半', '母', 9.60, 1, 1, '健康稳定', '活泼、亲人', '南门公交站旁', '2026-05-07 17:40:00', '2026-05-07 18:30:00', '合作宠物医院', '中', '完成体检，无明显外伤，已具备领养条件', '/static/assets/animal-dog-2.jpg', '需稳定遛狗时间，接受定期回访', 'OPEN_FOR_ADOPTION', 2),
('AN202605005', '灰灰', '猫', '狸花猫', '灰黑', '2岁左右', '母', 3.60, 0, 0, '待进一步检查', '警惕、怕生', '城北仓库区', '2026-05-09 22:10:00', '2026-05-09 22:50:00', '临时观察笼位', '高', '现场情况复杂，照片和伤情说明不足，待补充后重新提交', '/static/assets/animal-cat-3.jpg', '仅限有隔离经验家庭', 'RESCUE_REJECTED', 2);

INSERT INTO rescue_record (animal_id, volunteer_user_id, found_location, found_time, rescue_time, emergency_level, initial_health_desc, rescue_desc, audit_status, audit_by, audit_remark, audit_time) VALUES
(1, 2, '大学城东门草坪', '2026-05-05 09:30:00', '2026-05-05 10:10:00', '中', '营养不良但精神状态尚可', '已转运到校园救助站，完成基础消杀与喂食', 'APPROVED', 1, '信息完整，同意入库', '2026-05-05 11:00:00'),
(2, 2, '老城区菜市场后巷', '2026-05-04 18:20:00', '2026-05-04 19:05:00', '高', '右后腿受伤，需尽快处理', '已送合作医院拍片观察', 'APPROVED', 1, '先治疗后开放领养', '2026-05-04 20:00:00'),
(3, 2, '图书馆后侧停车场', '2026-05-08 20:15:00', '2026-05-08 21:00:00', '低', '精神状态稳定，无明显外伤', '已完成绝育和基础护理，等待领养申请审核', 'APPROVED', 1, '可进入领养审核阶段', '2026-05-08 21:40:00'),
(4, 2, '南门公交站旁', '2026-05-07 17:40:00', '2026-05-07 18:30:00', '中', '体征平稳，可正常进食', '完成基础体检后开放领养', 'APPROVED', 1, '护理完成，可开放领养', '2026-05-07 19:10:00'),
(5, 2, '城北仓库区', '2026-05-09 22:10:00', '2026-05-09 22:50:00', '高', '呼吸急促，环境嘈杂，需补充更完整伤情说明', '现场仅做临时转移，资料和照片不足', 'REJECTED', 1, '信息不完整，请补充现场照片和伤情记录后重新提交', '2026-05-09 23:20:00');

INSERT INTO animal_medical_record (animal_id, record_type, record_date, hospital_name, doctor_name, detail_desc, cost, operator_user_id) VALUES
(1, 'CHECKUP', '2026-05-05 10:40:00', '爱心宠物诊所', '周医生', '完成基础体检，整体情况稳定', 120.00, 2),
(1, 'STERILIZE', '2026-05-10 09:00:00', '爱心宠物诊所', '周医生', '已绝育，恢复良好', 300.00, 2),
(2, 'TREATMENT', '2026-05-04 19:40:00', '城西宠物医院', '林医生', '处理腿部外伤，建议继续观察3天', 260.00, 2),
(3, 'CHECKUP', '2026-05-08 21:10:00', '爱心宠物诊所', '周医生', '完成入站检查，可正常领养', 100.00, 2),
(4, 'VACCINE', '2026-05-08 10:20:00', '城南宠物门诊', '王医生', '完成首针免疫，状态稳定', 180.00, 2);

INSERT INTO adoption_application (animal_id, applicant_user_id, apply_reason, housing_info, family_info, pet_experience, status, audit_remark, audit_by, audit_time) VALUES
(1, 3, '一直想领养流浪猫，家中已做封窗，希望给它稳定环境；接受回访：接受；方便联系时段：工作日晚上', '自住房，已封窗，允许养猫', '两人居住，家人均同意；工作稳定性：稳定坐班', '有两年养猫经验', 'APPROVED', '资料完整，允许进入待交接阶段', 1, '2026-05-06 15:30:00'),
(3, 4, '希望领养一只性格安静的猫陪伴生活；接受回访：需提前预约；方便联系时段：周末均可', '租房，已封窗，有独立猫房', '与室友同住，室友已同意；工作稳定性：弹性工作', '曾养过一只猫，了解应激处理', 'SUPPLEMENT', '请补充最近半年居住稳定性证明及回访配合说明', 1, '2026-05-10 14:20:00'),
(4, 4, '平时有遛狗习惯，想给狗狗更稳定的家；接受回访：接受；方便联系时段：工作日下午', '合租，白天家中无人', '与朋友同住，家人不同住；工作稳定性：经常出差', '仅有短期寄养经验', 'REJECTED', '当前居住与陪伴条件暂不满足该犬只领养要求', 1, '2026-05-10 16:10:00');

INSERT INTO adoption_followup (animal_id, adopter_user_id, volunteer_user_id, followup_time, followup_result, followup_content) VALUES
(1, 3, 2, '2026-05-12 18:00:00', 'NORMAL', '动物适应良好，饮食和精神状态正常，领养人反馈积极'),
(1, 3, 2, '2026-05-19 18:30:00', 'NORMAL', '第二次回访正常，猫咪已适应新环境，闭环完成');

INSERT INTO animal_status_log (animal_id, from_status, to_status, remark, operator_user_id) VALUES
(1, NULL, 'RESCUED_PENDING_REVIEW', '志愿者提交救助记录', 2),
(1, 'RESCUED_PENDING_REVIEW', 'UNDER_RESCUE', '管理员审核通过', 1),
(1, 'UNDER_RESCUE', 'OPEN_FOR_ADOPTION', '完成基础医疗，开放领养', 1),
(1, 'OPEN_FOR_ADOPTION', 'APPLICATION_REVIEW', '用户提交领养申请', 3),
(1, 'APPLICATION_REVIEW', 'ADOPTION_APPROVED', '管理员审核通过', 1),
(1, 'ADOPTION_APPROVED', 'FOLLOWUP_PENDING', '完成线下交接，等待回访', 1),
(1, 'FOLLOWUP_PENDING', 'ADOPTION_COMPLETED', '回访正常，闭环完成', 2),
(2, NULL, 'RESCUED_PENDING_REVIEW', '志愿者提交救助记录', 2),
(2, 'RESCUED_PENDING_REVIEW', 'UNDER_RESCUE', '管理员审核通过，先治疗', 1),
(3, NULL, 'RESCUED_PENDING_REVIEW', '志愿者提交救助记录', 2),
(3, 'RESCUED_PENDING_REVIEW', 'UNDER_RESCUE', '管理员审核通过', 1),
(3, 'UNDER_RESCUE', 'OPEN_FOR_ADOPTION', '完成基础护理后开放领养', 1),
(3, 'OPEN_FOR_ADOPTION', 'APPLICATION_REVIEW', '用户提交领养申请', 4),
(3, 'APPLICATION_REVIEW', 'APPLICATION_REVIEW', '管理员要求补充领养资料', 1),
(4, NULL, 'RESCUED_PENDING_REVIEW', '志愿者提交救助记录', 2),
(4, 'RESCUED_PENDING_REVIEW', 'UNDER_RESCUE', '管理员审核通过', 1),
(4, 'UNDER_RESCUE', 'OPEN_FOR_ADOPTION', '完成体检后开放领养', 1),
(4, 'OPEN_FOR_ADOPTION', 'APPLICATION_REVIEW', '用户提交领养申请', 4),
(4, 'APPLICATION_REVIEW', 'OPEN_FOR_ADOPTION', '管理员驳回领养申请，重新开放领养', 1),
(5, NULL, 'RESCUED_PENDING_REVIEW', '志愿者提交救助记录', 2),
(5, 'RESCUED_PENDING_REVIEW', 'RESCUE_REJECTED', '管理员驳回救助记录，要求补充资料', 1);

INSERT INTO article (title, category, cover_image, summary, content, publish_status) VALUES
('五月救助简报：本周新增5只待安置动物', 'NEWS', '/static/assets/news-1.jpg', '展示本周救助进展、补资料流程与领养成果', '本周新增5只流浪动物进入救助流程，其中1只已完成完整领养回访闭环，1只处于补充资料阶段。', 1),
('从街头到新家：小橘的领养故事', 'STORY', '/static/assets/story-1.jpg', '讲述小橘完成救助、审核、领养和回访的完整闭环', '小橘在大学城被发现后，由志愿者送往救助站，经过检查和绝育后成功被领养。', 1),
('审核提醒：资料不足的救助/领养申请需补充后再流转', 'NOTICE', '/static/assets/notice-1.jpg', '用于答辩展示系统支持驳回与补充资料机制', '管理员可对不完整资料执行驳回或补充资料操作，系统会保留审核意见与状态流转记录。', 1);
