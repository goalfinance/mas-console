/**
 * 
 */
package pan.mas.console.output.web.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.springframework.beans.factory.annotation.Autowired;

import pan.mas.console.core.outpost.web.security.domain.SecurityRolePermission;
import pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
public class DefaultRolePermissionResolver implements RolePermissionResolver {
	private Log log = LogFactory.getLog(this.getClass());

	private OutpostWebSecurityService securityService;
	
	private PermissionResolver permissionResolver = new WildcardPermissionResolver();
	

	public DefaultRolePermissionResolver(OutpostWebSecurityService securityService) {
		super();
		Validate.notNull(securityService, "Serivce[OutpostWebSecurityService] must not null");
		this.securityService = securityService;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.authz.permission.RolePermissionResolver#resolvePermissionsInRole(java.lang.String)
	 */
	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		Long roleSid = Long.valueOf(roleString);
		Set<Permission> rolePermission = new HashSet<Permission>();
		try {
			List<SecurityRolePermission> srps = securityService.findPermissionByRoleSid(roleSid);
			
			for(SecurityRolePermission srp : srps){
				Permission p = permissionResolver.resolvePermission(srp.getPermission());
				rolePermission.add(p);
			}
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}
		return rolePermission;
	}

	public OutpostWebSecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(OutpostWebSecurityService securityService) {
		this.securityService = securityService;
	}

}
