/**
 * 
 */
package pan.mas.console.output.web.service.security.reception;

import java.util.List;
import java.util.Set;

import pan.mas.console.output.web.service.security.reception.models.ResourcesGroup;
import pan.mas.console.output.web.service.security.reception.models.SecuredResource;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
public interface SecurityReceptionService {
	
	List<ResourcesGroup> getResourceGroup(Long userSid) throws AppBizException;
	
	Set<SecuredResource> getSecuredResources(Long groupSid) throws AppBizException;

}
