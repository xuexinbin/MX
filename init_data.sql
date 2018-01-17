# 修改时区为北京时间
# set global time_zone = '+8:00';
# set time_zone = '+8:00';
# flush PRIVILEGES; 

用户表
INSERT INTO sys_user(user_id, user_name, password, role_id, theme_url) VALUES
('admin', '管理员', '1', 1, 'css/common/theme-black.css');

# 文章列表
INSERT INTO demo_article(title, content, keyword, author, recommend, top) VALUES
('文章一', '内容一', '一', 'admin', '0', '0'),
('文章2', '内容2', '一', 'admin', '0', '0'),
('文章3', '内容3', '一', 'admin', '0', '0'),
('文章4', '内容4', '一', 'admin', '0', '0'),
('文章5', '内容5', '一', 'admin', '0', '0'),
('文章6', '内容6', '一', 'admin', '0', '0'),
('文章7', '内容7', '一', 'admin', '0', '0'),
('文章8', '内容8', '一', 'admin', '0', '0'),
('文章9', '内容9', '一', 'admin', '0', '0'),
('文章10', '内10', '一', 'admin', '0', '0'),
('文章11', '内容11', '一', 'admin', '0', '0'),
('文章12', '内容12', '一', 'admin', '0', '0'),
('文章13', '内容13', '一', 'admin', '0', '0'),
('文章14', '内容14', '一', 'admin', '0', '0');


#管理员菜单
INSERT INTO sys_menu(menu_id, role_id, name, level, sort, parent_menu_id, url, js, icon) VALUES
(1, '1', '首页', 1, 10, 0, 'admin/admin_home', 'js/admin/admin_home.js', 'glyphicon glyphicon-home'),
(2, '1', '资讯管理', 1, 10, 0, 'admin/admin_setting', 'js/admin/admin_news.js', 'glyphicon glyphicon-list-alt'),
(3, '1', '最新资讯', 2, 10, 2, 'admin/admin_news', 'js/admin/admin_news.js', 'glyphicon glyphicon-globe'),
(4, '1', '新增文章', 2, 20, 2, 'admin/admin_newsAdd', 'js/admin/admin_newsAdd.js', 'glyphicon glyphicon-leaf'),
(5, '1', '权限管理', 1, 20, 0, '', 'js/admin/admin_news.js', 'glyphicon glyphicon-wrench'),
(6, '1', '用户', 2, 10, 5, '', 'js/admin/admin_news.js', 'glyphicon glyphicon-user'),
(7, '1', '角色管理', 2, 20, 5, '', 'js/admin/admin_news.js', 'glyphicon glyphicon-pencil'),
(8, '1', '系统管理', 1, 30, 0, '', 'js/admin/admin_news.js', 'glyphicon glyphicon-cog'),
(9, '1', '系统设置', 2, 10, 8, '', 'js/admin/admin_news.js', 'glyphicon glyphicon-edit'),
(10, '1', '其他', 2, 20, 8, '', 'js/admin/admin_news.js', 'glyphicon glyphicon-info-sign'),
(11, '1', '其他2', 2, 30, 8, '', 'js/admin/admin_news.js', 'glyphicon glyphicon-info-sign');



#角色
INSERT INTO sys_role(role_id, name, del, remarks) VALUES
(1, 'superAdmin', 0, '超级管理员'),
(2, 'admin', 0, '管理员');

#角色权限
INSERT INTO sys_role_permissions(role_id, type, type_id, del, remarks) VALUES
(1, 'menu', 1, 0, ''),
(1, 'menu', 2, 0, ''),
(1, 'menu', 3, 0, ''),
(1, 'menu', 4, 0, ''),
(1, 'menu', 5, 0, ''),
(1, 'menu', 6, 0, ''),
(1, 'menu', 7, 0, ''),
(1, 'menu', 8, 0, ''),
(1, 'menu', 9, 0, ''),
(1, 'menu', 10, 0, ''),
(1, 'menu', 11, 0, ''),
(2, 'menu', 0, 0, '');