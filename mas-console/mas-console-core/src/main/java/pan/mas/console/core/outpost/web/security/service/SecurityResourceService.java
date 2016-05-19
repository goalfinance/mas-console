/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import java.util.List;

import pan.mas.console.core.outpost.web.security.domain.SecurityResource;
import pan.mas.console.core.outpost.web.security.domain.SecurityResourceGroup;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
public interface SecurityResourceService {
	
	public List<SecurityResourceGroup> findAllSecurityResourceGroup() throws AppBizException;
	
	public List<SecurityResource> findSecurityResourceByGroupSid(Long groupSid) throws AppBizException;
	
	public SecurityResource findSecurityResourceBySid(Long resourceSid);
	
	public List<SecurityResourceGroup> findSecurityResourceGroupByUserSid(Long userSid) throws AppBizException;

}
