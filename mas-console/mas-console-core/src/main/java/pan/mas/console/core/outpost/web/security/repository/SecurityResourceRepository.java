package pan.mas.console.core.outpost.web.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import pan.mas.console.core.outpost.web.security.domain.SecurityResource;

public interface SecurityResourceRepository extends PagingAndSortingRepository<SecurityResource, Long> {
	
	@Query("select sr from SecurityResource sr where sr.groupSid = :groupSid")
	public List<SecurityResource> findByGroupSid(@Param("groupSid")Long groupId);

}
