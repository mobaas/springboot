use stfx;

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

insert into taskinfo (id, name, jobClazz, jobData, cronExp, modified, del) values
	(1,'Test Job','com.mobaas.stfx.schedule.NopJob',NULL,'0 0/1 * * * ?','2018-01-19 00:00:00',0);
