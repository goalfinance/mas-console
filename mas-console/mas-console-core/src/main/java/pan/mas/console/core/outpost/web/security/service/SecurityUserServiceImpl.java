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

/**
 * @author panqingrong
 *
 */
@Component
@Service(version = "1.0.0")
public class SecurityUserServiceImpl implements SecurityUserService {

	@Autowired
	private SecurityUserRepository securityUserRepository;

	public Page<SecurityUser> findByUserId(String userId, int pageNumber, int pageSize){
		if (userId == null){
			userId = "";
		}
		return securityUserRepository.findByUserIdLike(userId, new PageRequest(pageNumber, pageSize));
	}

}
