package pan.mas.console.core.outpost.web.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;


public interface SecurityUserRepository extends PagingAndSortingRepository<SecurityUser, Long> {
	
	@Query("select su from SecurityUser su where su.userId = :userId")
	public SecurityUser findByUserId(@Param("userId")String userId);
	
	@Query("select su from SecurityUser su where su.userId like %:userId%")
	public Page<SecurityUser> findByUserIdLike(@Param("userId")String userId, Pageable pageable);

}
