/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.repository.SecurityUserRepository;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.security.shiro.CredentialsService;

/**
 * @author panqingrong
 *
 */
@Component
@Service(version = "1.0.0")
public class SecurityUserServiceImpl implements SecurityUserService {

	@Autowired
	private SecurityUserRepository securityUserRepository;

	@Autowired
	private CredentialsService credentialsService;

	private void encryptPassword(SecurityUser securityUser) throws AppBizException {
		securityUser.setSalt(credentialsService.generateSalt());
		securityUser
				.setPasswd(credentialsService.encryptPassword(securityUser.getPlainPasswd(), securityUser.getSalt()));
	}

	public Page<SecurityUser> findByUserId(String userId, int pageNumber, int pageSize) {
		if (userId == null) {
			userId = "";
		}
		return securityUserRepository.findByUserIdLike(userId, new PageRequest(pageNumber, pageSize));
	}

	public void register(SecurityUser securityUser) throws AppBizException {
		encryptPassword(securityUser);
		securityUserRepository.save(securityUser);
	}

	public SecurityUser findOne(Long sid) throws AppBizException {
		SecurityUser securityUser = securityUserRepository.findOne(sid);
		if (securityUser == null) {
			Object[] args = new Object[1];
			args[0] = sid;
			throw new AppBizException(AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[0],
					AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[1], args);
		} else {
			return securityUser;
		}
	}

}
