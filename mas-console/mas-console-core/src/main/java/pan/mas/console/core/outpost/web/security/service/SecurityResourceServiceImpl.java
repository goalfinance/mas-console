/**
 * 
 */
package pan.mas.console.core.outpost.web.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import pan.mas.console.core.outpost.web.security.domain.SecurityResource;
import pan.mas.console.core.outpost.web.security.domain.SecurityResourceGroup;
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
	/* (non-Javadoc)
	 * @see pan.mas.console.core.outpost.web.security.service.SecurityResourceService#findAllSecurityResourceGroup()
	 */
	public List<SecurityResourceGroup> findAllSecurityResourceGroup() throws AppBizException {
		return (List<SecurityResourceGroup>)securityResourceGroupRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see pan.mas.console.core.outpost.web.security.service.SecurityResourceService#findSecurityResourceByGroupSid(java.lang.Long)
	 */
	public List<SecurityResource> findSecurityResourceByGroupSid(Long groupSid) throws AppBizException {
		return securityResourceRepository.findByGroupSid(groupSid);
	}

}
