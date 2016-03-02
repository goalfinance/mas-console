/**
 * 
 */
package pan.mas.console.core.authorizednetwork.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

import pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork;

/**
 * @author panqingrong
 *
 */
public interface AuthorizedNetworkRepository extends PagingAndSortingRepository<AuthorizedNetwork, Long> {

	public Page<AuthorizedNetwork> findAll(Specification<AuthorizedNetwork> spec, Pageable pageable);
	
	public AuthorizedNetwork findByAuthNetId(String authNetId);

	public AuthorizedNetwork findByName(String name);

	public Page<AuthorizedNetwork> findByAuthNetType(String authNetType, Pageable pageable);

}
