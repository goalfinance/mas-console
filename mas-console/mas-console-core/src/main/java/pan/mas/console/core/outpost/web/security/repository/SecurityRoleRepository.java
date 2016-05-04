/**
 * 
 */
package pan.mas.console.core.outpost.web.security.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pan.mas.console.core.outpost.web.security.domain.SecurityRole;

/**
 * @author panqingrong
 *
 */
public interface SecurityRoleRepository extends PagingAndSortingRepository<SecurityRole, Long> {

}
