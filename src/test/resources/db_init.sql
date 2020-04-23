CREATE TABLE `sys_error` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  `message` varchar(2000) DEFAULT NULL,
  `content` text,
  `cause` longtext
);

CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `category` varchar(45) DEFAULT NULL,
  `opName` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `result` varchar(500) DEFAULT NULL,
  `logTime` datetime DEFAULT NULL,
  `logIp` varchar(45) DEFAULT NULL
);

CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `permission` varchar(45) DEFAULT NULL
);

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `role` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` bit(1) DEFAULT NULL
);

CREATE TABLE `sys_role_permission` (
  `roleId` int(11) PRIMARY KEY NOT NULL,
  `permissionId` int(11) PRIMARY KEY NOT NULL
);

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL
);

CREATE TABLE `sys_user_role` (
  `userId` int(11) PRIMARY KEY NOT NULL,
  `roleId` int(11) PRIMARY KEY NOT NULL
);

insert into sys_permission (id, name, url, permission) values 
	(1, '用户查看', 'sys/userlist', 'sysuser:view'),
	(2, '用户添加', 'sys/useradd', 'sysuser:add'),
	(3, '用户编辑', 'sys/useredit', 'sysuser:edit'),
	(4, '用户删除', 'sys/userdel', 'sysuser:del'),
	(5, '角色查看', 'sys/rolelist', 'sysrole:view'),
	(6, '日志查看', 'sys/loglist', 'syslog:view'),
	(7, '错误查看', 'sys/errorlist', 'syserror:view');

insert into sys_role (id, role, discription, available) values
	(1,'admin','管理员',1);

insert into sys_user (id, username, password, salt, type, state, roleId) values
	(1, 'admin', 'ebd721861dfa75f4c29bdf9d1bc022c8', '', 1, 1, 0);


insert into sys_role_permission (roleId, permissionId) values 
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6),
	(1, 7);

insert into sys_user_role (userId, roleId) values
	(1, 1);
