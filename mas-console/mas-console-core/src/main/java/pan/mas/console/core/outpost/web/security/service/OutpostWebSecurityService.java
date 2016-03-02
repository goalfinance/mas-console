/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
public interface OutpostWebSecurityService {
	
	public SecurityUser findSecurityUserInfoByUserId(String userId) throws AppBizException;

}
