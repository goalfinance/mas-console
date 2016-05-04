/**
 * 
 */
package pan.mas.console.output.web.shiro;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.domain.SecurityUserPermission;
import pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService;
import pan.utils.AppBizException;
import pan.utils.Encodes;
import pan.utils.security.shiro.CredentialsService;

/**
 * @author panqingrong
 *
 */
public class ApplicationRealm extends AuthorizingRealm {
	private Log log = LogFactory.getLog(this.getClass());

	private OutpostWebSecurityService outpostWebSecurityService;

	private CredentialsService credentialsService;
	
	private PermissionResolver permissionResolver = new WildcardPermissionResolver();

	public ApplicationRealm(OutpostWebSecurityService outpostWebSecurityService,
			CredentialsService credentialsService) {
		super(credentialsService.getCredentialsMatcher());
		this.outpostWebSecurityService = outpostWebSecurityService;
		this.credentialsService = credentialsService;
	}

	@Override
	protected void onInit() {
		super.onInit();
		Validate.notNull(this.outpostWebSecurityService, "Service[OutpostWebSecurityService] must not be null.");
		Validate.notNull(this.credentialsService, "Service[CredentialsService] must not be null.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
	 * .shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		ShiroUser sUser = (ShiroUser) principals.getPrimaryPrincipal();
		info.addObjectPermissions(resolveUserPermission(sUser));
		info.addRoles(resolveUserRoles(sUser));
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.
	 * apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();

		try {
			SecurityUser su = this.outpostWebSecurityService.findSecurityUserInfoByUserId(username);
			byte[] salt = Encodes.decodeHex(su.getSalt());
			return new SimpleAuthenticationInfo(createShiroUser(su), su.getPasswd(), ByteSource.Util.bytes(salt),
					getName());
		} catch (Exception e) {
			throw new AuthenticationException(e.getMessage(), e);
		}
	}

	protected ShiroUser createShiroUser(SecurityUser su) {

		ShiroUser sUser = new ShiroUser();

		sUser.setFullname(su.getFullName());
		sUser.setNickname(su.getNickName());
		sUser.setUserid(su.getUserId());
		sUser.setUsersid(su.getSId().toString());
		sUser.setRoles(su.getRolesSet());
	
		return sUser;
	}
	
	private Set<Permission> resolveUserPermission(ShiroUser sUser){
		Long userSid = Long.valueOf(sUser.getUsersid());

		Set<Permission> userPermission = new HashSet<Permission>();
		try {
			List<SecurityUserPermission> sups = outpostWebSecurityService.findPermisionByUserSid(userSid);

			for (SecurityUserPermission sup : sups) {
				Permission p = permissionResolver.resolvePermission(sup.getPermission());
				userPermission.add(p);
			}
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}
		return userPermission;
	}
	
	private Set<String> resolveUserRoles(ShiroUser sUser){
		return sUser.getRoles();
	}

	public OutpostWebSecurityService getOutpostWebSecurityService() {
		return outpostWebSecurityService;
	}

	public void setOutpostWebSecurityService(OutpostWebSecurityService outpostWebSecurityService) {
		this.outpostWebSecurityService = outpostWebSecurityService;
	}
	
	public static class ShiroUser implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 4005700659499005021L;
		private String usersid;
		private String userid;
		private String fullname;
		private String nickname;
		private Set<String> roles;
		public String getUsersid() {
			return usersid;
		}
		public void setUsersid(String usersid) {
			this.usersid = usersid;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public Set<String> getRoles() {
			return roles;
		}
		public void setRoles(Set<String> roles) {
			this.roles = roles;
		}
		
	}

}
