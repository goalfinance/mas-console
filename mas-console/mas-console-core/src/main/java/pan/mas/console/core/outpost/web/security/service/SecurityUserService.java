package pan.mas.console.core.outpost.web.security.service;

import org.springframework.data.domain.Page;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.utils.AppBizException;

public interface SecurityUserService {

	public Page<SecurityUser> findByUserId(String userId, int pageNumber, int pageSize);
	
	public void save(SecurityUser securityUser);
	
	public SecurityUser findOne(Long sid) throws AppBizException;

}
