/**
 * 
 */
package pan.mas.console.core.outpost.web.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import pan.mas.console.core.outpost.web.security.domain.SecurityRolePermission;

/**
 * @author panqingrong
 *
 */
public interface SecurityRolePermissionRepository extends PagingAndSortingRepository<SecurityRolePermission, Long> {
	
	@Query("select srp from SecurityRolePermission srp where srp.roleSid = :roleSid")
	public List<SecurityRolePermission> findByRoleSid(@Param("roleSid") Long roleSid);
}
