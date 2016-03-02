/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.repository.SecurityUserRepository;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;

/**
 * @author panqingrong
 *
 */
@Component
@Service(version="1.0.0")
public class OutpostWebSecurityServiceImpl implements OutpostWebSecurityService {
	
	@Autowired
	private SecurityUserRepository securityUserRepository;

	/* (non-Javadoc)
	 * @see pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService#findSecurityUserInfoByUserId(java.lang.String)
	 */
	public SecurityUser findSecurityUserInfoByUserId(String userId) throws AppBizException {
		SecurityUser su = securityUserRepository.findByUserId(userId);
		if (su == null){
			throw new AppBizException(AppExceptionCodes.SEC_USER_DOES_NOT_EXIST);
		}else{
			return su;			
		}
	}

}
