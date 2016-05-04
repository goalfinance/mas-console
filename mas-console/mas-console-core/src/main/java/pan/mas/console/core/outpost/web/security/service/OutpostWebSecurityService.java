/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import java.util.List;

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
	
	public List<SecurityUserPermission> findPermisionByUserSid(Long userSid) throws AppBizException;
	
	public List<SecurityRolePermission> findPermissionByRoleSid(Long roleSid) throws AppBizException;
}
