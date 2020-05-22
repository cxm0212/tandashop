DROP DATABASE IF EXISTS tdshop;
CREATE DATABASE tdshop;
USE tdshop;


DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`(
  id INT(18) PRIMARY KEY AUTO_INCREMENT NOT NULL ,
  title VARCHAR(100) COMMENT '课程标题',
  description VARCHAR(200) COMMENT '课程描述',
  category_id INT(11) COMMENT '分类id',
  price DECIMAL(8,2) COMMENT '原价',
  vip_price DECIMAL(8,2) COMMENT '会员价格',
  state TINYINT(1) COMMENT '是否上架 0为未上架 ,1为上架,-1删除',
  is_hot TINYINT(1) COMMENT '是否上推荐热门 0为否,1为是',
  is_seckill TINYINT(1) COMMENT '是否开启秒杀',
  browse INT(11) COMMENT '浏览量',
  is_group TINYINT(1) COMMENT '是否开启团购',
  group_price DECIMAL(8,2) COMMENT '团购价格',
  group_usernum INT(11) COMMENT '团购人数',
  group_start_time TIMESTAMP COMMENT '团购开始时间',
  group_end_time TIMESTAMP COMMENT '团购结束时间',
  mix_age_use INT(11) COMMENT '适用最小年龄',
  max_age_use INT(11) COMMENT '使用最大年龄',
  create_user_id INT(11) COMMENT '建立用户id',
  course_mini_img VARCHAR(300) COMMENT '课程首页缩略图',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)CHARACTER SET =utf8 COMMENT '课程表';

DROP TABLE IF EXISTS `t_course_category`;
CREATE TABLE `t_course_category`(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(20) NOT NULL COMMENT '课程名称',
  state VARCHAR(20) NOT NULL COMMENT '状态 -1删除 1未删除',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)CHARACTER SET =utf8 COMMENT '课程分类表';

-- DROP TABLE IF EXISTS `t_course_video`;
-- CREATE TABLE `t_course_video`(
--   id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
--   course_id INT(11) NOT NULL COMMENT '课程id',
--   tryvideo_url VARCHAR(300) COMMENT '试看视频',
--   full_video_url VARCHAR(300) COMMENT '完整视频',
--   update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
-- )CHARACTER SET =utf8 COMMENT '课程视频表';

DROP TABLE IF EXISTS `t_course_img`;
CREATE TABLE `t_course_img`(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  course_id int(11) COMMENT '课程id',
  file_size int(11) COMMENT '文件大小',
  old_name VARCHAR(300) COMMENT '上传前文件名',
  new_name VARCHAR(300) COMMENT '上传后文件唯一名',
  relative_path VARCHAR(300) COMMENT '图片相对项目路径',
  absolute_path VARCHAR(300) COMMENT '图片绝对路径',
  type TINYINT(1) COMMENT '1:头部图片,2:课程详情,3:课程大纲,4:课程须知,5:试看视频,6:完整视频',
  display_order INT(11) COMMENT '显示顺序',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)CHARACTER SET =utf8 COMMENT '课程图片表';

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  order_num VARCHAR(50) NOT NULL COMMENT '订单编号',
  course_id INT(11) NOT NULL COMMENT '课程id',
  wx_openid varchar(100) NOT NULL COMMENT '微信号',
  state TINYINT(1) NOT NULL COMMENT '订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：待评价；-1：已退款）',
  refund_status tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款状态 0 未退款 1 申请中 2 已退款',
  price DECIMAL(8,2) NOT NULL COMMENT '订单价格',
  coupon_id INT(11) COMMENT '优惠券id',
  coupon_price DECIMAL(8,2) COMMENT '优惠金额',
  real_price DECIMAL(8,2) COMMENT '实付金额',
  pay_time TIMESTAMP COMMENT '付款时间',
  refund_time TIMESTAMP COMMENT '退款时间',
  refund_price DECIMAL(8,2) COMMENT '退款金额',
  refund_reason VARCHAR(100) COMMENT '退款理由',
  groupbuy_id INT(11) COMMENT '团购订单id',
  create_time TIMESTAMP COMMENT '下单时间',
  end_time TIMESTAMP COMMENT '结束时间',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)CHARACTER SET =utf8 COMMENT '订单表';

DROP TABLE IF EXISTS `t_groupbuy_order`;
CREATE TABLE `t_groupbuy_order`(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  order_id INT(11) NOT NULL COMMENT '订单id',
  wx_openid varchar(100) NOT NULL COMMENT '微信号',
  course_id INT(11) NOT NULL COMMENT '课程id',
  is_initiator TINYINT(1) COMMENT '是否为发起人/团长 0是团长',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)CHARACTER SET =utf8 COMMENT '团购订单表';

DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon`(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  price DECIMAL(8,2) NOT NULL COMMENT '优惠金额',
  use_date INT(11) NOT NULL COMMENT '有效期 以天为单位',
  min_price DECIMAL(8,2) NOT NULL COMMENT '最低消费',
  type_id INT(11) COMMENT '优惠券类别 新人专享/新课立减',
  status TINYINT(1) COMMENT '是否开启 1开启 0关闭',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)CHARACTER SET =utf8 COMMENT '优惠券表';

DROP TABLE IF EXISTS `t_user_coupon`;
CREATE TABLE `t_user_coupon`(
  wx_openid varchar(100) NOT NULL COMMENT '微信号',
  coupon_id INT(11) COMMENT '优惠券id',
  get_time TIMESTAMP COMMENT '领用时间',
  use_time TIMESTAMP COMMENT '使用时间',
  use_status TINYINT(1) COMMENT '使用状态 0未使用 1使用',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)CHARACTER SET =utf8 COMMENT  '用户优惠券表';

DROP TABLE IF EXISTS `t_user_course`;
CREATE TABLE `t_user_course`(
  wx_openid varchar(100) NOT NULL COMMENT '微信号',
  course_id INT(11) NOT NULL COMMENT '课程id',
  view_time BIGINT COMMENT '已观看时长',
  lastview_time TIMESTAMP COMMENT '最后观看时间',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)CHARACTER SET =utf8 comment '用户课程表';

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`(
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL COMMENT '登录名',
  password VARCHAR(20) NOT NULL ,
  type TINYINT(1) COMMENT '用户类型 0管理员',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)CHARACTER SET =utf8 COMMENT '用户表';

INSERT INTO `t_user` (id,name, password, type) VALUES (1,'admin','admin',0);