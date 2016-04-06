/**
 * 
 */
package pan.mas.console.core.authorizednetwork.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
public interface AuthorizedNetworkService {
	
	public void save(AuthorizedNetwork authorizedNetwork);

	public void delete(AuthorizedNetwork authorizedNetwork);
	
	public AuthorizedNetwork findOne(Long sId) throws AppBizException;
	
	public AuthorizedNetwork findByAuthNetId(String authNetId);
	
	public Page<AuthorizedNetwork> findAll(Pageable pageable);
	
	public Iterable<AuthorizedNetwork> findAll(Sort sort);
	
	public Long count();
	
	public boolean exists(Long sId);
	
	public Page<AuthorizedNetwork> findByAuthNetIdAndNameEndsWithAndAuthNetType(String authNetId, String name, String authNetType, Pageable pageable);
}
