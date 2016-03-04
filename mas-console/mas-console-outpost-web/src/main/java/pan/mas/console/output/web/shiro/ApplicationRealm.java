/**
 * 
 */
package pan.mas.console.output.web.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.domain.SecurityUserPermission;
import pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService;
import pan.utils.AppBizException;


/**
 * @author panqingrong
 *
 */
public class ApplicationRealm extends AuthorizingRealm {
	private Log log = LogFactory.getLog(this.getClass());
	
	private OutpostWebSecurityService outpostWebSecurityService;
	
	public ApplicationRealm(OutpostWebSecurityService outpostWebSecurityService) {
		super();
		this.outpostWebSecurityService = outpostWebSecurityService;
	}
	

	@Override
	protected void onInit() {
		super.onInit();
		if (this.outpostWebSecurityService == null){
			throw new IllegalStateException("The backend service is invalid!");
		}
	}


	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		Map<String, String> account = principals.oneByType(java.util.Map.class);
		
		Long userSid = Long.valueOf(account.get("usersid"));
		
		try {
			List<SecurityUserPermission> sups = outpostWebSecurityService.findPermisionByUserSid(userSid);
			WildcardPermissionResolver permissionResolver =new WildcardPermissionResolver();
			
			for (SecurityUserPermission sup:sups){
				Permission p = permissionResolver.resolvePermission(sup.getPermission());
				info.addObjectPermission(p);
			}
		} catch (AppBizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		String username = token.getUsername();

		try{
			SecurityUser su = this.outpostWebSecurityService.findSecurityUserInfoByUserId(username);
			
			return new SimpleAuthenticationInfo(createPrincipals(su), su.getPasswd());
		}catch(AppBizException e){
			//
			throw new AuthenticationException(e.getCode());
		}
	}
	
	protected PrincipalCollection createPrincipals(SecurityUser su) {

        LinkedHashMap<String, String> props = new LinkedHashMap<String, String>();
 
        Collection<Object> principals = new ArrayList<Object>(2);
        
        props.put("fullname", su.getFullName());
        props.put("nickname", su.getNickName());
        props.put("userid", su.getUserId());
        props.put("usersid", su.getSId().toString());
        principals.add(props);

        return new SimplePrincipalCollection(principals, getName());
    }

	public OutpostWebSecurityService getOutpostWebSecurityService() {
		return outpostWebSecurityService;
	}

	public void setOutpostWebSecurityService(OutpostWebSecurityService outpostWebSecurityService) {
		this.outpostWebSecurityService = outpostWebSecurityService;
	}
	
	

}
