/**
 * 
 */
package pan.mas.console.core.outpost.web.security.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pan.mas.console.core.outpost.web.security.domain.SecurityResourceGroup;

/**
 * @author panqingrong
 *
 */
public interface SecurityResourceGroupRepository extends PagingAndSortingRepository<SecurityResourceGroup, Long> {

}
