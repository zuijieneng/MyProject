/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : db_crowd

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 30/07/2022 10:21:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crowd_back
-- ----------------------------
DROP TABLE IF EXISTS `crowd_back`;
CREATE TABLE `crowd_back`  (
  `cid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '以back_开头',
  `pbid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目id',
  `clevel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报等级',
  `ctitle` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报标题',
  `cdescribe` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报档描述',
  `cpost_way` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮费方式',
  `csend_date` datetime(6) NULL DEFAULT NULL COMMENT '回报发送日期',
  `csend_way` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报发送方式',
  `cmoney` float(30, 0) NULL DEFAULT NULL COMMENT '回报档金额',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报档类型，早鸟档等',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crowd_back
-- ----------------------------
INSERT INTO `crowd_back` VALUES ('back_1', 'project_1', '回报档1', '这是回报标题', '送公仔一个', '包邮', '2022-07-28 00:00:00.000000', '邮递', 25, NULL);
INSERT INTO `crowd_back` VALUES ('back_5', 'project_7', '回报档5', '送礼物', '送龙猫公仔一个', '包邮', '2022-07-28 00:00:00.000000', '邮递', 150, NULL);

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `dtype` int(11) NULL DEFAULT NULL COMMENT '类型：1 省份 2 城市 3 账户类型 4 银行类型 5 证件类型 6 项目类型',
  `fdid` int(11) NULL DEFAULT NULL COMMENT '关联类型   城市所在的省份',
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (10, '河南省', 1, NULL);
INSERT INTO `dictionary` VALUES (11, '郑州', 2, 10);
INSERT INTO `dictionary` VALUES (12, '南阳', 2, 10);
INSERT INTO `dictionary` VALUES (13, '安阳', 2, 10);
INSERT INTO `dictionary` VALUES (14, '信阳', 2, 10);
INSERT INTO `dictionary` VALUES (20, '河北省', 1, NULL);
INSERT INTO `dictionary` VALUES (21, '石家庄', 2, 20);
INSERT INTO `dictionary` VALUES (22, '太原', 2, 20);
INSERT INTO `dictionary` VALUES (23, '邯郸', 2, 20);
INSERT INTO `dictionary` VALUES (24, '廊坊', 2, 20);
INSERT INTO `dictionary` VALUES (30, '北京', 1, NULL);
INSERT INTO `dictionary` VALUES (31, '丰台', 2, 30);
INSERT INTO `dictionary` VALUES (32, '海淀', 2, 30);
INSERT INTO `dictionary` VALUES (33, '朝阳', 2, 30);
INSERT INTO `dictionary` VALUES (34, '通州', 2, 30);
INSERT INTO `dictionary` VALUES (40, '云南省', 1, NULL);
INSERT INTO `dictionary` VALUES (41, '昆明', 2, 40);
INSERT INTO `dictionary` VALUES (42, '丽江', 2, 40);
INSERT INTO `dictionary` VALUES (43, '大理', 2, 40);
INSERT INTO `dictionary` VALUES (44, '西双版纳', 2, 40);
INSERT INTO `dictionary` VALUES (45, '身份证（中国大陆）', 5, NULL);
INSERT INTO `dictionary` VALUES (46, '港澳居民来往内地通行证（香港澳门）', 5, NULL);
INSERT INTO `dictionary` VALUES (47, '台湾居民来往大陆通行证（台湾人员）', 5, NULL);
INSERT INTO `dictionary` VALUES (48, '护照（仅外籍人员）', 5, NULL);
INSERT INTO `dictionary` VALUES (49, '储蓄卡', 3, NULL);
INSERT INTO `dictionary` VALUES (50, '银行卡', 3, NULL);
INSERT INTO `dictionary` VALUES (51, '中国建设银行', 4, NULL);
INSERT INTO `dictionary` VALUES (52, '中国农业银行', 4, NULL);
INSERT INTO `dictionary` VALUES (53, '中国工商银行', 4, NULL);
INSERT INTO `dictionary` VALUES (54, '中国银行', 4, NULL);
INSERT INTO `dictionary` VALUES (55, '借记卡', 3, NULL);
INSERT INTO `dictionary` VALUES (56, '游戏', 6, NULL);
INSERT INTO `dictionary` VALUES (57, '动漫', 6, NULL);
INSERT INTO `dictionary` VALUES (58, '出版', 6, NULL);
INSERT INTO `dictionary` VALUES (59, '桌游', 6, NULL);
INSERT INTO `dictionary` VALUES (60, '卡牌', 6, NULL);
INSERT INTO `dictionary` VALUES (61, '潮玩模型', 6, NULL);
INSERT INTO `dictionary` VALUES (62, '影视', 6, NULL);
INSERT INTO `dictionary` VALUES (63, '音乐', 6, NULL);
INSERT INTO `dictionary` VALUES (64, '活动', 6, NULL);
INSERT INTO `dictionary` VALUES (65, '设计', 6, NULL);
INSERT INTO `dictionary` VALUES (66, '科技', 6, NULL);
INSERT INTO `dictionary` VALUES (67, '食品', 6, NULL);
INSERT INTO `dictionary` VALUES (68, '爱心通道', 6, NULL);
INSERT INTO `dictionary` VALUES (69, '动物救援', 6, NULL);
INSERT INTO `dictionary` VALUES (70, '个人愿望', 6, NULL);
INSERT INTO `dictionary` VALUES (71, '其他', 6, NULL);
INSERT INTO `dictionary` VALUES (72, '全部', 6, NULL);

-- ----------------------------
-- Table structure for img_mp4
-- ----------------------------
DROP TABLE IF EXISTS `img_mp4`;
CREATE TABLE `img_mp4`  (
  `imid` int(11) NOT NULL AUTO_INCREMENT,
  `im_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片path',
  `im_owner_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片所属对象的编号,因为动态、项目等id都有明显特征，比如log开头，因此已经细分对象',
  PRIMARY KEY (`imid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img_mp4
-- ----------------------------
INSERT INTO `img_mp4` VALUES (84, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590028497091.jpg', 'log_1');
INSERT INTO `img_mp4` VALUES (85, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590028497222.jpg', 'log_1');
INSERT INTO `img_mp4` VALUES (86, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590031809671.jpg', 'log_2');
INSERT INTO `img_mp4` VALUES (87, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590031809852.jpg', 'log_2');
INSERT INTO `img_mp4` VALUES (88, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590033440851.jpg', 'log_2');
INSERT INTO `img_mp4` VALUES (89, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590033441042.jpg', 'log_2');
INSERT INTO `img_mp4` VALUES (90, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590034162381.jpg', 'log_3');
INSERT INTO `img_mp4` VALUES (91, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590034162612.jpg', 'log_3');
INSERT INTO `img_mp4` VALUES (92, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590035558807.jpg', 'log_6');
INSERT INTO `img_mp4` VALUES (93, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590035558982.jpg', 'log_6');
INSERT INTO `img_mp4` VALUES (94, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590036637347.jpg', 'log_6');
INSERT INTO `img_mp4` VALUES (95, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590036637522.jpg', 'log_6');
INSERT INTO `img_mp4` VALUES (96, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590037357137.jpg', 'log_7');
INSERT INTO `img_mp4` VALUES (97, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590037357292.jpg', 'log_7');
INSERT INTO `img_mp4` VALUES (98, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590060878727.jpg', 'log_8');
INSERT INTO `img_mp4` VALUES (99, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590060878842.jpg', 'log_8');
INSERT INTO `img_mp4` VALUES (100, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\project\\projectapp16590075961148.jpg', 'project_1');
INSERT INTO `img_mp4` VALUES (101, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\project\\projectapp165900759611420.jpg', 'project_1');
INSERT INTO `img_mp4` VALUES (102, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\project\\projectdetail16590076218327.jpg', 'project_1');
INSERT INTO `img_mp4` VALUES (103, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590116851867.jpg', 'log_10');
INSERT INTO `img_mp4` VALUES (104, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog1659011685247', 'log_10');
INSERT INTO `img_mp4` VALUES (105, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\project\\projectapp16590118340102.jpg', 'project_2');
INSERT INTO `img_mp4` VALUES (106, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\project\\projectapp16590118340101.jpg', 'project_2');
INSERT INTO `img_mp4` VALUES (107, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\log\\userlog16590625214921.jpg', 'log_16');
INSERT INTO `img_mp4` VALUES (108, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\project\\projectapp16590758054452.jpg', 'project_7');
INSERT INTO `img_mp4` VALUES (109, 'F:\\Java_ZhiYou\\Project\\MODUO\\src\\main\\resources\\static\\imgs\\project\\projectapp16590758054451.jpg', 'project_7');

-- ----------------------------
-- Table structure for log_title
-- ----------------------------
DROP TABLE IF EXISTS `log_title`;
CREATE TABLE `log_title`  (
  `uloid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tid` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '话题编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_title
-- ----------------------------
INSERT INTO `log_title` VALUES ('log_1', 'title_3');
INSERT INTO `log_title` VALUES ('log_2', 'title_3');
INSERT INTO `log_title` VALUES ('log_3', 'title_3');
INSERT INTO `log_title` VALUES ('log_4', 'title_3');
INSERT INTO `log_title` VALUES ('log_5', 'title_3');
INSERT INTO `log_title` VALUES ('log_7', 'title_3');
INSERT INTO `log_title` VALUES ('log_8', 'title_3');
INSERT INTO `log_title` VALUES ('log_9', 'title_3');
INSERT INTO `log_title` VALUES ('log_10', 'title_3');
INSERT INTO `log_title` VALUES ('log_11', 'title_4');
INSERT INTO `log_title` VALUES ('log_12', 'title_3');
INSERT INTO `log_title` VALUES ('log_13', 'title_3');
INSERT INTO `log_title` VALUES ('log_14', 'title_3');
INSERT INTO `log_title` VALUES ('log_15', 'title_3');
INSERT INTO `log_title` VALUES ('log_16', 'title_5');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `messageid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '以message_开头',
  `message_time` date NULL DEFAULT NULL COMMENT '留言创建时间',
  `message_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言文字内容',
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言发布者',
  `m_owner_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言所属对象的编号，动态',
  `target_uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他的用户ID，因为用户与用户之间的也是多对多的关系',
  PRIMARY KEY (`messageid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('messageid_1', '2022-07-28', '我对project_1留言，不带图片，留言第三次', '40280981824473c8018244752bf50000', 'log_7', NULL);
INSERT INTO `message` VALUES ('messageid_2', '2022-07-28', '我自己对自己的动态一留言', '4028098182442a4c0182442ac1fc0000', 'log_1', NULL);
INSERT INTO `message` VALUES ('messageid_3', '2022-07-28', '我自己对自己的手办众筹项目留言', '4028098182442a4c0182442ac1fc0000', 'project_1', NULL);
INSERT INTO `message` VALUES ('messageid_4', '2022-07-29', '动漫！', '4028098182442a4c0182442ac1fc0000', 'project_6', NULL);
INSERT INTO `message` VALUES ('messageid_5', '2022-07-29', '话题不错哦', '4028098182442a4c0182442ac1fc0000', 'title_3', NULL);
INSERT INTO `message` VALUES ('messageid_6', '2022-07-29', '项目做的不错', '4028098182442a4c0182442ac1fc0000', 'project_6', NULL);
INSERT INTO `message` VALUES ('messageid_7', '2022-07-29', '我觉得可以继续扩展', '4028098182442a4c0182442ac1fc0000', 'project_6', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `message` VALUES ('messageid_8', '2022-07-29', '我觉得你说得对', '40280981824473c8018244752bf50000', 'project_6', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `message` VALUES ('messageid_9', '2022-07-29', '我非常的喜欢宫崎骏的动漫！！！', '4028098182442a4c0182442ac1fc0000', 'project_7', NULL);

-- ----------------------------
-- Table structure for project_basic
-- ----------------------------
DROP TABLE IF EXISTS `project_basic`;
CREATE TABLE `project_basic`  (
  `pbid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '以project_开头',
  `pname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目标题',
  `ptype` int(11) NULL DEFAULT NULL COMMENT '项目类型',
  `pdevelop_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态：1创意2预热3众筹中4众筹成功',
  `pshow_first` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目简介',
  `pcontent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目具体内容',
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目发起者',
  `ptime` date NULL DEFAULT NULL COMMENT '项目发起时间',
  `pstatus` int(11) NULL DEFAULT 1 COMMENT '1,审核通过2，未通过',
  PRIMARY KEY (`pbid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_basic
-- ----------------------------
INSERT INTO `project_basic` VALUES ('project_1', '经典电影人物手办', 64, '全部', '天龙八部', '经典电影人物手办', '4028098182442a4c0182442ac1fc0000', '2022-07-28', 1);
INSERT INTO `project_basic` VALUES ('project_2', '经典', 64, '全部', '音乐', '电音节', '4028098182442a4c0182442ac1fc0000', '2022-07-28', 1);
INSERT INTO `project_basic` VALUES ('project_3', '经典', 64, '众筹', '音乐', '电音', '4028098182442a4c0182442ac1fc0000', '2022-07-29', 1);
INSERT INTO `project_basic` VALUES ('project_4', '经典', 64, '全部', '音乐', '电音222', '4028098182442a4c0182442ac1fc0000', '2022-07-29', 1);
INSERT INTO `project_basic` VALUES ('project_5', '经典', 64, '众筹', '音乐', '电节', '4028098182442a4c0182442ac1fc0000', '2022-07-29', 1);
INSERT INTO `project_basic` VALUES ('project_6', '经典', 64, '众筹', '音乐', '动漫', '4028098182442a4c0182442ac1fc0000', '2022-07-29', 0);
INSERT INTO `project_basic` VALUES ('project_7', '宫崎骏动漫手策众筹项目', 57, '众筹', '经典动漫人物，哈尔的移动城堡苏菲，哈尔，龙猫等', '宫崎骏的动漫手册', '4028098182442a4c0182442ac1fc0000', '2022-07-29', 1);

-- ----------------------------
-- Table structure for project_crowd
-- ----------------------------
DROP TABLE IF EXISTS `project_crowd`;
CREATE TABLE `project_crowd`  (
  `pcid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '以crowd_开头',
  `pbid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目基本信息',
  `pcrowd_money` float(100, 2) NULL DEFAULT NULL COMMENT '众筹目标金额',
  `pcrowd_start` datetime(0) NULL DEFAULT NULL COMMENT '众筹开始时间',
  `pcrowd_end` datetime(0) NULL DEFAULT NULL COMMENT '众筹结束时间',
  PRIMARY KEY (`pcid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_crowd
-- ----------------------------
INSERT INTO `project_crowd` VALUES ('crowd_1', 'project_3', 0.00, '2022-07-29 10:18:36', NULL);
INSERT INTO `project_crowd` VALUES ('crowd_2', 'project_5', 0.00, '2022-07-29 10:32:37', NULL);
INSERT INTO `project_crowd` VALUES ('crowd_3', 'project_6', 120000.00, '2022-07-29 10:34:22', NULL);
INSERT INTO `project_crowd` VALUES ('crowd_4', 'project_7', 200000.00, '2022-07-29 14:25:29', NULL);

-- ----------------------------
-- Table structure for project_team
-- ----------------------------
DROP TABLE IF EXISTS `project_team`;
CREATE TABLE `project_team`  (
  `pbid` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目主键',
  `phone` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '运营者手机号',
  `pqq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信或QQ',
  `ptemail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `pworker` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摩点工作人员',
  PRIMARY KEY (`pbid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_team
-- ----------------------------
INSERT INTO `project_team` VALUES ('project_1', '15290285123', '2874873910', '15290285123@163.com', '');

-- ----------------------------
-- Table structure for project_user
-- ----------------------------
DROP TABLE IF EXISTS `project_user`;
CREATE TABLE `project_user`  (
  `pbid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目详情编号',
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户参与者编号',
  PRIMARY KEY (`pbid`, `uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_user
-- ----------------------------
INSERT INTO `project_user` VALUES ('project_1', '4028098182442a4c0182442ac1fc0000');

-- ----------------------------
-- Table structure for team_user
-- ----------------------------
DROP TABLE IF EXISTS `team_user`;
CREATE TABLE `team_user`  (
  `pbid` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目编号',
  `uid` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '团队成员',
  PRIMARY KEY (`pbid`, `uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_user
-- ----------------------------
INSERT INTO `team_user` VALUES ('project_1', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `team_user` VALUES ('project_2', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `team_user` VALUES ('project_3', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `team_user` VALUES ('project_4', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `team_user` VALUES ('project_5', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `team_user` VALUES ('project_6', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `team_user` VALUES ('project_7', '4028098182442a4c0182442ac1fc0000');

-- ----------------------------
-- Table structure for title
-- ----------------------------
DROP TABLE IF EXISTS `title`;
CREATE TABLE `title`  (
  `tid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '以title_开头',
  `tcontent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '话题内容',
  `ttime` datetime(0) NULL DEFAULT NULL COMMENT '话题创建时间',
  `tphoto` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '话题图片',
  `tuser_count` int(11) NULL DEFAULT 1 COMMENT '话题讨论者统计人数',
  `tlog_count` int(11) NULL DEFAULT 0 COMMENT '话题动态统计人数',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of title
-- ----------------------------
INSERT INTO `title` VALUES ('title_3', '日常happy', '2022-07-28 18:07:35', '', 1, 14);
INSERT INTO `title` VALUES ('title_4', '话题内容嘎嘎嘎', '2022-07-29 09:54:40', '', 0, 1);
INSERT INTO `title` VALUES ('title_5', '话题嘎嘎', '2022-07-29 10:42:17', '', 0, 1);

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account`  (
  `uacid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `uaccount_type_id` int(100) NULL DEFAULT NULL COMMENT '账户类型编号，0储蓄卡，1信用卡',
  `uaccount_bank_id` int(100) NULL DEFAULT NULL COMMENT '账户银行编号 #建行 #农行',
  `uaccount_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `uaccount_owner` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号拥有者名字',
  `uaccount_owner_person_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号拥有者身份证号',
  PRIMARY KEY (`uacid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES (1, '4028098182442a4c0182442ac1fc0000', 1, 51, '6228 4320 1562 679', '张玉', '411525200012010234');

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
  `uaid` int(11) NOT NULL AUTO_INCREMENT,
  `uaddress_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址名称',
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `uaddress_receiver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人',
  `uaddress_province` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份\r\n',
  `city_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `uaddress_detail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址详情',
  `uaddress_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `uaddress_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `uaddress_postal_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址邮编',
  `is_default` int(255) NULL DEFAULT NULL COMMENT '是否是默认地址，1是，0不是',
  `province_id` int(11) NULL DEFAULT NULL,
  `city_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`uaid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES (3, '地址一', '4028098182442a4c0182442ac1fc0000', '张玉', '河南省', '郑州', '中原中路41号', '15290285123@163.com', '15290285123', '410000', 1, 10, 11);
INSERT INTO `user_address` VALUES (4, '地址一', '40280981824473c8018244752bf50000', '一一', '河南省', '郑州', '金水区', '15565534258@163.com', '15290285123', '410000', 1, 10, 11);

-- ----------------------------
-- Table structure for user_basic
-- ----------------------------
DROP TABLE IF EXISTS `user_basic`;
CREATE TABLE `user_basic`  (
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `uname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `uphoto` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1.jpg' COMMENT '头像',
  `usex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未知' COMMENT '性别',
  `ubirth` date NULL DEFAULT NULL COMMENT '生日',
  `uaddress` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '居住地：省-市',
  `unative` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家乡：省-市',
  `ushow` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `utype` int(11) NULL DEFAULT NULL COMMENT '是否认证:0未认证 1 个人认证 2 企业认证'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_basic
-- ----------------------------
INSERT INTO `user_basic` VALUES ('4028098182442a4c0182442ac1fc0000', 'e850ed1', NULL, NULL, '2022-07-28', NULL, NULL, NULL, 2);
INSERT INTO `user_basic` VALUES ('40280981824473c8018244752bf50000', '4f2b42b', NULL, NULL, '2022-07-28', NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for user_company_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_company_auth`;
CREATE TABLE `user_company_auth`  (
  `ucaid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `ucompany_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名字',
  `ucompany_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司社会信用代码',
  `ucompany_person` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人',
  `ucompany_person_paper_type_id` int(100) NULL DEFAULT NULL COMMENT '法人证件类型编号',
  `ucompany_person_paper_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人证件编号',
  `ucompany_person_paper_front_photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人证件前照',
  `ucompany_person_paper_back_photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人证件后照',
  `ucompany_person_paper_start` datetime(0) NULL DEFAULT NULL COMMENT '法人证件起始时间',
  `ucompany_person_paper_end` datetime(0) NULL DEFAULT NULL COMMENT '法人证件结束时间',
  `ucompany_license_photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司营业执照',
  `ucompany_license_start` datetime(0) NULL DEFAULT NULL COMMENT '营业执照起始时间',
  `ucompany_license_end` datetime(0) NULL DEFAULT NULL COMMENT '营业执照结束时间',
  `ucompany_show` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司介绍',
  `ucompany_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司地址',
  `ucompany_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司电话',
  `ucompany_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司邮箱',
  `ucompany_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司官网',
  PRIMARY KEY (`ucaid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_company_auth
-- ----------------------------
INSERT INTO `user_company_auth` VALUES (8, '4028098182442a4c0182442ac1fc0000', '空调公司', '2134526', '鱼', 45, '411523200011010912', 'companyauthfront4028098182442a4c0182442ac1fc0000.jpg', 'companyauthback4028098182442a4c0182442ac1fc0000.jpg', '2012-11-11 00:00:00', '2022-11-11 00:00:00', 'companyauthlicense4028098182442a4c0182442ac1fc0000.jpg', '2018-11-11 00:00:00', '2018-12-11 00:00:00', '买空调的', '上海嘉庆', '00-22-11111', '2874873910@qq.com', 'https://baidu.com');

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log`  (
  `uloid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '以log_开头',
  `ulog_time` datetime(0) NULL DEFAULT NULL COMMENT '动态创建时间',
  `ulog_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动态文字内容',
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动态发布者',
  PRIMARY KEY (`uloid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('log_1', '2022-07-28 18:07:35', '今天去看了漫展，感觉不错！开心！', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `user_log` VALUES ('log_10', '2022-07-28 20:35:30', '我是新动态', '40280981824473c8018244752bf50000');
INSERT INTO `user_log` VALUES ('log_11', '2022-07-29 09:54:40', '动态内容嘎嘎嘎', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `user_log` VALUES ('log_12', '2022-07-29 10:02:04', '我是新动态', '40280981824473c8018244752bf50000');
INSERT INTO `user_log` VALUES ('log_13', '2022-07-29 10:02:15', '我是新动态', '40280981824473c8018244752bf50000');
INSERT INTO `user_log` VALUES ('log_14', '2022-07-29 10:02:30', '我是新动态', '40280981824473c8018244752bf50000');
INSERT INTO `user_log` VALUES ('log_15', '2022-07-29 10:03:14', '我是新动态', '40280981824473c8018244752bf50000');
INSERT INTO `user_log` VALUES ('log_16', '2022-07-29 10:42:17', '动态内容嘎嘎嘎', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `user_log` VALUES ('log_2', '2022-07-28 18:13:42', '跟朋友一起看电影，开心！', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `user_log` VALUES ('log_3', '2022-07-28 18:15:53', '跟朋友一起看电影，开心！', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `user_log` VALUES ('log_4', '2022-07-28 18:16:59', '跟朋友一起看电影，开心！', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `user_log` VALUES ('log_5', '2022-07-28 18:19:20', '跟朋友一起看电影，开心！', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `user_log` VALUES ('log_6', '2022-07-28 18:21:07', 'hhh', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `user_log` VALUES ('log_7', '2022-07-28 18:22:18', '跟朋友一起看电影，开心！', '4028098182442a4c0182442ac1fc0000');
INSERT INTO `user_log` VALUES ('log_8', '2022-07-28 19:02:00', '跟朋友一起看电影，开心！', '40280981824473c8018244752bf50000');
INSERT INTO `user_log` VALUES ('log_9', '2022-07-28 19:05:24', '跟朋友一起看电影，开心！', '40280981824473c8018244752bf50000');

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login`  (
  `ulid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `uphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `uemail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `upwd` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `uyzm` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验证码',
  `uyzm_time` datetime(0) NULL DEFAULT NULL COMMENT '验证码发送时间：不能超过五分钟',
  `ubefore_login_time` datetime(0) NULL DEFAULT NULL COMMENT '本次登陆时间',
  `ucurrent_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登陆时间',
  `uscore` int(10) NULL DEFAULT NULL COMMENT '安全积分',
  `u_before_login_time` datetime(6) NULL DEFAULT NULL,
  `u_current_login_time` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`ulid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES (15, '4028098182442a4c0182442ac1fc0000', '15290285123', NULL, '12', '2407', '2022-07-28 18:57:58', '2022-07-29 14:21:15', '2022-07-29 14:35:14', 50, NULL, NULL);
INSERT INTO `user_login` VALUES (16, '40280981824473c8018244752bf50000', '15565534258', NULL, '12', '2465', '2022-07-28 18:58:26', '2022-07-29 11:58:08', '2022-07-29 12:02:07', 30, NULL, NULL);

-- ----------------------------
-- Table structure for user_person_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_person_auth`;
CREATE TABLE `user_person_auth`  (
  `upaid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `ureal_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实名字',
  `upaper_type_id` int(18) NULL DEFAULT NULL COMMENT '证件类型编号',
  `upaper_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件编号',
  `upaper_front_photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件照前',
  `upaper_back_photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件照后',
  `upaper_start` datetime(0) NULL DEFAULT NULL COMMENT '证件起始时间',
  `upaper_end` datetime(0) NULL DEFAULT NULL COMMENT '证件结束时间',
  `uaddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `umail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`upaid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_person_auth
-- ----------------------------
INSERT INTO `user_person_auth` VALUES (7, '4028098182442a4c0182442ac1fc0000', '张玉', 45, '411525200012010234', 'personauthfront4028098182442a4c0182442ac1fc0000.jpg', 'personauthback4028098182442a4c0182442ac1fc0000.jpg', '2018-12-02 00:00:00', '2018-12-02 00:00:00', '河南省', '15290285123@163.com');

-- ----------------------------
-- Table structure for user_project_care
-- ----------------------------
DROP TABLE IF EXISTS `user_project_care`;
CREATE TABLE `user_project_care`  (
  `pbid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目详情编号',
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户关注者编号',
  PRIMARY KEY (`pbid`, `uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_project_care
-- ----------------------------
INSERT INTO `user_project_care` VALUES ('project_1', '4028098182442a4c0182442ac1fc0000');

-- ----------------------------
-- Table structure for user_user_care
-- ----------------------------
DROP TABLE IF EXISTS `user_user_care`;
CREATE TABLE `user_user_care`  (
  `uzhu_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被关注者编号',
  `ufen_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '粉丝编号',
  PRIMARY KEY (`uzhu_id`, `ufen_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_user_care
-- ----------------------------
INSERT INTO `user_user_care` VALUES ('40280981822a68fc01822a6954690000', '40280981824473c8018244752bf50000');

-- ----------------------------
-- Table structure for zan_xu
-- ----------------------------
DROP TABLE IF EXISTS `zan_xu`;
CREATE TABLE `zan_xu`  (
  `uid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '赞嘘者',
  `zx_owner_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被赞嘘对象的编号',
  `zx` int(11) NULL DEFAULT NULL COMMENT '赞 1 嘘 -1'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zan_xu
-- ----------------------------
INSERT INTO `zan_xu` VALUES ('4028098182442a4c0182442ac1fc0000', 'log_1', 1);
INSERT INTO `zan_xu` VALUES ('4028098182442a4c0182442ac1fc0000', 'log_1', 1);
INSERT INTO `zan_xu` VALUES ('4028098182442a4c0182442ac1fc0000', '', NULL);

SET FOREIGN_KEY_CHECKS = 1;
