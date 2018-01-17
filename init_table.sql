# 用户表
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
	`user_id` VARCHAR(20) NOT NULL COMMENT '用户唯一Id',
	`user_name` VARCHAR(20) NOT NULL,
	`password` VARCHAR(20) NOT NULL,
	`email` VARCHAR(30) UNIQUE COMMENT '用户唯一邮箱',
	`cellphone` VARCHAR(15) UNIQUE COMMENT '用户唯一手机号',
	`sex` TINYINT(1) COMMENT '女:0 男:1 保密:2',
	`role_id` INT NOT NULL DEFAULT 0 COMMENT '角色', 
	`remarks` VARCHAR(50) COMMENT '备注',
	`theme_url` VARCHAR(40) COMMENT '用户自定义主题',
	`del` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '有效:0 失效:1',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`update_user` VARCHAR(20),
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`create_user` VARCHAR(20),
	primary key (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#文章列表
DROP TABLE IF EXISTS demo_article;
CREATE TABLE demo_article (
	`article_id` INT AUTO_INCREMENT COMMENT '文章唯一id',
	`title` VARCHAR(30) NOT NULL COMMENT '文章标题',
	`content` TEXT COMMENT '内容',
	`keyword` VARCHAR(20) COMMENT '文章关键字',
	`author` VARCHAR(20) COMMENT '作者',
	`recommend` TINYINT(1) DEFAULT 0 COMMENT '不推荐:0 推荐:1',
	`top` TINYINT(1) DEFAULT 0 COMMENT '不置顶:0 置顶:1',
	`del` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '有效:0 失效:1',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`update_user` VARCHAR(20),
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`create_user` VARCHAR(20),
	primary key (`article_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#管理员菜单
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu (
	`menu_id` INT AUTO_INCREMENT COMMENT '菜单唯一Id',
	`name` VARCHAR(10) NOT NULL COMMENT '菜单名称',
	`level` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '第一级菜单:1 第二级菜单:2 ...',
	`sort` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '顺序',
	`parent_menu_id` INT NOT NULL DEFAULT 0 COMMENT '父菜单 0:一级菜单',
	`url` VARCHAR(30) COMMENT '菜单对应url',
	`js` VARCHAR(30) COMMENT '菜单页面对应的js文件',
	`icon` VARCHAR(30) COMMENT '菜单对应图标',
	`del` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '有效:0 失效:1',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`update_user` VARCHAR(20),
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`create_user` VARCHAR(20),
	primary key (`menu_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#数据字典
DROP TABLE IF EXISTS sys_dictionary;
CREATE TABLE sys_dictionary (
	`id` INT AUTO_INCREMENT COMMENT '唯一id',
	`name` VARCHAR(10) NOT NULL COMMENT '名称',
	`type` INT COMMENT '类型',
	`value` VARCHAR(30) COMMENT '类型对应值',
	`del` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '有效:0 失效:1',
	`remarks` VARCHAR(50) COMMENT '备注',
	primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#角色
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
	`role_id` INT AUTO_INCREMENT COMMENT '角色id',
	`name` VARCHAR(10) NOT NULL COMMENT '名称',
	`del` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '有效:0 失效:1',
	`remarks` VARCHAR(50) COMMENT '备注',
	primary key (`role_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#角色对应的权限
DROP TABLE IF EXISTS sys_role_permissions;
CREATE TABLE sys_role_permissions (
	`id` INT AUTO_INCREMENT COMMENT 'id',
	`role_id` INT NOT NULL COMMENT '角色Id',
	`type` VARCHAR(10) NOT NULL COMMENT '权限类型',
	`type_id` INT COMMENT '类型id(一个类型多个值)',
	`del` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '有效:0 失效:1',
	`remarks` VARCHAR(50) COMMENT '备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


