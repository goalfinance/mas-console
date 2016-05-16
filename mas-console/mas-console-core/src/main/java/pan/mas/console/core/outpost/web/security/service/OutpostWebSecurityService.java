/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import java.util.List;

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
	
	public List<SecurityUserPermission> findPermisionByUserSid(Long userSid) throws AppBizException;
	
	public List<SecurityRolePermission> findPermissionByRoleSid(Long roleSid) throws AppBizException;
}
