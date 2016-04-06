package pan.mas.console.core.outpost.web.security.service;

import org.springframework.data.domain.Page;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;

public interface SecurityUserService {

	public Page<SecurityUser> findByUserId(String userId, int pageNumber, int pageSize);

}
