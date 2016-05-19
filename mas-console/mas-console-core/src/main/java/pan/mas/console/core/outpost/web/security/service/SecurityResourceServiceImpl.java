/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import pan.mas.console.core.outpost.web.security.domain.SecurityResource;
import pan.mas.console.core.outpost.web.security.domain.SecurityResourceGroup;
import pan.mas.console.core.outpost.web.security.domain.SecurityRolePermission;
import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.repository.SecurityResourceGroupRepository;
import pan.mas.console.core.outpost.web.security.repository.SecurityResourceRepository;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
@Component
@Service(version="1.0.0")
public class SecurityResourceServiceImpl implements SecurityResourceService {

	@Autowired
	private SecurityResourceGroupRepository securityResourceGroupRepository;
	
	@Autowired
	private SecurityResourceRepository securityResourceRepository;
	
	@Autowired
	private OutpostWebSecurityService outpostWebSecurityService;
	
	/* (non-Javadoc)
	 * @see pan.mas.console.core.outpost.web.security.service.SecurityResourceService#findAllSecurityResourceGroup()
	 */
	public List<SecurityResourceGroup> findAllSecurityResourceGroup() throws AppBizException {
		return (List<SecurityResourceGroup>)securityResourceGroupRepository.findAllOrderBySortIdx();
	}

	/* (non-Javadoc)
	 * @see pan.mas.console.core.outpost.web.security.service.SecurityResourceService#findSecurityResourceByGroupSid(java.lang.Long)
	 */
	public List<SecurityResource> findSecurityResourceByGroupSid(Long groupSid) throws AppBizException {
		return securityResourceRepository.findByGroupSidOrderBySortIdx(groupSid);
	}

	public SecurityResource findSecurityResourceBySid(Long resourceSid) {
		return securityResourceRepository.findOne(resourceSid);
	}

	public List<SecurityResourceGroup> findSecurityResourceGroupByUserSid(Long userSid) throws AppBizException{
		//First, I get the security user's all roles assigned.
		SecurityUser securityUser = outpostWebSecurityService.findSecurityUserBySid(userSid);
		Set<String> roleSet = securityUser.getRolesSet();
		
		//Second, I get the info of all permissions belong to roles.
		List<SecurityRolePermission> resourcesPermitted = new ArrayList<SecurityRolePermission>();
		for (String roleSid:roleSet){
			Long sid = Long.valueOf(roleSid);
			List<SecurityRolePermission> srps =  outpostWebSecurityService.findPermissionByRoleSid(sid);
			
			resourcesPermitted.addAll(srps);
		}
		
		//The third, I get all resources group's sids that are not repeatable from the permissions assigned to the security user.
		List<Long> resourceGroupPermitted = new ArrayList<Long>();
		for (SecurityRolePermission srp:resourcesPermitted){
			if (!resourceGroupPermitted.contains(srp.getResourceGroupSid().longValue())){
				resourceGroupPermitted.add(srp.getResourceGroupSid().longValue());
			}
		}
		
		//Finally, I get all resources groups' info that are sorted.
		return securityResourceGroupRepository.findAllBySidInOrderBySortIdxAsc(resourceGroupPermitted);
	}

}
