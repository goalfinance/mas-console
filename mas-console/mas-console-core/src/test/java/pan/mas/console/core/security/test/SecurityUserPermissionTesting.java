/**
 * 
 */
package pan.mas.console.core.security.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import pan.mas.console.core.ApplicationConfig;
import pan.mas.console.core.outpost.web.security.domain.SecurityUserPermission;
import pan.mas.console.core.outpost.web.security.repository.SecurityUserPermissionRepository;


/**
 * @author panqingrong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ApplicationConfig.class)
@ActiveProfiles("ut")
public class SecurityUserPermissionTesting {
	@Autowired
	private SecurityUserPermissionRepository securityUserPermissionRepository;
	
	private void generateDataForTest(int howMany){
		assert howMany >= 0;
		
		for(int i = 0;i < howMany;i++){
			SecurityUserPermission sup = new SecurityUserPermission();
			sup.setUserSid(0L);
			sup.setPermission("test");
			
			securityUserPermissionRepository.save(sup);
		}
		
	}
	@Test
	public void testFindByUserSidPagination(){
		long rowCount = 7;
		this.generateDataForTest((int)rowCount);
		
		assertThat(securityUserPermissionRepository.count(), equalTo(rowCount));
		
		Page<SecurityUserPermission> sups = securityUserPermissionRepository.findByUserSid(0L, new PageRequest(1,2));
		assertThat(sups.getTotalPages(), equalTo(4));
		assertThat(sups.getSize(), equalTo(2));
		securityUserPermissionRepository.deleteAll();
		
	}
}
