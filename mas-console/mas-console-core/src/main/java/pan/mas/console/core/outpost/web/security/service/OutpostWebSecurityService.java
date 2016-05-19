/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import pan.mas.console.core.outpost.web.security.domain.SecurityRole;
import pan.mas.console.core.outpost.web.security.domain.SecurityRolePermission;
import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.domain.SecurityUserPermission;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
public interface OutpostWebSecurityService {
	
	public SecurityUser findSecurityUserInfoByUserId(String userId) throws AppBizException;
	
	public Page<SecurityUser> findSecurityUserByUserId(String userId, int pageNumber, int pageSize);
	
	public void registerSecurityUser(SecurityUser securityUser) throws AppBizException;
	
	public SecurityUser findSecurityUserBySid(Long sid) throws AppBizException;
	
	public void saveSecurityUser(SecurityUser su) throws AppBizException;
	
	public void deleteSecurityUser(SecurityUser su);
	
	public boolean changePassword(Long sid, String oldOne, String newOne) throws AppBizException;
	
	public List<SecurityRole> findAllSecurityRoles();
	
	public List<SecurityRole> findAllActiveSecurityRoles();
	
	public void saveSecurityRole(SecurityRole securityRole) throws AppBizException;
	
	public void deleteSecurityRole(SecurityRole securityRole);
	
	public SecurityRole findSecurityRoleBySid(Long sid) throws AppBizException;
	
	public List<SecurityUserPermission> findPermisionByUserSid(Long userSid) throws AppBizException;
	
	public List<SecurityRolePermission> findPermissionByRoleSid(Long roleSid) throws AppBizException;
	
	public void saveSecurityRolePermissions(Long roleSid, Set<Long> resourcesPermitted, String permittedAction) throws AppBizException;
	
	public void deleteSecurityRolePermission(SecurityRolePermission securityRolePermission);
	
	public SecurityRolePermission findSecurityRolePermissionBySid(Long sid) throws AppBizException;
}
