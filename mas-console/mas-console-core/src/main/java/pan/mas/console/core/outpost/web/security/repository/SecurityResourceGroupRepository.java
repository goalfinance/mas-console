/**
 * 
 */
package pan.mas.console.core.outpost.web.security.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import pan.mas.console.core.outpost.web.security.domain.SecurityResourceGroup;

/**
 * @author panqingrong
 *
 */
public interface SecurityResourceGroupRepository extends PagingAndSortingRepository<SecurityResourceGroup, Long> {
	
	@Query("Select srp from SecurityResourceGroup srp order by srp.sortIdx asc")
	public List<SecurityResourceGroup> findAllOrderBySortIdx();
	
	public List<SecurityResourceGroup> findAllBySidInOrderBySortIdxAsc(Collection<Long> sids);

}
