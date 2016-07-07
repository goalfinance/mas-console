set SQL_SAFE_UPDATES = 0;
delete from mas.t_security_resource;
delete from mas.t_security_resource_group;
delete from mas.t_security_user;
delete from mas.t_security_user_permission;
delete from mas.t_security_role;
delete from mas.t_security_role_permission;
set SQL_SAFE_UPDATE = 1;

INSERT INTO `mas`.`t_security_resource_group` (`sid`, `name`, `desc`, `status`, `sort_idx`) VALUES ('1', 'System Admin', 'System Admin', 'A', '999');
INSERT INTO `mas`.`t_security_resource_group` (`sid`, `name`, `desc`, `status`, `sort_idx`) VALUES ('2', 'Authorized Network', 'Authorized Network', 'A', '1');
INSERT INTO `mas`.`t_security_resource` (`sid`, `name`, `location`, `desc`, `status`, `group_sid`, `sort_index`) VALUES ('1', 'Maitaining Security User', '/dojo/security/maintaining/user/maintain_security_user', 'Maitaining Security User', 'A', '1', '1');
INSERT INTO `mas`.`t_security_resource` (`sid`, `name`, `location`, `desc`, `status`, `group_sid`, `sort_index`) VALUES ('2', 'Maintaining Security Role', '/dojo/security/maintaining/role/maintain_security_role', 'Maintaining Security Role', 'A', '1', '2');
INSERT INTO `mas`.`t_security_resource` (`sid`, `name`, `location`, `desc`, `status`, `group_sid`, `sort_index`) VALUES ('3', 'Maintaining Authorized Network', '/dojo/authnet/list_auth_nets', 'Maintaining Authorized Network', 'A', '2', '1');
